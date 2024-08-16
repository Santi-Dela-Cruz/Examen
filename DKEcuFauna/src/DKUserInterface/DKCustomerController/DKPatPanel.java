package DKUserInterface.DKCustomerController;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;

public class DKPatPanel extends JPanel {

    private int cornerRadius; // Radio de los bordes redondeados
    private Color backgroundColor; // Color de fondo con transparencia

    public DKPatPanel(int cornerRadius, Color backgroundColor) {
        this.cornerRadius = cornerRadius;
        this.backgroundColor = new Color(
                backgroundColor.getRed(),
                backgroundColor.getGreen(),
                backgroundColor.getBlue(),
                192); // Ajustar la transparencia al 50%
        setOpaque(false); // Para permitir transparencia
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Dibujar el fondo del panel con bordes redondeados y color de fondo
        g2.setColor(backgroundColor);
        g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius));

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 200); // Tamaño preferido del panel
    }
}
