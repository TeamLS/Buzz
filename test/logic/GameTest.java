package logic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author thanasis
 * @author tasosxak
 * @since 30/11/16
 * @version 1.0
 */
public class GameTest {

    private Game game;

    public GameTest() {
    }

    @Before
    public void setUp() throws IOException {
        game = new Game();
        game.initialize();
    }

    @After
    public void tearDown() {
    }

    /**
     *
     *
     */
    @Test
    public void testInitialize() {

        boolean shouldFail, failed;

        game.setLocale(new Locale("tt", "TT"));
        shouldFail = true;
        try {
            game.initialize();
            failed = false;
        } catch (IOException ex) {
            failed = true;
        }
        assertEquals(shouldFail, failed);

        game.setLocale(new Locale("en", "EN"));
        shouldFail = false;
        try {
            game.initialize();
            failed = false;
        } catch (IOException ex) {
            failed = true;
        }
        assertEquals(shouldFail, failed);

        game.setLocale(new Locale("gr", "GR"));
        shouldFail = false;
        try {
            game.initialize();
            failed = false;
        } catch (IOException ex) {
            failed = true;
        }
        assertEquals(shouldFail, failed);

    }

    /**
     * Test of addPlayer method, of class Game.
     */
    @Test
    public void testAddPlayer() {
        System.out.println("addPlayer");

        String keys;
        String expKeys;

        keys = game.addPlayer("");
        expKeys = null;
        assertEquals(expKeys, keys);

        keys = game.addPlayer("   ");
        expKeys = null;
        assertEquals(expKeys, keys);

        keys = game.addPlayer("TestName");
        expKeys = "asdf";
        assertEquals(expKeys, keys);

        keys = game.addPlayer("TestName");
        expKeys = null;
        assertEquals(expKeys, keys);

        keys = game.addPlayer("TestName2");
        expKeys = "hjkl";
        assertEquals(expKeys, keys);

        keys = game.addPlayer("TestName3");
        expKeys = null;
        assertEquals(expKeys, keys);

    }

    /**
     * Test of canContinue method, of class Game.
     */
    @Test
    public void testCanContinue() {
        System.out.println("canContinue");

        boolean canContinue;

        game.addPlayer("TestName");
        game.start();

        int numRounds = game.getNumOfRounds();
        for (int i = 0; i < numRounds; i++) {
            canContinue = game.canContinue();
            assertEquals(true, canContinue);
        }

        canContinue = game.canContinue();
        assertEquals(false, canContinue);
    }

    /**
     * Test of getCurQuestion method, of class Game.
     */
    @Test
    public void testGetCurQuestion() {
        System.out.println("getCurQuestion");

        Question question;
        boolean isNull;
        for (int i = 0; i < 500; i++) {
            question = game.getCurQuestion();
            isNull = (question == null);
            assertEquals(false, isNull);
        }

    }

