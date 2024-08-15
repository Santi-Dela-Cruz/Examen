package DKUserInterface.DKForms;

import javax.swing.*;
import DKUserInterface.DKCustomerController.DKPatButton;
import DKUserInterface.DKCustomerController.DKPatPanel;
import DKUserInterface.DKCustomerController.DKStyles;
import java.awt.*;

public class DKHormigueroPanel extends DKPatPanel {

    public DKHormigueroPanel() {
        // Configuración del panel con bordes redondeados y fondo transparente
        super(20, DKStyles.DKCOLOR_GREEN5); // Mantiene el fondo del panel principal

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;

        // Fila 1: Logo, Título "Hormiguero virtual", Botón "Crear hormiga larva"
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
        createAntButton.setPreferredSize(new Dimension(150, 25)); // Ajustar el tamaño del botón
        createAntButton.setBackground(DKStyles.DKCOLOR_GREEN2);
        add(createAntButton, gbc);

        // Fila 2: Grilla de 3 filas y 6 columnas
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 6;
        gbc.weightx = 1.0;
        JPanel gridPanel = new JPanel(new GridLayout(3, 6, 10, 10)); // Ajuste del grid
        gridPanel.setOpaque(false); // Hacer el gridPanel completamente transparente

        for (int i = 0; i < 18; i++) {
            JTextField field = new JTextField("");
            field.setEditable(false);
            field.setBackground(Color.WHITE); // Si deseas también puedes quitar o ajustar este color
            field.setPreferredSize(new Dimension(40, 25)); // Ajuste del tamaño a más rectangular
            gridPanel.add(field);
        }
        add(gridPanel, gbc);
    }
}
