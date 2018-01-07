package logic;

import java.io.Serializable;

/**
 *
 * Η κλάση αυτή περιέχει όλες τις πληροφορίες και τις μεθόδους που έχουν σχέση
 * με την έννοια του παίχτη.
 *
 * @author thanasis
 * @author tasosxak
 * @since 10/11/16
 * @version 1.0
 */
public class Player implements Serializable {

    private final String name;
    private final transient String keys;
    private Integer score;
    private int wins;

    public Player(String name, String keys) {
        this.name = name;
        this.keys = keys;
        score = null;
        wins = 0;
    }

    /**
     *
     * @param points Οι πόντοι που θα προστεθούν στο συνολικό σκορ
     */
    public void addPoints(int points) {
        if (score == null) {
            score = 0;
        }
        score += points;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Integer getScore() {
        return score;
    }

    public void addWin() {
        wins++;
    }

    public int getWins() {
        return wins;
    }

    public String getName() {
        return name;
    }

    /**
     *
     * @param key Συμβολοσειρά με έναν χαρακτήρα που δηλώνει το πλήκτρο το οποίο
     * θέλουμε να δούμε αν είναι αυτού του παίχτη.
     * @return Επιστρέφει true αν το key είναι πλήκτρο αυτού του πάιχτη. Αν το
     * key δεν είναι ένας χαρακτήρας ή δεν είναι πλήκτρο αυτού του παίχτη
     * επιστρέφει false.
     */
    public boolean isMyKey(String key) {
        return key.length() == 1 && keys.contains(key);
    }

    /**
     *
     * @param key Συμβολοσειρά με έναν χαρακτήρα ο οποίος είναι το πλήκτρο του
     * οποίου η θέση ζητείται.
     * @return Επιστρέφει το id του key για αυτόν τον παίκτη (πχ αν ο παίκτης
     * έχει τα πλήκτρα "asdf" τότε το id του 'a' είναι το 0, το id του 's' είναι
     * το 1 κτλ) αν ο παίκτης έχει τέτοιο πλήκτρο, αλλιώς επιστρέφει -1.
     */
    public int getKeyId(String key) {
        if (keys.contains(key)) {
            return keys.indexOf(key);
        } else {
            return -1;
        }
    }

    public String getAllKeys() {
        return this.keys;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Player)) {
            return false;
        }
        Player player = (Player) obj;
        return this.name.equals(player.getName());
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.name.hashCode();
        return hash;
    }

    /**
     *
     * @return Επιστρέφει το όνομα και το σκορ του παίχτη στη μορφή "Όνομα:
     * Σκορ".
     */
    @Override
    public String toString() {
        return this.name + ": " + this.score + ", " + this.wins;
    }

}
