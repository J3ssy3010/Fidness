package fidness.vista;

import javax.swing.*;
import java.awt.*;

public class VentanaMenuPrincipal extends JFrame {

    private JButton btnConsultarEjercicios;
    private JButton btnCrearRutina;
    private JButton btnMisRutinas;
    private JButton btnCerrarSesion;

    public VentanaMenuPrincipal() {

        setTitle("Fidness - Menú principal");
        setSize(500, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(24, 27, 45));

        JLabel lblTitulo = new JLabel("FIDNESS");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 32));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setBounds(165, 25, 200, 45);
        panel.add(lblTitulo);

        JLabel lblBienvenida = new JLabel("¡Bienvenido a tu espacio de entrenamiento!");
        lblBienvenida.setFont(new Font("Arial", Font.PLAIN, 14));
        lblBienvenida.setForeground(Color.LIGHT_GRAY);
        lblBienvenida.setBounds(90, 75, 330, 25);
        panel.add(lblBienvenida);

        btnConsultarEjercicios = crearBoton("CONSULTAR EJERCICIOS", 120);
        btnCrearRutina = crearBoton("CREAR RUTINA", 185);
        btnMisRutinas = crearBoton("MIS RUTINAS", 250);
        btnCerrarSesion = crearBoton("CERRAR SESIÓN", 325);

        panel.add(btnConsultarEjercicios);
        panel.add(btnCrearRutina);
        panel.add(btnMisRutinas);
        panel.add(btnCerrarSesion);

        // Abrir ventana de consulta de ejercicios
        btnConsultarEjercicios.addActionListener(e -> {
            VentanaConsultarEjercicios ventana =
                    new VentanaConsultarEjercicios();

            ventana.setVisible(true);
        });

        btnCrearRutina.addActionListener(e ->
                JOptionPane.showMessageDialog(this,
                        "Módulo de creación de rutinas en desarrollo."));

        btnMisRutinas.addActionListener(e ->
                JOptionPane.showMessageDialog(this,
                        "Módulo de mis rutinas en desarrollo."));

        btnCerrarSesion.addActionListener(e -> {
            dispose();
            new VentanaLogin().setVisible(true);
        });

        add(panel);
    }

    private JButton crearBoton(String texto, int posicionY) {

        JButton boton = new JButton(texto);

        boton.setBounds(115, posicionY, 270, 42);
        boton.setBackground(new Color(130, 90, 220));
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Arial", Font.BOLD, 13));
        boton.setFocusPainted(false);

        return boton;
    }
}