import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;


public class Calculadora extends JFrame {
    private JTextField txtOperacion;
    private JTextField txtResultado;

    private void agregarTexto(String texto) {
        txtOperacion.setText(txtOperacion.getText() + texto);
    }
    private void calcularResultado(boolean grados){
        try {

            String expresion = txtOperacion.getText();
            // Evaluamos la expresión matemática
            double resultado = EvaluarExpresion.evaluarExpresion(expresion, grados);
            // Mostramos el resultado en la pantalla inferior
            txtResultado.setText(String.valueOf(resultado));
        }
        catch (Exception ex) {
            txtResultado.setText("Error"); }
    }

    public Calculadora() {
        setTitle("Calculadora");
        setSize(360, 520);
        setMinimumSize(new Dimension(320, 480));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Estilo.FONDO);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH; 

        // --- PANTALLA LCD UNIFICADA ---
        JPanel panelPantalla = new JPanel(new GridLayout(2, 1));
        panelPantalla.setBackground(Estilo.PANTALLA);

        Border bordeExt = new LineBorder(Estilo.BORDE_PANTALLA, 1);
        Border bordeInt = new EmptyBorder(8, 12, 8, 12);
        panelPantalla.setBorder(BorderFactory.createCompoundBorder(bordeExt, bordeInt));

        txtOperacion = new JTextField();
        txtOperacion.setEditable(false);
        txtOperacion.setHorizontalAlignment(JTextField.RIGHT);
        txtOperacion.setFont(Estilo.FUENTE_OPERACION);
        txtOperacion.setBackground(Estilo.PANTALLA);
        txtOperacion.setForeground(Estilo.TEXTO_SECUNDARIO);
        txtOperacion.setBorder(null);

        txtResultado = new JTextField("0");
        txtResultado.setEditable(false);
        txtResultado.setHorizontalAlignment(JTextField.RIGHT);
        txtResultado.setFont(Estilo.FUENTE_RESULTADO);
        txtResultado.setBackground(Estilo.PANTALLA);
        txtResultado.setForeground(Estilo.TEXTO_BLANCO);
        txtResultado.setBorder(null);

        panelPantalla.add(txtOperacion);
        panelPantalla.add(txtResultado);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4; // Ocupa las 4 columnas
        gbc.weightx = 1.0;
        gbc.weighty = 0.0; // Altura fija según su contenido
        gbc.insets = new Insets(10, 10, 4, 10);
        add(panelPantalla, gbc);

        // --- SELECTOR DE MODO ---
        JPanel panelModo = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        panelModo.setOpaque(false);

        JRadioButton botonGrados = new JRadioButton("DEG", true);
        JRadioButton botonRadianes = new JRadioButton("RAD");
        Estilo.aplicarRadio(botonGrados);
        Estilo.aplicarRadio(botonRadianes);

        ButtonGroup grupoModo = new ButtonGroup();
        grupoModo.add(botonGrados);
        grupoModo.add(botonRadianes);

        panelModo.add(botonGrados);
        panelModo.add(botonRadianes);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 4; // Ocupa las 4 columnas
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;
        gbc.insets = new Insets(2, 10, 6, 10);
        add(panelModo, gbc);

        // --- CONFIGURACIÓN BASE PARA LA BOTONERA ---
        gbc.weightx = 0.25;
        gbc.weighty = 1.0; // Todas las filas crecen uniformemente
        gbc.gridwidth = 1;  // A partir de aquí cada botón ocupa 1 celda
        // Fila 4: sin, cos, tan, C
        JButton botonSin = new JButton("sin"); Estilo.aplicarBoton(botonSin, Estilo.BOTON_OPERADOR, Estilo.TEXTO_BLANCO, Estilo.FUENTE_FUNCIONES);
        JButton botonCos = new JButton("cos"); Estilo.aplicarBoton(botonCos, Estilo.BOTON_OPERADOR, Estilo.TEXTO_BLANCO, Estilo.FUENTE_FUNCIONES);
        JButton botonTan = new JButton("tan"); Estilo.aplicarBoton(botonTan, Estilo.BOTON_OPERADOR, Estilo.TEXTO_BLANCO, Estilo.FUENTE_FUNCIONES);
        JButton botonC = new JButton("C");     Estilo.aplicarBoton(botonC, Estilo.BOTON_BORRAR, Estilo.TEXTO_BLANCO, Estilo.FUENTE_NUMEROS);
        gbc.gridy = 2;
        gbc.gridx = 0; gbc.insets = new Insets(4, 10, 4, 4); add(botonSin, gbc);
        gbc.gridx = 1; gbc.insets = new Insets(4, 4, 4, 4);  add(botonCos, gbc);
        gbc.gridx = 2; gbc.insets = new Insets(4, 4, 4, 4);  add(botonTan, gbc);
        gbc.gridx = 3; gbc.insets = new Insets(4, 4, 4, 10); add(botonC, gbc);

