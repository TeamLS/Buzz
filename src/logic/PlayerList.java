package logic;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * Αυτή η κλάση χειρίζεται το σύνολο των παιχτών που συμμετέχουν στο παιχνίδι.
 * Περιέχει μία λίστα από αντικείμενα τύπου Player και μεθόδους που επιτρέπουν
 * την προσθήκη νέου παίχτη, την αλλαγή του σκορ του, καθώς και μεθόδους
 * πρόσβασης στα στοιχεία του όπως το όνομα, τα πλήκτρα και το σκορ. *
 *
 * @author thanasis
 * @author tasosxak
 * @since 9/11/16
 * @version 1.0
 */
public class PlayerList implements Serializable {

    private final Map<Integer, Player> players;
    private int num = 0;

    public PlayerList() {
        players = new HashMap<>();
    }

    /**
     *
     * Αυτή η μέθοδος προσθέτει έναν παίχτη στην λίστα των παιχτών με όνομα και
     * πλήκτρα αυτά που θα πάρει ως παραμέτρους, αφού ελέγξει αν το όνομα
     * περιέχει χαρακτήρες (δεν είναι η κενή συμβολοσειρά), αν τα πλήκτρα είναι
     * ακριβώς 4 και αν δεν υπάρχει άλλος παίχτης με το ίδιο όνομα ή με κάποιο
     * κοινό πλήκτρο.
     *
     * @param name Το όνομα του νέου παίκτη.
     * @param keys Τα πλήκτρα που αντιστοιχούν στον παίχτη (πχ "asdf").
     * @return Επιστρέφει true αν έγινε εισαγωγή του παίχτη, αλλιώς false.
     */
    public boolean addPlayer(String name, String keys) {
        if (keys.length() == 4 && name.length() > 0) {
            for (Player player : players.values()) {

                // Έλλεγχος αν υπάρχει άλλος παίχτης με όνομα name.
                if (player.getName().equals(name)) {
                    return false;
                }

                /* Έλλεγχος αν υπάρχει κάποιο πλήκτρο από αυτά που
                 δόθηκαν (keys), χρησιμοποιείται ήδη από άλλο παίχτη. */
                for (int i = 0; i < 4; i++) {
                    if (player.getAllKeys().contains(Character.toString(keys.charAt(i)))) {
                        return false;
                    }
                }
            }

            // Εισαγωγή του παίχτη στο HashMap.
            Player player = new Player(name, keys);
            players.put(++num, player);
            return true;
        }
        return false;
    }

    /**
     *
     * @param points Οι πόντοι που θα προστεθούν στον παίκτη.
     * @param player_id Το id του παίκτη (Ποιος παίκτης είναι: 1 ή 2) του οποίου
     * το σκορ μεταβάλλεται.
     */
    public void addPointsToPlayer(int points, int player_id) {
        if (players.containsKey(player_id)) {
            players.get(player_id).addPoints(points);
        }
    }

    /**
     *
     * @param player_id Το id του παίκτη (Ποιος παίκτης είναι: 1 ή 2) του οποίου
     * το σκορ ζητείται.
     * @return Επιστρέφει το σκορ του παίκτη αν ο παίκτης υπάρχει, αλλιώς null.
     */
    public Integer getScoreOfPlayer(int player_id) {
        if (players.containsKey(player_id)) {
            Integer score = players.get(player_id).getScore();
            if (score != null) {
                return score;
            } else {
                return 0;
            }
        } else {
            return null;
        }
    }

    /**
     *
     * @param player_id Το id του παίκτη (Ποιος παίκτης είναι: 1 ή 2) του οποίου
     * το όνομα ζητείται.
     * @return Επιστρέφει το όνομα του παίκτη αν ο παίκτης υπάρχει, αλλιώς null
     */
    public String getNameOfPlayer(int player_id) {
        if (players.containsKey(player_id)) {
            return players.get(player_id).getName();
        } else {
            return null;
        }
    }

    public int getNumOfPlayers() {
        return num;
    }

    /**
     *
     * @param key Συμβολοσειρά με έναν χαρακτήρα ο οποίος είναι το πλήκτρο του
     * οποίου η θέση ζητείται.
     * @param player_id Το id του παίκτη (Ποιος παίκτης είναι: 1 ή 2) για τον
     * οποίο ζητούμε το id του πλήκτρου.
     * @return Επιστρέφει το id του key για αυτόν τον παίκτη (πχ αν ο παίκτης
     * έχει τα πλήκτρα "asdf" τότε το id του 'a' είναι το 0, το id του 's' είναι
     * το 1 κτλ) αν υπάρχει παίκτης που έχει τέτοιο πλήκτρο, αλλιώς επιστρέφει
     * -1.
     */
    public int getKeyIdOfPlayer(String key, int player_id) {
        if (players.containsKey(player_id) && players.get(player_id).isMyKey(key)) {
            return players.get(player_id).getKeyId(key);
        } else {
            return -1;
        }
    }

