import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GeneradorJugadores {

    public static ArrayList<Jugador> cargarMercado() {
        ArrayList<Jugador> mercado = new ArrayList<>();

        // ==================== PORTEROS (POR) ====================
        mercado.add(new Jugador("Thibaut Courtois", 32, 89, 25.0,
                new ArrayList<>(List.of("POR")), "Izquierda", 8.4, 28.0,
                "https://livesport-ott-images.ssl.cdn.cra.cz/r900xfq60/d69c30d4-2b14-4273-b477-587eff25a04b.jpeg"));

        mercado.add(new Jugador("Jan Oblak", 31, 88, 20.0,
                new ArrayList<>(List.of("POR")), "Derecha", 8.1, 22.0, ""));

        mercado.add(new Jugador("Marc-André ter Stegen", 32, 89, 22.5,
                new ArrayList<>(List.of("POR")), "Derecha", 8.0, 24.0, ""));

        // ==================== DEFENSAS (DEF) ====================
        mercado.add(new Jugador("Antonio Rüdiger", 31, 88, 28.0,
                new ArrayList<>(List.of("DEF")), "Derecha", 8.7, 30.0, ""));

        mercado.add(new Jugador("Virgil van Dijk", 33, 89, 30.0,
                new ArrayList<>(List.of("DEF")), "Derecha", 8.8, 32.0,
                "https://tse2.mm.bing.net/th/id/OIP.9jYKsXzn78j6ue2qXxljZwHaE8?pid=ImgDetMain"));

        mercado.add(new Jugador("William Saliba", 23, 87, 35.0,
                new ArrayList<>(List.of("DEF")), "Derecha", 8.6, 38.0, ""));

        mercado.add(new Jugador("Rúben Dias", 27, 88, 32.0,
                new ArrayList<>(List.of("DEF")), "Derecha", 8.5, 34.0, "https://cdn.sportwitness.co.uk/wp-content/uploads/2025/11/rubendias1.jpg"));

        mercado.add(new Jugador("Achraf Hakimi", 26, 85, 27.0,
                new ArrayList<>(List.of("DEF", "MED")), "Derecha", 8.3, 29.0, ""));

        mercado.add(new Jugador("Alphonso Davies", 24, 84, 26.0,
                new ArrayList<>(List.of("DEF", "MED")), "Izquierda", 8.2, 28.0, ""));

        // ==================== MEDIOCAMPISTAS (MED) ====================
        mercado.add(new Jugador("Rodri Hernández", 28, 91, 45.0,
                new ArrayList<>(List.of("MED")), "Derecha", 9.2, 50.0, ""));

        mercado.add(new Jugador("Jude Bellingham", 21, 90, 42.0,
                new ArrayList<>(List.of("MED", "DEL")), "Derecha", 9.0, 48.0, ""));

        mercado.add(new Jugador("Kevin De Bruyne", 33, 90, 38.0,
                new ArrayList<>(List.of("MED")), "Derecha", 8.8, 40.0, ""));

        mercado.add(new Jugador("Federico Valverde", 26, 88, 36.0,
                new ArrayList<>(List.of("MED", "DEF")), "Derecha", 8.9, 39.0, ""));

        mercado.add(new Jugador("Pedri González", 22, 87, 34.0,
                new ArrayList<>(List.of("MED")), "Derecha", 8.7, 37.0, "https://www.completesports.com/wp-content/uploads/2025/01/pedri.jpg"));

        mercado.add(new Jugador("Eduardo Camavinga", 22, 83, 28.0,
                new ArrayList<>(List.of("MED", "DEF")), "Izquierda", 8.3, 30.0, ""));

        // ==================== DELANTEROS (DEL) ====================
        mercado.add(new Jugador("Erling Haaland", 24, 91, 50.0,
                new ArrayList<>(List.of("DEL")), "Izquierda", 9.3, 55.0, ""));

        mercado.add(new Jugador("Kylian Mbappé", 25, 91, 50.0,
                new ArrayList<>(List.of("DEL")), "Derecha", 9.2, 55.0,
                "/fotos_locales/Mbappe.jpg"));

        mercado.add(new Jugador("Vinicius Junior", 24, 90, 48.0,
                new ArrayList<>(List.of("DEL")), "Derecha", 9.1, 52.0,
                "/fotos_locales/Vinicius.jpg"));

        mercado.add(new Jugador("Harry Kane", 31, 90, 35.0,
                new ArrayList<>(List.of("DEL")), "Derecha", 8.9, 38.0, ""));

        mercado.add(new Jugador("Mohamed Salah", 32, 89, 36.0,
                new ArrayList<>(List.of("DEL")), "Izquierda", 8.8, 39.0, ""));

        mercado.add(new Jugador("Lamine Yamal", 17, 85, 42.0,
                new ArrayList<>(List.of("DEL")), "Izquierda", 9.0, 46.0,
                "https://tse4.mm.bing.net/th/id/OIP.FYC2bnw6iV8ni3QH7DBGYwHaE8?pid=ImgDetMain"));

        return mercado;
    }

    // Guarda la imagen original de cada ruta para no descargarla más de una vez
    private static final HashMap<String, ImageIcon> originales = new HashMap<>();

    private static ImageIcon cargarIcono(String ruta) {
        if (originales.containsKey(ruta)) {
            return originales.get(ruta);
        }
        ImageIcon icono = null;
        try {
            if (ruta.startsWith("http")) {
                icono = new ImageIcon(new java.net.URI(ruta).toURL());              // desde internet
            } else {
                icono = new ImageIcon(GeneradorJugadores.class.getResource(ruta));  // dentro del proyecto
            }
            if (icono.getImageLoadStatus() != MediaTracker.COMPLETE) {
                icono = null;
            }
        } catch (Exception e) {
            icono = null;
        }
        if (icono == null) {
            System.out.println("No se pudo cargar la imagen: " + ruta);
        }
        originales.put(ruta, icono);
        return icono;
    }

    // Foto estirada a un tamaño exacto (lista, vista previa y diálogo)
    public static ImageIcon obtenerImagenEscalada(String ruta, int ancho, int alto) {
        if (ruta == null || ruta.isEmpty()) {
            return null;
        }
        ImageIcon original = cargarIcono(ruta);
        if (original == null) {
            return null;
        }
        return new ImageIcon(original.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH));
    }

    // Foto que rellena todo el recuadro SIN deformarse: se escala hasta cubrirlo y se recorta lo que sobra
    public static ImageIcon obtenerImagenRellena(String ruta, int ancho, int alto) {
        if (ruta == null || ruta.isEmpty()) {
            return null;
        }
        ImageIcon original = cargarIcono(ruta);
        if (original == null) {
            return null;
        }

        // 1. Escala suficiente para cubrir el recuadro en ancho Y en alto
        double escala = Math.max((double) ancho / original.getIconWidth(),
                (double) alto / original.getIconHeight());
        int nuevoAncho = (int) Math.ceil(original.getIconWidth() * escala);
        int nuevoAlto = (int) Math.ceil(original.getIconHeight() * escala);
        Image escalada = new ImageIcon(
                original.getImage().getScaledInstance(nuevoAncho, nuevoAlto, Image.SCALE_SMOOTH)).getImage();

        // 2. Lienzo del tamaño exacto; la foto se dibuja centrada y lo que sobra queda fuera
        BufferedImage lienzo = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_ARGB);
        Graphics g = lienzo.getGraphics();
        g.drawImage(escalada, (ancho - nuevoAncho) / 2, (alto - nuevoAlto) / 2, null);
        g.dispose();

        return new ImageIcon(lienzo);
    }
}