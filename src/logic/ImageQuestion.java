
package logic;

/**
 *  Η κλάση ImageQuestion υλοποιεί την "Ερώτηση Εικόνας" στο παιχνίδι. Κληρονομεί από την Question. Η ερώτηση με 4 πιθανές σωστές απαντήσεις η μια εκ των 
 * οποίων είναι η σωστή, συνοδεύεται με φωτογραφία.
 * 
 * @author tasosxak
 * @author Thanasis
 *  @since 8/11/2016
 * @version 1.0
 */
public class ImageQuestion extends Question {

    private String imageurl;

    public ImageQuestion(String question, String category, String correct, String[] wrongs, String imageurl) {

        super(question, category, correct, wrongs);

        this.imageurl = imageurl;

    }

    public ImageQuestion(ImageQuestion question) {

        this(question.getQuestion(), question.getCategory(), question.getCorrect(), question.getAllWrongs(), question.getImage());

    }

    @Override
    public boolean isImage() {
        return true;
    }
    
    /**
     * 
     * @return Επιστρέφει το url της φωτογραφίας της συγκεκριμένης ερώτησης
     */

    public String getImage() {

        return this.imageurl;
    }

}