    /**
     * Test of inSameRound method, of class Game.
     *
     * @throws java.io.IOException
     */
    @Test
    public void testInSameRound() throws IOException {
        System.out.println("InSameRound");

        boolean inSameRound;
        int executeTimes;
        Round curRound;
        List<Integer> randoms = new ArrayList<>();
        randoms.add(0);
        randoms.add(1);
        randoms.add(2);
        randoms.add(3);

        // Τεστ για 1 παίχτη
        game.addPlayer("TestName");

        game.start();

        while (game.canContinue()) {
            curRound = game.getCurRound();
            executeTimes = curRound.getExecuteTimes();

            if (curRound instanceof CorrectAnswerRound) {
                for (int i = 0; i < executeTimes; i++) {
                    inSameRound = game.inSameRound();
                    assertEquals(true, inSameRound);
                    game.getCurQuestion();
                    game.executeRound("a", randoms);
                    curRound.executed();
                }
            } else if (curRound instanceof BetRound) {
                for (int i = 0; i < executeTimes; i++) {
                    inSameRound = game.inSameRound();
                    assertEquals(true, inSameRound);
                    game.getCurQuestion();
                    game.getCurRound().setBonus(250);
                    game.executeRound("a", randoms);
                    curRound.executed();
                }

            } else if (curRound instanceof CountDownRound) {
                for (int i = 0; i < executeTimes; i++) {
                    inSameRound = game.inSameRound();
                    assertEquals(true, inSameRound);
                    game.getCurQuestion();
                    game.executeRound("a", randoms);
                    curRound.executed();
                }

            }
            inSameRound = game.inSameRound();
            assertEquals(false, inSameRound);
        }

        game.initialize(); // Επανεκκίνηση του παιχνιδιού

        // Τεστ για 2 παίχτες
        game.addPlayer("TestName");
        game.addPlayer("TestName2");

        game.start();

        while (game.canContinue()) {
            curRound = game.getCurRound();
            executeTimes = curRound.getExecuteTimes();

            if (curRound instanceof CorrectAnswerRound) {
                for (int i = 0; i < executeTimes; i++) {

                    inSameRound = game.inSameRound();
                    assertEquals(true, inSameRound);
                    game.getCurQuestion();
                    game.executeRound("a", randoms);
                    game.executeRound("h", randoms);
                    curRound.executed();
                }
            } else if (curRound instanceof BetRound) {
                for (int i = 0; i < executeTimes; i++) {
                    inSameRound = game.inSameRound();
                    assertEquals(true, inSameRound);
                    game.getCurQuestion();
                    game.getCurRound().setBonus(250);
                    game.executeRound("a", randoms);
                    game.getCurQuestion();
                    game.getCurRound().setBonus(250);
                    game.executeRound("h", randoms);
                    curRound.executed();
                }

            } else if (curRound instanceof CountDownRound) {
                for (int i = 0; i < executeTimes; i++) {
                    inSameRound = game.inSameRound();
                    assertEquals(true, inSameRound);
                    game.getCurQuestion();
                    game.executeRound("a", randoms);
                    game.executeRound("h", randoms);
                    curRound.executed();
                }
            } else if (curRound instanceof FastAnswerRound) {
                for (int i = 0; i < executeTimes; i++) {
                    inSameRound = game.inSameRound();
                    assertEquals(true, inSameRound);
                    game.getCurQuestion();
                    game.executeRound("a", randoms);
                    game.executeRound("h", randoms);
                    curRound.executed();
                }
            } else if (curRound instanceof ThermometerRound) {
                for (int i = 0; i < executeTimes; i++) {
                    while (!((ThermometerRound) curRound).hasPlayerWon(1)) {
                        inSameRound = game.inSameRound();
                        assertEquals(true, inSameRound);
                        game.getCurQuestion();
                        game.executeRound("a", randoms);
                    }
                    curRound.executed();
                }
            }
            inSameRound = game.inSameRound();
            assertEquals(false, inSameRound);
        }

    }

    /**
     * Test of isValidKey method, of class Game.
     */
    @Test
    public void testIsValidKey() {
        System.out.println("isValidKey");

        boolean isValidKey = game.isValidKey("a");
        assertEquals(false, isValidKey);

        game.addPlayer("TestName");
        isValidKey = game.isValidKey("a");
        assertEquals(true, isValidKey);

        isValidKey = game.isValidKey("p");
        assertEquals(false, isValidKey);

        isValidKey = game.isValidKey("string");
        assertEquals(false, isValidKey);

        game.addPlayer("TestName2");
        isValidKey = game.isValidKey("h");
        assertEquals(true, isValidKey);
    }

