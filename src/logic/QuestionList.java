package logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Η κλάση QuestionList διαχειρίζεται μια λίστα από αντικείμενα Question. Σκοπός της είναι να δημιουργεί
 * αντικείμενα Question με βάση τα δεδομένα, και με μεθόδους να επιστρέφει αυτά τα αντικείμενα με τυχαίο τρόπο.
 *
 * @author tasosxak
 * @author Thanasis
 * @since 8/11/16
 * @version 1.0
 * 
 */
public class QuestionList {

    private List<Question> questions;  //Λίστα ερωτήσεων

    public QuestionList() {

        this.questions = new ArrayList<>();

    }

    /**
     * Η συνάρτηση αυτή δέχεται έναν θετικό αριθμό ως παράμετρο και επιστρέφει
     * ένα τυχαίο αριθμό στο διάστημα [0,τυχαίος_αριθμός) Σε περίπτωση που
     * δέχεται αρνητικό, παίρνει την απόλυτη τιμή αυτού.
     *
     * @param range Το άνω όριο του διαστήματος [0,range) όπου θα επιλέγεται
     * ένας τυχαίος αριθμός
     * @return Επιστρέφει τον τυχαίο αριθμό
     */
    private int getRandomIndexOfQuestion(int range) {

        Random ran = new Random();

        return ran.nextInt(Math.abs(range));
    }

    /**
     * Η nextQuestion παίρνει ένα τυχαίο αντικείμενο τύπου Question και έπειτα
     * το διαγράφει από τη λίστα ερωτήσεων.
     *
     * @return Επιστρέφει ένα τυχαίο αντικείμενο τύπου Question
     */
    public Question nextQuestion() {

        if (!(this.questions.isEmpty())) {

            int rand = this.getRandomIndexOfQuestion(this.questions.size()); //Λαμβάνει έναν τυχαίο αριθμό [0,μέγεθος πίνακα questions)

            Question luckyQuestion = this.questions.get(rand); // Παίρνει την ερώτηση με index τον τυχαίο αριθμό

            this.questions.remove(rand); // Διαγράφει την ερώτηση για να μην επαναχρησιμοποιηθεί

            return luckyQuestion;
        }

        return null;

    }

    /**
     * Η nextQuestion(String category) επιστρέφει μια τυχαία ερώτηση κατηγορίας
     * category.
     *
     * @param category Η κατηγορία που θέλουμε να ανήκει η ερώτηση
     * @return Επιστρέφει ερώτηση κατηγορίας category και null αν δεν υπάρχει η
     * συγκεκριμένη κατηγορία ή δεν υπάρχει ερώτηση αυτής της κατηγορίας (δηλαδή
     * μπορεί να υπήρχε αλλά χρησιμοποιήθηκε ήδη)
     */
    public Question nextQuestion(String category) {

        List<Question> questionsOfCategory = new ArrayList<>();

        for (Question question : this.questions) {

            if (question.getCategory().equals(category.toUpperCase())) //Έλεγχος αν η ερώτηση ανήκει στην κατηγορία category
            {

                questionsOfCategory.add(question);

            }
        }

        /*Ελέγχει αν ο πίνακας ερωτήσεων συγκεκριμένης κατηγορίας είναι κενός ή οχι
       
         */
        if (questionsOfCategory.isEmpty()) {

            return null;   //Επιστρέφει null αν είναι
        } else {

            // Επιστρέφει μια τυχαία ερώτηση από τον πίνακα ερωτήσεων συγκεκριμένης κατηγορίας
            int rand = this.getRandomIndexOfQuestion(questionsOfCategory.size());

            Question luckyquestion = questionsOfCategory.get(rand);

            this.questions.remove(luckyquestion);

            return luckyquestion;

        }
    }

    /**
     * Η addQuestion προσθέτει ένα αντικείμενο Question στην λίστα ερωτήσεων.
     * Επιτρέπει την πολλαπλή προσθήκη του ίδιου αντικειμένου
     *
     * @param question Το αντικείμενο Question που θα προστεθεί στη λίστα
     * ερωτήσεων
     * @return true αν η προσθήκη έγινε επιτυχώς και false αν η προσθήκη
     * αντικειμένου απέτυχε ( null)
     */
    public boolean addQuestion(Question question) {

        if (question != null) {

            this.questions.add(question);

            return true;
        }

        return false;

    }

    /**
     *
     * @return Επιστρέφει τον αριθμό ερωτήσεων που είναι αποθηκευμένο στη λίστα
     * ερωτήσεων
     */
    public int numOfQuestions() {

        return this.questions.size();
    }
    
    

}
