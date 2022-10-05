import LigaModel.Jugador;
import PrograAvan.NoLineal.Arbol_RN;

public class Main {
    public static void main(String[] args) {

        Jugador j1 = new Jugador("andres", 2003, 3, 7);
        Jugador j2 = new Jugador("pablo", 2004, 3, 5);
        Jugador j3 = new Jugador("pablo2 xd", 2005, 3, 9);
        Jugador j4 = new Jugador("camba", 2006, 3, 4);
        Jugador j5 = new Jugador("ingrdh", 2007, 3, 6);
        Jugador j6 = new Jugador("ingrdh", 2007, 3, 8);
        Jugador j7 = new Jugador("ingrdh", 2007, 3, 10);

        Arbol_RN<Integer, Jugador> Plantel = new Arbol_RN<>();

        Plantel.insertar(j1.getId_jugador(), j1);
        Plantel.insertar(j2.getId_jugador(), j2);
        Plantel.insertar(j3.getId_jugador(), j3);
        Plantel.insertar(j4.getId_jugador(), j4);
        Plantel.insertar(j5.getId_jugador(), j5);
        Plantel.insertar(j6.getId_jugador(), j6);
        Plantel.insertar(j7.getId_jugador(), j7);

        Plantel.profundidad();
        System.out.println();
        Plantel.amplitud();
    }
}
