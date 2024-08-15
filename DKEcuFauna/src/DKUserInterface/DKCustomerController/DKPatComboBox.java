package DKUserInterface.DKCustomerController;

import javax.swing.JComboBox;
import java.awt.Dimension;

public class DKPatComboBox extends JComboBox<String> {

    public DKPatComboBox(String[] items) {
        super(items);
        customizeComponent();
    }

    private void customizeComponent() {
        setFont(DKStyles.DKFONT_LANGOSTIONS_SMALL);
        setForeground(DKStyles.DKCOLOR_FONT);
        // Ajustar el tamaño preferido del ComboBox
        setPreferredSize(new Dimension(150, 30)); // Puedes ajustar estas dimensiones según tus necesidades
        // setBackground(new Color(173, 216, 230)); // Color azul claro
        // setBorder(DKStyles.dkCreateBorderRect()); // Bordes redondeados
    }
}
