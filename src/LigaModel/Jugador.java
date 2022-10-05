package LigaModel;

public class Jugador {
    private Integer id_jugador;
    private String nombre;
    private int anio_nac;
    private int goles;

    public Jugador(String nombre, int anio_nac, int goles, Integer id) {
        this.nombre = nombre;
        this.anio_nac = anio_nac;
        this.goles = goles;
        this.id_jugador = id;
    }

    public Integer getId_jugador() {
        return id_jugador;
    }

    public void setId_jugador(Integer id_jugador) {
        this.id_jugador = id_jugador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAnio_nac() {
        return anio_nac;
    }

    public void setAnio_nac(int anio_nac) {
        this.anio_nac = anio_nac;
    }

    public int getGoles() {
        return goles;
    }

    public void setGoles(int goles) {
        this.goles = goles;
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "id_jugador=" + id_jugador +
                ", nombre='" + nombre + '\'' +
                ", anio_nac='" + anio_nac + '\'' +
                ", goles=" + goles +
                '}';
    }
}
