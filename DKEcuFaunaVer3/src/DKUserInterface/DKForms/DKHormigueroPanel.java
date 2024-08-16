package DKUserInterface.DKForms;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import DKUserInterface.DKCustomerController.DKPatButton;
import DKUserInterface.DKCustomerController.DKPatPanel;
import DKUserInterface.DKCustomerController.DKStyles;
import DKDataAccess.DKDAO.DKHormigaDAO;
import DKDataAccess.DKDTO.DKHormigaDTO;
import DKBusinessLogic.DKGetHormigaBL;
import java.awt.*;
import java.util.List;

public class DKHormigueroPanel extends DKPatPanel {

    private JTable tablaHormigas;
    private DKHormigaDAO hormigaDAO;
    private DKGetHormigaBL getHormigaBL;
    private DKMainPanel mainPanel;

    public DKHormigueroPanel(DKMainPanel mainPanel) {
        super(20, DKStyles.DKCOLOR_GREEN5);
        setOpaque(true);
        setBackground(DKStyles.DKCOLOR_GREEN5);
        this.mainPanel = mainPanel;
        hormigaDAO = new DKHormigaDAO();
        getHormigaBL = new DKGetHormigaBL();

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;

        initializeComponents(gbc);
        cargarHormigas();
        configurarColumnaEvolucion();
    }

    private void initializeComponents(GridBagConstraints gbc) {
        // Logo
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.weightx = 0.05;
        gbc.anchor = GridBagConstraints.WEST;
        add(new JLabel(new ImageIcon(DKStyles.URL_ICON_ANT)), gbc);

        // Title
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 0.25;
        gbc.anchor = GridBagConstraints.WEST;
        JLabel titleLabel = new JLabel("Hormiguero virtual", JLabel.LEFT);
        titleLabel.setFont(DKStyles.DKFONT_LANGOSTIONS);
        add(titleLabel, gbc);

        // Button
        gbc.gridx = 3;
        gbc.gridwidth = 1;
        gbc.weightx = 0.1;
        gbc.anchor = GridBagConstraints.EAST;
        DKPatButton createAntButton = new DKPatButton("Crear hormiga larva", false, DKStyles.DKFONT_LANGOSTIONS_SMALL);
        createAntButton.setPreferredSize(new Dimension(150, 25));
        createAntButton.setBackground(DKStyles.DKCOLOR_GREEN2);
        createAntButton.addActionListener(e -> mainPanel.mostrarFormularioCrearHormiga());
        add(createAntButton, gbc);

        // Table
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 6;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        tablaHormigas = new JTable();
        JScrollPane scrollPane = new JScrollPane(tablaHormigas);
        add(scrollPane, gbc);
    }

    public void cargarHormigas() {
        try {
            List<DKHormigaDTO> hormigas = hormigaDAO.dkReadAll();
            DefaultTableModel model = new DefaultTableModel(
                    new String[] { "Nombre", "Sexo", "GenoAlimento", "IngestaNativa", "Provincia", "Tipo Hormiga",
                            "Evolución (%)" },
                    0);

            for (DKHormigaDTO hormiga : hormigas) {
                String nombreSexo = getHormigaBL.obtenerNombreSexo(hormiga.getIdSexo());
                String nombreGenoAlimento = getHormigaBL.obtenerNombreGenoAlimento(hormiga.getIdGenoAlimento());
                String nombreIngestaNativa = getHormigaBL.obtenerNombreIngestaNativa(hormiga.getIdIngestaNativa());
                String nombreProvincia = getHormigaBL.obtenerNombreProvincia(hormiga.getIdProvincia());

                model.addRow(new Object[] {
                        hormiga.getNombre(),
                        nombreSexo,
                        nombreGenoAlimento,
                        nombreIngestaNativa,
                        nombreProvincia,
                        hormiga.getTipoHormiga(),
                        hormiga.getPorcentajeEvolucion()
                });
            }

            tablaHormigas.setModel(model);
            configurarColumnaEvolucion(); // Reconfigura la columna de evolución para asegurar que la barra de progreso
                                          // esté activa

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

    public void actualizarHormigaEnTabla(DKHormigaDTO hormiga, boolean evolucionada) {
        int selectedRow = tablaHormigas.getSelectedRow();
        if (selectedRow != -1) {
            // Reinicia la barra de progreso aquí, si es necesario
            tablaHormigas.setValueAt(hormiga.getTipoHormiga(), selectedRow, 5);

            // Usar los métodos de DKGetHormigaBL para mostrar nombres en vez de IDs
            try {
                tablaHormigas.setValueAt(getHormigaBL.obtenerNombreGenoAlimento(hormiga.getIdGenoAlimento()),
                        selectedRow, 2);
                tablaHormigas.setValueAt(getHormigaBL.obtenerNombreIngestaNativa(hormiga.getIdIngestaNativa()),
                        selectedRow, 3);
                tablaHormigas.setValueAt(getHormigaBL.obtenerNombreSexo(hormiga.getIdSexo()), selectedRow, 1);
                tablaHormigas.setValueAt(getHormigaBL.obtenerNombreProvincia(hormiga.getIdProvincia()), selectedRow, 4);
            } catch (Exception e) {
                e.printStackTrace();
            }

            int currentPercentage = (int) tablaHormigas.getValueAt(selectedRow, 6);

            if (evolucionada) {
                JOptionPane.showMessageDialog(this, "La hormiga ha evolucionado a " + hormiga.getTipoHormiga() + "!",
                        "Evolución", JOptionPane.INFORMATION_MESSAGE);
            }

            animateProgressBar(selectedRow, 6, currentPercentage, hormiga.getPorcentajeEvolucion(), evolucionada,
                    hormiga);
        }
    }

    private void animateProgressBar(int row, int column, int startPercentage, int endPercentage, boolean evolucionada,
            DKHormigaDTO hormiga) {
        new Thread(() -> {
            try {
                for (int i = startPercentage + 1; i <= Math.min(endPercentage, 100); i++) {
                    final int progress = i;
                    SwingUtilities.invokeLater(() -> tablaHormigas.setValueAt(progress, row, column));
                    Thread.sleep(50);
                }

                if (endPercentage >= 100) {
                    SwingUtilities.invokeLater(() -> {
                        tablaHormigas.setValueAt(0, row, column);

                        if (evolucionada) {
                            JOptionPane.showMessageDialog(this,
                                    "La hormiga ha evolucionado a " + hormiga.getTipoHormiga() + "!", "Evolución",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }

                        int remainingPercentage = endPercentage - 100;
                        if (remainingPercentage > 0) {
                            animateProgressBar(row, column, 0, remainingPercentage, false, hormiga);
                        }
                    });
                } else {
                    SwingUtilities.invokeLater(() -> tablaHormigas.setValueAt(endPercentage, row, column));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void configurarColumnaEvolucion() {
        int columnIndex = tablaHormigas.getColumnModel().getColumnIndex("Evolución (%)");
        TableColumn evolutionColumn = tablaHormigas.getColumnModel().getColumn(columnIndex);
        evolutionColumn.setCellRenderer(new ProgressBarCellRenderer());
    }

    private static class ProgressBarCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                int row, int column) {
            if (value instanceof Integer) {
                int progressValue = (Integer) value;
                JProgressBar progressBar = new JProgressBar(0, 100);
                progressBar.setValue(progressValue);
                progressBar.setStringPainted(true);
                return progressBar;
            }
            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        }
    }
}
