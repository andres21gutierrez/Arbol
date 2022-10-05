package PrograAvan.NoLineal;

import PrograAvan.Lineal.Cola;
import PrograAvan.Lineal.Pila;

public class Arbol_RN<Llave extends Comparable<Llave>, Valor> {
    private static final boolean ROJO = true;
    private static final boolean NEGRO = false;

    private Nodo raiz;

    public Llave get_max_index() {
        return null;
    }

    class Nodo{

        private Llave llave;
        private Valor valor;
        private Nodo izquierdo;
        private Nodo derecho;
        private boolean color;
        private int tam;

        public Nodo(Llave llave, Valor valor, boolean color, int tam) {
            this.llave = llave;
            this.valor = valor;
            this.color = color;
            this.tam = tam;
        }
    }


    public Arbol_RN(){
    }

    private boolean es_rojo(Nodo x){
        if(x == null){
            return NEGRO;
        }
        return x.color;
    }

    public void insertar(Llave llave, Valor val){
        raiz = insertar(raiz, llave, val);
        raiz.color = NEGRO;
    }

    private Nodo insertar(Nodo x, Llave l, Valor v){
        if(x == null){
            return new Nodo(l, v, ROJO, 1);
        }
        int cmp = l.compareTo(x.llave);
        if(cmp < 0){
            x.izquierdo = insertar(x.izquierdo, l, v);
        }
        else{
            x.derecho = insertar(x.derecho, l, v);
        }

        // VERIFICAR BALANCEO
        if(es_rojo(x.derecho) && !es_rojo(x.izquierdo)){
            x = rotar_izq(x);
        }
        if(es_rojo(x.izquierdo) && es_rojo(x.izquierdo.izquierdo)){
            x = rotar_der(x);
        }
        if(es_rojo(x.izquierdo) && es_rojo(x.derecho)){
            cambiar_color(x);
        }

        x.tam = get_tam(x.izquierdo) + get_tam(x.derecho) +1;
        return x;
    }

    private void cambiar_color(Nodo x) {
        x.color = !x.color;
        x.derecho.color = !x.derecho.color;
        x.izquierdo.color = !x.izquierdo.color;
    }

    private Nodo rotar_der(Nodo x) {
        Nodo aux = x.izquierdo;
        x.izquierdo = aux.derecho;
        aux.derecho = x;
        aux.color = aux.derecho.color;
        aux.derecho.color = ROJO;
        aux.tam = x.tam;
        x.tam = get_tam(x.izquierdo) + get_tam(x.derecho) + 1;
        return aux;
    }

    private Nodo rotar_izq(Nodo x) {
        Nodo aux = x.derecho;
        x.derecho = aux.izquierdo;
        aux.izquierdo = x;
        aux.color = aux.izquierdo.color;
        aux.izquierdo.color = ROJO;
        aux.tam = x.tam;
        aux.tam  = get_tam(x.izquierdo) + get_tam(aux.derecho) + 1;
        return aux;
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

    public Iterable<Llave> profundidad(){

        Pila<Nodo> pilita = new Pila<>();
        Cola<Llave> mostrado = new Cola<>();
        Nodo recor = raiz;
        pilita.empilar(recor);
        System.out.println(recor.llave);

        while(!pilita.esta_vacia()){
            recor = pilita.desempilar();
            mostrado.encolar(recor.llave);
            if(recor.izquierdo != null){
                pilita.empilar(recor.izquierdo);
            }
            if(recor.derecho != null){
                pilita.empilar(recor.derecho);
            }
        }
        return mostrado;
    }

    public Iterable<Llave> amplitud(){

        Cola<Nodo> colita = new Cola<>();
        Nodo recor = raiz;
        Cola<Llave> mostrado = new Cola<>();
        colita.encolar(recor);

        while(!colita.esta_vacio()){
            recor = colita.desencolar();
            mostrado.encolar(recor.llave);
            System.out.println(recor.llave);

            if(recor.izquierdo != null){
                colita.encolar(recor.izquierdo);
            }
            if(recor.derecho != null){
                colita.encolar(recor.derecho);
            }
        }
        return mostrado;
    }
}
