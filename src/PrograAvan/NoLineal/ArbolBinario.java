package PrograAvan.NoLineal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArbolBinario<Llave extends Comparable<Llave>, Valor> implements Iterable<Valor>{
    private Nodo raiz;
    private class Nodo{
        private Llave llave;
        private Valor valor;
        private Nodo izquierdo;
        private Nodo derecho;
        private int tam;

        public Nodo(Llave llave, Valor valor, int tam) {
            this.llave = llave;
            this.valor = valor;
            this.tam = tam;
        }
    }

    public ArbolBinario() {
    }

    public boolean esta_vacio(){
        return get_tam() == 0;
    }

    public int get_tam(){
        return get_tam(raiz);
    }
    public int get_tam(Nodo x){
        if (x == null){
            return 0;
        }
        else{
            return x.tam;
        }
    }

    public Valor get_element_by_llave(Llave l){
        return get_element_by_llave(raiz, l);
    }
    private Valor get_element_by_llave(Nodo x, Llave l){
        if (x == null){
            return null;
        }
        int cmp = l.compareTo(x.llave);
        if (cmp < 0){
            return get_element_by_llave(x.izquierdo, l);
        }
        else if (cmp > 0){
            return get_element_by_llave(x.derecho, l);
        }
        else{
            return x.valor;
        }
    }

    public Nodo get_nodo_by_llave(Llave l){
        return get_nodo_by_llave(raiz,l);
    }

    public Nodo get_nodo_by_llave(Nodo x, Llave l){
        if(x == null){
            return null;
        }
        int cmp = l.compareTo(x.llave);
        if(cmp<0){
            return get_nodo_by_llave(x.izquierdo, l);
        }
        else if(cmp > 0){
            return get_nodo_by_llave(x.derecho, l);
        }
        else return x;
    }

    public Nodo get_nodo_by_llave_anterior(Llave l){
        return get_nodo_by_llave_anterior(raiz,l);
    }

    public Nodo get_nodo_by_llave_anterior(Nodo x, Llave l){
        if(x == null){
            return null;
        }
        int cmp = l.compareTo(x.llave);
        if(cmp<0){
            if(x.izquierdo.llave == l){ return x;}
            return get_nodo_by_llave(x.izquierdo, l);
        }
        else if(cmp > 0){
            if(x.derecho.llave == l) return x;
            return get_nodo_by_llave(x.derecho, l);

        }
        else return null;
    }


    public void insertar(Llave l, Valor v){
        raiz = insertar(raiz, l, v);
    }
    private Nodo insertar(Nodo x, Llave l, Valor v){
        if (x == null){
            return new Nodo(l, v, 1);
        }
        int cmp = l.compareTo(x.llave);
        if (cmp < 0){
            x.izquierdo = insertar(x.izquierdo, l, v);
        }
        else if (cmp > 0){
            x.derecho = insertar(x.derecho, l, v);
        }
        else {
            throw new IllegalArgumentException();
        }
        x.tam = 1 + get_tam(x.izquierdo) + get_tam(x.derecho);
        return x;
    }

    public Llave get_min(){
        // OBTIENE EL MENOR
        Nodo p = raiz;
        return get_min(p);
    }

    public Llave get_min(Nodo p){
        if(p.izquierdo == null){
            return p.llave;
        }
        return get_min(p.izquierdo);
    }

    public Llave get_max(){
        // OBTIENE EL MÁXIMO
        Nodo p = raiz;
        return get_max(p);
    }

    public Llave get_max(Nodo p){
        if(p.derecho == null){
            return p.llave;
        }
        return get_max(p.derecho);
    }

    public void eliminar_min(){
        Nodo r = raiz;
        Llave min = get_min();
        while(r.izquierdo != get_nodo_by_llave(min)){
            r = r.izquierdo;
        }
        Nodo x = get_nodo_by_llave(min);
        if(x.derecho == null){
            // SE ELIMINA EL ENLACE AL MENOR
            r.izquierdo = null;
        }
        else{
            // SI EXISTE UN MAYOR ENLAZADO AL MENOR, ESTOS CAMBIAN DE VALOR Y LLAVE
            Nodo t = x.derecho;
            x.llave = t.llave;
            x.valor = t.valor;
            x.derecho = null;
        }

    }

    public void eliminar_max(){
        Nodo r = raiz;
        Llave max = get_max();
        while(r.derecho != get_nodo_by_llave(max)){
            r = r.derecho;
        }
        Nodo x = get_nodo_by_llave(max);
        if(x.izquierdo == null){
            // SE ELIMINA EL ENLACE AL MAYOR
            r.derecho = null;
        }
        else{
            // SI EXISTE UN MENOR ENLAZADO AL MAYOR, ESTOS CAMBIAN DE VALOR Y LLAVE
            Nodo t = x.izquierdo;
            x.llave = t.llave;
            x.valor = t.valor;
            x.izquierdo = null;
        }

    }


    public void eliminar_nodo_by_llave(Llave l){
        // EL METODO ELIMINA EN BASE A LA LLAVE DEL NODO

        Nodo e = get_nodo_by_llave_anterior(l);

        // RECUPERAMOS EL NODO ANTERIOR AL QUE QUEREMOS ELIMINAR PARA TENER LOS ENLACES

        Nodo aux;

        //  EL NODO AUXILIAR TOMA EL VALOR DEL NODO QUE QUEREMOS ELIMINAR

        if(e.izquierdo.llave == l){
            aux = e.izquierdo;
            if(aux.izquierdo == null){
                e.izquierdo = aux.derecho;
                // SI EL LADO IZQUIERDO DEL NODO QUE QUEREMOS ELIMINAR ESTA VACIO
                // ENTONCES SE CONECTA DIRECTAMENTE CON EL DERECHO EL NODO ANTERIOS
            }
            else{
                // SI LOS 2 LADOS TIENEN CONECCIONES

                e.izquierdo = aux.derecho;
                // EL LADO IZQUIERDO DEL NODO ANTERIOR TOMA EL VALOR DEL LADO DERECHO, PORQUE ES EL MAYOR
                Nodo aux2 = aux.izquierdo;
                while(e.izquierdo!= null){
                    e = e.izquierdo;
                }
                e.izquierdo = aux2;
            }
        }
        else{
            aux = e.derecho;
            if(aux.derecho == null){
                e.derecho = aux.izquierdo;

                // SI EL LADO DERECHO DEL NODO QUE QUEREMOS ELIMINAR ESTA VACIO
                // ENTONCES SE CONECTA DIRECTAMENTE CON EL IZQUIERDO EL NODO ANTERIOS

            }
            else{

                // SI LOS 2 LADOS TIENEN CONECCIONES

                e.derecho = aux.izquierdo;

                // EL LADO DERECHO DEL NODO ANTERIOR TOMA EL VALOR DEL LADO IZQUIERDO, PORQUE ES EL MENOR

                Nodo aux2 = aux.derecho;
                while(e.derecho!= null){
                    e = e.derecho;
                }
                e.derecho = aux2;
            }
        }
    }

    @Override
    public Iterator<Valor> iterator() {
        return new ArbolIterador(raiz);
    }

    private class ArbolIterador implements Iterator<Valor>{

        private Nodo actual;
        private ArrayList<Nodo> pila;
        public ArbolIterador(Nodo primero){
            pila = new ArrayList<Nodo>();
            actual = primero;
            moverIZquierda(primero);
        }
        @Override
        public boolean hasNext() {
            return !pila.isEmpty()  ;
        }

        @Override
        public Valor next() {
            Valor resultado = null;

            if(!hasNext()){
                throw new NoSuchElementException();
            }
            Nodo raiz = pila.remove(pila.size()-1);
            if(actual.derecho != null){

                moverIZquierda(raiz.derecho);
            }
            return raiz.valor;
        }

        @Override
        public void remove() {
            Iterator.super.remove();
        }
        public void moverIZquierda(Nodo primero){
            while(primero != null){
                pila.add(primero);
                primero= primero.izquierdo;
            }
        }
    }
}

