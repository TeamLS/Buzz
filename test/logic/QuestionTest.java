package logic;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * 
 * @author tasosxak
 * @author thanasis
 * @since 30/11/16
 * @version 1.0
 */
public class QuestionTest {

    private Question question;

    @Before
    public void setUp() {

        question = new Question("Test question?", "sport", "testCorrectAnswer",
                new String[]{"testWrong1", "testWrong2", "testWrong3"});
    }

    /**
     * Test of isCorrect method, of class Question.
     */
    @Test
    public void testIsCorrect() {
        System.out.println("isCorrect");

        assertEquals(false, question.isCorrect(question.getWrong(0)));
        assertEquals(false, question.isCorrect(question.getWrong(1)));
        assertEquals(false, question.isCorrect(question.getWrong(2)));

    }

    /**
     * Test of setCorrectAnswer method, of class Question.
     */
    @Test
    public void testSetCorrectAnswer() {
        System.out.println("setCorrectAnswer");

        assertEquals(false, question.setCorrectAnswer(""));
        assertEquals(true, question.setCorrectAnswer("test"));

    }

    @Test
    public void testBelongs() {

        System.out.println("belongs");
        assertEquals(false, Question.belongs(""));

    }
    
    @Test
    public void testGetCategory() {
        
        System.out.println("getCategory");
        question = new Question("Test question?", "wrafgsgfdfdg", "testCorrectAnswer",
                new String[]{"testWrong1", "testWrong2", "testWrong3"});
        
         assertTrue( "OTHERS".equals(question.getCategory()));
         
         question = new Question("Test question?", "", "testCorrectAnswer",
                new String[]{"testWrong1", "testWrong2", "testWrong3"});
        
         assertTrue( "OTHERS".equals(question.getCategory()));
    }

    @Test
    public void testEquals_Symmetric() {

        System.out.println("Equals and HashCode");

        Question x = new Question("Test question?", "SPORT", "aCorrectAnswer",
                new String[]{"aWrong1", "aWrong2", "aWrong3"});

        Question y = new Question("Test question?", "Sport", "aCorrectAnswer",
                new String[]{"aWrong2", "aWrong3", "aWrong1"});

        assertTrue(x.equals(y) && y.equals(x));
        assertTrue(x.hashCode() == y.hashCode());
    }

}
