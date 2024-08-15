package DKUserInterface.DKForms;

import DKUserInterface.DKCustomerController.DKPatButton;
import DKUserInterface.DKCustomerController.DKPatComboBox;
import DKUserInterface.DKCustomerController.DKPatPanel;
import DKUserInterface.DKCustomerController.DKStyles;

import java.awt.*;

public class DKActionPanel extends DKPatPanel {

    public DKActionPanel() {
        // Configuración del panel con bordes redondeados y fondo transparente
        super(20, DKStyles.DKCOLOR_GREEN6); // Radio de 20px, verde con transparencia

        // Creas un boton con el CustomerStyle -> Este conserva las propiedades de los
        // botones
        DKPatButton buttonAlimentarGA = new DKPatButton("Alimentar con Genoalimento", false,
                DKStyles.DKFONT_LANGOSTIONS_SMALL);
        DKPatButton buttonAlimentarIN = new DKPatButton("Alimentar con Ingesta Nativa", false,
                DKStyles.DKFONT_LANGOSTIONS_SMALL);
        buttonAlimentarGA.setBackground(DKStyles.DKCOLOR_GREEN2);
        buttonAlimentarIN.setBackground(DKStyles.DKCOLOR_GREEN2);

        // setBackground, setForeground --> Son propiedades de los botones

        // Para cambiar el color de un boton [nombre del
        // Boton].setBackground([Introducir el color])
        // Para cambiar el color del nombre del boton [nombre del
        // Boton].setForeground([Introducir el color])

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Contenido del panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new DKPatComboBox(new String[] { "GenoAlimento" }), gbc);

        gbc.gridx = 1;
        add(buttonAlimentarGA, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new DKPatComboBox(new String[] { "IngestaNativa" }), gbc);

        gbc.gridx = 1;
        add(buttonAlimentarIN, gbc);
    }
}
