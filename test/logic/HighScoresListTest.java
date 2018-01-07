
package logic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author thanasis
 * @author tasosxak
 * @since 18/1/17
 * @version 1.0
 */

public class HighScoresListTest {

    private HighScoresList list;

    public HighScoresListTest() {
    }

    @Before
    public void setUp() {

        list = new HighScoresList();
        list.addPlayer("Player 1");
        list.addPlayer("Player 2");
        list.addPlayer("Player 3");

        list.playerWon("Player 2");
        list.playerWon("Player 2");
        list.playerWon("Player 3");

        // Αρα Player 1 -> 0 νικες, Player 2 -> 2 νίκες, Player 3 -> 1 νίκη.
        
        list.setScoreOfPlayer(-10, "Player 1");
        list.setScoreOfPlayer(1020, "Player 2");

        // Αρα Player 1 -> -10 , Player 2 -> 1020, Player 3 -> null.
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addPlayer method, of class HighScoresList.
     */
    @Test
    public void testAddPlayer() {
        System.out.println("addPlayer");

        assertEquals(true, list.addPlayer("Player 4"));
        assertEquals(false, list.addPlayer("Player 1"));

    }

    /**
     * Test of setScoreOfPlayer method, of class HighScoresList.
     */
    @Test
    public void testSetScoreOfPlayer() {

        System.out.println("setScoreOfPlayer");

        assertTrue(!list.setScoreOfPlayer(0, "TestName"));
        assertTrue(list.setScoreOfPlayer(100, "Player 1"));

    }

    /**
     * Test of getScoreOfPlayer method, of class HighScoresList.
     */
    @Test
    public void testGetScoreOfPlayer() {
        System.out.println("getScoreOfPlayer");

        assertEquals(null, list.getScoreOfPlayer("TestName"));
        assertEquals(null, list.getScoreOfPlayer("Player 3"));

        assertTrue(list.getScoreOfPlayer("Player 1").equals(-10));
        assertTrue(list.getScoreOfPlayer("Player 2").equals(1020));

    }

    /**
     * Test of playerExists method, of class HighScoresList.
     */
    @Test
    public void testPlayerExists() {
        System.out.println("playerExists");
        
        assertEquals(true, list.playerExists("Player 1"));
        assertEquals(false, list.playerExists("Player 4"));
        
    }

    /**
     * Test of playerWon method, of class HighScoresList.
     */
    @Test
    public void testPlayerWon() {
        System.out.println("playerWon");
        
        assertTrue(list.playerWon("Player 1"));    
        assertEquals(1, list.getWinsOfPlayer("Player 1"));        
        
        assertTrue(!list.playerWon("Player 4"));
        
    }

    /**
     * Test of getNamesForWins method, of class HighScoresList.
     */
    @Test
    public void testGetNamesForWins() {

        System.out.println("getNamesForWins");

        String[] names = list.getNamesForWins(3);
        String expectedName;

        expectedName = "Player 2";
        assertEquals(expectedName, names[0]);

        expectedName = "Player 3";
        assertEquals(expectedName, names[1]);

        expectedName = "Player 1";
        assertEquals(expectedName, names[2]);

    }

    /**
     * Test of getTopWins method, of class HighScoresList.
     */
    @Test
    public void testGetTopWins() {
        System.out.println("getTopWins");

        Integer[] wins = list.getTopWins(3);
        Integer expectedWins;

        expectedWins = 2;
        assertEquals(expectedWins, wins[0]);

        expectedWins = 1;
        assertEquals(expectedWins, wins[1]);

        expectedWins = 0;
        assertEquals(expectedWins, wins[2]);
    }

    /**
     * Test of getNamesForHighScores method, of class HighScoresList.
     */
    @Test
    public void testGetNamesForHighScores() {

        System.out.println("getNamesForHighScores");

        String[] names = list.getNamesForHighScores(3);
        String expectedName;

        expectedName = "Player 2";
        assertEquals(expectedName, names[0]);

        expectedName = "Player 1";
        assertEquals(expectedName, names[1]);

        expectedName = "Player 3";
        assertEquals(expectedName, names[2]);
    }

    /**
     * Test of getHighScores method, of class HighScoresList.
     */
    @Test
    public void testGetHighScores() {

        System.out.println("getHighScores");

        Integer[] scores = list.getHighScores(3);
        Integer expectedScore;

        expectedScore = 1020;
        assertEquals(expectedScore, scores[0]);

        expectedScore = -10;
        assertEquals(expectedScore, scores[1]);

        expectedScore = null;
        assertEquals(expectedScore, scores[2]);
    }


}
