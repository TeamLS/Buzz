package logic;

import logic.Player;
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
public class PlayerTest {

    private Player player;

    public PlayerTest() {
    }

    @Before
    public void setUp() {
        player = new Player("TestName", "asdf");

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addPoints method, of class Player.
     */
    @Test
    public void testAddPoints() {
        System.out.println("addPoints");

        Integer expectedScore, score;
        
        expectedScore = null;
        score = player.getScore();
        assertEquals(expectedScore, score);

        player.addPoints(100);
        score = player.getScore();
        expectedScore = 100;
        assertEquals(expectedScore, score);

        player.addPoints(-200);
        score = player.getScore();
        expectedScore = -100;
        assertEquals(expectedScore, score);
    }

    /**
     * Test of getScore method, of class Player.
     */
    @Test
    public void testGetScore() {
        System.out.println("getScore");
        
        Integer expectedScore, score;
        
        expectedScore = null;
        score = player.getScore();
        assertEquals(expectedScore, score);

        player.addPoints(100);
        score = player.getScore();
        expectedScore = 100;
        assertEquals(expectedScore, score);

        player.addPoints(-200);
        score = player.getScore();
        expectedScore = -100;
        assertEquals(expectedScore, score);
    }

    /**
     * Test of getName method, of class Player.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");

        String name = player.getName();
        assertEquals("TestName", name);

        String testName = "TestName2";
        Player player2 = new Player(testName, "hjkl");
        name = player2.getName();
        assertEquals(testName, name);

    }

    /**
     * Test of isMyKey method, of class Player.
     */
    @Test
    public void testIsMyKey() {
        System.out.println("isMyKey");

        boolean isMyKey = player.isMyKey("as");
        assertEquals(false, isMyKey);

        isMyKey = player.isMyKey("g");
        assertEquals(false, isMyKey);

        isMyKey = player.isMyKey("a");
        assertEquals(true, isMyKey);
    }

    /**
     * Test of getKeyId method, of class Player.
     */
    @Test
    public void testGetKeyId() {
        System.out.println("getKeyId");

        int key_id = player.getKeyId("g");
        assertEquals(-1, key_id);

        key_id = player.getKeyId("a");
        assertEquals(0, key_id);

        key_id = player.getKeyId("s");
        assertEquals(1, key_id);

        key_id = player.getKeyId("d");
        assertEquals(2, key_id);

        key_id = player.getKeyId("f");
        assertEquals(3, key_id);
    }

    /**
     * Test of getAllKeys method, of class Player.
     */
    @Test
    public void testGetAllKeys() {
        System.out.println("getAllKeys");

        String keys = player.getAllKeys();
        assertEquals("asdf", keys);
    }

    public void testEquals_Symmetric() {

        System.out.println("Equals and HashCode");

        Player x = new Player("TestName", "asdf");
        Player y = new Player("TestName", "asdf");

        assertTrue(x.equals(y) && y.equals(x));
        assertTrue(x.hashCode() == y.hashCode());
    }

}
