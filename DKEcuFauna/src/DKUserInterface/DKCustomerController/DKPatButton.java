package DKUserInterface.DKCustomerController;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class DKPatButton extends JButton implements MouseListener {

    private boolean isRound;
    private Color hoverBackgroundColor = new Color(128, 128, 128, 128);
    private boolean isHovering = false;
    private Color originalForegroundColor;

    public DKPatButton(String text, boolean isRound, Font customFont) {
        this.isRound = isRound;
        customizeComponent(text, customFont);
    }

    private void customizeComponent(String text, Font customFont) {
        setText(text);
        setFont(customFont);
        originalForegroundColor = Color.WHITE;
        setForeground(originalForegroundColor);
        setOpaque(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setCursor(DKStyles.DKCURSOR_HAND);
        addMouseListener(this);

        if (isRound) {
            setPreferredSize(new Dimension(40, 40));
        } else {
            setPreferredSize(new Dimension(200, 40));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Pintar el color de fondo
        g2.setColor(getBackground());
        if (isRound) {
            g2.fillOval(0, 0, getWidth(), getHeight());
        } else {
            g2.fillRect(0, 0, getWidth(), getHeight());
        }

        // Si el ratón está encima, pintar el color semitransparente
        if (isHovering) {
            g2.setColor(hoverBackgroundColor);
            if (isRound) {
                g2.fillOval(0, 0, getWidth(), getHeight());
            } else {
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        }

        // Pintar el texto centrado en el botón
        FontMetrics fm = g2.getFontMetrics();
        int x = (getWidth() - fm.stringWidth(getText())) / 2;
        int y = (getHeight() + fm.getAscent()) / 2 - fm.getDescent();
        g2.setColor(getForeground());
        g2.drawString(getText(), x, y);

    }

    // Eliminar o comentar el método paintBorder para no dibujar ningún borde
    /*
     * @Override
     * protected void paintBorder(Graphics g) {
     * // No se dibuja borde
     * }
     */

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        isHovering = true; // Indicar que el ratón está sobre el botón
        setForeground(Color.BLACK); // Cambiar el color del texto a negro
        repaint(); // Volver a dibujar el botón
    }

    @Override
    public void mouseExited(MouseEvent e) {
        isHovering = false; // Indicar que el ratón ha salido del botón
        setForeground(originalForegroundColor); // Restaurar el color de texto original
        repaint(); // Volver a dibujar el botón
    }
}
