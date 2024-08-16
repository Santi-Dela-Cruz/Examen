package DKUserInterface.DKForms;

import javax.swing.*;

import DKUserInterface.DKCustomerController.DKPatButton;
import DKUserInterface.DKCustomerController.DKStyles;

import java.awt.*;

public class DKGridPanel extends JPanel {

    public DKGridPanel() {
        setLayout(new BorderLayout());

        JLabel logoLabel = new JLabel(new ImageIcon(DKStyles.URL_ICON_ANT));
        add(logoLabel, BorderLayout.WEST);

        // Ajuste de la grilla para 3 filas y 4 columnas
        JPanel grid = new JPanel(new GridLayout(3, 4, 5, 5));
        for (int i = 0; i < 12; i++) { // 3x4 = 12 cuadros
            grid.add(new JLabel(""));
        }
        add(grid, BorderLayout.CENTER);

        DKPatButton createAntButton = new DKPatButton("Crear hormiga larva", false, DKStyles.DKFONT_LANGOSTIONS);
        add(createAntButton, BorderLayout.EAST);
    }
}