    /**
     *
     * @param key Συμβολοσειρά με έναν χαρακτήρα ο οποίος είναι το πλήκτρο που
     * θέλουμε να δούμε αν αντιστοιχεί σε κάποιον παίχτη.
     * @return Επιστρέφει true αν το πλήκτρο (key) ανήκει σε κάποιον παίχτη,
     * αλλιώς false.
     */
    public boolean isSomeonesKey(String key) {
        for (Player player : players.values()) {
            if (player.isMyKey(key)) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @return Επιστρέφει μία συμβολοσειρά με όλα τα πλήκτρα όλων των παιχτών
     * (πχ "asdfhjkl").
     */
    public String getAllKeys() {
        String keys = "";
        for (Player player : players.values()) {
            keys += player.getAllKeys();
        }
        return keys;
    }

    /**
     *
     * @param player_id Το id του παίκτη (Ποιος παίκτης είναι: 1 ή 2) του οποίου
     * τα πλήκτρα ζητούνται.
     * @return Επιστρέφει μια συμβολοσειρά με τα πλήκτρα του παίχτη που έχει το
     * id που δόθηκε ως παράμετρος (πχ "asdf") ή null αν ο παίχτης δεν υπάρχει.
     */
    public String getKeysOfPlayer(int player_id) {
        if (players.containsKey(player_id)) {
            return players.get(player_id).getAllKeys();
        } else {
            return null;
        }
    }

    /**
     *
     * @param key Συμβολοσειρά με έναν χαρακτήρα ο οποίος είναι το πλήκτρο που
     * θέλουμε να δούμε σε ποιόν παίχτη αντιστοιχεί.
     * @return Επιστρέφει το id του παίχτη στον οποίο αντιστοιχεί το πλήκτρο
     * (key) αν υπάρχει, αλλιώς επιστρέφει -1.
     */
    public int whoseKeyIs(String key) {
        if (isSomeonesKey(key)) {
            for (Integer player_id : players.keySet()) {
                if (players.get(player_id).isMyKey(key)) {
                    return player_id;
                }
            }
        }
        return -1;
    }

    /**
     *
     * @param key Συμβολοσειρά με έναν χαρακτήρα ο οποίος είναι το πλήκτρο που
     * θέλουμε να δούμε αν ανήκει στον παίκτη με id ίσο με player_id.
     * @param player_id Το id του παίκτη (Ποιος παίκτης είναι: 1 ή 2) για τον
     * οποίο ζητούμε το id του πλήκτρου.
     * @return Επιστρέφει true αν το πλήκτρο (key) ανήκει στον παίκτη με id ίσο
     * με player_id.
     */
    public boolean isKeyOfPlayer(String key, int player_id) {
        return players.containsKey(player_id) && players.get(player_id).isMyKey(key);
    }

    /**
     *
     * @return Επιστρέφει το id του παίχτη που νίκησε, ή 0 αν έληξε με ισοπαλία. 
     */
    public int getWinnersId() {
        if (getNumOfPlayers() > 1) {
            int[] scores = getScoreTableScores();
            String[] names = getScoreTableNames();

            if (scores[0] != scores[1]) {
                return getIdByPlayerName(names[0]);
            }
        }

        return 0;

    }

    /**
     *
     * @param name Το όνομα του χρήστη του οποίο το id ζητείται.
     * @return Επιστρέφει το id του παίχτη με όνομα name, ή 0 αν δεν  υπάρχει τέτοιος παίχτης.
     */
    public int getIdByPlayerName(String name) {
        for (int id : players.keySet()) {
            if (players.get(id).getName().equals(name)) {
                return id;
            }
        }
        return 0;
    }

    /**
     *
     * @return Επιστρέφει έναν πίνακα με τα ονόματα των παιχτών που έπαιξαν,
     * σε φθήνουσα σειρά με βάση το σκορ.
     */
    public String[] getScoreTableNames() {
        HighScoresList highScores = new HighScoresList();
        for (Player player : players.values()) {
            String name = player.getName();
            int score = 0;
            if (player.getScore() != null) {
                score = player.getScore();
            }
            highScores.addPlayer(name);
            highScores.setScoreOfPlayer(score, name);
        }
        return highScores.getNamesForHighScores(num);
    }
    
    /**
     *
     * @return Επιστρέφει έναν πίνακα με τα σκορ των παιχτών που έπαιξαν,
     * σε φθήνουσα σειρά με βάση το σκορ.
     */
    public int[] getScoreTableScores() {
        HighScoresList highScores = new HighScoresList();
        for (Player player : players.values()) {
            String name = player.getName();
            int score = 0;
            if (player.getScore() != null) {
                score = player.getScore();
            }
            highScores.addPlayer(name);
            highScores.setScoreOfPlayer(score, name);
        }

        // Μετατροπή των null σε 0
        Integer[] scores = highScores.getHighScores(num);
        int[] results = new int[num];
        for (int i = 0; i < num; i++) {
            if (scores[i] == null) {
                results[i] = 0;
            } else {
                results[i] = scores[i];
            }
        }
        return results;
    }

    /**
     *
     * @return Επιστρέφει το όνομα και το σκορ από κάθε παίχτη στην μορφή
     * "Παίχτης1: Σκορ1 Παίχτης2: Σκορ2"
     */
    @Override
    public String toString() {
        String result = "";
        for (Player player : players.values()) {
            result += player + "\n"; // εδώ καλείται η toString της player
        }
        return result;
    }

}
