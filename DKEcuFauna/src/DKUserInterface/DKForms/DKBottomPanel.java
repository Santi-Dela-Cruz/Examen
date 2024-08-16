package DKUserInterface.DKForms;

import DKUserInterface.DKCustomerController.DKPatButton;
import DKUserInterface.DKCustomerController.DKPatPanel;
import DKUserInterface.DKCustomerController.DKStyles;

import java.awt.*;

public class DKBottomPanel extends DKPatPanel {

    public DKBottomPanel() {
        super(15, DKStyles.DKCOLOR_GREEN8);
        DKPatButton buttonEliminar = new DKPatButton("Eliminar", false, DKStyles.DKFONT_LANGOSTIONS_SMALL);
        DKPatButton buttonGuardar = new DKPatButton("Guardar", false, DKStyles.DKFONT_LANGOSTIONS_SMALL);
        buttonEliminar.setBackground(DKStyles.DKCOLOR_GREEN2);
        buttonGuardar.setBackground(DKStyles.DKCOLOR_GREEN2);

        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Contenido del panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(buttonEliminar, gbc);

        gbc.gridx = 1;
        add(buttonGuardar, gbc);
    }
}