        // Fila 5: 7, 8, 9, /
        JButton boton7 = new JButton("7"); Estilo.aplicarBoton(boton7, Estilo.BOTON_NUMERO, Estilo.TEXTO_BLANCO, Estilo.FUENTE_NUMEROS);
        JButton boton8 = new JButton("8"); Estilo.aplicarBoton(boton8, Estilo.BOTON_NUMERO, Estilo.TEXTO_BLANCO, Estilo.FUENTE_NUMEROS);
        JButton boton9 = new JButton("9"); Estilo.aplicarBoton(boton9, Estilo.BOTON_NUMERO, Estilo.TEXTO_BLANCO, Estilo.FUENTE_NUMEROS);
        JButton botonDiv = new JButton("/"); Estilo.aplicarBoton(botonDiv, Estilo.BOTON_OPERADOR, Estilo.TEXTO_BLANCO, Estilo.FUENTE_NUMEROS);
        gbc.gridy = 3;
        gbc.gridx = 0; gbc.insets = new Insets(4, 10, 4, 4); add(boton7, gbc);
        gbc.gridx = 1; gbc.insets = new Insets(4, 4, 4, 4);  add(boton8, gbc);
        gbc.gridx = 2; gbc.insets = new Insets(4, 4, 4, 4);  add(boton9, gbc);
        gbc.gridx = 3; gbc.insets = new Insets(4, 4, 4, 10); add(botonDiv, gbc);

        // Fila 6: 4, 5, 6, *
        JButton btn4 = new JButton("4"); Estilo.aplicarBoton(btn4, Estilo.BOTON_NUMERO, Estilo.TEXTO_BLANCO, Estilo.FUENTE_NUMEROS);
        JButton btn5 = new JButton("5"); Estilo.aplicarBoton(btn5, Estilo.BOTON_NUMERO, Estilo.TEXTO_BLANCO, Estilo.FUENTE_NUMEROS);
        JButton btn6 = new JButton("6"); Estilo.aplicarBoton(btn6, Estilo.BOTON_NUMERO, Estilo.TEXTO_BLANCO, Estilo.FUENTE_NUMEROS);
        JButton btnMul = new JButton("*"); Estilo.aplicarBoton(btnMul, Estilo.BOTON_OPERADOR, Estilo.TEXTO_BLANCO, Estilo.FUENTE_NUMEROS);
        gbc.gridy = 4;
        gbc.gridx = 0; gbc.insets = new Insets(4, 10, 4, 4); add(btn4, gbc);
        gbc.gridx = 1; gbc.insets = new Insets(4, 4, 4, 4);  add(btn5, gbc);
        gbc.gridx = 2; gbc.insets = new Insets(4, 4, 4, 4);  add(btn6, gbc);
        gbc.gridx = 3; gbc.insets = new Insets(4, 4, 4, 10); add(btnMul, gbc);

        // Fila 7: 1, 2, 3, -
        JButton btn1 = new JButton("1"); Estilo.aplicarBoton(btn1, Estilo.BOTON_NUMERO, Estilo.TEXTO_BLANCO, Estilo.FUENTE_NUMEROS);
        JButton btn2 = new JButton("2"); Estilo.aplicarBoton(btn2, Estilo.BOTON_NUMERO, Estilo.TEXTO_BLANCO, Estilo.FUENTE_NUMEROS);
        JButton btn3 = new JButton("3"); Estilo.aplicarBoton(btn3, Estilo.BOTON_NUMERO, Estilo.TEXTO_BLANCO, Estilo.FUENTE_NUMEROS);
        JButton btnResta = new JButton("-"); Estilo.aplicarBoton(btnResta, Estilo.BOTON_OPERADOR, Estilo.TEXTO_BLANCO, Estilo.FUENTE_NUMEROS);
        gbc.gridy = 5;
        gbc.gridx = 0; gbc.insets = new Insets(4, 10, 4, 4); add(btn1, gbc);
        gbc.gridx = 1; gbc.insets = new Insets(4, 4, 4, 4);  add(btn2, gbc);
        gbc.gridx = 2; gbc.insets = new Insets(4, 4, 4, 4);  add(btn3, gbc);
        gbc.gridx = 3; gbc.insets = new Insets(4, 4, 4, 10); add(btnResta, gbc);

