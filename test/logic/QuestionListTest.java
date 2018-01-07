package logic;

import logic.Question;
import logic.QuestionList;
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
public class QuestionListTest {

    private QuestionList questionlist;
    private Question question;

    @Before
    public void setUp() {

        questionlist = new QuestionList();
        question = new Question("Test question?", "sport", "testCorrectAnswer",
                new String[]{"testWrong1", "testWrong1", "testWrong1"});
    }

    /**
     * Test of nextQuestion method, of class QuestionList.
     */
    @Test
    public void testNextQuestion() {

        System.out.println("nextQuestion");

        assertEquals(null, questionlist.nextQuestion());
        questionlist.addQuestion(question);

        assertTrue(questionlist.nextQuestion() != null);

    }

    /**
     * Test of nextQuestion method, of class QuestionList.
     */
    @Test
    public void testNextQuestion_String() {

        System.out.println("nextQuestion(category)");

        assertTrue(questionlist.nextQuestion("") == null);
        assertTrue(questionlist.nextQuestion(" ") == null);
        assertTrue(questionlist.nextQuestion("SOMETHING") == null);
        assertTrue(questionlist.nextQuestion("SPORT") == null);

        questionlist.addQuestion(question);

        assertTrue(questionlist.nextQuestion("") == null);
        assertTrue(questionlist.nextQuestion(" ") == null);
        assertTrue(questionlist.nextQuestion("SOMETHING") == null);
        assertTrue(questionlist.nextQuestion("SPORT") != null);
        assertTrue(questionlist.nextQuestion("SPORT") == null);

        questionlist.addQuestion(question);

        assertTrue(questionlist.nextQuestion("Sport") != null);
        assertTrue(questionlist.nextQuestion("Sport") == null);

        questionlist.addQuestion(question);
        questionlist.addQuestion(question);

        assertTrue(questionlist.nextQuestion("Sport") != null);
        assertTrue(questionlist.nextQuestion("Sport") != null);
        assertTrue(questionlist.nextQuestion("Sport") == null);
    }

    /**
     * Test of addQuestion method, of class QuestionList.
     */
    @Test
    public void addQuestion() {

        System.out.println("addQuestion");

        assertEquals(true, questionlist.addQuestion(question));
        assertEquals(true, questionlist.addQuestion(question));

        question = null;

        assertEquals(false, questionlist.addQuestion(question));

    }

}
