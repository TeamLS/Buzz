package logic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * Η κλάση αυτή διαβάζει και γράφει στο αρχείο των σκορ .
 *
 * @author Thanasis
 * @author tasosxak
 * @since 7/1/2017
 * @version 1.0
 */
public class HighScores {

    private HighScoresList players;

    public HighScores() {
        readFile();
    }

    private void readFile() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("scores.ser"))) { //Διάβασμα του αποθηκευμένου αντικειμένου
            players = (HighScoresList) in.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            players = new HighScoresList(); //Σε περίπτωση που δεν υπάρχει το αρχείο των σκορ, δημιουργείτε νέα λίστα HighScore
        }
    }

    /**
     *
     * @param curPlayers Η λίστα παιχτών που θα αποθηκευτούν τα highScore
     * @throws IOException αν δν μπορει να γραψει στο αρχειο συνηθως αν δν ειναι
     * σε φακελο που εχει δικαιωματα να γραψει
     */
    public void writeFile(PlayerList curPlayers) throws IOException {

        if (curPlayers.getNumOfPlayers() > 0) { //Αν η λίστα δεν είναι άδεια

            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("scores.ser"));
            Integer highScore, curScore;
            int numOfPlayers = curPlayers.getNumOfPlayers();
            String name;

            if (numOfPlayers == 1) {
                name = curPlayers.getNameOfPlayer(1);
                if (players.playerExists(name)) { //Αν ο player ήταν ήδη εγγεγραμένος στο αρχείο των σκορ
                    highScore = players.getScoreOfPlayer(name); //Παίρνει το παλιό σκορ του player
                    curScore = curPlayers.getScoreOfPlayer(1); //Παίρνει το νέο σκορ του player
                    if (highScore == null || curScore > highScore) { //Αν το παλιο είναι μικρότερο ή κενό 
                        players.setScoreOfPlayer(curScore, name); //Εγγράφεται το νέο σκορ
                    }
                } else {
                    players.addPlayer(name); //Προστίθεται το όνομα του νέου παίχτη
                    players.setScoreOfPlayer(curPlayers.getScoreOfPlayer(1), name); //Θέτει το σκορ του παίχτη στο hashmap με κλειδί το όνομα του παίχτη
                }
            }else {
                int winnersId = curPlayers.getWinnersId();
                if (winnersId >= 0) {
                    for (int i = 1; i <= numOfPlayers; i++) {
                        name = curPlayers.getNameOfPlayer(i);
                        if (players.playerExists(name)) {//Αν υπάρχει ο χρήστης στο αρχείο 
                            if (i == winnersId) {
                                players.playerWon(name);//Προσθέτει μια νίκη στον  ήδη υπάρχον παίχτη που κέρδισε το παιχνίδι
                            } 
                        } else {
                            players.addPlayer(name); //Προσθέτει καινούργιο παίχτη
                            if (i == winnersId) {
                                players.playerWon(name);//Προσθέτει μια νίκη στον καινούργιο παίχτη που κέρδισε το παιχνίδι
                            }
                        }
                    }
                } 
            }

            out.writeObject(players); //Γράφει το αντικείμενο με τους παίχτες
            out.close();
        }

    }

    /**
     *
     * @param num Τον αριθμό τον παιχτών με τις περισσότερες νίκες
     * @return Επιστρέφει πίνακα με τα num ονομάτα των παιχτών με τις
     * περισσότερες νίκες κατά φθίνουσα σειρά
     */
    public String[] getNamesForWins(int num) {
        return players.getNamesForWins(num);
    }

    /**
     *
     * @param num Τον αριθμό τον παιχτών με τις περισσότερες νίκες
     * @return Επιστρέφει πίνακα με τις num μεγαλύτερες νίκες κατά φθίνουσα
     * σειρά
     */
    public Integer[] getTopWins(int num) {
        return players.getTopWins(num);
    }

    /**
     *
     * @param num Τον αριθμό τον παιχτών με το μεγαλύτερο σκορ
     * @return Επιστρέφει πίνακα με τα num ονομάτα των παιχτών με τις
     * περισσότερες νίκες κατά φθίνουσα σειρά
     */
    public String[] getNamesForHighScores(int num) {
        return players.getNamesForHighScores(num);
    }

    /**
     *
     * @param num Τον αριθμό τον παιχτών με το μεγαλύτερο σκορ
     * @return Επιστρέφει πίνακα με τα num υψηλότερα σκορ κατά φθίνουσα σειρά
     */
    public Integer[] getHighScores(int num) {
        return players.getHighScores(num);
    }

    public int getNumOfHighScores() {
        return players.getNumOfPlayers();
    }

    @Override
    public String toString() {
        return players.toString();
    }

}
