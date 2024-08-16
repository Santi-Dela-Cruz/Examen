package DKUserInterface.DKCustomerController;

import javax.swing.JComboBox;
import java.awt.Dimension;
import java.util.List;

public class DKPatComboBox extends JComboBox<String> {

    public DKPatComboBox(String[] items) {
        super(items);
        customizeComponent();

    }

    public DKPatComboBox(List<String> items, String dkNombreComboBox) {
        super();
        // Agregar el elemento por defecto al inicio
        addItem(dkNombreComboBox);

        // Agregar el resto de los elementos
        for (String item : items) {
            addItem(item);
        }

        // Personalizar el componente
        customizeComponent();
    }

    private void customizeComponent() {
        setFont(DKStyles.DKFONT_LANGOSTIONS_SMALL);
        setForeground(DKStyles.DKCOLOR_FONT);
        // Ajustar el tamaño preferido del ComboBox
        setPreferredSize(new Dimension(150, 30)); // Puedes ajustar estas dimensiones según tus necesidades
        // setBackground(new Color(173, 216, 230)); // Color azul claro
        // setBorder(DKStyles.dkCreateBorderRect()); // Bordes redondeados
        setSelectedIndex(0);
    }

    @Override
    public void setSelectedIndex(int index) {
        // Evitar la selección del primer elemento (el texto por defecto)
        if (index == 0) {
            return;
        }
        super.setSelectedIndex(index);
    }
}
