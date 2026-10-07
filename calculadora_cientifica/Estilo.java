import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Estilo {
    // Paleta de colores
    public static final Color FONDO = new Color(32, 33, 36);
    public static final Color PANTALLA = new Color(45, 48, 51);
    public static final Color BOTON_NUMERO = new Color(60, 64, 67);
    public static final Color BOTON_OPERADOR = new Color(80, 84, 88);
    public static final Color BOTON_IGUAL = new Color(26, 115, 232);
    public static final Color BOTON_BORRAR = new Color(217, 48, 37);
    public static final Color TEXTO_BLANCO = Color.WHITE;
    public static final Color TEXTO_SECUNDARIO = new Color(180, 180, 180);
    public static final Color BORDE_BOTON = new Color(50, 50, 50);

    // Bordes claramente visibles que delimitan la tecla
    public static final Color BORDE_TECLA = new Color(70, 72, 80);
    public static final Color BORDE_PANTALLA = new Color(45, 46, 52);

    // Tipografías
    // Tipografías
    public static final Font FUENTE_NUMEROS = new Font("SansSerif", Font.BOLD, 17);
    public static final Font FUENTE_FUNCIONES = new Font("SansSerif", Font.PLAIN, 14);
    public static final Font FUENTE_OPERACION = new Font("SansSerif", Font.PLAIN, 14);
    public static final Font FUENTE_RESULTADO = new Font("SansSerif", Font.BOLD, 24);

    /**
     Aplica el estilo base a un botón según los colores y fuente indicados
     **/
    public static void aplicarBoton(JButton btn, Color fondo, Color texto, Font fuente) {
        btn.setBackground(fondo);
        btn.setForeground(texto);
        btn.setFont(fuente);
        btn.setFocusable(false);
        btn.setBorder(new LineBorder(BORDE_BOTON, 1));

        Border linea = new LineBorder(BORDE_TECLA, 1);
        Border margenInterno = new EmptyBorder(4, 4, 4, 4);
        btn.setBorder(BorderFactory.createCompoundBorder(linea, margenInterno));
    }

    /**
     Aplica estilo a las pantallas de texto de la calculadora
     **/
    public static void aplicarPantalla(JTextField txt, Font fuente, Color colorTexto, int paddingArriba, int paddingAbajo) {
        txt.setEditable(false);
        txt.setHorizontalAlignment(JTextField.RIGHT);
        txt.setFont(fuente);
        txt.setBackground(PANTALLA);
        txt.setForeground(colorTexto);
        txt.setBorder(new EmptyBorder(paddingArriba, 10, paddingAbajo, 10));
    }

    /**
     Aplica estilo a los botones de radio
     **/
    public static void aplicarRadio(JRadioButton radio) {
        radio.setFont(new Font("SansSerif", Font.BOLD, 12));
        radio.setForeground(TEXTO_SECUNDARIO);
        radio.setOpaque(false);
        radio.setFocusable(false);
    }
}
