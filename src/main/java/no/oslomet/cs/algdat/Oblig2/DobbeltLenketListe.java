package no.oslomet.cs.algdat.Oblig2;


////////////////// class DobbeltLenketListe //////////////////////////////


import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;


public class DobbeltLenketListe<T> implements Liste<T> {

    /**
     * Node class
     *
     * @param <T>
     */
    private static final class Node<T> {
        private T verdi;                   // nodens verdi
        private Node<T> forrige, neste;    // pekere

        private Node(T verdi, Node<T> forrige, Node<T> neste) {
            this.verdi = verdi;
            this.forrige = forrige;
            this.neste = neste;
        }

        private Node(T verdi) {
            this(verdi, null, null);
        }
    }

    // instansvariabler
    private Node<T> hode;          // peker til den første i listen
    private Node<T> hale;          // peker til den siste i listen
    private int antall;            // antall noder i listen
    private int endringer;         // antall endringer i listen

    public DobbeltLenketListe() {
        hode = null;
        antall = 0;
        endringer = 0;
    }

    public DobbeltLenketListe(T[] a) {
        antall = 0;
        endringer = 0;
        Objects.requireNonNull(a, "Tabellen a er null"); //Tabellen eksisterer ikke
        if (a.length == 0) {
            return;
        }
        Node prev = null;
        for (int i = 0; i < a.length; i++) {
            if (a[i] != null) {
                Node current = new Node(a[i]);
                if (hode == null) {
                    hode = current;
                    hode.forrige = null;
                }
                hale = current;
                current.forrige = prev;
                if (prev != null) {
                    prev.neste = current;
                }
                prev = current;
                antall++;                // Antall noder i listen
                endringer++; // Teller antall endringer som skjer i lenken
                hale.neste = null;
            }
        }
    }

    private static void fratilKontroll(int antall, int fra, int til) {
        if (fra < 0) {
            throw new IndexOutOfBoundsException("fra verdien: " + fra + " er negativ!");
        }
        if (til > antall) {
            throw new IndexOutOfBoundsException("til verdien: " + til + " er utenfor listen!");
        }
        if (fra > til) {
            throw new IllegalArgumentException("fra verdien: " + fra + " er større enn til verdien: " + til + " = Ugyldig! Try again!");
        }
    }

    public Liste<T> subliste(int fra, int til) {
        fratilKontroll(antall, fra, til); //sjekker at intervallet er gyldig

        Node<T> current = finnNode(fra); //initialisere hva som er current verdien
        Liste<T> instansDDL = new DobbeltLenketListe<>(); //returnere en liste, instans av klassen DobbeltLenketListe

        for (int i = fra; i < til; i++) { //Starter fra første noden i listen
            instansDDL.leggInn(current.verdi);
            current = current.neste;
        }
        return instansDDL;
    }


    @Override
    public int antall() {
        return antall;
    }

