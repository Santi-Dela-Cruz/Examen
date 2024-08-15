package DKUserInterface.DKForms;

import javax.swing.*;

import DKUserInterface.DKCustomerController.DKStyles;

import java.awt.*;

public class DKMainPanel extends JPanel {

    public DKMainPanel() {
        setBackground(DKStyles.DKCOLOR_GREEN2);
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

        // Fila 2: HormigueroPanel
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weighty = 0.6;
        gbc.anchor = GridBagConstraints.CENTER;
        add(new DKHormigueroPanel(), gbc);

        // Fila 3: ActionPanel
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.weighty = 0.1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(new DKActionPanel(), gbc);

        // Fila 4: BottomPanel
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.weighty = 0.1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(new DKBottomPanel(), gbc);
    }
}
