package DKUserInterface.DKCustomerController;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public abstract class DKStyles {
    public static final Color DKBACKGROUND_COLOR = new Color(245, 245, 245); // Fondo claro
    public static final Color DKCOLOR_FONT = new Color(0, 0, 0); // Color de fuente negro
    public static final Color DKCOLOR_FONT_LIGHT = new Color(120, 120, 120); // Color de fuente más claro
    public static final Color DKCOLOR_CURSOR = new Color(0, 0, 0); // Color del cursor
    public static final Color DKCOLOR_BORDER = new Color(180, 180, 180); // Color del borde claro
    // Colores Naturaleza
    public static final Color DKCOLOR_GREEN1 = new Color(0, 75, 35);
    public static final Color DKCOLOR_GREEN2 = new Color(0, 100, 0);
    public static final Color DKCOLOR_GREEN3 = new Color(0, 114, 0);
    public static final Color DKCOLOR_GREEN4 = new Color(0, 128, 0);
    public static final Color DKCOLOR_GREEN5 = new Color(56, 176, 0);
    public static final Color DKCOLOR_GREEN6 = new Color(112, 224, 0);
    public static final Color DKCOLOR_GREEN7 = new Color(158, 240, 26);
    public static final Color DKCOLOR_GREEN8 = new Color(204, 255, 51);
    // Colores Azules Oscuro
    public static final Color DKCOLOR_BLUE1 = new Color(13, 27, 42);
    public static final Color DKCOLOR_BLUE2 = new Color(27, 38, 59);
    public static final Color DKCOLOR_BLUE3 = new Color(65, 90, 119);
    public static final Color DKCOLOR_BLUE4 = new Color(119, 141, 169);
    public static final Color DKCOLOR_BLUE5 = new Color(224, 225, 221);
    // Colores Botones
    public static final Color DKCOLOR_YELLOW = new Color(249, 254, 125);
    public static final Color DKCOLOR_RED = new Color(254, 104, 104);
    public static final Color DKCOLOR_GREEN = new Color(182, 254, 104);

    public static final Font DKFONT = new Font("Arial", Font.PLAIN, 18); // Fuente más grande
    public static final Font DKFONT_BOLD = new Font("Arial", Font.BOLD, 18);
    public static final Font DKFONT_BOLD_SMALL = new Font("Arial", Font.BOLD, 9);

    // Método para cargar una fuente personalizada
    public static Font loadCustomFont(String path, float size) {
        try {
            InputStream is = DKStyles.class.getResourceAsStream(path);
            if (is == null) {
                throw new IOException("Resource not found: " + path);
            }
            Font font = Font.createFont(Font.TRUETYPE_FONT, is);
            return font.deriveFont(size);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            return new Font("Arial", Font.PLAIN, (int) size); // Fuente por defecto en caso de error
        }
    }

    // Fuente personalizada cargada desde el archivo AmidoneGrotesk.ttf
    public static final Font DKFONT_AMIDONEGROTESK = loadCustomFont(
            "/DKUserInterface/DKResources/Fonts/AmidoneGrotesk.ttf", 14f);
    public static final Font DKFONT_ANGELACINDY = loadCustomFont(
            "/DKUserInterface/DKResources/Fonts/AngelaCindysttar.otf", 14f);
    public static final Font DKFONT_DESTRUCTION = loadCustomFont(
            "/DKUserInterface/DKResources/Fonts/DestructionManual.otf", 20f);
    public static final Font DKFONT_LANGOSTIONS = loadCustomFont("/DKUserInterface/DKResources/Fonts/LANGOSTIONS.ttf",
            14f);
    public static final Font DKFONT_LANGOSTIONS_SMALL = loadCustomFont(
            "/DKUserInterface/DKResources/Fonts/LANGOSTIONS.ttf", 11f);

    public static final Cursor DKCURSOR_HAND = new Cursor(Cursor.HAND_CURSOR);
    public static final Cursor DKCURSOR_DEFAULT = new Cursor(Cursor.DEFAULT_CURSOR);

    public static final URL URL_ICON_ANT = DKStyles.class
            .getResource("/DKUserInterface/DKResources/Icons/iconAnt3.png");
    public static final URL URL_LOGO_ANT = DKStyles.class
            .getResource("/DKUserInterface/DKResources/Logos/logoHormiga2.png");

    public static final CompoundBorder dkCreateBorderRect() {
        return BorderFactory.createCompoundBorder(new LineBorder(DKCOLOR_BORDER), new EmptyBorder(5, 5, 5, 5));
    }

    public static final void showMsg(String msg) {
        JOptionPane.showMessageDialog(null, msg, "👾 ECUFAUNA", JOptionPane.INFORMATION_MESSAGE);
    }

    public static final void showMsgError(String msg) {
        JOptionPane.showMessageDialog(null, msg, "☣️ ECUFAUNA", JOptionPane.OK_OPTION);
    }

    public static final boolean showConfirmYesNo(String msg) {
        return (JOptionPane.showConfirmDialog(null, msg, "❓ ECUFAUNA",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
    }
}
