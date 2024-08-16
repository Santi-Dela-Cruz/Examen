package DKUserInterface.DKCustomerController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DKPatPanelBar extends JPanel {

    private boolean isMaximized = false; // Variable para rastrear el estado de maximización
    private Point previousLocation;
    private Dimension previousSize;

    public DKPatPanelBar(JFrame frame) {
        setLayout(new BorderLayout());
        setBackground(DKStyles.DKCOLOR_GREEN1); // Fondo claro

        // Cargar el ícono del logo
        ImageIcon logoIcon = new ImageIcon(DKStyles.URL_LOGO_ANT); // Ajusta la ruta según tu proyecto
        JLabel logoLabel = new JLabel(logoIcon);

        JLabel titleLabel = new JLabel("EcuaFauna 2K24A");
        titleLabel.setFont(DKStyles.DKFONT_DESTRUCTION);
        titleLabel.setForeground(DKStyles.DKCOLOR_FONT);

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        titlePanel.setOpaque(false);
        titlePanel.add(logoLabel); // Agregar el logo al panel
        titlePanel.add(titleLabel); // Agregar el texto al panel

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.setBackground(DKStyles.DKCOLOR_GREEN1);

        // Creación de botones con el nuevo constructor que permite personalizar la
        // fuente y el texto
        DKPatButton closeButton = new DKPatButton("X", true, DKStyles.DKFONT_LANGOSTIONS_SMALL);
        DKPatButton minimizeButton = new DKPatButton("_", true, DKStyles.DKFONT_LANGOSTIONS_SMALL);
        DKPatButton maximizeButton = new DKPatButton("O", true, DKStyles.DKFONT_LANGOSTIONS_SMALL);

        closeButton.setBackground(DKStyles.DKCOLOR_RED);
        minimizeButton.setBackground(DKStyles.DKCOLOR_YELLOW);
        maximizeButton.setBackground(DKStyles.DKCOLOR_GREEN);

        buttonPanel.add(minimizeButton);
        buttonPanel.add(maximizeButton);
        buttonPanel.add(closeButton);

        add(titlePanel, BorderLayout.WEST); // Agregar el panel con el logo y el título al lado izquierdo
        add(buttonPanel, BorderLayout.EAST); // Botones a la derecha

        // Funcionalidad de los botones
        closeButton.addActionListener(e -> frame.dispose());

        minimizeButton.addActionListener(e -> {
            frame.setState(JFrame.ICONIFIED);
        });

        maximizeButton.addActionListener(e -> {
            if (isMaximized) {
                // Restaurar al tamaño y posición anteriores
                frame.setExtendedState(JFrame.NORMAL);
                frame.setLocation(previousLocation);
                frame.setSize(previousSize);
                isMaximized = false;
            } else {
                // Guardar la ubicación y tamaño actuales
                previousLocation = frame.getLocation();
                previousSize = frame.getSize();
                // Maximizar la ventana
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                isMaximized = true;
            }
        });

        // Añadir un WindowListener para restaurar el estado de maximización después de
        // minimizar
        frame.addWindowStateListener(e -> {
            if (e.getNewState() == JFrame.NORMAL && isMaximized) {
                // Si la ventana se restaura después de ser minimizada y estaba maximizada
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            }
        });

        // Hacer la barra de título arrastrable
        MouseAdapter mouseAdapter = new MouseAdapter() {
            private Point initialClick;

            @Override
            public void mousePressed(MouseEvent e) {
                initialClick = e.getPoint();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (!isMaximized) { // Solo permite mover la ventana si no está maximizada
                    int x = frame.getLocation().x + e.getX() - initialClick.x;
                    int y = frame.getLocation().y + e.getY() - initialClick.y;
                    frame.setLocation(x, y);
                }
            }
        };

        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);
    }
}
