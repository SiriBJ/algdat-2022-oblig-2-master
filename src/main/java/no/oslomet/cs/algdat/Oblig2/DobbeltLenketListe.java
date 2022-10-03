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

    public Liste<T> subliste(int fra, int til) {
        throw new UnsupportedOperationException();
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
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean inneholder(T verdi) {
        throw new UnsupportedOperationException();
    }

    // Oppgave 3 a)
    private Node<T> finnNode(int indeks) {
        if (indeks < (antall / 2)) { //indeksen er midre enn midten, starter fra hodet mot høyre
            Node current = hode;
            for (int i = 0; i < indeks; i++){
                current = current.neste;
            }
            return current;

        } else { // Letingen skal gå fra halen så til forrige til venstre 
            Node current = hale;
            for (int i = antall; i > indeks; i--){
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
        throw new UnsupportedOperationException();
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean fjern(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T fjern(int indeks) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void nullstill() {
        throw new UnsupportedOperationException();
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


