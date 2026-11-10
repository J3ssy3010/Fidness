package fidness.vista;

import fidness.modelo.CatalogoEjercicios;
import fidness.modelo.Ejercicio;
import fidness.modelo.GestorRutinas;
import fidness.modelo.Rutina;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VentanaCrearRutina extends JFrame {

    private JTextField txtNombreRutina;
    private JComboBox<String> cmbGrupoMuscular;
    private JList<Ejercicio> listaDisponibles;
    private JList<Ejercicio> listaSeleccionados;

    private DefaultListModel<Ejercicio> modeloDisponibles;
    private DefaultListModel<Ejercicio> modeloSeleccionados;

    private GestorRutinas gestorRutinas;
    private List<Ejercicio> ejercicios;

    public VentanaCrearRutina(GestorRutinas gestorRutinas) {

        this.gestorRutinas = gestorRutinas;
        this.ejercicios = CatalogoEjercicios.obtenerEjercicios();

        setTitle("Fidness - Crear rutina");
        setSize(750, 570);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(24, 27, 45));

        JLabel lblTitulo = new JLabel("CREAR MI RUTINA");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 25));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setBounds(250, 20, 300, 35);
        panel.add(lblTitulo);

        JLabel lblNombre = new JLabel("Nombre de la rutina:");
        lblNombre.setForeground(Color.WHITE);
        lblNombre.setFont(new Font("Arial", Font.BOLD, 14));
        lblNombre.setBounds(40, 75, 170, 25);
        panel.add(lblNombre);

        txtNombreRutina = new JTextField();
        txtNombreRutina.setBounds(210, 75, 450, 30);
        panel.add(txtNombreRutina);

        JLabel lblGrupo = new JLabel("Grupo muscular:");
        lblGrupo.setForeground(Color.WHITE);
        lblGrupo.setFont(new Font("Arial", Font.BOLD, 14));
        lblGrupo.setBounds(40, 120, 150, 25);
        panel.add(lblGrupo);

        cmbGrupoMuscular = new JComboBox<>(
                new String[]{"Todos", "Piernas", "Espalda", "Brazos"}
        );
        cmbGrupoMuscular.setBounds(210, 120, 200, 30);
        panel.add(cmbGrupoMuscular);

        JLabel lblDisponibles = new JLabel("Ejercicios disponibles");
        lblDisponibles.setForeground(Color.WHITE);
        lblDisponibles.setFont(new Font("Arial", Font.BOLD, 14));
        lblDisponibles.setBounds(40, 170, 250, 25);
        panel.add(lblDisponibles);

        modeloDisponibles = new DefaultListModel<>();
        listaDisponibles = new JList<>(modeloDisponibles);
        listaDisponibles.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaDisponibles.setFont(new Font("Arial", Font.PLAIN, 13));
        configurarLista(listaDisponibles);

        JScrollPane scrollDisponibles = new JScrollPane(listaDisponibles);
        scrollDisponibles.setBounds(40, 200, 280, 210);
        panel.add(scrollDisponibles);

        JLabel lblSeleccionados = new JLabel("Ejercicios de mi rutina");
        lblSeleccionados.setForeground(Color.WHITE);
        lblSeleccionados.setFont(new Font("Arial", Font.BOLD, 14));
        lblSeleccionados.setBounds(430, 170, 250, 25);
        panel.add(lblSeleccionados);

        modeloSeleccionados = new DefaultListModel<>();
        listaSeleccionados = new JList<>(modeloSeleccionados);
        listaSeleccionados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaSeleccionados.setFont(new Font("Arial", Font.PLAIN, 13));
        configurarLista(listaSeleccionados);

        JScrollPane scrollSeleccionados = new JScrollPane(listaSeleccionados);
        scrollSeleccionados.setBounds(430, 200, 280, 210);
        panel.add(scrollSeleccionados);

        JButton btnAgregar = crearBoton("AGREGAR →", 340, 250);
        JButton btnEliminar = crearBoton("← ELIMINAR", 340, 310);
        JButton btnGuardar = crearBoton("GUARDAR RUTINA", 180, 450);
        JButton btnCancelar = crearBoton("CANCELAR", 420, 450);

        panel.add(btnAgregar);
        panel.add(btnEliminar);
        panel.add(btnGuardar);
        panel.add(btnCancelar);

        cmbGrupoMuscular.addActionListener(e -> cargarEjercicios());

        btnAgregar.addActionListener(e -> agregarEjercicio());

        btnEliminar.addActionListener(e -> eliminarEjercicio());

        btnGuardar.addActionListener(e -> guardarRutina());

        btnCancelar.addActionListener(e -> dispose());

        cargarEjercicios();

        add(panel);
    }

    private void configurarLista(JList<Ejercicio> lista) {

        lista.setCellRenderer(new DefaultListCellRenderer() {

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
    }

    private JButton crearBoton(String texto, int x, int y) {

        JButton boton = new JButton(texto);
        boton.setBounds(x, y, 150, 38);
        boton.setBackground(new Color(130, 90, 220));
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Arial", Font.BOLD, 12));
        boton.setFocusPainted(false);

        return boton;
    }

    private void cargarEjercicios() {

        modeloDisponibles.clear();

        String grupo = cmbGrupoMuscular.getSelectedItem().toString();

        for (Ejercicio ejercicio : ejercicios) {

            if (grupo.equals("Todos")
                    || ejercicio.getGrupoMuscular().equals(grupo)) {

                modeloDisponibles.addElement(ejercicio);
            }
        }
    }

    private void agregarEjercicio() {

        Ejercicio seleccionado = listaDisponibles.getSelectedValue();

        if (seleccionado == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "Seleccione un ejercicio para agregar."
            );
            return;
        }

        if (modeloSeleccionados.contains(seleccionado)) {
            JOptionPane.showMessageDialog(
                    this,
                    "Este ejercicio ya está agregado a la rutina."
            );
            return;
        }

        modeloSeleccionados.addElement(seleccionado);
    }

    private void eliminarEjercicio() {

        Ejercicio seleccionado = listaSeleccionados.getSelectedValue();

        if (seleccionado == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "Seleccione un ejercicio para eliminar."
            );
            return;
        }

        modeloSeleccionados.removeElement(seleccionado);
    }

    private void guardarRutina() {

        String nombre = txtNombreRutina.getText().trim();

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Debe ingresar el nombre de la rutina."
            );
            return;
        }

        if (modeloSeleccionados.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Debe agregar al menos un ejercicio."
            );
            return;
        }

        Rutina rutina = gestorRutinas.crearRutina(nombre);

        for (int i = 0; i < modeloSeleccionados.size(); i++) {
            rutina.agregarEjercicio(modeloSeleccionados.getElementAt(i));
        }

        gestorRutinas.guardarRutina(rutina);

        JOptionPane.showMessageDialog(
                this,
                "¡Rutina guardada correctamente!",
                "Fidness",
                JOptionPane.INFORMATION_MESSAGE
        );

        dispose();
    }
}