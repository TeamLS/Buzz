package logic;

import java.io.File;
import java.io.IOException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 * @author thanasis
 * @author tasosxak
 * @since 18/1/17
 * @version 1.0
 */
public class HighScoresTest {

    private HighScores highScores;

    public HighScoresTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {

        /*
        Διαγραφή του αρχείου scores.ser για να υπάρχουν μόνο οι παίχτες που
        θα προστεθούν παρακάτω (Player 1, Player 2, Player 3), ώστε να ελεγχτούν
        σωστά από τις μεθόδους αυτού του test.
         */
        
        File file = new File("scores.ser");
        file.delete();

        HighScores highScores = new HighScores();

        PlayerList curPlayers = new PlayerList();
        curPlayers.addPlayer("Player 1", "asdf");
        curPlayers.addPlayer("Player 2", "hjkl");
        curPlayers.addPointsToPlayer(500, 1);
        curPlayers.addPointsToPlayer(1200, 2);

        highScores.writeFile(curPlayers);

        curPlayers = new PlayerList();
        curPlayers.addPlayer("Player 3", "asdf");
        curPlayers.addPlayer("Player 2", "hjkl");
        curPlayers.addPointsToPlayer(1500, 1);
        curPlayers.addPointsToPlayer(1200, 2);

        highScores.writeFile(curPlayers);

        curPlayers = new PlayerList();
        curPlayers.addPlayer("Player 2", "asdf");
        curPlayers.addPlayer("Player 1", "hjkl");
        curPlayers.addPointsToPlayer(1800, 1);
        curPlayers.addPointsToPlayer(-500, 2);

        highScores.writeFile(curPlayers);

        // Αρα Player 1 -> 0 νικες, Player 2 -> 2 νίκες, Player 3 -> 1 νίκη.
        curPlayers = new PlayerList();
        curPlayers.addPlayer("Player 1", "asdf");
        curPlayers.addPointsToPlayer(-10, 1);

        highScores.writeFile(curPlayers);

        curPlayers = new PlayerList();
        curPlayers.addPlayer("Player 2", "asdf");
        curPlayers.addPointsToPlayer(1000, 1);

        highScores.writeFile(curPlayers);

        curPlayers = new PlayerList();
        curPlayers.addPlayer("Player 2", "asdf");
        curPlayers.addPointsToPlayer(1020, 1);

        highScores.writeFile(curPlayers);

        // Αρα Player 1 -> -10 , Player 2 -> 1020, Player 3 -> null.
    }

    @Before
    public void setUp() {

        highScores = new HighScores();

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of writeFile method, of class HighScores.
     */
    @Test
    public void testWriteFile() {

        System.out.println("writeFile");

        PlayerList curPlayers = new PlayerList();

        boolean failed;

        try {
            highScores.writeFile(curPlayers);
            failed = false;
        } catch (IOException ex) {
            failed = true;
        }

        assertEquals(false, failed);

    }

    /**
     * Test of getNamesForWins method, of class HighScores.
     *
     */
    @Test
    public void testGetNamesForWins() {

        System.out.println("getNamesForWins");

        // Υποθεση: το αρχείο scores.ser έχει μόνο τους παίχτες που προστέθηκαν από την setUpClass
        String[] names = highScores.getNamesForWins(3);
        String expectedName;

        expectedName = "Player 2";
        assertEquals(expectedName, names[0]);

        expectedName = "Player 3";
        assertEquals(expectedName, names[1]);

        expectedName = "Player 1";
        assertEquals(expectedName, names[2]);

    }

    /**
     * Test of getTopWins method, of class HighScores.
     */
    @Test
    public void testGetTopWins() {

        System.out.println("getTopWins");

        // Υποθεση: το αρχείο scores.ser έχει μόνο τους παίχτες που προστέθηκαν από την setUpClass
        Integer[] wins = highScores.getTopWins(3);
        Integer expectedWins;

        expectedWins = 2;
        assertEquals(expectedWins, wins[0]);

        expectedWins = 1;
        assertEquals(expectedWins, wins[1]);

        expectedWins = 0;
        assertEquals(expectedWins, wins[2]);
    }

    /**
     * Test of getNamesForHighScores method, of class HighScores.
     */
    @Test
    public void testGetNamesForHighScores() {

        System.out.println("getNamesForHighScores");

        // Υποθεση: το αρχείο scores.ser έχει μόνο τους παίχτες που προστέθηκαν από την setUpClass
        String[] names = highScores.getNamesForHighScores(3);
        String expectedName;

        expectedName = "Player 2";
        assertEquals(expectedName, names[0]);

        expectedName = "Player 1";
        assertEquals(expectedName, names[1]);

        expectedName = "Player 3";
        assertEquals(expectedName, names[2]);

    }

    /**
     * Test of getHighScores method, of class HighScores.
     */
    @Test
    public void testGetHighScores() {

        System.out.println("getHighScores");

        // Υποθεση: το αρχείο scores.ser έχει μόνο τους παίχτες που προστέθηκαν από την setUpClass
        Integer[] scores = highScores.getHighScores(3);
        Integer expectedScore;

        expectedScore = 1020;
        assertEquals(expectedScore, scores[0]);

        expectedScore = -10;
        assertEquals(expectedScore, scores[1]);

        expectedScore = null;
        assertEquals(expectedScore, scores[2]);

    }

}
