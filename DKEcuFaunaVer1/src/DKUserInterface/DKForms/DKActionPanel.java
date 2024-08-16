package DKUserInterface.DKForms;

import DKUserInterface.DKCustomerController.DKPatButton;
import DKUserInterface.DKCustomerController.DKPatComboBox;
import DKUserInterface.DKCustomerController.DKPatPanel;
import DKUserInterface.DKCustomerController.DKStyles;
import java.util.List;
import javax.swing.JOptionPane;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DKDataAccess.DKDAO.DKGenoAlimentoDAO;
import DKDataAccess.DKDAO.DKHormigaDAO;
import DKDataAccess.DKDAO.DKIngestaNativaDAO;
import DKDataAccess.DKDTO.DKGenoAlimentoDTO;
import DKDataAccess.DKDTO.DKHormigaDTO;
import DKDataAccess.DKDTO.DKIngestaNativaDTO;
import DKDataAccess.DKDataHelper.DKDataHelper;

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
            JOptionPane.showMessageDialog(this, "Por favor, seleccione una hormiga antes de alimentarla.",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Obtener el id del alimento seleccionado y actualizar la hormiga
        if (tipoAlimento.equals("GenoAlimento")) {
            int idGenoAlimento = obtenerIdGenoAlimento(genoAlimentoComboBox.getSelectedItem().toString());
            hormiga.setIdGenoAlimento(idGenoAlimento);
        } else if (tipoAlimento.equals("IngestaNativa")) {
            int idIngestaNativa = obtenerIdIngestaNativa(ingestaNativaComboBox.getSelectedItem().toString());
            hormiga.setIdIngestaNativa(idIngestaNativa);
        }

        // Actualizar la base de datos
        try {
            hormigaDAO.dkUpdate(hormiga);
            hormigueroPanel.actualizarHormigaEnTabla(hormiga); // Opcional: Refrescar la tabla
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al actualizar el alimento de la hormiga", "Error",
                    JOptionPane.ERROR_MESSAGE);
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

}