        // Fila 8: 0, ., π, +
        JButton btn0 = new JButton("0"); Estilo.aplicarBoton(btn0, Estilo.BOTON_NUMERO, Estilo.TEXTO_BLANCO, Estilo.FUENTE_NUMEROS);
        JButton btnPunto = new JButton("."); Estilo.aplicarBoton(btnPunto, Estilo.BOTON_OPERADOR, Estilo.TEXTO_BLANCO, Estilo.FUENTE_NUMEROS);
        JButton btnPi = new JButton("π"); Estilo.aplicarBoton(btnPi, Estilo.BOTON_OPERADOR, Estilo.TEXTO_BLANCO, Estilo.FUENTE_FUNCIONES);
        JButton btnSuma = new JButton("+"); Estilo.aplicarBoton(btnSuma, Estilo.BOTON_OPERADOR, Estilo.TEXTO_BLANCO, Estilo.FUENTE_NUMEROS);
        gbc.gridy = 6;
        gbc.gridx = 0; gbc.insets = new Insets(4, 10, 4, 4); add(btn0, gbc);
        gbc.gridx = 1; gbc.insets = new Insets(4, 4, 4, 4);  add(btnPunto, gbc);
        gbc.gridx = 2; gbc.insets = new Insets(4, 4, 4, 4);  add(btnPi, gbc);
        gbc.gridx = 3; gbc.insets = new Insets(4, 4, 4, 10); add(btnSuma, gbc);

        // Fila 9: (, ), =
        JButton btnParentesisIzq = new JButton("("); Estilo.aplicarBoton(btnParentesisIzq, Estilo.BOTON_OPERADOR, Estilo.TEXTO_BLANCO, Estilo.FUENTE_NUMEROS);
        JButton btnParentesisDer = new JButton(")"); Estilo.aplicarBoton(btnParentesisDer, Estilo.BOTON_OPERADOR, Estilo.TEXTO_BLANCO, Estilo.FUENTE_NUMEROS);
        JButton btnIgual = new JButton("=");         Estilo.aplicarBoton(btnIgual, Estilo.BOTON_IGUAL, Estilo.TEXTO_BLANCO, Estilo.FUENTE_NUMEROS);
        gbc.gridy = 7;
        gbc.gridx = 0; gbc.gridwidth = 1; gbc.insets = new Insets(4, 10, 10, 4); add(btnParentesisIzq, gbc);
        gbc.gridx = 1; gbc.gridwidth = 1; gbc.insets = new Insets(4, 4, 10, 4);  add(btnParentesisDer, gbc);
        gbc.gridx = 2; gbc.gridwidth = 2; gbc.insets = new Insets(4, 4, 10, 10); add(btnIgual, gbc);

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
        btnParentesisDer.addActionListener(e -> agregarTexto(")"));
        btnParentesisIzq.addActionListener(e -> agregarTexto("("));
        botonSin.addActionListener(e -> agregarTexto("sin("));
        botonCos.addActionListener(e -> agregarTexto("cos("));
        botonTan.addActionListener(e -> agregarTexto("tan("));
        btnPunto.addActionListener(e -> agregarTexto("."));

        // Evento del botón C (Limpiar todo)
        botonC.addActionListener(e -> {
            txtOperacion.setText("");
            txtResultado.setText("0");
        });

        //Botón igual
        btnIgual.addActionListener(e -> calcularResultado(botonGrados.isSelected()));

    }
    public static void main(String[] args) {
        Calculadora calculadora = new Calculadora();
        calculadora.setVisible(true);
    }
}
