
package gui;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JButton;


/**
 * Η κλάση ImageButton υλοποιεί  τη σύνθετη λειτουργία εικόνας-κουμπιού. Κληρονομεί
 * από την JButton.
 *
 *  @author Thanasis
 *  @author tasosxak
 *  @version 1.0
 *  @since 10/1/17
 *
 */
public class ImageButton extends JButton {

    public ImageButton(String imageName) {
        this(imageName, 300, 70);
    }

    public ImageButton(String imageName, int width, int height) {

        setPreferredSize(new Dimension(width, height));
        setSize(width, height);
        setBackground(new Color(0, 0, 0, 0)); //Χωρίς χρώμα
        setOpaque(false);
        setIcon(new ImageIcon(getClass().getResource("/images/" + imageName))); //Θέτει ως background την εικόνα
        setVisible(true);
    }

}
