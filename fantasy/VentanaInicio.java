import javax.swing.*;
import java.awt.*;

public class VentanaInicio extends JFrame {
    private JTextField txtEquipo;
    private JComboBox<Double> comboPresupuesto;

    public VentanaInicio() {
        setTitle("Configuración Fantasy");
        setSize(380, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false); // Ventana fija
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 15)); // FlowLayout

        add(new JLabel("Nombre de tu Equipo:"));
        txtEquipo = new JTextField("Dream Team", 15);
        add(txtEquipo);

        add(new JLabel("Presupuesto Inicial:"));
        Double[] presupuestos = {150.0, 200.0, 250.0, 10000.0};
        comboPresupuesto = new JComboBox<>(presupuestos);
        comboPresupuesto.setSelectedIndex(1);
        add(comboPresupuesto);

        JButton btnEntrar = new JButton("Iniciar Partida");
        btnEntrar.addActionListener(e -> {
            String nombre = txtEquipo.getText().trim();
            double dinero = (double) comboPresupuesto.getSelectedItem();

            if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Introduce un nombre.");
                return;
            }

            // Abre el segundo JFrame pasándole los parámetros
            new VentanaPrincipal(nombre, dinero).setVisible(true);

            // Cierra la ventana de inicio actual
            this.dispose();
        });

        add(btnEntrar);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaInicio().setVisible(true));
    }
}