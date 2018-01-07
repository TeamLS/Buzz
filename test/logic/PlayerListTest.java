package logic;

import org.junit.After;
import org.junit.Assert;
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
public class PlayerListTest {

    private PlayerList playerlist;

    public PlayerListTest() {
    }

    @Before
    public void setUp() {
        playerlist = new PlayerList();

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addPlayer method, of class PlayerList.
     */
    @Test
    public void testAddPlayer() {
        System.out.println("addPlayer");
        String name = "TestName";
        String keys = "abcdefgh";
        boolean eightKeys = playerlist.addPlayer(name, keys);
        assertEquals(false, eightKeys);
        keys = "asdf";
        boolean correct = playerlist.addPlayer(name, keys);
        assertEquals(true, correct);

        // Υπάρχει πλέον παίχτης με όνομα TestName και πλήκτρα asdf.
        name = "TestName";
        keys = "hjkl";
        boolean sameName = playerlist.addPlayer(name, keys);
        assertEquals(false, sameName);
        name = "TestName2";
        keys = "fghj";
        boolean sameKey = playerlist.addPlayer(name, keys);
        assertEquals(false, sameKey);
    }

    /**
     * Test of addPointsToPlayer method, of class PlayerList.
     */
    @Test
    public void testAddPointsToPlayer() {
        System.out.println("addPointsToPlayer");
        playerlist.addPlayer("TestName", "asdf");
        playerlist.addPlayer("TestName2", "hjkl");

        int points = 100;
        playerlist.addPointsToPlayer(points, 1);
        int pointsOfPlayer1 = playerlist.getScoreOfPlayer(1);
        int pointsOfPlayer2 = playerlist.getScoreOfPlayer(2);
        assertEquals(100, pointsOfPlayer1);
        assertEquals(0, pointsOfPlayer2);

        playerlist.addPointsToPlayer(points, 2);
        points = -200;
        playerlist.addPointsToPlayer(points, 1);
        pointsOfPlayer1 = playerlist.getScoreOfPlayer(1);
        pointsOfPlayer2 = playerlist.getScoreOfPlayer(2);
        assertEquals(-100, pointsOfPlayer1);
        assertEquals(100, pointsOfPlayer2);
    }

    /**
     * Test of getScoreOfPlayer method, of class PlayerList.
     */
    @Test
    public void testGetScoreOfPlayer() {
        System.out.println("getScoreOfPlayer");

        Integer pointsOfPlayer = playerlist.getScoreOfPlayer(1);
        assertEquals(null, pointsOfPlayer);

        playerlist.addPlayer("TestName", "asdf");
        pointsOfPlayer = playerlist.getScoreOfPlayer(1);
        assertEquals(new Integer(0), pointsOfPlayer);

        playerlist.addPointsToPlayer(100, 1);
        pointsOfPlayer = playerlist.getScoreOfPlayer(1);
        assertEquals(new Integer(100), pointsOfPlayer);
    }

    /**
     * Test of getNameOfPlayer method, of class PlayerList.
     */
    @Test
    public void testGetNameOfPlayer() {
        System.out.println("getNameOfPlayer");

        String nameOfPlayer = playerlist.getNameOfPlayer(1);
        assertEquals(null, nameOfPlayer);

        String testName = "TestName1";
        playerlist.addPlayer(testName, "asdf");
        nameOfPlayer = playerlist.getNameOfPlayer(1);
        assertEquals(testName, nameOfPlayer);

        testName = "TestName2";
        playerlist.addPlayer(testName, "hjkl");
        nameOfPlayer = playerlist.getNameOfPlayer(2);
        assertEquals(testName, nameOfPlayer);
    }

    /**
     * Test of getNumOfPlayers method, of class PlayerList.
     */
    @Test
    public void testGetNumOfPlayers() {
        System.out.println("getNumOfPlayers");

        int numOfPlayers = playerlist.getNumOfPlayers();
        assertEquals(0, numOfPlayers);
        playerlist.addPlayer("TestName1", "asdf");
        numOfPlayers = playerlist.getNumOfPlayers();
        assertEquals(1, numOfPlayers);
        playerlist.addPlayer("TestName2", "hjkl");
        numOfPlayers = playerlist.getNumOfPlayers();
        assertEquals(2, numOfPlayers);
        playerlist.addPlayer("TestName3", "1234");
        numOfPlayers = playerlist.getNumOfPlayers();
        assertEquals(3, numOfPlayers);
        playerlist.addPlayer("TestName4", "7890");
        numOfPlayers = playerlist.getNumOfPlayers();
        assertEquals(4, numOfPlayers);
    }

    /**
     * Test of getKeyIdOfPlayer method, of class PlayerList.
     */
    @Test
    public void testGetKeyIdOfPlayer() {
        System.out.println("getKeyIdOfPlayer");
        String key = "a";
        int key_id = playerlist.getKeyIdOfPlayer(key, 1);
        assertEquals(-1, key_id);

        playerlist.addPlayer("TestName", "asdf");
        key = "as";
        key_id = playerlist.getKeyIdOfPlayer(key, 1);
        assertEquals(-1, key_id);

        key = "s";
        key_id = playerlist.getKeyIdOfPlayer(key, 1);
        assertEquals(1, key_id);

        key = "g";
        key_id = playerlist.getKeyIdOfPlayer(key, 1);
        assertEquals(-1, key_id);
    }

    /**
     * Test of isSomeonesKey method, of class PlayerList.
     */
    @Test
    public void testIsSomeonesKey() {
        System.out.println("isSomeonesKey");

        boolean isKey = playerlist.isSomeonesKey("a");
        assertEquals(false, isKey);

        playerlist.addPlayer("TestName", "asdf");
        isKey = playerlist.isSomeonesKey("a");
        assertEquals(true, isKey);

        playerlist.addPlayer("TestName2", "hjkl");
        isKey = playerlist.isSomeonesKey("k");
        assertEquals(true, isKey);

        isKey = playerlist.isSomeonesKey("g");
        assertEquals(false, isKey);

    }

    /**
     * Test of getAllKeys method, of class PlayerList.
     */
    @Test
    public void testGetAllKeys() {
        System.out.println("getAllKeys");
        String allKeys = playerlist.getAllKeys();
        assertEquals("", allKeys);

        playerlist.addPlayer("TestName", "asdf");
        allKeys = playerlist.getAllKeys();
        assertEquals("asdf", allKeys);

        playerlist.addPlayer("TestName2", "hjkl");
        allKeys = playerlist.getAllKeys();
        assertEquals("asdfhjkl", allKeys);
    }

    /**
     * Test of getKeysOfPlayer method, of class PlayerList.
     */
    @Test
    public void testGetKeysOfPlayer() {
        System.out.println("getKeysOfPlayer");
        String keysOfPlayer1 = playerlist.getKeysOfPlayer(1);
        assertEquals(null, keysOfPlayer1);

        playerlist.addPlayer("TestName1", "asdf");
        playerlist.addPlayer("TestName2", "hjkl");
        keysOfPlayer1 = playerlist.getKeysOfPlayer(1);
        String keysOfPlayer2 = playerlist.getKeysOfPlayer(2);
        assertEquals("asdf", keysOfPlayer1);
        assertEquals("hjkl", keysOfPlayer2);

    }

    /**
     * Test of whoseKeyIs method, of class PlayerList.
     */
    @Test
    public void testWhoseKeyIs() {
        System.out.println("whoseKeyIs");

        int player_id = playerlist.whoseKeyIs("a");
        assertEquals(-1, player_id);

        playerlist.addPlayer("TestName1", "asdf");
        playerlist.addPlayer("TestName2", "hjkl");
        player_id = playerlist.whoseKeyIs("a");
        assertEquals(1, player_id);
        player_id = playerlist.whoseKeyIs("j");
        assertEquals(2, player_id);
        player_id = playerlist.whoseKeyIs("f");
        assertEquals(1, player_id);

    }

    /**
     * Test of isKeyOfPlayer method, of class PlayerList.
     */
    @Test
    public void testIsKeyOfPlayer() {
        System.out.println("isKeyOfPlayer");

        boolean isKey = playerlist.isKeyOfPlayer("a", 1);
        assertEquals(false, isKey);

        playerlist.addPlayer("TestName1", "asdf");
        playerlist.addPlayer("TestName2", "hjkl");
        isKey = playerlist.isKeyOfPlayer("a", 1);
        assertEquals(true, isKey);
        isKey = playerlist.isKeyOfPlayer("a", 2);
        assertEquals(false, isKey);
        isKey = playerlist.isKeyOfPlayer("l", 1);
        assertEquals(false, isKey);
        isKey = playerlist.isKeyOfPlayer("l", 2);
        assertEquals(true, isKey);
    }
    
    /**
     * Test of getIdByPlayerName method, of class PlayerList.
     */
    @Test
    public void testGetIdByPlayerName(){
        
        assertEquals(0, playerlist.getIdByPlayerName("TestName"));
        
        playerlist.addPlayer("TestName1", "asdf");
        assertEquals(1, playerlist.getIdByPlayerName("TestName1"));
        
        playerlist.addPlayer("TestName2", "hjkl");
        assertEquals(2, playerlist.getIdByPlayerName("TestName2"));
        
        playerlist.addPlayer("TestName3", "hjkl");
        // Δεν προστήθεται ο παίχτης TestName3 γιατί έχει ίδια πλήκτρα με τον παίχτη TestName2 (hjkl).
        assertEquals(0, playerlist.getIdByPlayerName("TestName3"));
        
    }    
    
    /**
     * Test of getWinnersId method, of class PlayerList.
     */
    @Test
    public void testGetWinnersId(){
        
        assertEquals(0, playerlist.getWinnersId());
        
        playerlist.addPlayer("TestName1", "asdf");
        assertEquals(0, playerlist.getWinnersId()); // Υπάρχει μόνο ένας παίχτης
        
        playerlist.addPlayer("TestName2", "hjkl");
        assertEquals(0, playerlist.getWinnersId()); // Ισοπαλία 0-0
        
        playerlist.addPointsToPlayer(100, 1);
        playerlist.addPointsToPlayer(500, 2);
        assertEquals(2, playerlist.getWinnersId());
        
        playerlist.addPointsToPlayer(400, 1);
        assertEquals(0, playerlist.getWinnersId()); // Ισοπαλία 500-500
        
    }
    
    
    /**
     * Test of getScoreTableNames method, of class PlayerList.
     */
    @Test
    public void testGetScoreTableNames(){
        
        String[] names = playerlist.getScoreTableNames();
        Assert.assertArrayEquals(null, names);        
        
        playerlist.addPlayer("TestName1", "asdf");
        playerlist.addPointsToPlayer(100, 1);        
        names = playerlist.getScoreTableNames();
        Assert.assertArrayEquals(new String[]{"TestName1"}, names);
        
        playerlist.addPlayer("TestName2", "hjkl");
        playerlist.addPointsToPlayer(500, 2);
        names = playerlist.getScoreTableNames();
        Assert.assertArrayEquals(new String[]{"TestName2","TestName1"}, names);
        
        playerlist.addPlayer("TestName3", "zxcv");
        playerlist.addPointsToPlayer(200, 3);
        names = playerlist.getScoreTableNames();
        Assert.assertArrayEquals(new String[]{"TestName2","TestName3","TestName1"}, names);
    
    }
        
    /**
     * Test of getScoreTableScores method, of class PlayerList.
     */
    @Test
    public void testGetScoreTableScores(){
        
        int[] scores = playerlist.getScoreTableScores();
        Assert.assertArrayEquals(new int[]{}, scores);        
        
        playerlist.addPlayer("TestName1", "asdf");
        playerlist.addPointsToPlayer(100, 1);        
        scores = playerlist.getScoreTableScores();
        Assert.assertArrayEquals(new int[]{100}, scores);
        
        playerlist.addPlayer("TestName2", "hjkl");
        playerlist.addPointsToPlayer(500, 2);
        scores = playerlist.getScoreTableScores();
        Assert.assertArrayEquals(new int[]{500,100}, scores);
        
        playerlist.addPlayer("TestName3", "zxcv");
        playerlist.addPointsToPlayer(200, 3);
        scores = playerlist.getScoreTableScores();
        Assert.assertArrayEquals(new int[]{500,200,100}, scores);
        
    }
    
        
}
