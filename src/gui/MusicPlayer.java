package gui;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.*;

/**
 * Η κλάση MusicPlayer υλοποιεί έναν διοργανωτή μουσικής, δηλαδή με αυτή την κλάση μπορούμε να παίξουν άρχεια ήχου με κατάληξη .wav . 
 *
 * @author tasosxak
 * @author Thanasis
 * @version 1.0
 * @since 8/1/17
 *
 */
public class MusicPlayer {

    private Clip clip;
    private MusicPlayer secondSound;
    private boolean playSecondSound;

    public MusicPlayer(String soundName) {

        
        // Link: http://www.java2s.com/Code/JavaAPI/javax.sound.sampled/LineaddLineListenerLineListenerlistener.htm
        
        
        URL path = getClass().getResource("/sounds/"+soundName);
        Line.Info linfo = new Line.Info(Clip.class);
        Line line;
        try {
            line = AudioSystem.getLine(linfo);
            clip = (Clip) line;
            AudioInputStream ais;
            ais = AudioSystem.getAudioInputStream(path);
            clip.open(ais);

        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(MusicPlayer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MusicPlayer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(MusicPlayer.class.getName()).log(Level.SEVERE, null, ex);
            Logger.getLogger(MusicPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *  Με την play() ξεκινάει το αρχείου ήχου να παίζει.
     */
    public void play() {
        playSecondSound = true;
        clip.start();
        
    }
    
    /**
     *   Με την loop() παίζει επαναληπτικά το αρχείο ήχου
     */
    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    /**
     *  Με την addNectSound() μπορούμε να προσθέσουμε ένα αρχείο ήχου το οποίο θα ξεκινήσει να παίζει ακριβώς μετά απο το αρχείο μουσικής που παίζει
     * @param soundName  Το όνομα του αρχείου ήχου μαζί με την κατάληξη που υποδηλώνει τον τύπο
     */
    public void addNextSound(final String soundName) {
        addNextSound(soundName, false);
    }
    
    /**
     *   Με την stop() σταματάει να παίζει το αρχείο ήχου
     */
    public void stop() {
        clip.stop();
        if (secondSound == null) {
            playSecondSound = false;
        } else {
            secondSound.stop();
        }
            
    }

    /**
     * Με την addNectSound μπορούμε να προσθέσουμε ένα αρχείο ήχου το οποίο θα ξεκινήσει να παίζει ακριβώς μετά απο το αρχείο μουσικής που παίζει
     * 
     * @param soundName Το όνομα του αρχείο ήχου π.χ.( filename.wav)
     * @param loop  true αν έχει 2ο αρχείο ήχου και θέλουμε να παίζει επαναληπτικά, false αν έχουμε 2ο αρχείο ήχου και θέλουμε να παίξει μια φορά
     */
    public void addNextSound(final String soundName, final boolean loop) {

        playSecondSound = true;
        clip.addLineListener(new LineListener() { //Ενεργοποιείται όταν τελειώνει ή αρχίζει το αρχείο ήχου

            @Override
            public void update(LineEvent le) {
                LineEvent.Type type = le.getType();
                if (type == LineEvent.Type.OPEN) {
                    //τίποτα
                } else if (type == LineEvent.Type.CLOSE) {
                    if (playSecondSound) { //Έλεγχος αν έχει 2ο αρχείου ήχου
                        secondSound = new MusicPlayer(soundName);
                        if (loop) { 
                            secondSound.loop(); //Παίζει το 2ο αρχείο ήχου επαναληπτικά
                        } else {
                            secondSound.play(); //Παίζει το αρχείο ήχου μια φορά
                        }
                    }
                } else if (type == LineEvent.Type.STOP) {
                    clip.close(); //Κλείνει το αρχείο ήχου
                }
            }
        });
    }

}
