package DKUserInterface.DKForms;

import DKUserInterface.DKCustomerController.DKPatButton;
import DKUserInterface.DKCustomerController.DKPatComboBox;
import DKUserInterface.DKCustomerController.DKPatPanel;
import DKUserInterface.DKCustomerController.DKStyles;
import java.util.List;
import java.awt.*;

import DKDataAccess.DKDAO.DKGenoAlimentoDAO;
import DKDataAccess.DKDAO.DKHormigaDAO;
import DKDataAccess.DKDAO.DKIngestaNativaDAO;
import DKDataAccess.DKDTO.DKGenoAlimentoDTO;
import DKDataAccess.DKDTO.DKHormigaDTO;
import DKDataAccess.DKDTO.DKIngestaNativaDTO;

public class DKActionPanel extends DKPatPanel {

    private DKHormigaDAO hormigaDAO;
    private DKPatComboBox genoAlimentoComboBox;
    private DKPatComboBox ingestaNativaComboBox;
    private DKHormigueroPanel hormigueroPanel;

    public DKActionPanel(DKHormigueroPanel hormigueroPanel) {
        super(20, DKStyles.DKCOLOR_GREEN6);
        this.hormigueroPanel = hormigueroPanel;

        // Inicializar hormigaDAO asegurando la conexión
        hormigaDAO = new DKHormigaDAO();

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

        // Actualizar el tipo de alimento en el objeto hormiga
        if (tipoAlimento.equals("GenoAlimento")) {
            int genoAlimentoId = obtenerIdGenoAlimento(genoAlimentoComboBox.getSelectedItem().toString());
            hormiga.setIdGenoAlimento(genoAlimentoId);
        } else if (tipoAlimento.equals("IngestaNativa")) {
            int ingestaNativaId = obtenerIdIngestaNativa(ingestaNativaComboBox.getSelectedItem().toString());
            hormiga.setIdIngestaNativa(ingestaNativaId);
        }

        // Establecer el nuevo porcentaje en el objeto hormiga
        int incremento = obtenerIncrementoPorAlimento(tipoAlimento);
        int porcentajeActual = hormiga.getPorcentajeEvolucion();
        int nuevoPorcentaje = porcentajeActual + incremento;

        boolean evolucionada = false;
        if (nuevoPorcentaje >= 100) {
            nuevoPorcentaje -= 100; // Ajusta con el porcentaje restante
            evolucionada = true;

            // Determinar la nueva evolución de la hormiga
            String nuevaEvolucion = hormigaDAO.determinarEvolucion(hormiga.getTipoHormiga(), tipoAlimento);
            hormiga.setTipoHormiga(nuevaEvolucion);
        }

        hormiga.setPorcentajeEvolucion(nuevoPorcentaje);

        // Actualizar la tabla y manejar la animación de la barra de progreso
        hormigueroPanel.actualizarHormigaEnTabla(hormiga, evolucionada);

        try {
            hormigaDAO.dkUpdate(hormiga);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int obtenerIdGenoAlimento(String nombreGenoAlimento) {
        DKGenoAlimentoDAO genoAlimentoDAO = new DKGenoAlimentoDAO();
        try {
            List<DKGenoAlimentoDTO> genoAlimentos = genoAlimentoDAO.dkReadAll();
            for (DKGenoAlimentoDTO genoAlimento : genoAlimentos) {
                if (genoAlimento.getNombre().equals(nombreGenoAlimento)) {
                    return genoAlimento.getIdCatalogoAl();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1; // Valor predeterminado en caso de no encontrar el alimento
    }

    private int obtenerIdIngestaNativa(String nombreIngestaNativa) {
        DKIngestaNativaDAO ingestaNativaDAO = new DKIngestaNativaDAO();
        try {
            List<DKIngestaNativaDTO> ingestaNativas = ingestaNativaDAO.dkReadAll();
            for (DKIngestaNativaDTO ingestaNativa : ingestaNativas) {
                if (ingestaNativa.getNombre().equals(nombreIngestaNativa)) {
                    return ingestaNativa.getIdCatalogoAl();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1; // Valor predeterminado en caso de no encontrar el alimento
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
