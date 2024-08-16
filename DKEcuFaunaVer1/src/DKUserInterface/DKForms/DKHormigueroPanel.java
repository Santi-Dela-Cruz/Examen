package DKUserInterface.DKForms;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import DKUserInterface.DKCustomerController.DKPatButton;
import DKUserInterface.DKCustomerController.DKPatPanel;
import DKUserInterface.DKCustomerController.DKStyles;
import DKDataAccess.DKDAO.DKHormigaDAO;
import DKDataAccess.DKDTO.DKHormigaDTO;
import java.awt.*;
import java.util.List;

public class DKHormigueroPanel extends DKPatPanel {

    private JTable tablaHormigas;
    private DKHormigaDAO hormigaDAO;

    public DKHormigueroPanel() {
        super(20, DKStyles.DKCOLOR_GREEN5); // Mantiene el fondo del panel principal
        hormigaDAO = new DKHormigaDAO();

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.weightx = 0.05;
        gbc.anchor = GridBagConstraints.WEST;
        JLabel logoLabel = new JLabel(new ImageIcon(DKStyles.URL_ICON_ANT));
        add(logoLabel, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 0.25;
        gbc.anchor = GridBagConstraints.WEST;
        JLabel titleLabel = new JLabel("Hormiguero virtual", JLabel.LEFT);
        titleLabel.setFont(DKStyles.DKFONT_LANGOSTIONS);
        add(titleLabel, gbc);

        gbc.gridx = 3;
        gbc.gridwidth = 1;
        gbc.weightx = 0.1;
        gbc.anchor = GridBagConstraints.EAST;
        DKPatButton createAntButton = new DKPatButton("Crear hormiga larva", false, DKStyles.DKFONT_LANGOSTIONS_SMALL);
        createAntButton.setPreferredSize(new Dimension(150, 25));
        createAntButton.setBackground(DKStyles.DKCOLOR_GREEN2);
        add(createAntButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 6;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        tablaHormigas = new JTable();
        JScrollPane scrollPane = new JScrollPane(tablaHormigas);
        add(scrollPane, gbc);

        cargarHormigas();
    }

    public void cargarHormigas() {
        try {
            List<DKHormigaDTO> hormigas = hormigaDAO.dkReadAll();
            DefaultTableModel model = new DefaultTableModel(
                    new String[] { "Nombre", "Sexo", "GenoAlimento", "IngestaNativa", "Provincia", "Tipo Hormiga" }, 0);

            for (DKHormigaDTO hormiga : hormigas) {
                model.addRow(new Object[] {
                        hormiga.getNombre(),
                        hormiga.getIdSexo(),
                        hormiga.getIdGenoAlimento(),
                        hormiga.getIdIngestaNativa(),
                        hormiga.getIdProvincia(),
                        hormiga.getTipoHormiga()
                });
            }

            tablaHormigas.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar hormigas", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public DKHormigaDTO obtenerHormigaSeleccionada() {
        int selectedRow = tablaHormigas.getSelectedRow();
        if (selectedRow != -1) {
            String nombreHormiga = (String) tablaHormigas.getValueAt(selectedRow, 0);
            try {
                return hormigaDAO.dkReadAll().stream()
                        .filter(h -> h.getNombre().equals(nombreHormiga))
                        .findFirst().orElse(null);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al obtener la hormiga seleccionada", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        return null;
    }

    public void actualizarHormigaEnTabla(DKHormigaDTO hormiga) {
        int selectedRow = tablaHormigas.getSelectedRow();
        if (selectedRow != -1) {
            tablaHormigas.setValueAt(hormiga.getTipoHormiga(), selectedRow, 5);
            tablaHormigas.setValueAt(hormiga.getIdGenoAlimento(), selectedRow, 2);
            tablaHormigas.setValueAt(hormiga.getIdIngestaNativa(), selectedRow, 3);
        }
    }
}
