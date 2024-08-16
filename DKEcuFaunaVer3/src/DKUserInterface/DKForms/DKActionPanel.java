package DKUserInterface.DKForms;

import DKUserInterface.DKCustomerController.DKPatButton;
import DKUserInterface.DKCustomerController.DKPatComboBox;
import DKUserInterface.DKCustomerController.DKPatPanel;
import DKUserInterface.DKCustomerController.DKStyles;
import java.util.List;
import java.awt.*;

import DKBusinessLogic.DKGenoAlimentoBL;
import DKBusinessLogic.DKHormigaBL;
import DKBusinessLogic.DKIngestaNativaBL;
import DKDataAccess.DKDTO.DKHormigaDTO;

public class DKActionPanel extends DKPatPanel {

    private DKHormigaBL hormigaBL;
    private DKGenoAlimentoBL genoAlimentoBL;
    private DKIngestaNativaBL ingestaNativaBL;
    private DKPatComboBox genoAlimentoComboBox;
    private DKPatComboBox ingestaNativaComboBox;
    private DKHormigueroPanel hormigueroPanel;

    public DKActionPanel(DKHormigueroPanel hormigueroPanel) {
        super(20, DKStyles.DKCOLOR_GREEN6);
        this.hormigueroPanel = hormigueroPanel;

        hormigaBL = new DKHormigaBL();
        genoAlimentoBL = new DKGenoAlimentoBL();
        ingestaNativaBL = new DKIngestaNativaBL();

        List<String> ingestaItems = ingestaNativaBL.obtenerNombresIngestaNativa();
        List<String> genoAlimentoItems = genoAlimentoBL.obtenerNombresGenoAlimento();

        genoAlimentoComboBox = new DKPatComboBox(genoAlimentoItems, "GenoAlimento");
        ingestaNativaComboBox = new DKPatComboBox(ingestaItems, "IngestaNativa");

        DKPatButton buttonAlimentarGA = new DKPatButton("Alimentar con Genoalimento", false,
                DKStyles.DKFONT_LANGOSTIONS_SMALL);
        DKPatButton buttonAlimentarIN = new DKPatButton("Alimentar con Ingesta Nativa", false,
                DKStyles.DKFONT_LANGOSTIONS_SMALL);

        buttonAlimentarGA.setBackground(DKStyles.DKCOLOR_GREEN2);
        buttonAlimentarIN.setBackground(DKStyles.DKCOLOR_GREEN2);

        buttonAlimentarGA.addActionListener(e -> alimentarHormiga("GenoAlimento"));
        buttonAlimentarIN.addActionListener(e -> alimentarHormiga("IngestaNativa"));

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(genoAlimentoComboBox, gbc);

        gbc.gridx = 1;
        add(buttonAlimentarGA, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(ingestaNativaComboBox, gbc);

        gbc.gridx = 1;
        add(buttonAlimentarIN, gbc);
    }

    private void alimentarHormiga(String tipoAlimento) {
        DKHormigaDTO hormiga = hormigueroPanel.obtenerHormigaSeleccionada();
        if (hormiga == null) {
            return;
        }

        if (tipoAlimento.equals("GenoAlimento")) {
            int genoAlimentoId = genoAlimentoBL
                    .obtenerIdGenoAlimento(genoAlimentoComboBox.getSelectedItem().toString());
            hormiga.setIdGenoAlimento(genoAlimentoId);
        } else if (tipoAlimento.equals("IngestaNativa")) {
            int ingestaNativaId = ingestaNativaBL
                    .obtenerIdIngestaNativa(ingestaNativaComboBox.getSelectedItem().toString());
            hormiga.setIdIngestaNativa(ingestaNativaId);
        }

        int incremento = obtenerIncrementoPorAlimento(tipoAlimento);
        int porcentajeActual = hormiga.getPorcentajeEvolucion();
        int nuevoPorcentaje = porcentajeActual + incremento;

        boolean evolucionada = false;
        if (nuevoPorcentaje >= 100) {
            nuevoPorcentaje -= 100;
            evolucionada = true;

            String nuevaEvolucion = hormigaBL.determinarEvolucion(hormiga.getTipoHormiga(), tipoAlimento);
            hormiga.setTipoHormiga(nuevaEvolucion);
        }

        hormiga.setPorcentajeEvolucion(nuevoPorcentaje);

        hormigueroPanel.actualizarHormigaEnTabla(hormiga, evolucionada);

        try {
            hormigaBL.guardarHormiga(hormiga);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int obtenerIncrementoPorAlimento(String tipoAlimento) {
        switch (tipoAlimento) {
            case "GenoAlimento":
                return obtenerIncrementoGenoAlimento(genoAlimentoComboBox.getSelectedItem().toString());
            case "IngestaNativa":
                return obtenerIncrementoIngestaNativa(ingestaNativaComboBox.getSelectedItem().toString());
            default:
                return 0;
        }
    }

    private int obtenerIncrementoGenoAlimento(String genoAlimento) {
        switch (genoAlimento) {
            case "X":
                return 10;
            case "XX":
                return 20;
            case "XY":
                return 30;
            default:
                return 0;
        }
    }

    private int obtenerIncrementoIngestaNativa(String ingestaNativa) {
        switch (ingestaNativa) {
            case "Carnivoro":
                return 15;
            case "Herbivoro":
                return 10;
            case "Omnivoro":
                return 20;
            case "Insectivoro":
                return 25;
            default:
                return 0;
        }
    }
}
