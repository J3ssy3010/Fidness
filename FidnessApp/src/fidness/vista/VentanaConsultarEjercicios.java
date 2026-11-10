package fidness.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VentanaConsultarEjercicios extends JFrame {

    private JComboBox<String> cmbGrupoMuscular;
    private JList<String> listaEjercicios;
    private DefaultListModel<String> modeloLista;
    private JTextArea txtInformacion;

    private final String[][] ejercicios = {
        {"Sentadilla", "Piernas", "Ejercicio para fortalecer los músculos de las piernas y glúteos.",
            "1. Coloque los pies al ancho de los hombros.\n"
            + "2. Flexione las rodillas y baje la cadera.\n"
            + "3. Mantenga la espalda recta.\n"
            + "4. Regrese lentamente a la posición inicial."},

        {"Peso muerto", "Piernas", "Ejercicio que trabaja principalmente las piernas y los glúteos.",
            "1. Coloque los pies al ancho de las caderas.\n"
            + "2. Sujete la barra frente a sus piernas.\n"
            + "3. Incline la cadera manteniendo la espalda recta.\n"
            + "4. Levante el peso de manera controlada."},

        {"Extensión de piernas", "Piernas", "Ejercicio enfocado en fortalecer los cuádriceps.",
            "1. Siéntese correctamente en la máquina.\n"
            + "2. Coloque los tobillos detrás del soporte.\n"
            + "3. Extienda las piernas lentamente.\n"
            + "4. Regrese a la posición inicial."},

        {"Remo con barra", "Espalda", "Ejercicio que fortalece los músculos de la espalda.",
            "1. Sujete la barra con ambas manos.\n"
            + "2. Incline ligeramente el torso hacia adelante.\n"
            + "3. Lleve la barra hacia el abdomen.\n"
            + "4. Baje la barra lentamente."},

        {"Jalón al pecho", "Espalda", "Ejercicio para trabajar los músculos de la espalda.",
            "1. Siéntese frente a la máquina.\n"
            + "2. Sujete la barra con ambas manos.\n"
            + "3. Lleve la barra hacia la parte superior del pecho.\n"
            + "4. Regrese lentamente a la posición inicial."},

        {"Press de banca", "Brazos", "Ejercicio que trabaja principalmente el pecho y los tríceps.",
            "1. Acuéstese en el banco.\n"
            + "2. Sujete la barra con ambas manos.\n"
            + "3. Baje la barra de manera controlada.\n"
            + "4. Empuje la barra hacia arriba."},

        {"Curl de bíceps", "Brazos", "Ejercicio para fortalecer los músculos bíceps.",
            "1. Sostenga una mancuerna en cada mano.\n"
            + "2. Mantenga los codos cerca del cuerpo.\n"
            + "3. Flexione los brazos llevando las mancuernas hacia los hombros.\n"
            + "4. Baje lentamente las mancuernas."}
    };

    public VentanaConsultarEjercicios() {

        setTitle("Fidness - Consultar ejercicios");
        setSize(650, 520);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(null);
        panelPrincipal.setBackground(new Color(24, 27, 45));

        JLabel lblTitulo = new JLabel("CONSULTAR EJERCICIOS");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 25));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setBounds(150, 20, 400, 35);
        panelPrincipal.add(lblTitulo);

        JLabel lblGrupo = new JLabel("Grupo muscular:");
        lblGrupo.setFont(new Font("Arial", Font.BOLD, 14));
        lblGrupo.setForeground(Color.WHITE);
        lblGrupo.setBounds(40, 80, 150, 25);
        panelPrincipal.add(lblGrupo);

        cmbGrupoMuscular = new JComboBox<>(
                new String[]{"Todos", "Piernas", "Espalda", "Brazos"}
        );
        cmbGrupoMuscular.setBounds(180, 80, 200, 30);
        panelPrincipal.add(cmbGrupoMuscular);

        JLabel lblEjercicios = new JLabel("Lista de ejercicios");
        lblEjercicios.setFont(new Font("Arial", Font.BOLD, 14));
        lblEjercicios.setForeground(Color.WHITE);
        lblEjercicios.setBounds(40, 130, 200, 25);
        panelPrincipal.add(lblEjercicios);

        modeloLista = new DefaultListModel<>();
        listaEjercicios = new JList<>(modeloLista);
        listaEjercicios.setFont(new Font("Arial", Font.PLAIN, 14));
        listaEjercicios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollLista = new JScrollPane(listaEjercicios);
        scrollLista.setBounds(40, 160, 250, 220);
        panelPrincipal.add(scrollLista);

        JLabel lblInformacion = new JLabel("Información del ejercicio");
        lblInformacion.setFont(new Font("Arial", Font.BOLD, 14));
        lblInformacion.setForeground(Color.WHITE);
        lblInformacion.setBounds(320, 130, 250, 25);
        panelPrincipal.add(lblInformacion);

        txtInformacion = new JTextArea();
        txtInformacion.setFont(new Font("Arial", Font.PLAIN, 13));
        txtInformacion.setLineWrap(true);
        txtInformacion.setWrapStyleWord(true);
        txtInformacion.setEditable(false);
        txtInformacion.setText("Seleccione un ejercicio para consultar su información e instrucciones.");

        JScrollPane scrollInformacion = new JScrollPane(txtInformacion);
        scrollInformacion.setBounds(320, 160, 280, 220);
        panelPrincipal.add(scrollInformacion);

        JButton btnVolver = new JButton("VOLVER");
        btnVolver.setBounds(250, 420, 150, 35);
        btnVolver.setBackground(new Color(128, 83, 220));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFont(new Font("Arial", Font.BOLD, 13));
        btnVolver.setFocusPainted(false);
        panelPrincipal.add(btnVolver);

        cmbGrupoMuscular.addActionListener(e -> cargarEjercicios());

        listaEjercicios.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                mostrarInformacion();
            }
        });

        btnVolver.addActionListener(e -> dispose());

        cargarEjercicios();

        add(panelPrincipal);
    }

    private void cargarEjercicios() {

        modeloLista.clear();
        txtInformacion.setText("Seleccione un ejercicio para consultar su información e instrucciones.");

        String grupoSeleccionado =
                cmbGrupoMuscular.getSelectedItem().toString();

        for (String[] ejercicio : ejercicios) {

            if (grupoSeleccionado.equals("Todos")
                    || ejercicio[1].equals(grupoSeleccionado)) {

                modeloLista.addElement(ejercicio[0]);
            }
        }
    }

    private void mostrarInformacion() {

        String seleccionado = listaEjercicios.getSelectedValue();

        if (seleccionado == null) {
            return;
        }

        for (String[] ejercicio : ejercicios) {

            if (ejercicio[0].equals(seleccionado)) {

                txtInformacion.setText(
                        "EJERCICIO: " + ejercicio[0]
                        + "\n\nGRUPO MUSCULAR: " + ejercicio[1]
                        + "\n\nDESCRIPCIÓN:\n" + ejercicio[2]
                        + "\n\nINSTRUCCIONES:\n" + ejercicio[3]
                );

                return;
            }
        }
    }
}