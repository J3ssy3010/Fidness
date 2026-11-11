package fidness.vista;

import fidness.modelo.Usuario;
import javax.swing.*;
import java.awt.*;

public class VentanaLogin extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtContrasena;
    private JButton btnIngresar;

    private Usuario usuarioDemo;

    public VentanaLogin() {

        usuarioDemo = new Usuario(1, "admin", "1234");

        setTitle("Fidness - Inicio de sesión");
        setSize(420, 370);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(24, 27, 45));

        JLabel lblTitulo = new JLabel("FIDNESS");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 32));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setBounds(125, 25, 200, 45);
        panel.add(lblTitulo);

        JLabel lblSubtitulo = new JLabel(
                "Tu entrenamiento comienza aquí"
        );
        lblSubtitulo.setFont(new Font("Arial", Font.PLAIN, 13));
        lblSubtitulo.setForeground(Color.LIGHT_GRAY);
        lblSubtitulo.setBounds(100, 75, 250, 25);
        panel.add(lblSubtitulo);

        JLabel lblUsuario = new JLabel("Nombre de usuario");
        lblUsuario.setForeground(Color.WHITE);
        lblUsuario.setFont(new Font("Arial", Font.BOLD, 13));
        lblUsuario.setBounds(65, 120, 200, 25);
        panel.add(lblUsuario);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(65, 150, 280, 35);
        txtUsuario.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(txtUsuario);

        JLabel lblContrasena = new JLabel("Contraseña");
        lblContrasena.setForeground(Color.WHITE);
        lblContrasena.setFont(new Font("Arial", Font.BOLD, 13));
        lblContrasena.setBounds(65, 195, 200, 25);
        panel.add(lblContrasena);

        txtContrasena = new JPasswordField();
        txtContrasena.setBounds(65, 225, 280, 35);
        txtContrasena.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(txtContrasena);

        btnIngresar = new JButton("INGRESAR");
        btnIngresar.setBounds(135, 285, 150, 38);
        btnIngresar.setBackground(new Color(130, 90, 220));
        btnIngresar.setForeground(Color.WHITE);
        btnIngresar.setFont(new Font("Arial", Font.BOLD, 13));
        btnIngresar.setFocusPainted(false);
        panel.add(btnIngresar);

        btnIngresar.addActionListener(e -> iniciarSesion());
        txtContrasena.addActionListener(e -> iniciarSesion());

        add(panel);
    }

    private void iniciarSesion() {

        String usuario = txtUsuario.getText().trim();

        String contrasena =
                new String(txtContrasena.getPassword());

        if (usuario.isEmpty() || contrasena.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe ingresar el usuario y la contraseña.",
                    "Campos obligatorios",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        if (usuarioDemo.iniciarSesion(usuario, contrasena)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Bienvenido a Fidness, " + usuario,
                    "Inicio de sesión",
                    JOptionPane.INFORMATION_MESSAGE
            );

            VentanaMenuPrincipal menu =
                    new VentanaMenuPrincipal();

            menu.setVisible(true);

            this.dispose();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "El usuario o la contraseña son incorrectos.",
                    "Error de autenticación",
                    JOptionPane.ERROR_MESSAGE
            );

            txtContrasena.setText("");
            txtUsuario.requestFocus();
        }
    }
}