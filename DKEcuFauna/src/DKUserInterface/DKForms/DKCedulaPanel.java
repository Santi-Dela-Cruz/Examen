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
        super(20, DKStyles.DKCOLOR_GREEN7);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel cedulaLabel = new JLabel("Cedula del alumno:");
        cedulaLabel.setFont(DKStyles.DKFONT_LANGOSTIONS_SMALL);
        add(cedulaLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 1.0;
        DKPatTextBox cedulaField = new DKPatTextBox();
        cedulaField.setFont(DKStyles.DKFONT_LANGOSTIONS_SMALL);
        cedulaField.setText(dkCedula);
        cedulaField.setEditable(false);
        cedulaField.setPreferredSize(new Dimension(200, 25));
        add(cedulaField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel nombresLabel = new JLabel("Nombres completos del alumno:");
        nombresLabel.setFont(DKStyles.DKFONT_LANGOSTIONS_SMALL);
        add(nombresLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 1.0;
        DKPatTextBox nombresField = new DKPatTextBox();
        nombresField.setFont(DKStyles.DKFONT_LANGOSTIONS_SMALL);
        nombresField.setText(dkNombre);
        nombresField.setEditable(false);
        nombresField.setPreferredSize(new Dimension(200, 25));
        add(nombresField, gbc);
    }
}
