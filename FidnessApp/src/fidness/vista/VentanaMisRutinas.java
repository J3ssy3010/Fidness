package fidness.vista;

import fidness.modelo.GestorRutinas;
import fidness.modelo.Rutina;
import fidness.modelo.ExportadorRutina;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class VentanaMisRutinas extends JFrame {

    private GestorRutinas gestorRutinas;
    private JList<Rutina> listaRutinas;
    private DefaultListModel<Rutina> modeloLista;
    private JTextArea txtInformacion;

    public VentanaMisRutinas(GestorRutinas gestorRutinas) {

        this.gestorRutinas = gestorRutinas;

        setTitle("Fidness - Mis rutinas");
        setSize(750, 530);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(24, 27, 45));

        JLabel lblTitulo = new JLabel("MIS RUTINAS");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 25));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setBounds(280, 20, 250, 35);
        panel.add(lblTitulo);

        JLabel lblLista = new JLabel("Rutinas guardadas");
        lblLista.setFont(new Font("Arial", Font.BOLD, 14));
        lblLista.setForeground(Color.WHITE);
        lblLista.setBounds(40, 85, 250, 25);
        panel.add(lblLista);

        modeloLista = new DefaultListModel<>();
        listaRutinas = new JList<>(modeloLista);
        listaRutinas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaRutinas.setFont(new Font("Arial", Font.PLAIN, 14));

        listaRutinas.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(
                    JList<?> list, Object value, int index,
                    boolean isSelected, boolean cellHasFocus) {

                super.getListCellRendererComponent(
                        list, value, index, isSelected, cellHasFocus
                );

                if (value instanceof Rutina) {
                    setText(((Rutina) value).getNombre());
                }

                return this;
            }
        });

        JScrollPane scrollLista = new JScrollPane(listaRutinas);
        scrollLista.setBounds(40, 115, 280, 260);
        panel.add(scrollLista);

        JLabel lblInformacion = new JLabel("Información de la rutina");
        lblInformacion.setFont(new Font("Arial", Font.BOLD, 14));
        lblInformacion.setForeground(Color.WHITE);
        lblInformacion.setBounds(360, 85, 280, 25);
        panel.add(lblInformacion);

        txtInformacion = new JTextArea();
        txtInformacion.setFont(new Font("Arial", Font.PLAIN, 13));
        txtInformacion.setLineWrap(true);
        txtInformacion.setWrapStyleWord(true);
        txtInformacion.setEditable(false);
        txtInformacion.setText(
                "Seleccione una rutina para consultar sus ejercicios."
        );

        JScrollPane scrollInformacion = new JScrollPane(txtInformacion);
        scrollInformacion.setBounds(360, 115, 340, 260);
        panel.add(scrollInformacion);

        JButton btnEliminar = crearBoton("ELIMINAR RUTINA", 40, 410);
        JButton btnExportar = crearBoton("EXPORTAR RUTINA", 220, 410);
        JButton btnVolver = crearBoton("VOLVER", 490, 410);

        panel.add(btnEliminar);
        panel.add(btnExportar);
        panel.add(btnVolver);

        listaRutinas.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                mostrarInformacion();
            }
        });

        btnEliminar.addActionListener(e -> eliminarRutina());
        btnExportar.addActionListener(e -> exportarRutina());
        btnVolver.addActionListener(e -> dispose());

        cargarRutinas();

        add(panel);
    }

    private JButton crearBoton(String texto, int x, int y) {

        JButton boton = new JButton(texto);
        boton.setBounds(x, y, 170, 40);
        boton.setBackground(new Color(130, 90, 220));
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Arial", Font.BOLD, 12));
        boton.setFocusPainted(false);

        return boton;
    }

    private void cargarRutinas() {

        modeloLista.clear();

        for (Rutina rutina : gestorRutinas.listarRutinas()) {
            modeloLista.addElement(rutina);
        }

        txtInformacion.setText(
                "Seleccione una rutina para consultar sus ejercicios."
        );
    }

    private void mostrarInformacion() {

        Rutina seleccionada = listaRutinas.getSelectedValue();

        if (seleccionada == null) {
            return;
        }

        txtInformacion.setText(seleccionada.mostrarRutina());
    }

    private void eliminarRutina() {

        Rutina seleccionada = listaRutinas.getSelectedValue();

        if (seleccionada == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "Seleccione una rutina para eliminar."
            );
            return;
        }

        int respuesta = JOptionPane.showConfirmDialog(
                this,
                "¿Está seguro de que desea eliminar la rutina "
                + seleccionada.getNombre() + "?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION
        );

        if (respuesta == JOptionPane.YES_OPTION) {

            gestorRutinas.eliminarRutina(seleccionada.getIdRutina());

            cargarRutinas();

            JOptionPane.showMessageDialog(
                    this,
                    "Rutina eliminada correctamente."
            );
        }
    }

    private void exportarRutina() {

        Rutina seleccionada = listaRutinas.getSelectedValue();

        if (seleccionada == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "Seleccione una rutina para exportar."
            );
            return;
        }

        JFileChooser selector = new JFileChooser();
        selector.setDialogTitle("Guardar rutina");
        selector.setSelectedFile(
                new File(seleccionada.getNombre() + ".txt")
        );

        int resultado = selector.showSaveDialog(this);

        if (resultado == JFileChooser.APPROVE_OPTION) {

            File archivo = selector.getSelectedFile();

            String ruta = archivo.getAbsolutePath();

            if (!ruta.toLowerCase().endsWith(".txt")) {
                ruta += ".txt";
            }

            ExportadorRutina exportador = new ExportadorRutina();

            boolean exportado = exportador.exportar(
                    seleccionada,
                    ruta
            );

            if (exportado) {
                JOptionPane.showMessageDialog(
                        this,
                        "Rutina exportada correctamente."
                );
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "No fue posible exportar la rutina.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }
}