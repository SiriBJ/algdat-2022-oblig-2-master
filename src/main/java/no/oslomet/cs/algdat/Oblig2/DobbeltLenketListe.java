package no.oslomet.cs.algdat.Oblig2;


////////////////// class DobbeltLenketListe //////////////////////////////


import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;
import java.util.*;


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
        if (fra < 0) {                                                                              //Sjekker om fra verdi er gyldig
            throw new IndexOutOfBoundsException("fra verdien: " + fra + " er negativ!");
        }
        if (til > antall) {                                                                         //Sjekker om til verdi er gyldig
            throw new IndexOutOfBoundsException("til verdien: " + til + " er utenfor listen!");
        }
        if (fra > til) {                                                                            //Sjekker om fra ikke er større enn til
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
            throw new NullPointerException("Kan ikke putte inn en Null verdi"); // Kastes unntak hvis indeksen er null
        }
        if (0 > indeks || indeks > antall) {
            throw new IndexOutOfBoundsException("Ikke en gyldig indeks");
        }
        Node<T> ny = new Node(verdi);           // Lager ny node for tallet som skal inn

        if (hode == null) {                     // Hvis tabellen ikke har noen verdier i fra før av blir den nye hode og hale og den pekere blir null
            hode = ny;
            hode.neste = null;
            hale = ny;
            hale.forrige = null;
        }

        if (indeks == antall) {                 // Legger inn verdi på hale plassen
            hale.neste = ny;
            ny.forrige = hale;
            ny.neste = null;
            hale = ny;
        } else if (indeks == 0) {                           // Legger inn verdi på hode plassen
            hode.forrige = ny;          // Gjør hode sin forrige til ny
            ny.neste = hode;            // Gjør hode til ny sin neste
            hode = ny;                  // Endrer hode pekeren
            ny.forrige = null;          // Ny sin neste blir nulll
        } else {
            Node<T> current = hode;                 // Lager en peker til den vi "er på"
            Node<T> next = hode.neste;              // Lager en peker til den vi "er på" sin neste
            for (int i = 0; i < indeks - 1; i++) {  // Finner noden til plassen vi skal sette inn den nye noden på
                current = next;                     //Går igjennom nodene til den finner den riktig
                next = next.neste;
            }
            ny.forrige = current;                   // Setter riktige forrige og neste pekere
            ny.neste = next;
            next.forrige = ny;
            current.neste = ny;

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
        if (verdi == null) return false;                       //Listen er tom

        Node<T> posisjon = hode;                               //Setter posisjon til hode sin plass

        //Indeks er på første plass
        if (verdi.equals(posisjon.verdi)) {                    //Sjekker om verdi og posisjon(hode) har samme verdi
            hode.forrige = null;                               //Setter hode sin forrige peker til null, slik at verdien vår blir slettet
            hode = hode.neste;                                 //Setter hode til noden etter verdien som skal fjernes
            antall--;
            endringer++;
            return true;
        }

        //Indeks er på siste plass
        posisjon = hale;                                       //Setter posisjon til hale sin plass
        if (verdi.equals(posisjon.verdi)) {                    //Sjekker om verdi og posisjon(hale) har samme verdi
            hale.neste = null;                                 //Setter hale sin neste peker til null, slik at verdien blir slettet
            hale = posisjon.forrige;                           //Setter hale til verdien før verdien som skal fjernes
            antall--;
            endringer++;
            return true;
        }

        //Indeks er et sted imellom første plass og siste plass
        posisjon = hode;
        for (int i = 0; i < antall - 1; i++) {                        //Looper gjennom listen til indeks verdi i listen er lik verdien vi skal slette
            posisjon = posisjon.neste;
            if (posisjon.verdi.equals(verdi)) {                       //Verdien er lik og for løkka stopper
                posisjon.forrige.neste = posisjon.neste;              //Noden før posisjon sin nestepeker peker på noden etter posisjon
                posisjon.neste.forrige = posisjon.forrige;            //Noden etter posisjon sin forrigepeker peker på noden før posisjon
                antall--;
                endringer++;
                return true;
            }
        }
        return false;
    }

    @Override
    public T fjern(int indeks) {
        indeksKontroll(indeks, false);              //Sjekker om indeks er gyldig

        Node<T> posisjon = hode;                            //Setter posisjon til hode sin plass
        T fjernes;                                          //Verdi som skal fjernes

        //Indeks er på første plass
        if (indeks == 0) {
            fjernes = posisjon.verdi;                       //Setter fjernes til posisjon sin verdi(hode)
            if (posisjon.neste != null) {                   //Sjekker om det er flere verdier i listen
                hode = hode.neste;                          //Setter hode til noden etter den som skal fjernes
                hode.forrige = null;                        //Setter hode sin forrige peker til null
            } else {                                        //Arrayet har kun en verdi, den som blir fjernet
                hode = null;
                hale = null;
            }

            //Indeks er på siste plass
        } else if (indeks == antall - 1) {
            fjernes = hale.verdi;                          //Setter fjernes til hale sin node
            hale = hale.forrige;                           //Setter hale til nest siste node
            hale.neste = null;                             //Setter neste peker til hale til null, slik at verdien blir slettet

            //Indeks er et sted imellom første plass og siste plass
        } else {
            for (int i = 0; i < indeks; i++) {             //Looper gjennom hver indeks i listen til i er like stor som indeks
                posisjon = posisjon.neste;
            }
            fjernes = posisjon.verdi;                      //Setter fjernes til indeks verdien

            posisjon.forrige.neste = posisjon.neste;      //Noden før posisjon sin nestepeker peker på noden etter posisjon
            posisjon.neste.forrige = posisjon.forrige;    //Noden etter posisjon sin forrigepeker peker på noden før posisjon
        }

        antall--;
        endringer++;

        return fjernes;
    }

    @Override
    public void nullstill() {

        for (int i = 0; i < antall; i++) {
            fjern(0);
        }
        hode = null;
        hale = null;
        antall = 0;
        endringer++;
    }

    @Override
    public String toString() {
        if (antall == 0) {                                              //Sjekker om den er tom
            return "[]";
        }

        StringBuilder s = new StringBuilder();                          //Oppretter string builder
        Node current = hode;                                            //Setter current til hode
        s.append('[').append(hode.verdi);                               //Legger til klammeparantes og hode sin verdi

        for (int i = 1; i < antall; i++) {                              //Looper gjennom lista med for løkke og legger til , mellomrom og verdi til listen er tom
            s.append(',').append(' ').append(current.neste.verdi);
            current = current.neste;
        }
        s.append(']');                                                  //Lukker stringen med klammeparantes

        return s.toString();                                            //Stringen returneres
    }

    public String omvendtString() {                                     //Samme fremgangsmåte som toString men starter bakfra
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
        return new DobbeltLenketListeIterator();
    }

    public Iterator<T> iterator(int indeks) {
        indeksKontroll(indeks, false);
        return new DobbeltLenketListeIterator(indeks);
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
            Node current = hode;
            for (int i = 0; i < indeks; i++) {
                current = current.neste;
            }
            denne = current;
            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer
        }

        @Override
        public boolean hasNext() {
            return denne != null;
        }

        @Override
        public T next() {
            if (iteratorendringer != endringer) {
                throw new ConcurrentModificationException("Listen har blitt endret");
            }
            if (!hasNext()) {
                throw new NoSuchElementException("Noden har ingen verdi, så den har ingen neste");
            }

            fjernOK = true;
            Node<T> retur = denne;
            denne = denne.neste;
            return retur.verdi;
        }

        @Override
        public void remove() {

            if (antall == 0 || denne == hode) {
                throw new IllegalStateException();
            }
            if (iteratorendringer != endringer) {
                throw new ConcurrentModificationException("Listen har blitt endret");
            }
            if (antall == 1) {
                hode = null;
                hale = null;
            } else if (denne == null) {
                hale = hale.forrige;
                hale.neste = null;
            } else if (denne.forrige == hode) {
                hode = denne;
                hode.forrige = null;
            } else {
                Node fjernes = denne.forrige;
                denne.forrige = fjernes.forrige;
                fjernes.forrige.neste = denne;
            }
            antall--;
            endringer++;
            iteratorendringer++;
        }

    } // class DobbeltLenketListeIterator

    //Kopiert bytt fra KOMPENDIET (Programkode 1.1.8 d)
    public static <T> void bytt(Liste<T> liste, int i, int j) {
        T temp = liste.hent(i);
        liste.oppdater(i, liste.hent(j));
        liste.oppdater(j, temp);
    }

    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {

        if (liste.antall() == 0 || liste.antall() == 1) {
            return;
        }
        int i, j;


        for (i = 0; i < liste.antall(); i++) {
            for (j = 0; j < liste.antall(); j++) {
                if (c.compare(liste.hent(i), liste.hent(j)) < 0) {
                    bytt(liste, j, i);
                }
            }
        }
    }


} // class DobbeltLenketListe




