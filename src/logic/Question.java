package logic;

import java.util.Set;
import java.util.HashSet;

/**
 *
 * Κλάση Question , η οποία επιτρέπει τη δημιουργία αντικειμένων ερωτήσεων. Στο
 * αντικείμενο αποθηκεύεται η ερώτηση, η σωστή και οι λανθασμένες απαντήσεις
 * καθώς και η κατηγορία στην οποία ανήκει. Ως default κατηγορίες ερωτήσεων
 * έχουν οριστεί πέντε.
 *
 * +++ Οι κλάσεις VideoQuestion , SoundQuestion και ImageQuestion κληρονομούν
 * από την Question +++
 *
 *
 * @author tasosxak
 * @author Thanasis
 * @since 8/11/2016
 * @version 1.0
 *
 */
public class Question {

    private static final String[] CATEGORIES = {"SPORT", "SCIENCE", "HISTORY", "TECHNOLOGY", "OTHERS"};
    public static final int ANSWERS = 4;

    private String question;
    private String correctAnswer;
    private String[] wrongs;
    private String category;

    private Question(String question) {

        this.wrongs = new String[Question.ANSWERS - 1];
        this.question = question;
    }

    private Question(String question, String correct) {

        this(question);
        this.correctAnswer = correct;
    }

    private Question(String question, String category, String correct) {

        this(question, correct);

        if (Question.belongs(category)) {

            this.category = category.toUpperCase();
        } else {
            this.category = "OTHERS"; //Σε περίπτωση που δεν ανήκει σε καμιά από τις υποστηριζόμενες κατηγορίες , κατάσσεται στην κατηγορία "others"
        }

    }

    public Question(Question question) {

        this(question.getQuestion(), question.getCategory(), question.getCorrect(), question.getAllWrongs());

    }

    /**
     * Public Construtor
     *
     * @param question Η ερώτηση του αντικειμένου
     * @param category Η κατηγορία στην οποία ανήκει η ερώτηση
     * @param correct Η σωστή απάντηση της ερώτησης
     * @param wrongs Πίνακας συμβολοσειρών που περιέχει τις λανθασμένες
     * απαντήσεις
     */
    public Question(String question, String category, String correct, String[] wrongs) {

        this(question, category, correct);

        /* Αντιγραφή του πίνακα των λανθασμένων απαντήσεων wrongs στον 
        this.wrongs (πίνακα λανθασμένων απαντήσεων μιας ερώτησης)*/
        System.arraycopy(wrongs, 0, this.wrongs, 0, Question.ANSWERS - 1);

    }

    /**
     * Ελέγχει αν η απάντηση που δίνεται ως παράμετρος είναι η σωστή απάντηση
     * που αντιστοιχεί στην ερώτηση
     *
     *
     * @param answer Αντιστοιχεί στην απάντηση που δίνει ο χρήστης
     * @return Επιστρέφει true αν η απάντηση είναι σωστή και false αν είναι
     * λάθος
     */
    public boolean isCorrect(String answer) {

        return this.correctAnswer.equals(answer);
    }

    /**
     * Θέτει την σωστή απάντηση της ερώτησης, η σωστή απάντηση πρέπει να έχει
     * μήκος μεγαλύτερο του μηδενός
     *
     * @param correctAnswer Η απάντηση που θα τεθεί ως η σωστή απάντηση της
     * ερώτησης
     * @return true αν η προσθήκη έγινε επιτυχώς (όταν το μήπως της παραμέτρου
     * είναι μεγαλύτερο του μηδενός), διαφορετικά false
     */
    public boolean setCorrectAnswer(String correctAnswer) {

        if (correctAnswer.length() != 0) {

            this.correctAnswer = correctAnswer;

            return true;
        }

        return false;
    }

    /**
     *
     * @return Επιστρέφει την ερώτηση
     */
    public String getQuestion() {

        return this.question;
    }

    /**
     *
     * @return Επιστρέφει την κατηγορία που ανήκει η ερώτηση
     */
    public String getCategory() {

        return this.category;
    }

