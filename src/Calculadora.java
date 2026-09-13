import javax.swing.*;
import java.awt.*;

public class Calculadora extends JFrame {
    private JTextField txtOperacion;
    private JTextField txtResultado;

    private void agregarTexto(String texto) {
        txtOperacion.setText(txtOperacion.getText() + texto);
    }
    public Calculadora() {
        setTitle("Calculadora");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        //Fila 1
        txtOperacion = new JTextField();
        txtOperacion.setEditable(false);
        txtOperacion.setHorizontalAlignment(JTextField.RIGHT);
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 1.0; gbc.gridwidth = 4; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.insets = new Insets(5, 5, 5, 5);
        add(txtOperacion, gbc);

        //Fila 2
        txtResultado = new JTextField();
        txtResultado.setEditable(false);
        txtResultado.setHorizontalAlignment(JTextField.RIGHT);
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 1.0; gbc.gridwidth = 4; gbc.fill = GridBagConstraints.HORIZONTAL;
        add(txtResultado, gbc);

        //Fila 3
        JRadioButton botonGrados = new JRadioButton("Grados", true);
        JRadioButton botonRadianes = new JRadioButton("Radianes");
        ButtonGroup grupoModo = new ButtonGroup();
        grupoModo.add(botonGrados);
        grupoModo.add(botonRadianes);

        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0.5; gbc.gridwidth = 2; add(botonGrados, gbc);
        gbc.gridx = 2; gbc.gridy = 2; gbc.gridwidth = 2; add(botonRadianes, gbc);

        gbc.gridwidth = 1;  // A partir de ahora cada botón ocupa 1 sola celda
        gbc.weightx = 0.25; // Cada uno de los 4 botones ocupa el 25% del ancho

        //Fila 4
        JButton botonSin = new JButton("sin");
        gbc.gridx = 0; gbc.gridy = 3;
        add(botonSin, gbc);

        JButton botonCos = new JButton("cos");
        gbc.gridx = 1; gbc.gridy = 3;
        add(botonCos, gbc);

        JButton botonTan = new JButton("tan");
        gbc.gridx = 2; gbc.gridy = 3;
        add(botonTan, gbc);

        JButton botonC = new JButton("C");
        gbc.gridx = 3; gbc.gridy = 3; gbc.gridheight = 2; gbc.fill = GridBagConstraints.BOTH;
        add(botonC, gbc);

        // RESTAURAMOS los valores por defecto para que los demás botones no salgan gigantes
        gbc.gridheight = 1; gbc.fill = GridBagConstraints.HORIZONTAL;

        //Fila 5
        JButton boton7 = new JButton("7");
        gbc.gridy = 4; gbc.gridx = 0; add(boton7, gbc);
        JButton boton8 = new JButton("8");
        gbc.gridy = 4; gbc.gridx = 1; add(boton8, gbc);
        JButton boton9 = new JButton("9");
        gbc.gridy = 4; gbc.gridx = 2; add(boton9, gbc);

        //Fila 6
        JButton btn4 = new JButton("4"); gbc.gridx = 0; gbc.gridy = 5; add(btn4, gbc);
        JButton btn5 = new JButton("5"); gbc.gridx = 1; gbc.gridy = 5; add(btn5, gbc);
        JButton btn6 = new JButton("6"); gbc.gridx = 2; gbc.gridy = 5; add(btn6, gbc);
        JButton botonDiv = new JButton("/"); gbc.gridy = 5; gbc.gridx = 3; add(botonDiv, gbc);

        //Fila 7
        JButton btn1 = new JButton("1"); gbc.gridx = 0; gbc.gridy = 6; add(btn1, gbc);
        JButton btn2 = new JButton("2"); gbc.gridx = 1; gbc.gridy = 6; add(btn2, gbc);
        JButton btn3 = new JButton("3"); gbc.gridx = 2; gbc.gridy = 6; add(btn3, gbc);
        JButton btnMul = new JButton("*"); gbc.gridx = 3; gbc.gridy = 6; add(btnMul, gbc);

        //Fila 8
        JButton btn0 = new JButton("0"); gbc.gridx = 0; gbc.gridy = 7; gbc.gridwidth = 1; add(btn0, gbc);
        JButton btnPi = new JButton("π"); gbc.gridx = 1; gbc.gridy = 7; add(btnPi, gbc);
        JButton btnSuma = new JButton("+"); gbc.gridx = 2; gbc.gridy = 7; add(btnSuma, gbc);
        JButton btnResta = new JButton("-"); gbc.gridx = 3; gbc.gridy = 7; add(btnResta, gbc);

        //Fila 9: Boton igual
        JButton btnIgual = new JButton("="); gbc.gridx = 2; gbc.gridy = 8; gbc.gridwidth = 2; add(btnIgual, gbc);

        // --- EVENTOS DE BOTONES NUMÉRICOS ---
        btn0.addActionListener(e -> agregarTexto("0"));
        btn1.addActionListener(e -> agregarTexto("1"));
        btn2.addActionListener(e -> agregarTexto("2"));
        btn3.addActionListener(e -> agregarTexto("3"));
        btn4.addActionListener(e -> agregarTexto("4"));
        btn5.addActionListener(e -> agregarTexto("5"));
        btn6.addActionListener(e -> agregarTexto("6"));
        btnPi.addActionListener(e -> agregarTexto("π"));
        btnSuma.addActionListener(e -> agregarTexto("+"));
        btnResta.addActionListener(e -> agregarTexto("-"));
        botonDiv.addActionListener(e -> agregarTexto("/"));
        btnMul.addActionListener(e -> agregarTexto("*"));
        boton7.addActionListener(e -> agregarTexto("7"));
        boton8.addActionListener(e -> agregarTexto("8"));
        boton9.addActionListener(e -> agregarTexto("9"));

        // Evento del botón C (Limpiar todo)
        botonC.addActionListener(e -> {
            txtOperacion.setText("");
            txtResultado.setText("0");
        });
    }
    public static void main(String[] args) {
        Calculadora calculadora = new Calculadora();
        calculadora.setVisible(true);
    }
}
