package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;

/**
 * Η κλάση BButton υλοποιεί τα χαρακτηριστικά του κουμπιού της εφαρμογής.
 * Κληρονομεί από την JButton.
 *
 * @author Thanasis
 * @author tasosxak
 * @version 1.0
 * @since 8/1/17
 *
 */
public class BButton extends JButton {

    public BButton(String text) {
        this(text, new Color(0x1159FF), new Color(0x1579FF));
    }

    public BButton(String text, final Color firstColor, final Color secondColor) {

        setText(text);
        setBackground(firstColor);
        setForeground(Color.WHITE);
        setPreferredSize(new Dimension(300, 70));
        setHorizontalAlignment(CENTER); //Κεντράρισμα στο κέντρο (το κείμενο του κουμπιού)
        setSize(250, 50); //Ορισμός μεγέθου
        setFont(new Font("Georgia", Font.BOLD, 20)); //Ορισμός γραμματοσειράς
        setVisible(true);
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MusicPlayer("click.wav").play(); //Ηχητικό όταν το κουμπί πατιέται
            }
        });

        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent me) {
                me.getComponent().setBackground(secondColor); //Φωτισμός του κουμπιού όταν ο δείκτης του ποντικιού περνάει πάνω από αυτό
            }

            @Override
            public void mouseExited(MouseEvent me) {
                me.getComponent().setBackground(firstColor); //Σβήσιμο φωτισμού του κουμπιού ΄όταν ο δείκτης του ποντικιού φεύγει από αυτό
            }
        });

        // Σχήμα και χαρακτηριστικά κουμπιού (padding, border κτλπ) Link: http://stackoverflow.com/questions/23698092/design-button-in-java-like-in-css
        setUI(new BasicButtonUI() {

            @Override
            public void installUI(JComponent c) {
                super.installUI(c);
                AbstractButton button = (AbstractButton) c;
                button.setOpaque(false);
                button.setBorder(new EmptyBorder(5, 15, 5, 15)); //padding
            }

            @Override
            public void paint(Graphics g, JComponent c) {
                AbstractButton b = (AbstractButton) c;
                paintBackground(g, b, b.getModel().isPressed() ? 2 : 0); //Μεταβολή χρώματος όταν πατιέται
                super.paint(g, c);
            }

            private void paintBackground(Graphics g, JComponent c, int yOffset) {
                
                //Δημιουργία του background του κουμπιού, με βάση το σχήμα του, το μέγεθος του  και το χρώμα του
                Dimension size = c.getSize();
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g.setColor(c.getBackground().darker()); //Πιο σκοτείνο background στο κάτω μέρος των κουμπιών
                g.fillRoundRect(0, yOffset, size.width, size.height - yOffset, 60, 60);
                g.setColor(c.getBackground()); //Πιο ανοιχτό background στο κουμπί
                g.fillRoundRect(0, yOffset, size.width, size.height + yOffset - 5, 60, 60);
            }
        });

    }

}
