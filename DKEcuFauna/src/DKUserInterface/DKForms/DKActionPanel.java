package DKUserInterface.DKForms;

import DKUserInterface.DKCustomerController.DKPatButton;
import DKUserInterface.DKCustomerController.DKPatComboBox;
import DKUserInterface.DKCustomerController.DKPatPanel;
import DKUserInterface.DKCustomerController.DKStyles;
import java.util.List;

import java.awt.*;

import DKDataAccess.DKDAO.DKGenoAlimentoDAO;
import DKDataAccess.DKDAO.DKIngestaNativaDAO;

public class DKActionPanel extends DKPatPanel {

    public DKActionPanel() {
        super(20, DKStyles.DKCOLOR_GREEN6);

        DKIngestaNativaDAO ingestaDAO = new DKIngestaNativaDAO();
        DKGenoAlimentoDAO genoAlimentoDAO = new DKGenoAlimentoDAO();

        List<String> ingestaItems;
        List<String> genoAlimentoItems;

        try {
            ingestaItems = ingestaDAO.dkReadAll().stream().map(ingesta -> ingesta.getNombre()).toList();
            genoAlimentoItems = genoAlimentoDAO.dkReadAll().stream().map(genoAlimento -> genoAlimento.getNombre())
                    .toList();
        } catch (Exception e) {
            e.printStackTrace();
            ingestaItems = List.of("Error al cargar");
            genoAlimentoItems = List.of("Error al cargar");
        }

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
        add(new DKPatComboBox(genoAlimentoItems, "GenoAlimento"), gbc);

        gbc.gridx = 1;
        add(buttonAlimentarGA, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new DKPatComboBox(ingestaItems, "IngestaNativa"), gbc);

        gbc.gridx = 1;
        add(buttonAlimentarIN, gbc);
    }
}