    /**
     * Test of isCorrectByAnswer method, of class Game.
     */
    @Test
    public void testIsCorrectByAnswer() {
        System.out.println("isCorrectByAnswer");

        game.addPlayer("TestName");
        game.start();

        List<Integer> randoms = new ArrayList<>();
        randoms.add(0);
        randoms.add(1);
        randoms.add(2);
        randoms.add(3);
        game.canContinue();
        game.getCurQuestion();
        boolean isCorrect = game.isCorrectByAnswer("a", randoms);

        /* Θα είναι σωστή η απάντηση αφού η λίστα randoms δεν είναι ανακατεμένη
         και άρα το a θα είναι η πρώτη πιθανή απάντηση που είναι πάντα σωστή. */
        assertEquals(true, isCorrect);

        game.getCurQuestion();
        isCorrect = game.isCorrectByAnswer("s", randoms);

        /* Θα είναι λάθος η απάντηση αφού η λίστα randoms δεν είναι ανακατεμένη
         και άρα το s θα είναι η δεύτερη πιθανή απάντηση που είναι πάντα λάθος. */
        assertEquals(false, isCorrect);
    }

    /**
     * Test of getNameOfPlayerByAnswer method, of class Game.
     */
    @Test
    public void testGetNameOfPlayerByAnswer() {
        game.addPlayer("TestName");
        game.start();

        game.canContinue();
        game.getCurQuestion();
        String name = game.getNameOfPlayerByAnswer("a");
        assertEquals("TestName", name);

        game.getCurQuestion();
        name = game.getNameOfPlayerByAnswer("h");
        assertEquals(null, name);

        game.getCurQuestion();
        name = game.getNameOfPlayerByAnswer("g");
        assertEquals(null, name);

        game.getCurQuestion();
        name = game.getNameOfPlayerByAnswer("string");
        assertEquals(null, name);

        try {
            game.initialize();
        } catch (IOException ex) {
            Logger.getLogger(GameTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        game.addPlayer("TestName");
        game.addPlayer("TestName2");

        name = game.getNameOfPlayerByAnswer("a");
        assertEquals("TestName", name);

        game.getCurQuestion();
        name = game.getNameOfPlayerByAnswer("h");
        assertEquals("TestName2", name);

        game.getCurQuestion();
        name = game.getNameOfPlayerByAnswer("g");
        assertEquals(null, name);

        game.getCurQuestion();
        name = game.getNameOfPlayerByAnswer("string");
        assertEquals(null, name);
    }

    /**
     * Test of executeRound method, of class Game.
     */
    @Test
    public void testExecuteRound() {
        System.out.println("executeRound");

        game.addPlayer("TestName");
        game.start();

        List<Integer> randoms = new ArrayList<>();
        randoms.add(0);
        randoms.add(1);
        randoms.add(2);
        randoms.add(3);
        boolean executedRight;

        Round curRound;
        while (game.canContinue()) {
            curRound = game.getCurRound();
            game.getCurQuestion();

            executedRight = game.executeRound("g", randoms);
            assertEquals(false, executedRight);

            executedRight = game.executeRound("string", randoms);
            assertEquals(false, executedRight);

            executedRight = game.executeRound("a", randoms);
            assertEquals(true, executedRight);

            curRound.executed();

        }

    }

    /**
     * Test of getScoreByPlayerId method, of class Game.
     */
    @Test
    public void testGetScoreByPlayerId() {
        System.out.println("getScoreByPlayerId");

        Integer score = game.getScoreByPlayerId(0);
        assertEquals(null, score);

        score = game.getScoreByPlayerId(1);
        assertEquals(null, score);

        game.addPlayer("TestName");
        score = game.getScoreByPlayerId(1);
        assertEquals(new Integer(0), score);

        score = game.getScoreByPlayerId(2);
        assertEquals(null, score);

        game.addPlayer("TestName2");
        score = game.getScoreByPlayerId(2);
        assertEquals(new Integer(0), score);

    }

    /**
     * Test of getNameByPlayerId method, of class Game.
     */
    @Test
    public void testGetNameByPlayerId() {
        System.out.println("getNameByPlayerId");

        String name = game.getNameByPlayerId(0);
        assertEquals(null, name);

        name = game.getNameByPlayerId(1);
        assertEquals(null, name);

        game.addPlayer("TestName");
        name = game.getNameByPlayerId(1);
        assertEquals("TestName", name);

        name = game.getNameByPlayerId(2);
        assertEquals(null, name);

        game.addPlayer("TestName2");
        name = game.getNameByPlayerId(2);
        assertEquals("TestName2", name);
    }

    /**
     * Test of start method, of class Game.
     *
     * @throws java.io.IOException
     */
    @Test
    public void testStart() throws IOException {
        System.out.println("start");

        Round curRound;

        // Έλλεγχος για 1 παίχτη
        game.addPlayer("TestName");
        game.start();

        while (game.canContinue()) {

            while (game.inSameRound()) {
                curRound = game.getCurRound();
                assertTrue(curRound instanceof CorrectAnswerRound || curRound instanceof BetRound || curRound instanceof CountDownRound);
                curRound.executed();
            }

        }

        game.initialize(); // Επανεκκίνηση του παιχνιδιού

        // Έλεγχος για 2 παίχτες
        game.addPlayer("TestName");
        game.addPlayer("TestName2");
        game.start();

        while (game.canContinue()) {

            while (game.inSameRound()) {
                curRound = game.getCurRound();
                assertTrue(curRound != null);
                curRound.executed();
            }

        }

    }

    /**
     * Test of getPlayersLeftToAdd method, of class Game.
     */
    @Test
    public void testGetPlayersLeftToAdd() {

        int maxPlayers = game.getMaxPlayers();

        int i = 0;
        while (game.getPlayersLeftToAdd() > 0) {
            assertEquals(maxPlayers - i, game.getPlayersLeftToAdd());
            game.addPlayer("TestName" + i);
            i++;
        }

    }

    /**
     * Test of getNextID method, of class Game.
     */
    @Test
    public void testGetNextID() {

        int player_id;
        
        game.addPlayer("TestName");
        game.addPlayer("TestName2");        

        player_id = game.getNextID(false);
        assertEquals(1, player_id);

        player_id = game.getNextID(true);
        assertEquals(2, player_id);
        
        player_id = game.getNextID(false);
        assertEquals(2, player_id);
        
        player_id = game.getNextID(true);
        assertEquals(1, player_id);
        
        player_id = game.getNextID(false);
        assertEquals(1, player_id);

    }

    /**
     * Test of canPlay method, of class Game.
     */
    @Test
    public void testCanPlay() {
        
        boolean canPlay;
        
        game.addPlayer("TestName");
        game.addPlayer("TestName2");
        
        canPlay = game.canPlay(0);
        assertEquals(false, canPlay);
        
        canPlay = game.canPlay(3);
        assertEquals(false, canPlay);
        
        canPlay = game.canPlay(2);
        assertEquals(true, canPlay);
        
        canPlay = game.canPlay(2);
        assertEquals(false, canPlay);
        
        canPlay = game.canPlay(1);
        assertEquals(true, canPlay);
        
        canPlay = game.canPlay(1);
        assertEquals(false, canPlay);
        
    }

    /**
     * Test of allHavePlayed method, of class Game.
     */
    @Test
    public void testAllHavePlayed() {
        System.out.println("allHavePlayed");
        
        
        boolean allHavePlayed;
        
        
        game.addPlayer("TestName");
        game.addPlayer("TestName2");
        
        
        allHavePlayed = game.allHavePlayed();
        assertEquals(false, allHavePlayed);
        
        game.canPlay(2);
        
        allHavePlayed = game.allHavePlayed();
        assertEquals(false, allHavePlayed);
        
        game.canPlay(1);
        
        allHavePlayed = game.allHavePlayed();
        assertEquals(true, allHavePlayed);
        
    }

    /**
     * Test of gameOver method, of class Game.
     */
    @Test
    public void testGameOver() {
        
        boolean failed;
        
        game.addPlayer("TestName");
        
        try{
            game.gameOver();
            failed = false;
        } catch (IOException ex) {
            failed = true;
        }
        
        assertEquals(false, failed);
        
    }

}
