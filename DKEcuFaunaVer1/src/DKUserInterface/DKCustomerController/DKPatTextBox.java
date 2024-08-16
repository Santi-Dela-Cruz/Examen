package DKUserInterface.DKCustomerController;

import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.Insets;

public class DKPatTextBox extends JTextField {

    public DKPatTextBox() {
        customizeComponent();
    }

    private void customizeComponent() {
        setFont(DKStyles.DKFONT);
        setForeground(DKStyles.DKCOLOR_FONT_LIGHT);
        setCaretColor(DKStyles.DKCOLOR_CURSOR);
        setMargin(new Insets(5, 5, 5, 5));
        setBorderRect();
    }

    public void setBorderRect() {
        Border lineBorder = BorderFactory.createLineBorder(DKStyles.DKCOLOR_BORDER);
        Border emptyBorder = new EmptyBorder(5, 5, 5, 5);
        setBorder(new CompoundBorder(lineBorder, emptyBorder));
    }
}
