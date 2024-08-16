package DKUserInterface.DKForms;

import DKUserInterface.DKCustomerController.DKPatComboBox;
import DKUserInterface.DKCustomerController.DKPatPanel;
import DKUserInterface.DKCustomerController.DKPatButton;
import DKUserInterface.DKCustomerController.DKStyles;
import DKBusinessLogic.DKCrearHormigaBL;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class DKCrearHormigaPanel extends DKPatPanel {

    private JTextField nombreField;
    private DKPatComboBox sexoComboBox;
    private DKPatComboBox provinciaComboBox;
    private DKPatComboBox tipoHormigaComboBox;
    private DKPatComboBox genoAlimentoComboBox;
    private DKPatComboBox ingestaNativaComboBox;
    private DKCrearHormigaBL crearHormigaBL;
    private DKMainPanel mainPanel;

    public DKCrearHormigaPanel(DKMainPanel mainPanel) {
        super(20, DKStyles.DKCOLOR_GREEN5);
        setOpaque(true);
        setBackground(DKStyles.DKCOLOR_GREEN5);
        setBorder(BorderFactory.createEmptyBorder()); // Asegúrate de que no haya bordes
        this.mainPanel = mainPanel;
        crearHormigaBL = new DKCrearHormigaBL();

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;

        initializeComponents(gbc);
    }

    private void initializeComponents(GridBagConstraints gbc) {
        // Campo para Nombre
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        nombreField = new JTextField(20);
        add(nombreField, gbc);

        // ComboBox para Sexo
        sexoComboBox = new DKPatComboBox(crearHormigaBL.obtenerNombresSexo(), "Sexo");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Sexo:"), gbc);
        gbc.gridx = 1;
        add(sexoComboBox, gbc);

        // ComboBox para Provincia
        provinciaComboBox = new DKPatComboBox(crearHormigaBL.obtenerNombresProvincia(), "Provincia");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Provincia:"), gbc);
        gbc.gridx = 1;
        add(provinciaComboBox, gbc);

        // ComboBox para GenoAlimento
        genoAlimentoComboBox = new DKPatComboBox(crearHormigaBL.obtenerNombresGenoAlimento(), "GenoAlimento");
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("GenoAlimento:"), gbc);
        gbc.gridx = 1;
        add(genoAlimentoComboBox, gbc);

        // ComboBox para Ingesta Nativa
        ingestaNativaComboBox = new DKPatComboBox(crearHormigaBL.obtenerNombresIngestaNativa(), "IngestaNativa");
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(new JLabel("Ingesta Nativa:"), gbc);
        gbc.gridx = 1;
        add(ingestaNativaComboBox, gbc);

        // ComboBox para Tipo Hormiga
        // ComboBox para Tipo Hormiga
        String[] tiposHormiga = { "Larva", "Obrera", "Reina", "Soldado", "Guerrero" };
        tipoHormigaComboBox = new DKPatComboBox(Arrays.asList(tiposHormiga), "Tipo Hormiga");
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(new JLabel("Tipo Hormiga:"), gbc);
        gbc.gridx = 1;
        add(tipoHormigaComboBox, gbc);

        // Botón para crear la hormiga
        DKPatButton crearButton = new DKPatButton("Crear Hormiga", false, DKStyles.DKFONT_LANGOSTIONS_SMALL);
        crearButton.setBackground(DKStyles.DKCOLOR_GREEN);
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(crearButton, gbc);

        // Botón para cancelar
        DKPatButton cancelarButton = new DKPatButton("Cancelar", false, DKStyles.DKFONT_LANGOSTIONS_SMALL);
        cancelarButton.setBackground(DKStyles.DKCOLOR_RED);
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(cancelarButton, gbc);

        // Acciones de los botones
        crearButton.addActionListener(e -> {
            crearHormiga();
            resetForm(); // Restablecer el formulario después de crear
        });
        cancelarButton.addActionListener(e -> {
            mainPanel.mostrarHormiguero();
            mainPanel.getHormigueroPanel().cargarHormigas(); // Recarga la tabla al cancelar
            resetForm(); // Restablecer el formulario después de cancelar
        });
    }

    private void crearHormiga() {
        // Validaciones
        if (nombreField.getText().trim().isEmpty() ||
                sexoComboBox.getSelectedIndex() == 0 ||
                provinciaComboBox.getSelectedIndex() == 0 ||
                genoAlimentoComboBox.getSelectedIndex() == 0 ||
                ingestaNativaComboBox.getSelectedIndex() == 0 ||
                tipoHormigaComboBox.getSelectedIndex() == 0) {

            JOptionPane.showMessageDialog(this, "Todos los campos deben estar completos", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        String nombre = nombreField.getText();
        String sexo = sexoComboBox.getSelectedItem().toString();
        String provincia = provinciaComboBox.getSelectedItem().toString();
        String genoAlimento = genoAlimentoComboBox.getSelectedItem().toString();
        String ingestaNativa = ingestaNativaComboBox.getSelectedItem().toString();
        String tipoHormiga = tipoHormigaComboBox.getSelectedItem().toString();

        boolean exito = crearHormigaBL.crearNuevaHormiga(nombre, sexo, genoAlimento, ingestaNativa, provincia,
                tipoHormiga);

        if (exito) {
            JOptionPane.showMessageDialog(this, "Hormiga creada exitosamente", "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);
            mainPanel.mostrarHormiguero(); // Volver a la tabla
            mainPanel.getHormigueroPanel().cargarHormigas(); // Actualizar la tabla
        } else {
            JOptionPane.showMessageDialog(this, "Error al crear la hormiga", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void resetForm() {
        nombreField.setText("");

        // Limpiar los ComboBox antes de agregar elementos
        sexoComboBox.removeAllItems();
        sexoComboBox.addItem("Sexo");
        for (String item : crearHormigaBL.obtenerNombresSexo()) {
            sexoComboBox.addItem(item);
        }
        sexoComboBox.setSelectedIndex(0);

        provinciaComboBox.removeAllItems();
        provinciaComboBox.addItem("Provincia");
        for (String item : crearHormigaBL.obtenerNombresProvincia()) {
            provinciaComboBox.addItem(item);
        }
        provinciaComboBox.setSelectedIndex(0);

        genoAlimentoComboBox.removeAllItems();
        genoAlimentoComboBox.addItem("GenoAlimento");
        for (String item : crearHormigaBL.obtenerNombresGenoAlimento()) {
            genoAlimentoComboBox.addItem(item);
        }
        genoAlimentoComboBox.setSelectedIndex(0);

        ingestaNativaComboBox.removeAllItems();
        ingestaNativaComboBox.addItem("IngestaNativa");
        for (String item : crearHormigaBL.obtenerNombresIngestaNativa()) {
            ingestaNativaComboBox.addItem(item);
        }
        ingestaNativaComboBox.setSelectedIndex(0);

        tipoHormigaComboBox.removeAllItems();
        tipoHormigaComboBox.addItem("Tipo Hormiga");
        for (String item : new String[] { "Larva", "Obrera", "Reina", "Soldado", "Guerrero" }) {
            tipoHormigaComboBox.addItem(item);
        }
        tipoHormigaComboBox.setSelectedIndex(0);
    }

    /*
     * private void resetComboBox(DKPatComboBox comboBox) {
     * comboBox.setSelectedIndex(-1); // Elimina cualquier selección actual
     * comboBox.setSelectedIndex(0); // Selecciona el primer ítem (el nombre
     * principal)
     * comboBox.repaint(); // Forzar la actualización visual
     * }
     */
}
