
import java.util.ArrayList;

public class Jugador {
    private final String nombreCompleto;
    private int edad;
    private int media;
    private double precio;
    private ArrayList<String> posicion = new ArrayList<>();
    private String piernaFavorita;
    private double ultimaValoracion;
    private double valorMercado;
    private String rutaFoto; // Puede ser URL de internet o ruta local

    public Jugador(String nombreCompleto, int edad, int media, double precio,
                   ArrayList<String> posicion, String piernaFavorita,
                   double ultimaValoracion, double valorMercado, String rutaFoto) {
        this.nombreCompleto = nombreCompleto;
        this.edad = edad;
        this.media = media;
        this.precio = precio;
        this.posicion = posicion;
        this.piernaFavorita = piernaFavorita;
        this.ultimaValoracion = ultimaValoracion;
        this.valorMercado = valorMercado;
        this.rutaFoto = rutaFoto;
    }

    // Getters existentes...
    public String getNombreCompleto() { return nombreCompleto; }
    public int getEdad() { return edad; }
    public int getMedia() { return media; }
    public double getPrecio() { return precio; }
    public ArrayList<String> getPosicion() { return posicion; }
    public String getPiernaFavorita() { return piernaFavorita; }
    public double getUltimaValoracion() { return ultimaValoracion; }
    public double getValorMercado() { return valorMercado; }
    public String getRutaFoto() { return rutaFoto; }

    @Override
    public String toString() {
        return nombreCompleto + " (" + String.join("/", posicion) + " - " + media + ") - " + valorMercado + "M€";
    }
}