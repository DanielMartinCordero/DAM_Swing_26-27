import java.util.ArrayList;

public class GestorPartida {
    private String nombre;
    private double presupuesto;
    private final ArrayList<Jugador> plantilla;
    private final Jugador[] titulares; // Tamaño 11 para el 4-3-3

    public GestorPartida(String nombre, double presupuestoInicial) {
        this.nombre = nombre;
        this.presupuesto = presupuestoInicial;
        this.plantilla = new ArrayList<>();
        this.titulares = new Jugador[11];
    }

    public boolean fichar(Jugador jugador) {
        if (jugador.getValorMercado() > presupuesto) {
            return false; // Fondos insuficientes
        }
        presupuesto -= jugador.getValorMercado();
        plantilla.add(jugador);
        return true;
    }

    public void vender(Jugador jugador) {
        if (plantilla.remove(jugador)) {
            presupuesto += jugador.getValorMercado();
        }
    }

    // Getters
    public double getPresupuesto() { return presupuesto; }
    public ArrayList<Jugador> getPlantilla() { return plantilla; }
}