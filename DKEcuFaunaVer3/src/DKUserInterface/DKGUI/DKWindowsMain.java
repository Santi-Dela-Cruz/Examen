package DKUserInterface.DKGUI;

import DKUserInterface.DKCustomerController.DKPatPanelBar;
import DKUserInterface.DKForms.DKMainPanel;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import java.awt.BorderLayout;
import java.awt.Container;

public class DKWindowsMain extends JFrame {

    public DKWindowsMain(String titleApp) {
        super(titleApp); // Pasar el título a la superclase JFrame
        customizeComponent(titleApp);
    }

    private void customizeComponent(String titleApp) {
        setUndecorated(true);
        setSize(600, 580);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Configurar el contenedor principal
        Container container = getContentPane();
        container.setLayout(new BorderLayout());

        // Añadir barra de título personalizada
        container.add(new DKPatPanelBar(this), BorderLayout.NORTH);

        // Añadir panel principal con CardLayout
        DKMainPanel mainPanel = new DKMainPanel();
        container.add(mainPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        // Iniciar la aplicación en el hilo de despacho de eventos
        SwingUtilities.invokeLater(() -> {
            new DKWindowsMain("Hormiguero Virtual").setVisible(true);
        });
    }
}
