package logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 *
 * Αυτή η κλάση χρησιμοποιείται για την αποθήκευση των παιχτών από την
 * HighScores. Περιέχει μία λίστα από αντικείμενα τύπου Player και μεθόδους που
 * επιτρέπουν την προσθήκη νέου παίχτη, την αλλαγή του σκορ του, καθώς και
 * μεθόδους πρόσβασης στα στοιχεία του όπως το όνομα, τα πλήκτρα και το σκορ.
 * Επίσης υπάρχουν μέθοδοι που επιστρέφουν τα ονόματα τα στοιχεία των παιχτών
 * ταξινομημένα ανά σκορ ή ανά νίκη.
 *
 * @author thanasis
 * @author tasosxak
 * @since 9/11/16
 * @version 1.0
 */
public class HighScoresList implements Serializable {

    private final List<Player> players;
    private int num = 0;

    public HighScoresList() {
        players = new ArrayList<>();
    }

    /**
     *
     * Αυτή η μέθοδος προσθέτει έναν παίχτη στην λίστα των παιχτών με όνομα αυτό
     * που θα πάρει ως παράμετρο, αφού ελέγξει αν δεν υπάρχει άλλος παίχτης με
     * το ίδιο όνομα.
     *
     * @param name Το όνομα του νέου παίκτη.
     * @return Επιστρέφει true αν έγινε εισαγωγή του παίχτη, αλλιώς false.
     */
    public boolean addPlayer(String name) {

        // Εισαγωγή του παίχτη στο HashMap.
        Player player = new Player(name, "abcd");
        if (!playerExists(name)) {
            return players.add(player);
        } else {
            return false;
        }

    }

