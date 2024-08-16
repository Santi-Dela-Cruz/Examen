package DKUserInterface.DKForms;

import DKUserInterface.DKCustomerController.DKPatButton;
import DKUserInterface.DKCustomerController.DKPatPanel;
import DKUserInterface.DKCustomerController.DKStyles;
import DKBusinessLogic.DKHormigaBL;
import DKDataAccess.DKDTO.DKHormigaDTO;

import java.awt.*;

import javax.swing.JOptionPane;

public class DKBottomPanel extends DKPatPanel {

    private DKHormigueroPanel hormigueroPanel;
    private DKHormigaBL hormigaBL;

    public DKBottomPanel(DKHormigueroPanel hormigueroPanel) {
        super(15, DKStyles.DKCOLOR_GREEN8);
        this.hormigueroPanel = hormigueroPanel;
        hormigaBL = new DKHormigaBL();

        DKPatButton buttonEliminar = new DKPatButton("Eliminar", false, DKStyles.DKFONT_LANGOSTIONS_SMALL);
        DKPatButton buttonGuardar = new DKPatButton("Guardar", false, DKStyles.DKFONT_LANGOSTIONS_SMALL);

        buttonEliminar.setBackground(DKStyles.DKCOLOR_GREEN2);
        buttonGuardar.setBackground(DKStyles.DKCOLOR_GREEN2);

        buttonGuardar.addActionListener(e -> guardarCambios());
        buttonEliminar.addActionListener(e -> eliminarHormiga());

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(buttonEliminar, gbc);

        gbc.gridx = 1;
        add(buttonGuardar, gbc);
    }

    private void guardarCambios() {
        DKHormigaDTO hormiga = hormigueroPanel.obtenerHormigaSeleccionada();
        if (hormiga != null) {
            try {
                hormigaBL.guardarHormiga(hormiga);
                JOptionPane.showMessageDialog(this, "Cambios guardados exitosamente", "Guardar",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al guardar los cambios", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void eliminarHormiga() {
        DKHormigaDTO hormiga = hormigueroPanel.obtenerHormigaSeleccionada();
        if (hormiga != null) {
            try {
                hormigaBL.eliminarHormiga(hormiga.getIdHormiga());
                hormigueroPanel.cargarHormigas();
                JOptionPane.showMessageDialog(this, "Hormiga eliminada exitosamente", "Eliminar",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al eliminar la hormiga", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
