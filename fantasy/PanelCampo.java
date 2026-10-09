import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PanelCampo extends JPanel {
    private final ArrayList<JButton> huecosCampo = new ArrayList<>();

    public PanelCampo(VentanaPrincipal ventana) {
        setLayout(new GridLayout(4, 1, 0, 8));
        setBackground(new Color(34, 139, 34));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel filaDEL = crearFila();
        crearHueco(filaDEL, "DEL", ventana);
        crearHueco(filaDEL, "DEL", ventana);
        crearHueco(filaDEL, "DEL", ventana);

        JPanel filaMED = crearFila();
        crearHueco(filaMED, "MED", ventana);
        crearHueco(filaMED, "MED", ventana);
        crearHueco(filaMED, "MED", ventana);

        JPanel filaDEF = crearFila();
        crearHueco(filaDEF, "DEF", ventana);
        crearHueco(filaDEF, "DEF", ventana);
        crearHueco(filaDEF, "DEF", ventana);
        crearHueco(filaDEF, "DEF", ventana);

        JPanel filaPOR = crearFila();
        crearHueco(filaPOR, "POR", ventana);

        add(filaDEL);
        add(filaMED);
        add(filaDEF);
        add(filaPOR);
    }

    private JPanel crearFila() {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        p.setOpaque(false);
        return p;
    }

    private void crearHueco(JPanel panelFila, String rol, VentanaPrincipal ventana) {
        JButton btn = new JButton("<html><center><b>" + rol + "</b><br>+ Añadir</center></html>");
        btn.setPreferredSize(new Dimension(130, 150));
        btn.setMargin(new Insets(2, 2, 2, 2));        btn.setFocusable(false);
        btn.setBackground(new Color(245, 245, 245));

        btn.putClientProperty("posicion", rol);
        btn.putClientProperty("jugador", null);

        btn.addActionListener(e -> ventana.alinearEnBoton(btn));

        huecosCampo.add(btn);
        panelFila.add(btn);
    }

    public ArrayList<JButton> getHuecosCampo() {
        return huecosCampo;
    }
}