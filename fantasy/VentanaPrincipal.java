import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VentanaPrincipal extends JFrame {
    private GestorPartida miEquipo;
    private ArrayList<Jugador> mercado;

    private PanelCampo panelCampo;
    private JLabel lblPresupuesto;
    private DefaultListModel<Jugador> modeloMercado;
    private JList<Jugador> listaMercado;
    private JLabel lblFotoMercado;

    public VentanaPrincipal(String nombre, double dinero) {
        setTitle("Fantasy Champions League 2026 - " + nombre);
        setSize(1100, 780);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());

        this.mercado = GeneradorJugadores.cargarMercado();
        this.miEquipo = new GestorPartida(nombre, dinero);

        panelSuperior();
        panelCentral();
        panelLateralMercado();
    }

    private void panelSuperior() {
        JPanel sup = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        lblPresupuesto = new JLabel();
        lblPresupuesto.setFont(new Font("Arial", Font.BOLD, 18));
        actualizarTextoPresupuesto();
        sup.add(lblPresupuesto);

        JButton btnReiniciar = new JButton("Reiniciar Fantasy");
        btnReiniciar.addActionListener(e -> {
            int respuesta = JOptionPane.showConfirmDialog(this,
                    "¿Seguro que quieres reiniciar? Perderás tu plantilla y tu alineación.",
                    "Reiniciar Fantasy", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (respuesta == JOptionPane.YES_OPTION) {
                new VentanaInicio().setVisible(true);
                dispose();
            }
        });
        sup.add(btnReiniciar);

        add(sup, BorderLayout.NORTH);
    }

    private void panelCentral() {
        panelCampo = new PanelCampo(this);
        add(panelCampo, BorderLayout.CENTER);
    }

    private void panelLateralMercado() {
        JPanel panelMercado = new JPanel(new BorderLayout(5, 5));
        panelMercado.setPreferredSize(new Dimension(340, 0));
        panelMercado.setBorder(BorderFactory.createTitledBorder("Mercado de Jugadores"));

        modeloMercado = new DefaultListModel<>();
        for (Jugador j : mercado) {
            modeloMercado.addElement(j);
        }
        JComboBox<String> comboPosicion = new JComboBox<>(new String[]{"TODAS", "POR", "DEF", "MED", "DEL"});
        comboPosicion.addActionListener(e -> {
            String filtro = (String) comboPosicion.getSelectedItem();
            modeloMercado.clear();
            for (Jugador j : mercado) {
                if (filtro.equals("TODAS") || j.getPosicion().contains(filtro)) {
                    modeloMercado.addElement(j);
                }
            }
        });
        panelMercado.add(comboPosicion, BorderLayout.NORTH);
        listaMercado = new JList<>(modeloMercado);
        listaMercado.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        panelMercado.add(new JScrollPane(listaMercado), BorderLayout.CENTER);

        // Panel de previsualización de la foto seleccionada
        JPanel panelPreview = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        lblFotoMercado = new JLabel();
        lblFotoMercado.setPreferredSize(new Dimension(150, 160));
        lblFotoMercado.setHorizontalAlignment(SwingConstants.CENTER);
        lblFotoMercado.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        panelPreview.add(lblFotoMercado);

        listaMercado.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Jugador sel = listaMercado.getSelectedValue();
                if (sel != null) {
                    ImageIcon icono = GeneradorJugadores.obtenerImagenEscalada(sel.getRutaFoto(), 150, 160);
                    lblFotoMercado.setIcon(icono);
                } else {
                    lblFotoMercado.setIcon(null);
                }
            }
        });

        JButton btnFichar = new JButton("Fichar Jugador");
        btnFichar.setFont(new Font("Arial", Font.BOLD, 14));
        btnFichar.addActionListener(e -> {
            Jugador seleccionado = listaMercado.getSelectedValue();
            if (seleccionado != null) {
                mostrarDialogoFichaje(seleccionado);
            } else {
                JOptionPane.showMessageDialog(this, "Selecciona a un jugador de la lista.");
            }
        });

        JPanel panelInferior = new JPanel(new BorderLayout(5, 5));
        panelInferior.add(panelPreview, BorderLayout.NORTH);
        panelInferior.add(btnFichar, BorderLayout.SOUTH);

        panelMercado.add(panelInferior, BorderLayout.SOUTH);
        add(panelMercado, BorderLayout.EAST);

        if (!modeloMercado.isEmpty()) {
            listaMercado.setSelectedIndex(0);
        }
    }

    private void mostrarDialogoFichaje(Jugador j) {
        JDialog dialog = new JDialog(this, "Confirmar Fichaje", true);
        dialog.setSize(330, 230);
        dialog.setLocationRelativeTo(this);
        dialog.setResizable(false);
        dialog.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 12));

        JLabel lblFoto = new JLabel();
        ImageIcon icono = GeneradorJugadores.obtenerImagenEscalada(j.getRutaFoto(), 100, 120);
        if (icono != null) {
            lblFoto.setIcon(icono);
        }

        JLabel lblInfo = new JLabel("<html><center>¿Fichar a <b>" + j.getNombreCompleto()
                + "</b>?<br>Coste: <b>" + j.getValorMercado() + " M€</b></center></html>");

        JButton btnConfirmar = new JButton("Confirmar");
        JButton btnCancelar = new JButton("Cancelar");

        btnConfirmar.addActionListener(e -> {
            dialog.dispose();
            intentarFichar(j);
        });

        btnCancelar.addActionListener(e -> dialog.dispose());

        dialog.add(lblFoto);
        dialog.add(lblInfo);
        dialog.add(btnConfirmar);
        dialog.add(btnCancelar);

        dialog.setVisible(true);
    }

    public void intentarFichar(Jugador j) {
        boolean fichado = miEquipo.fichar(j);
        if (fichado) {
            mercado.remove(j);
            modeloMercado.removeElement(j);
            actualizarTextoPresupuesto();
            JOptionPane.showMessageDialog(this, "¡Fichaje realizado! Ahora pulsa una casilla en el campo para alinearlo.");
        } else {
            JOptionPane.showMessageDialog(this, "No tienes suficiente presupuesto para fichar a " + j.getNombreCompleto(),
                    "Sin fondos", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void alinearEnBoton(JButton btn) {
        String posicionRequerida = (String) btn.getClientProperty("posicion");

        ArrayList<Jugador> disponibles = new ArrayList<>();
        for (Jugador j : miEquipo.getPlantilla()) {
            if (j.getPosicion().contains(posicionRequerida) && !estaAlineado(j)) {
                disponibles.add(j);
            }
        }

        if (disponibles.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "No tienes ningún " + posicionRequerida + " libre en tu plantilla.\nFíchalo primero en el mercado lateral.",
                    "Posición vacía", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Jugador seleccionado = (Jugador) JOptionPane.showInputDialog(
                this,
                "Selecciona un " + posicionRequerida + " para alinear:",
                "Alineación",
                JOptionPane.PLAIN_MESSAGE,
                null,
                disponibles.toArray(),
                disponibles.get(0)
        );

        if (seleccionado != null) {
            btn.putClientProperty("jugador", seleccionado);
            btn.setBackground(new Color(255, 215, 0));

            ImageIcon icono = GeneradorJugadores.obtenerImagenRellena(seleccionado.getRutaFoto(), 118, 94);
            btn.setIcon(icono);
            btn.setVerticalTextPosition(SwingConstants.BOTTOM);
            btn.setHorizontalTextPosition(SwingConstants.CENTER);

            btn.setText("<html><center><b>" + seleccionado.getNombreCompleto() + "</b><br>"
                    + seleccionado.getMedia() + " | " + seleccionado.getValorMercado() + "M€</center></html>");
            btn.revalidate();
            btn.repaint();
        }
    }

    private boolean estaAlineado(Jugador j) {
        for (JButton b : panelCampo.getHuecosCampo()) {
            if (b.getClientProperty("jugador") == j) {
                return true;
            }
        }
        return false;
    }

    private void actualizarTextoPresupuesto() {
        lblPresupuesto.setText(String.format("Presupuesto Restante: %.2f M€", miEquipo.getPresupuesto()));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaPrincipal("Dream Team", 200.0).setVisible(true));
    }
}