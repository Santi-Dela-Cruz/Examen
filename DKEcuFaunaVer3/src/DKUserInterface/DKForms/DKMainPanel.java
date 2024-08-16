package DKUserInterface.DKForms;

import javax.swing.*;
import DKUserInterface.DKCustomerController.DKStyles;
import java.awt.*;

public class DKMainPanel extends JPanel {

    private DKHormigueroPanel hormigueroPanel;
    private DKCrearHormigaPanel crearHormigaPanel;
    private JPanel panelCambio; // Panel que contiene el CardLayout

    public DKMainPanel() {
        setBackground(DKStyles.DKCOLOR_GREEN2);
        setOpaque(true);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.weightx = 1.0;
        gbc.weighty = 0.2;

        // Fila 1: CedulaPanel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(new DKCedulaPanel(), gbc);

        // Panel con CardLayout para cambiar entre tabla y formulario en el área de la
        // tabla
        panelCambio = new JPanel(new CardLayout());
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weighty = 0.6;
        gbc.anchor = GridBagConstraints.CENTER;
        add(panelCambio, gbc);

        // Inicializar paneles
        hormigueroPanel = new DKHormigueroPanel(this);
        crearHormigaPanel = new DKCrearHormigaPanel(this);

        panelCambio.setOpaque(true);
        panelCambio.setBackground(DKStyles.DKCOLOR_GREEN2);
        panelCambio.setBorder(BorderFactory.createEmptyBorder());

        // Agregar paneles al CardLayout
        panelCambio.add(hormigueroPanel, "hormigueroPanel");
        panelCambio.add(crearHormigaPanel, "crearHormigaPanel");

        // Fila 3: ActionPanel
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.weighty = 0.1;
        gbc.anchor = GridBagConstraints.CENTER;
        DKActionPanel actionPanel = new DKActionPanel(hormigueroPanel);
        add(actionPanel, gbc);

        // Fila 4: BottomPanel
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.weighty = 0.1;
        gbc.anchor = GridBagConstraints.CENTER;
        DKBottomPanel bottomPanel = new DKBottomPanel(hormigueroPanel);
        add(bottomPanel, gbc);
    }

    public void mostrarFormularioCrearHormiga() {
        crearHormigaPanel.resetForm(); // Restablece el formulario
        CardLayout cl = (CardLayout) panelCambio.getLayout();
        cl.show(panelCambio, "crearHormigaPanel");
        panelCambio.revalidate();
        panelCambio.repaint();
    }

    public void mostrarHormiguero() {
        CardLayout cl = (CardLayout) panelCambio.getLayout();
        cl.show(panelCambio, "hormigueroPanel");
        panelCambio.revalidate();
        panelCambio.repaint();
        getHormigueroPanel(); // Asegura que la tabla se actualice cuando regreses
    }

    public DKHormigueroPanel getHormigueroPanel() {
        return hormigueroPanel;
    }
}