    /**
     *
     * @return Επιστρέφει την σωστή απάντηση της ερώτησης
     */
    public String getCorrect() {

        return this.correctAnswer;
    }

    /**
     *
     * @param index Θέση μιας λανθασμένης απάντησης στον πίνακα wrongs
     * @return Επιστρέφει την λανθασμένη απάντηση στη θέση index του πίνακα
     * wrongs
     */
    public String getWrong(int index) {

        if (index < Question.ANSWERS - 1) {
            return this.wrongs[index];
        } else {
            return null;
        }
    }

    /**
     *
     * @return Επιστρέφει έναν νέο πίνακα συμβολοσειρών με τις λανθασμένες
     * ερωτήσεις
     */
    public String[] getAllWrongs() {

        return this.wrongs.clone();
    }

    /**
     *
     * @return Επιστρέφει όλες τις πιθανές επιλογές (απαντήσεις) της ερώτησης σε
     * πίνακα συμβολοσειρών
     */
    public String[] getAllAnswers() {

        String[] allAnswers = new String[Question.ANSWERS];

        allAnswers[0] = this.getCorrect();

        for (int i = 1; i < Question.ANSWERS; i++) {

            allAnswers[i] = this.getWrong(i - 1);
        }

        return allAnswers;
    }

    /**
     * Ελέγχει εαν μια κατηγορία που δίνεται ως παράμετρος πράγματι υπάρχει στον
     * πινακα CATEGORIES της Question
     *
     * @param category Η κατηγορία μιας ερώτησης
     * @return Επιστρέφει true αν η κατηγορία είναι έγκυρη ενώ false σε άλλη
     * περίπτωση
     */
    public static boolean belongs(String category) {

        for (String cat : Question.CATEGORIES) {

            if (cat.equals(category.toUpperCase())) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean equals(Object object2) {

        if (this == object2) {

            return true;
        }

        if (!(object2 instanceof Question)) {

            return false;
        }

        Question question2 = (Question) object2;

        if (this.getQuestion().equals(question2.getQuestion())) {

            if (this.getCategory().equals(question2.getCategory())) {

                if (this.getCorrect().equals(question2.getCorrect())) {

                    Set<String> wrongs1 = new HashSet<>();
                    Set<String> wrongs2 = new HashSet<>();

                    wrongs1.add(this.getAllWrongs()[0]);
                    wrongs1.add(this.getAllWrongs()[1]);
                    wrongs1.add(this.getAllWrongs()[2]);
                    wrongs2.add(question2.getAllWrongs()[0]);
                    wrongs2.add(question2.getAllWrongs()[1]);
                    wrongs2.add(question2.getAllWrongs()[2]);

                    return wrongs1.equals(wrongs2);

                }

            }
        }

        return false;

    }

    @Override
    public int hashCode() {

        int hash = 5;

        hash = 31 * hash + this.getQuestion().hashCode();
        hash = 17 * hash + this.getCorrect().hashCode();
        hash = 65 * hash + this.getCategory().hashCode();
        hash = 78 * hash + this.getAllWrongs()[0].hashCode() + this.getAllWrongs()[1].hashCode() + this.getAllWrongs()[2].hashCode();

        return hash;

    }

    /**
     *
     * @return Επιστρέφει την κατηγορία της ερωτήσης, την ερώτηση, και
     * απαριθμημένες τις απαντήσεις, με την πρώτη να είναι η σωστή και οι
     * υπόλοιπες λανθασμένες
     */
    @Override
    public String toString() {

        return "Κατηγορία: " + this.getCategory() + "\n"
                + "Ερώτηση: " + this.getQuestion() + "\n"
                + "Α) " + this.getCorrect() + "\n"
                + "B) " + this.getAllWrongs()[0] + "\n"
                + "C) " + this.getAllWrongs()[1] + "\n"
                + "D) " + this.getAllWrongs()[2] + "\n";

    }

    public boolean isImage() {
        return false;
    }

}
