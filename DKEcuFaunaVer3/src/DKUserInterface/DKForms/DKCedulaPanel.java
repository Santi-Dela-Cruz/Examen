package DKUserInterface.DKForms;

import DKUserInterface.DKCustomerController.DKPatPanel;
import DKUserInterface.DKCustomerController.DKPatTextBox;
import DKUserInterface.DKCustomerController.DKStyles;
import java.awt.*;

import javax.swing.JLabel;

public class DKCedulaPanel extends DKPatPanel {

    public String dkNombre = "Santiado De La Cruz";
    public String dkCedula = "172795491-7";

    public DKCedulaPanel() {
        // Configuración del panel con bordes redondeados y fondo transparente
        super(20, DKStyles.DKCOLOR_GREEN7); // Radio de 20px, azul con transparencia

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10); // Aumentar márgenes

        // Alineación y ajustes para el campo "Cedula"
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel cedulaLabel = new JLabel("Cedula del alumno:");
        cedulaLabel.setFont(DKStyles.DKFONT_LANGOSTIONS_SMALL); // Establecer fuente personalizada
        add(cedulaLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 1.0;
        DKPatTextBox cedulaField = new DKPatTextBox();
        cedulaField.setFont(DKStyles.DKFONT_LANGOSTIONS_SMALL); // Establecer fuente personalizada
        cedulaField.setText(dkCedula); // Inicializar con el valor de la cédula
        cedulaField.setEditable(false); // Deshabilitar el campo de edición
        cedulaField.setPreferredSize(new Dimension(200, 25)); // Ajustar tamaño
        add(cedulaField, gbc);

        // Alineación y ajustes para el campo "Nombres"
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel nombresLabel = new JLabel("Nombres completos del alumno:");
        nombresLabel.setFont(DKStyles.DKFONT_LANGOSTIONS_SMALL); // Establecer fuente personalizada
        add(nombresLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 1.0;
        DKPatTextBox nombresField = new DKPatTextBox();
        nombresField.setFont(DKStyles.DKFONT_LANGOSTIONS_SMALL); // Establecer fuente personalizada
        nombresField.setText(dkNombre); // Inicializar con el valor del nombre
        nombresField.setEditable(false); // Deshabilitar el campo de edición
        nombresField.setPreferredSize(new Dimension(200, 25)); // Ajustar tamaño
        add(nombresField, gbc);
    }
}
