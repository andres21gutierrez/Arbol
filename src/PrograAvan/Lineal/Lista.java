package PrograAvan.Lineal;

import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Lista<T> implements Iterable<T>, Serializable {
    private Nodo<T> cabeza;
    private int tam;

    public Lista() {
        cabeza = null;
        tam = 0;
    }

    public boolean esta_vacia(){
        return tam == 0;
    }

    public int getTam() {
        return tam;
    }
    public void insertar(T item){
        cabeza = insertar(cabeza, item);
    }
    private Nodo insertar(Nodo x, T item){
        if (x == null){
            x = new Nodo();
            x.item = item;
            x.enlace = null;
            tam++;
            return x;
        }
        x.enlace = insertar(x.enlace, item);
        return x;
    }

    public void intercambio(int c1, int c2){
        if(c2 >= tam || c1 >= tam){
            throw new IllegalArgumentException("UNO DE LOS INDICES ES MAYOR AL TAMANO");
        }

        else{
            Nodo recu = cabeza;
            T aux = get_Nodo(c1).item;
            get_Nodo(c1).item = get_Nodo(c2).item;
            get_Nodo(c2).item = aux;
        }

    }

    public Nodo<T> get_Nodo(int indice){
        if (indice>= tam){
            throw new IllegalArgumentException("Indice "+indice+ " fuera de rango : "+ tam);
        }
        else{
            Nodo aux = cabeza;
            for (int i=0; i <indice;i++){
                aux = aux.enlace;
            }
            return aux;
        }
    }

    public void mostrar_lista(){
        Nodo punt = cabeza;
        while(punt != null){
            System.out.println(punt.item);
            punt = punt.enlace;
        }
    }

    public T get(int indice){
        if (indice>= tam){
            throw new IllegalArgumentException("Indice "+indice+ " fuera de rango : "+ tam);
        }
        for (int i=0; i <indice;i++){
            cabeza = cabeza.enlace;
        }
        return cabeza.item;
    }

    public void eliminar(int c){
        if(c>=tam)
        {
            throw new IllegalArgumentException("Indice "+c+ " fuera de rango : "+ tam);
        }
        else{
            Nodo punt = get_Nodo(c);
            if(punt == cabeza){
                cabeza = cabeza.enlace;
                punt.enlace = null;
                tam--;
            }

            else{
                Nodo ant = get_Nodo(c-1);
                ant.enlace = punt.enlace;
                punt.enlace = null;
                tam--;
            }
        }
    }


    @Override
    public Iterator<T> iterator() {
        return new ListIterator(cabeza);
    }
    private class ListIterator implements Iterator<T>{
        private Nodo<T> actual;

        public ListIterator(Nodo<T> pri){
            actual = pri;
        }
        @Override
        public boolean hasNext() {
            return actual != null;
        }

        @Override
        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            T dato = actual.item;
            actual = actual.enlace;
            return dato;
        }
    }
}