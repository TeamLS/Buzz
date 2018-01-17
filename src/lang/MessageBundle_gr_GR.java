/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lang;

import java.util.ListResourceBundle;

/**
 *
 * @author tasosxak
 */
public class MessageBundle_gr_GR extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return contents;
    }

    private Object[][] contents = {
        {"startButton", "Εκκίνηση"},
        {"scoreButton", "Σκορ"},
        {"settingsButton", "Ρυθμίσεις"},
        {"howManyPlayersLabel", "Πόσοι παίχτες θα παίξετε;"},
        {"playerLabel", "Παίχτης"},
        {"playersLabel", "Παίχτες"},
        {"enterNamesLabel", "Συμπληρώστε τα ονόματα των παιχτών"},
        {"playButton", "Έναρξη"},
        {"okButton", "Εντάξει"},
        {"exitButton", "Έξοδος"},
        {"settingsButton", "Ρυθμίσεις"},
        {"languageLabel", "Γλώσσα"},
        {"saveLabel", "Αποθήκευση"},
        {"welcomeLabel", "Καλως Ήρθες στο Buzz!"},
        {"scoreLabel", "Βαθμολογία"},
        {"singleHighscoreLabel", "Ατομικό Παιχνίδι"},
        {"multiplayerHighscoreLabel", "Ομαδικό Παιχνίδι"},
        {"backLabel", "Πίσω"},
        {"winnerLabel", "Ο νικητής είναι ο/η"},
        {"tieLabel", "Ισοπαλία !"},
         {"OTHERS", "Διάφορα"},
        {"HISTORY", "Ιστορία"},
        {"SPORT", "Αθλητικά"},
        {"TECHNOLOGY", "Τεχνολογία"},
        {"SCIENCE", "Επιστήμη"},
        {"endGameLabel", "Τέλος παιχνιδιού!"},
        {"yourScoreLabel", "Το σκορ σου είναι"},
        {"keysLabel", "Τα πλήκτρα είναι"},
        {"betLabel", "Πόσα ποντάρεις "},
        {"backToMenuButton", "Πίσω στο μενού"},
        {"newGameButton", "Νέο παιχνίδι"},
        {"congratsLabel", "Συγχαρητήρια"},
        {"questionMark", ";"},
        {"betroundLabel", "Ποντάρισμα"},
        {"betroundDesc", "Επιλέγεις πόσους πόντους θα ποντάρεις. Αν απαντήσεις σωστά κερδίζεις αυτούς τους πόντους αλλιώς τους χάνεις."},
        {"correctanswerLabel", "Σωστή Απάντηση"},
        {"correctanswerDesc", "Όποιος προλάβει να απαντήσει και απαντήσει σωστά κερδίζει %s πόντους."},
        {"countdownLabel", "Αντίστροφη Μέτρηση"},
        {"countdownDesc", "Έχεις 5 δευτερόλεπτα. Όσο πιο γρήγορα απαντήσεις τόσο περισσότερους πόντους κερδίζεις"},
        {"fastanswerLabel", "Γρήγορη Απάντηση"},
        {"fastanswerDesc", "Ο πρώτος που θα απαντήσει σωστά κερδίζει %s πόντους. Ο δεύτερος %s πόντους."},
        {"thermometerLabel", "Θερμόμετρο"},
        {"thermometerDesc", "Ο πρώτος παίχτης που θα απαντήσει σωστά %s ερωτήσεις κερδίζει %s πόντους."},
        {"error02", "Δεν μπόρεσε να αποθηκευτεί το αρχείο των σκορ. Ελέγξτε τα δικαιώματα του χρήστη σας στο φάκελο που βρίσκεται το πρόγραμμα."},
         {"errorTitle02", "Αδυναμία εγγραφής στο αρχείο των σκορ"},
         {"error01", "Το αρχείο ερωτήσεων δε μπορεί να ανοίξει. Ελέγξτε τα δικαιώματα του χρήστη στο φάκελο που βρίσκεται το πρόγραμμα."},
         {"errorTitle01", "Αρχείο ερωτήσεων"},
          {"error00", "Το αρχείο ερωτήσεων δεν υπάρχει ή έχει μετονομαστεί.Ελέγξτε αν βρίσκετε στο φάκελο 'questions' του παιχνιδιού."},
         {"errorTitle00", "Δε βρέθηκε το αρχείο ερωτήσεων"}
    };
}
