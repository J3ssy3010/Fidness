package fidness.vista;

import fidness.modelo.CatalogoEjercicios;
import fidness.modelo.Ejercicio;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VentanaConsultarEjercicios extends JFrame {

    private JComboBox<String> cmbGrupoMuscular;
    private JList<Ejercicio> listaEjercicios;
    private DefaultListModel<Ejercicio> modeloLista;
    private JTextArea txtInformacion;

    private List<Ejercicio> ejercicios;

    public VentanaConsultarEjercicios() {

        ejercicios = CatalogoEjercicios.obtenerEjercicios();

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

        listaEjercicios.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(
                    JList<?> list, Object value, int index,
                    boolean isSelected, boolean cellHasFocus) {

                super.getListCellRendererComponent(
                        list, value, index, isSelected, cellHasFocus
                );

                if (value instanceof Ejercicio) {
                    setText(((Ejercicio) value).getNombre());
                }

                return this;
            }
        });

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
        txtInformacion.setText(
                "Seleccione un ejercicio para consultar su información e instrucciones."
        );

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

        txtInformacion.setText(
                "Seleccione un ejercicio para consultar su información e instrucciones."
        );

        String grupoSeleccionado =
                cmbGrupoMuscular.getSelectedItem().toString();

        for (Ejercicio ejercicio : ejercicios) {

            if (grupoSeleccionado.equals("Todos")
                    || ejercicio.getGrupoMuscular().equals(grupoSeleccionado)) {

                modeloLista.addElement(ejercicio);
            }
        }
    }

    private void mostrarInformacion() {

        Ejercicio seleccionado = listaEjercicios.getSelectedValue();

        if (seleccionado == null) {
            return;
        }

        txtInformacion.setText(
                "EJERCICIO: " + seleccionado.getNombre()
                + "\n\nGRUPO MUSCULAR: " + seleccionado.getGrupoMuscular()
                + "\n\nDESCRIPCIÓN:\n" + seleccionado.getDescripcion()
                + "\n\nINSTRUCCIONES:\n" + seleccionado.mostrarInstrucciones()
        );
    }
}