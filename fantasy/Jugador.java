import java.util.ArrayList;

public class Jugador {
    private final String nombreCompleto;
    private int edad;
    private int media;
    private double precio;
    private ArrayList<String> posicion = new ArrayList<>(); //Por si juega en varias posiciones
    private String piernaFavorita;
    private double ultimaValoracion;
    private double valorMercado;

    Jugador(String nombreCompleto, int edad, int media, double precio, ArrayList<String> posicion, String piernaFavorita, double ultimaValoracion, double valorMercado) {
        this.nombreCompleto = nombreCompleto;
        this.edad = edad;
        this.media = media;
        this.precio = precio;
        this.posicion = posicion;
        this.piernaFavorita = piernaFavorita;
        this.ultimaValoracion = ultimaValoracion;
        this.valorMercado = valorMercado;
    }
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public int getEdad() {
        return edad;
    }
    public int getMedia(){
        return media;
    }
    public double getPrecio(){
        return precio;
    }
    public ArrayList<String> getPosicion() {
        return posicion;
    }

    public String getPiernaFavorita() {
        return piernaFavorita;
    }

    public double getUltimaValoracion() {
        return ultimaValoracion;
    }

    public double getValorMercado() {
        return valorMercado;
    }
    public String toString() {
        return " "+nombreCompleto + " (" + posicion + " - " + media + ") - " + precio + "M€";
    }
}