    /**
     *
     * @param score Το σκορ που θα πάρει ο παίχτης.
     * @param name Το όνομα του παίχτη του οποίο το σκόρ θέλουμε να αλλάξει.
     */
    public boolean setScoreOfPlayer(int score, String name) {
        for (Player player : players) {
            if (player.getName().equals(name)) {
                player.setScore(score);
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @return Επιστρέφει τον αριθμό των παιχτών που υπάρχουν στη λίστα
     */
    public int getNumOfPlayers() {
        return players.size();
    }

    /**
     *
     * @param name Το όνομα του παίχτη του οποίου το σκορ ζητείται.
     * @return Επιστρέφει το σκορ του παίκτη με όνομα name αν ο παίκτης υπάρχει
     * και έχει παίξει ατομικό παιχνίδι, αλλιώς null.
     */
    public Integer getScoreOfPlayer(String name) {
        for (Player player : players) {
            if (player.getName().equals(name)) {
                return player.getScore();
            }
        }
        return null;
    }

    /**
     *
     * @param name Το όνομα του παίχτη του οποίου το πλήθος νικών ζητείται.
     * @return Επιστρέφει το πλήθος των νικών του παίχτη με όνομα name.
     */
    public int getWinsOfPlayer(String name) {
        for (Player player : players) {
            if (player.getName().equals(name)) {
                return player.getWins();
            }
        }
        return -1;
    }

    private Integer getScoreByPlace(int place) {
        if (players.size() > place) {
            return players.get(place).getScore();
        }
        return null;
    }

    private Integer getWinsByPlace(int place) {
        if (players.size() > place) {
            return players.get(place).getWins();
        }
        return null;
    }

    private String getNameByPlace(int place) {
        if (players.size() > place) {
            return players.get(place).getName();
        }
        return null;
    }

    //Comparator: http://docs.oracle.com/javase/tutorial/collections/interfaces/order.html
    private void sortByWins() {
        // Ορίζει διάταξη στους παίχτες με βάση τις νίκες τους.
        Collections.sort(players, Collections.reverseOrder(new Comparator<Player>() {
            @Override
            public int compare(Player p1, Player p2) {
                if (p1.getWins() > p2.getWins()) {
                    return 1;
                } else if (p1.getWins() == p2.getWins()) {
                    return 0;
                } else {
                    return -1;
                }
            }
        }));
    }

    private void sortByScore() {
        // Ορίζει διάταξη στους παίχτες με βάση τα σκορ τους.
        Collections.sort(players, Collections.reverseOrder(new Comparator<Player>() {
            @Override
            public int compare(Player p1, Player p2) {

                if (p1.getScore() == null) {
                    if (p2.getScore() == null) {
                        return 0;
                    }
                    return -1;
                }

                if (p2.getScore() == null) {
                    if (p1.getScore() == null) {
                        return 0;
                    }
                    return 1;
                }

                if (Objects.equals(p1.getScore(), p2.getScore())) {
                    return 0;
                }

                if (p1.getScore() > p2.getScore()) {
                    return 1;
                }

                return -1;
            }
        }));
    }

    /**
     *
     * @param name Το όνομα του παίχτη τον οποίο θέλουμε να ελέγξουμε αν υπάρχει
     * στη λίστα.
     * @return Επιστρέφει true αν ο παίχτης υπήρχε στη λίστα, αλλιώς false
     */
    public boolean playerExists(String name) {
        for (Player player : players) {
            if (player.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param name Το όνομα του παίχτη του οποίου τις νίκες θέλουμε να
     * αυξήσουμε.
     * @return Επιστρέφει true αν ο παίχτης με όνομα name βρέθηκε και προστέθηκε
     * σε αυτόν μία νίκη.
     */
    public boolean playerWon(String name) {
        for (Player player : players) {
            if (player.getName().equals(name)) {
                player.addWin();
                return true;
            }
        }
        return false;
    }

    /**
     * H getNamesForWins χρησιμοποιείται όταν θέλουμε να πάρουμε τα όνοματα των
     * παιχτών από το αρχείο των σκορ κατά φθίνουσα σειρά με βάση τις νίκες
     * τους.
     *
     * @param num Το πλήθος των παιχτών . Π.χ. τους 5 παίχτες με τις
     * περισσότερες νικές (num = 5)
     * @return Πίνακα με τα ονόματα των παιχτών ταξινομημένα με βάση τις νίκες
     * τους.
     */
    public String[] getNamesForWins(int num) {
        if (num > 0) {
            sortByWins();
            String[] names = new String[num];
            for (int i = 0; i < num; i++) {
                names[i] = getNameByPlace(i);
            }
            return names;
        }
        return null;
    }

     /**
     * H getTopWins χρησιμοποιείται όταν θέλουμε να πάρουμε τις νίκες των
     * παιχτών από το αρχείο των σκορ κατά φθίνουσα σειρά.
     *
     * @param num Το πλήθος των παιχτών . Π.χ. τους 5 παίχτες με τις
     * περισσότερες νικές (num = 5)
     * @return Ταξινομημένο πίνακα με τις νίκες των παιχτών.
     */
    
    public Integer[] getTopWins(int num) {
        if (num > 0) {
            sortByWins();
            Integer[] wins = new Integer[num];
            for (int i = 0; i < num; i++) {
                wins[i] = getWinsByPlace(i);
            }
            return wins;
        }
        return null;
    }

     /**
     * H getNamesForHighScores χρησιμοποιείται όταν θέλουμε να πάρουμε τα
     * όνοματα των παιχτών από το αρχείο των σκορ κατά φθίνουσα σειρά
     * με βάση το σκορ τους .
     *
     * @param num Το πλήθος των παιχτών . Π.χ. τους 5 παίχτες με το υψηλότερο
     * σκορ (num = 5).
     * @return Πίνακα με τα ονόματα των παιχτών ταξινομημένα με βάση το σκορ
     * τους.
     */
    
    public String[] getNamesForHighScores(int num) {
        if (num > 0) {
            sortByScore();
            String[] names = new String[num];
            for (int i = 0; i < num; i++) {
                names[i] = getNameByPlace(i);
            }
            return names;
        }
        return null;
    }
    
    /**
     * H getHighScores χρησιμοποιείται όταν θέλουμε να πάρουμε τα σκορ των
     * παιχτών από το αρχείο των σκορ κατά φθίνουσα σειρά.
     *
     * @param num Το πλήθος των παιχτών . Π.χ. τους 5 παίχτες με το υψηλότερο
     * σκορ (num = 5)
     * @return Ταξινομημένο πίνακα με τα σκορ των παιχτών.
     */
    public Integer[] getHighScores(int num) {
        if (num > 0) {
            sortByScore();
            Integer[] scores = new Integer[num];
            for (int i = 0; i < num; i++) {
                scores[i] = getScoreByPlace(i);
            }
            return scores;
        }
        return null;
    }

    /**
     *
     * @return Επιστρέφει το όνομα και το σκορ από κάθε παίχτη στην μορφή
     * "Παίχτης1: Σκορ1 Παίχτης2: Σκορ2"
     */
    @Override
    public String toString() {
        String result = "";
        for (Player player : players) {
            result += player + "\n"; // εδώ καλείται η toString της player
        }
        return result;
    }

}
