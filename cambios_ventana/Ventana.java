import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.net.URI;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.SpinnerNumberModel;

public class Ventana extends javax.swing.JFrame {

    private JLabel etiquetaImagen;
    private Image imagenOriginal;

    // Ahora son atributos para poder modificarlos desde repartir()
    private JPanel panelPrincipal;
    private JPanel panelSuperior;
    private JScrollPane scroll;

    public Ventana() {

        setTitle("Imagen redimensionable");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 1) Cargar la imagen
        try {
            String urlTexto = "https://www.mactualidad.com/wp-content/uploads/2013/06/fondo-pantalla-mac-13.jpg";
            imagenOriginal = ImageIO.read(new URI(urlTexto).toURL());
        } catch (Exception e) {
            System.err.println("No se pudo cargar la URL: " + e.getMessage());
            imagenOriginal = crearImagenRespaldo();
        }

        // 2) Etiqueta de la imagen, con el listener EN LA ETIQUETA
        etiquetaImagen = new JLabel();
        etiquetaImagen.setHorizontalAlignment(JLabel.CENTER);
        etiquetaImagen.setVerticalAlignment(JLabel.CENTER);
        etiquetaImagen.setPreferredSize(new Dimension(0, 0));
        etiquetaImagen.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                redimensionarImagen();
            }
        });

        // 3) PARTE SUPERIOR: imagen + botón + pestañas + spinner
        panelSuperior = new JPanel(new GridBagLayout());
        panelSuperior.add(etiquetaImagen, celda(0, 0, 1, 1));

        JButton miBoton = new JButton("Botón");
        GridBagConstraints gb = celda(0, 1, 1, 1);
        gb.weighty = 0;
        gb.fill = GridBagConstraints.HORIZONTAL;
        gb.insets = new Insets(5, 10, 5, 10);
        panelSuperior.add(miBoton, gb);

        JTabbedPane pestañas = new JTabbedPane();

        JPanel panelInicio = new JPanel();
        panelInicio.add(new JLabel("Bienvenido a la aplicación"));

        JPanel panelAlumnos = new JPanel();
        panelAlumnos.add(new JLabel("Gestión de alumnos"));
        panelAlumnos.add(new JButton("Añadir alumno"));

        JPanel panelConfiguracion = new JPanel();
        panelConfiguracion.add(new JCheckBox("Activar notificaciones"));

        // Pestaña nueva: Ajustes (con el slider que controla el reparto)
        JPanel panelAjustes = new JPanel();
        panelAjustes.add(new JLabel("Tema:"));
        panelAjustes.add(new JComboBox<>(new String[]{"Claro", "Oscuro", "Sistema"}));

        JLabel etiquetaReparto = new JLabel("Parte superior: 50 %");
        JSlider reparto = new JSlider(10, 90, 50);
        reparto.addChangeListener(e -> {
            etiquetaReparto.setText("Parte superior: " + reparto.getValue() + " %");
            repartir(reparto.getValue());
        });
        panelAjustes.add(etiquetaReparto);
        panelAjustes.add(reparto);

        pestañas.addTab("Inicio", panelInicio);
        pestañas.addTab("Alumnos", panelAlumnos);
        pestañas.addTab("Configuración", panelConfiguracion);
        pestañas.addTab("Ajustes", panelAjustes);
        pestañas.setPreferredSize(new Dimension(0, 0));
        panelSuperior.add(pestañas, celda(1, 0, 1, 1));


        //FUNCIONALIDAD AÑADIDA: Mostrar u ocultar botones
        JCheckBox chkBotones = new JCheckBox("Mostrar botones", true);
        chkBotones.addActionListener(e -> {
            scroll.setVisible(chkBotones.isSelected());
            panelPrincipal.revalidate();
        });
        panelAjustes.add(chkBotones);

        // 4) PARTE INFERIOR: 40 botones con scroll
        JPanel contenido = new JPanel(new GridBagLayout());
        GridBagConstraints gbc2 = new GridBagConstraints();
        //NUEVO CAMBIO: Fill en horizontal y weightx para rellenar el espacio
        gbc2.fill = GridBagConstraints.HORIZONTAL;
        gbc2.weightx = 1;
        gbc2.anchor = GridBagConstraints.CENTER;

        //FUNCIONALIDAD AÑADIDA: Spinner funcional, modifica el diseño de los botones
        JSpinner spinner = new JSpinner(new SpinnerNumberModel(2, 1, 6, 1));
        spinner.addChangeListener(e -> {
            int columnas = (int) spinner.getValue();
            contenido.setLayout(new GridLayout(0, columnas, 5, 5)); //0 en filas = las que hagan falta
            contenido.revalidate();
            contenido.repaint();
        });
        GridBagConstraints gs = celda(1, 1, 1, 1);
        gs.weighty = 0;
        gs.fill = GridBagConstraints.NONE;
        gs.anchor = GridBagConstraints.EAST;         // nuevo: pegado a la derecha
        panelSuperior.add(spinner, gs);

        for (int i = 1; i <= 40; i++) {
            JButton boton = new JButton("Botón " + i);
            boton.setPreferredSize(new Dimension(150, 35));
            boton.setMinimumSize(new Dimension(150, 35));
            gbc2.gridx = (i - 1) % 2;
            gbc2.gridy = (i - 1) / 2;
            gbc2.insets = new Insets(3, 5, 3, 5);        // en el bucle de los 40 botones
            contenido.add(boton, gbc2);
        }
        scroll = new JScrollPane(contenido);

        // 5) REPARTO con GridBagLayout y pesos (50 / 50 inicial)
        // Tamaño preferido 0: así TODO el espacio es "sobrante" y se reparte solo por peso.
        panelSuperior.setPreferredSize(new Dimension(0, 0));
        scroll.setPreferredSize(new Dimension(0, 0));

        panelPrincipal = new JPanel(new GridBagLayout());

        GridBagConstraints g = new GridBagConstraints();
        g.fill = GridBagConstraints.BOTH;
        g.gridx = 0;
        g.weightx = 1;

        g.gridy = 0;
        g.weighty = 0.5;
        panelPrincipal.add(panelSuperior, g);

        g.gridy = 1;
        panelPrincipal.add(scroll, g);

        add(panelPrincipal);
    }

    /**
     * Cambia en directo el porcentaje de alto de la parte superior.
     * La inferior recibe el resto.
     */
    private void repartir(int porcentaje) {
        GridBagLayout gl = (GridBagLayout) panelPrincipal.getLayout();

        GridBagConstraints a = gl.getConstraints(panelSuperior);
        a.weighty = porcentaje / 100.0;
        gl.setConstraints(panelSuperior, a);

        GridBagConstraints b = gl.getConstraints(scroll);
        b.weighty = (100 - porcentaje) / 100.0;
        gl.setConstraints(scroll, b);

        panelPrincipal.revalidate();
    }

    private GridBagConstraints celda(int x, int y, int w, int h) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = x;
        c.gridy = y;
        c.gridwidth = w;
        c.gridheight = h;
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        return c;
    }

    private void redimensionarImagen() {
        if (imagenOriginal == null) {
            return;
        }

        /*
        // VERSIÓN 1 (COMENTADA): DEFORMA LA IMAGEN SIN RESPETAR PROPORCIONES
        int ancho = etiquetaImagen.getWidth();
        int alto = etiquetaImagen.getHeight();

        if (ancho <= 0 || alto <= 0) {
            return;
        }

        Image imagenRedimensionada = imagenOriginal.getScaledInstance(
                ancho,
                alto,
                Image.SCALE_SMOOTH
        );

        etiquetaImagen.setIcon(
                new ImageIcon(imagenRedimensionada)
        );
        */

        // VERSIÓN 2 (ACTIVA): MANTIENE LA PROPORCIÓN (ASPECT RATIO)
        int anchoDisponible = etiquetaImagen.getWidth();
        int altoDisponible = etiquetaImagen.getHeight();

        if (anchoDisponible <= 0 || altoDisponible <= 0) {
            return;
        }

        int anchoOriginal = imagenOriginal.getWidth(null);
        int altoOriginal = imagenOriginal.getHeight(null);

        if (anchoOriginal <= 0 || altoOriginal <= 0) {
            return;
        }

        double proporcion = Math.min(
                (double) anchoDisponible / anchoOriginal,
                (double) altoDisponible / altoOriginal
        );

        int nuevoAncho = Math.max(1, (int) (anchoOriginal * proporcion));
        int nuevoAlto = Math.max(1, (int) (altoOriginal * proporcion));

        Image imagenRedimensionada = imagenOriginal.getScaledInstance(
                nuevoAncho,
                nuevoAlto,
                Image.SCALE_SMOOTH
        );

        etiquetaImagen.setIcon(
                new ImageIcon(imagenRedimensionada)
        );
    }

    private Image crearImagenRespaldo() {
        BufferedImage img = new BufferedImage(280, 200, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = img.createGraphics();
        g.setColor(new Color(30, 136, 229));
        g.fillRect(0, 0, 280, 200);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("IMAGEN PRUEBA", 55, 105);
        g.dispose();
        return img;
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana().setVisible(true);
            }
        });
    }
}