    @Override
    public boolean tom() {
        if (antall == 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean leggInn(T verdi) {
        Objects.requireNonNull(verdi, "Verdien er null");
        Node ny = new Node(verdi);
        if (hode == null) {
            hode = ny;
            hale = ny;
            hode.forrige = null;
            hale.neste = null;
            antall++;
            endringer++;
            return true;
        } else {
            Node prev = hale;
            ny.forrige = prev;
            prev.neste = ny;
            ny.neste = null;
            hale = ny;
            antall++;
            endringer++;
            return true;
        }
    }

    @Override
    public void leggInn(int indeks, T verdi) {

        if (verdi == null) {
            throw new NullPointerException("Kan ikke putte inn Null verdi"); // Kastes unntak hvis indeksen er null
        }
        indeksKontroll(indeks, false);

        Node<T> ny = new Node(verdi);                       // Lager ny node for tallet som skal inn

        if (indeks <= antall) {                             // Legger innn verdi på hale plassen
            ny.forrige = hale.forrige;
            hale = ny;
        } else if (hode == null) {                          // Hvis tabellen ikke har noen verdier i fra før av blir den nye hode og hale
            hode = ny;
            hode.neste = null;
            hale = ny;
            hale.forrige = null;
        } else {                                            // Legger verdien inn på indeks sin plass
            Node<T> current = finnNode(indeks);
            ny.neste = current.neste;
            current.forrige = ny.forrige;
            //ny.forrige = current;
        }
        antall++;
        endringer++;

    }

    @Override
    public boolean inneholder(T verdi) {
        if (indeksTil(verdi) == -1) {
            return false;
        }
        return true;
    }

    // Oppgave 3 a)
    private Node<T> finnNode(int indeks) {
        if (indeks == 0) {
            return hode;
        }
        if (indeks < (antall / 2)) { //indeksen er midre enn midten, starter fra hodet mot høyre
            Node<T> current = hode;
            for (int i = 0; i < indeks; i++) {
                current = current.neste;
            }
            return current;

        } else { // Letingen skal gå fra halen så til forrige til venstre
            Node<T> current = hale;
            for (int i = antall - 1; i > indeks; i--) {
                current = current.forrige;
            }
            return current;
        }

    }

    @Override
    public T hent(int indeks) {
        indeksKontroll(indeks, false);
        return finnNode(indeks).verdi;
    }

    @Override
    public int indeksTil(T verdi) {
        Node<T> current = hode;
        for (int i = 0; i < antall; i++) {
            if (current.verdi.equals(verdi)) {
                return i;
            }
            current = current.neste;
        }
        return -1;
    }


    @Override
    public T oppdater(int indeks, T nyverdi) {

        indeksKontroll(indeks, false);
        Node<T> gammel = finnNode(indeks);
        Objects.requireNonNull(nyverdi);
        T gammelverdi = gammel.verdi;
        gammel.verdi = nyverdi;
        endringer++;
        return gammelverdi;
    }

    @Override
    public boolean fjern(T verdi) {
        Node<T> current = hode;
        for (int i = 0; i < antall; i++) {
            if (current.verdi.equals(verdi)) {
                antall--;
            } else {
                return false;
            }
        }
        return true;
    }

    @Override
    public T fjern(int indeks) {
        indeksKontroll(indeks, false);

        Node<T> temp = hode;
        T fjernes;

        if (indeks == 0) {                        //Indeks er på første plass
            fjernes = temp.verdi;
            if (temp.neste != null) {          //Sjekker om neste verdi ikke er null
                hode = temp.neste;             //Setter hode til verdien etter den som skal fjernes
                hode.forrige = null;              //Setter hode sin forrige til null
            } else {                              //Arrayet har kun en verdi, den som blir fjernet
                hode = null;
                hale = null;
            }

        } else if (indeks == antall - 1) {                //Indeks er på siste plass
            temp = hale;
            fjernes = hale.verdi;

        } else {                                         //Indeks er et sted imellom

        }


        temp.forrige.neste = temp.neste;
        temp.neste.forrige = temp.forrige;

        antall--;
        endringer++;

        return temp.verdi;
    }

    @Override
    public void nullstill() {
        Node current = hode;
        Node next = hode.neste;
        for(int i=0;i<antall-1;i++){
            current.verdi = null;
            current.neste = null;
            current.forrige = null;
            current = next;
            next = next.neste;
        }
        hode=null;
        hale=null;
        antall=0;
        endringer++;
    }

    @Override
    public String toString() {
        if (antall == 0) {
            return "[]";
        }

        StringBuilder s = new StringBuilder();
        Node current = hode;
        s.append('[').append(hode.verdi);

        for (int i = 1; i < antall; i++) {
            s.append(',').append(' ').append(current.neste.verdi);
            current = current.neste;
        }
        s.append(']');

        return s.toString();
    }

    public String omvendtString() {
        if (antall == 0) {
            return "[]";
        }
        StringBuilder s = new StringBuilder();
        Node current = hale;
        s.append('[').append(hale.verdi);

        for (int i = antall; i > 1; i--) {
            s.append(',').append(' ').append(current.forrige.verdi);
            current = current.forrige;
        }
        s.append(']');

        return s.toString();
    }


    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException();
    }

    public Iterator<T> iterator(int indeks) {
        throw new UnsupportedOperationException();
    }

    private class DobbeltLenketListeIterator implements Iterator<T> {
        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;

        private DobbeltLenketListeIterator() {
            denne = hode;     // p starter på den første i listen
            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer
        }

        private DobbeltLenketListeIterator(int indeks) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext() {
            return denne != null;
        }

        @Override
        public T next() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();

        }

    } // class DobbeltLenketListeIterator

    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        throw new UnsupportedOperationException();
    }

} // class DobbeltLenketListe




