package gui;

import logic.BetRound;
import logic.FastAnswerRound;
import logic.CorrectAnswerRound;
import logic.CountDown;
import logic.CountDownListener;
import logic.CountDownRound;
import logic.Game;
import logic.ImageQuestion;
import logic.Question;
import logic.Round;
import logic.ThermometerRound;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import lang.Messages;

/**
 * Η κλάση Controller υλοποιεί το γραφικό περιβάλλον της εφαρμογής, και συνδέει
 * την λογική του παιχνιδιού με το user interface.
 *
 * @author tasosxak
 * @author Thanasis
 * @version 1.0
 * @since 28/11/16
 *
 */
public class Controller {

    private Game game;
    private BFrame root;

    private JButton startButton;
    private JButton scoresButton;
    private JButton exitButton;
    private JButton okNamesButton;
    private JButton startGameButton;
    private JButton returnButton;
    private JButton returnFromScoresButton;
    private JButton[] chooseNumPlayers;
    private JButton[] betOptionButton;

    private JPanel menuPanel;
    private JPanel playersPanel;
    private JPanel menuButtonsPanel;
    private JPanel playersKeysPanel;
    private JPanel playersNumPanel;
    private JPanel playersNamesPanel;
    private JPanel questionBackground;
    private JPanel imageOfQuestionPanel;
    private JPanel scorePanel;
    private JPanel mainGamePanel;
    private JPanel questionPanel;
    private JPanel questionTitlePanel;
    private JPanel answersPanel;
    private JPanel betOptionsPanel;
    private JPanel roundInfoPanel;
    private JPanel infoPanel;
    private JPanel gameOverPanel;
    private JPanel thermoPanel;
    private JPanel highScoresPanel;
    private JPanel[] thermometerIcon;
    private JPanel settingsPanel;

    private JLabel highScoresTitle;
    private JLabel winsTitle;
    private JLabel playersKeysTitle;
    private JLabel questionLabel;
    private JLabel playersNamesTitle;
    private JLabel playersNumTitle;
    private JLabel betOptionsTitle;
    private JLabel roundInfoTitle;
    private JLabel countDownLabel;
    private JLabel roundDescription;
    private JLabel categoryLabel;
    private JLabel winnerLabel;
    private JLabel scoresTitle;
    private JLabel betCategoryLabel;
    private JLabel roundTypeLabel;
    private JLabel[] playersScoresLabel;
    private JLabel[] highScoresLabel;
    private JLabel[] winsLabel;
    private JLabel[] answerLabel;
    private JLabel[] playersKeysLabel;
    private JLabel[] scoreNameLabel;
    private JLabel[] scoreLabel;

    private JTextField[] inputNamePlayer;

    private List<Integer> randoms;
    private Round curRound;
    private Question curQuestion;
    private MusicPlayer gameSound;
    private Thread threadclock;
    private CountDown clock;

    private boolean gameIsOver;
    private int players;
    private int playersAllowed;
    private int displayTop;
    private String curBet;
    private MusicPlayer menuSound;

    private Locale language;
    private Messages messages;

    public Controller() {
        root = new BFrame("Buzz Game!");

        menuSound = new MusicPlayer("menu.wav");
        menuSound.loop();

        chooseLanguage();

    }

    private void chooseLanguage() {

        //settingsPanel         
        settingsPanel = new BPanel(true, "buzz_images/8.png"); //Αρχικοποίηση του settingPanel με background

        ImageButton greekFlag = new ImageButton("buzz_images/greek_flag.png"); //Επιλογή Ελληνικής Γλώσσας
        greekFlag.setBounds(root.getWidth() / 2 - 250, 140, 200, 200); //Θέση της εικόνας
        greekFlag.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                language = new Locale("gr", "GR"); //Θέτει τη γλώσσα στα ελληνικά
                messages = new Messages("lang.MessageBundle", language);

                initComponents();
                settingsPanel.setVisible(false);
            }
        });
        settingsPanel.add(greekFlag);

        ImageButton englishFlag = new ImageButton("buzz_images/english_flag.png"); //Επιλογή Αγγλικής Γλώσσας
        englishFlag.setBounds(root.getWidth() / 2 + 50, 140, 200, 200); //Θέση της εικόνας
        englishFlag.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                language = new Locale("en", "EN"); //Θέτει τη γλώσσα στα αγγλικά
                messages = new Messages("lang.MessageBundle", language);

                initComponents();
                settingsPanel.setVisible(false);
            }
        });
        settingsPanel.add(englishFlag);

        settingsPanel.setFocusable(true); //Μεταφέρεται το focus στο settingsPanel
        root.getContentPane().add(settingsPanel); //Προσθήκη του settingsPanel στο κεντρικό παράθυρο

        root.setVisible(true);
    }

    private void initComponents() {

        game = new Game(); //Δημιουργία του παιχνίδιου (Λογική)
        //game.initialize();
        game.setLocale(language); //Θέτει τη γλώσσα στο παιχνίδι ώστε να φορτωθεί το κατάλληλο αρχείο ερωτήσεων
        gameIsOver = false;

        curBet = "0";

        root.setFocusable(true); //Μεταφέρεται το focus στο κεντρικό παράθυρο
        root.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyPressed(KeyEvent ke) {
                // System.out.println(Character.toString((char) ke.getKeyCode()).toLowerCase());
                String answer;
                answer = Character.toString((char) ke.getKeyCode()).toLowerCase(); //Το κουμπί που πατήθηκε (η απάντηση του χρήστη)

                if (game.isValidKey(answer) && !gameIsOver) {

                    //Εκτέλεση της CorrectAnswerRound
                    if (curRound instanceof CorrectAnswerRound && game.canPlay(game.getIdOfPlayerByKey(answer))) {

                        game.executeRound(answer, randoms); //Ελέγχει αν είναι σωστή και προστίθονται οι αντίστοιχοι ποντοι
                        String correctAnswer = curQuestion.getCorrect(); //Παίρνει την σωστή απάντηση της συγκεκριμένης ερώτησης
                        afterAnswer(answer, correctAnswer); //Ηχητικό μετά την απάντηση
                        afterAnswerBonus(); //Ενημέρωση πόντων
                        //showScores(); // Εμφάνιση πόντων

                        if (game.allHavePlayed()) {
                            curRound.executed(); // Μειώνεται ο αριθμός επιτρεπτών εκτελέσεων του γύρου
                            nextQuestion(); //Καλείτε η επόμενη ερώτηση
                        }

                    } else if (curRound instanceof BetRound && game.isKeyOfPlayer(answer, game.getNextID(false))) {

                        //Parser.show("Είναι η σειρά του παίκτη " + game.getNameByPlayerId(game.getNextID(false)));
                        // Parser.show("Κατηγορία: " + curQuestion.getCategory());
                        //Parser.show("Πόσα ποντάρεις;");
                        //betOptions = ((BetRound) curRound).getBetOptions(); // Παίρνει τα επιτρεπτά πονταρίσματα
                        curRound.setBonus(Integer.parseInt(curBet)); //Θέτει το ποντάρισμα που διαβάστηκε

                        game.executeRound(answer, randoms); //Ελέγχει αν είναι σωστή και προστίθονται οι αντίστοιχοι ποντοι

                        String correctAnswer = curQuestion.getCorrect();
                        afterAnswer(answer, correctAnswer);
                        afterAnswerBonus();
                        //showScores();

                        if (game.getNextID(true) == 1) {
                            curRound.executed();  // Μειώνεται ο αριθμός επιτρεπτών εκτελέσεων του γύρου
                        }

                        nextQuestion();

                    } else if (curRound instanceof CountDownRound && game.canPlay(game.getIdOfPlayerByKey(answer))) {

                        curRound.setBonus(clock.getMilliSeconds()); //Θέτει τους πόντους με βάση της ταχύτητα απάντησης

                        game.executeRound(answer, randoms); //Ελέγχει αν είναι σωστή και προστίθονται οι αντίστοιχοι ποντοι

                        String correctAnswer = curQuestion.getCorrect();
                        afterAnswer(answer, correctAnswer);
                        afterAnswerBonus();
                        //showScores();

                        if (game.allHavePlayed()) {
                            threadclock.interrupt();//Διακόπτεται το ρολόι
                            countDownLabel.setVisible(false); //Εξαφανίζεται το ρολόι
                            curRound.executed();  // Μειώνεται ο αριθμός επιτρεπτών εκτελέσεων του γύρου
                            nextQuestion();
                        }

                    } else if (curRound instanceof FastAnswerRound && game.canPlay(game.getIdOfPlayerByKey(answer))) {

                        game.executeRound(answer, randoms); //Ελέγχει αν είναι σωστή και προστίθονται οι αντίστοιχοι ποντοι

                        String correctAnswer = curQuestion.getCorrect();
                        afterAnswer(answer, correctAnswer);
                        afterAnswerBonus();
                        //showScores();

                        if (game.allHavePlayed()) {
                            curRound.executed(); // Μειώνεται ο αριθμός επιτρεπτών εκτελέσεων του γύρου
                            nextQuestion();
                        }

                    } else if (curRound instanceof ThermometerRound) {

                        game.executeRound(answer, randoms); //Ελέγχει αν είναι σωστή και προστίθονται οι αντίστοιχοι ποντοι

                        //Γίνεται το ανάλογο update στο εικονίδιο θερμόμετρο με βάση τις σωστές απαντήσεις του παίχτη
                        ((BPanel) thermometerIcon[game.getIdOfPlayerByKey(answer) - 1]).setImage("buzz_images/thermo_" + ((ThermometerRound) curRound).getWins(game.getIdOfPlayerByKey(answer)) + ".png");

                        String correctAnswer = curQuestion.getCorrect();
                        afterAnswer(answer, correctAnswer);

                        if (((ThermometerRound) curRound).hasPlayerWon(game.getIdOfPlayerByKey(answer))) {

                            afterAnswerBonus();
                            // showScores();
                            curRound.executed(); // Μειώνεται ο αριθμός επιτρεπτών εκτελέσεων του γύρου
                            thermoPanel.setVisible(false); //Εξαφανίζονται τα θερμόμετρα
                            hideThermoPanel();
                        }

                        nextQuestion();
                    }

                }

            }

            @Override

            public void keyReleased(KeyEvent ke) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        }
        );

        //menupanel
        menuPanel = new BPanel(true, "buzz_images/2.png");
        root.getContentPane().add(menuPanel);

        //playerspanel
        playersPanel = new BPanel(false, "buzz_images/3.jpg");
        root.getContentPane().add(playersPanel);

        //playersNumPanel
        playersNumPanel = new BPanel(false);

        //playersNumTitle
        playersNumTitle = new BLabel(messages.getString("howManyPlayersLabel"));
        playersNumTitle.setBounds(0, 50, root.getWidth(), 100);

        //playersNamesPanel
        playersNamesPanel = new BPanel(false);

        //playersNameTitle
        playersNamesTitle = new BLabel(messages.getString("enterNamesLabel"));
        playersNamesTitle.setBounds(0, 50, root.getWidth(), 100);

        //Add playersNamesTitle to playersNamesPanel
        playersNamesPanel.add(playersNamesTitle);

        //questionBackground
        questionBackground = new BPanel(true);

        //imageOfQuestionPanel
        imageOfQuestionPanel = new BPanel(330, 188, 390, 238);

        //questionLabel
        questionLabel = new BLabel(messages.getString("welcomeLabel"), true, SwingConstants.LEFT);
        questionLabel.setBounds(80, 40, root.getWidth() - 250, 65);

        //answersPanel
        answersPanel = new BPanel(true);
        questionBackground.add(answersPanel);

        //answerLabel
        answerLabel = new BLabel[4];
        for (int i = 0; i < 4; i++) {
            answerLabel[i] = new BLabel("Answer " + i);
            answerLabel[i].setBounds(60, 175 + 75 * i, 200, 50);
            answersPanel.add(answerLabel[i]);
        }

        //infopanel
        infoPanel = new BPanel(true);

        categoryLabel = new BLabel(); // κατηγορία  τρέχοντος ερώτησης
        categoryLabel.setBounds(56, 483, 210, 50);

        roundTypeLabel = new BLabel(); //τύπος τρέχοντος γύρου
        roundTypeLabel.setBounds(root.getWidth() - 255, 483, 210, 50);

        infoPanel.add(categoryLabel);
        infoPanel.add(roundTypeLabel);

        //questionPanel
        questionTitlePanel = new BPanel(true);

        questionTitlePanel.add(questionLabel);
        questionBackground.add(infoPanel);
        questionBackground.add(questionTitlePanel);

        //countDownLabel
        countDownLabel = new BLabel();
        questionBackground.add(countDownLabel);
        countDownLabel.setVisible(false);
        countDownLabel.setBounds(root.getWidth() - 130, 47, 30, 40);
        countDownLabel.setFont(new Font("Serif", Font.BOLD, 35));

        //ThermoPanel
        thermoPanel = new BPanel(true);
        questionBackground.add(thermoPanel);

        //scorePanel  
        scorePanel = new BPanel(true);
        questionBackground.add(scorePanel);

        //Add mainGamePanel to root
        mainGamePanel = new BPanel(false);

        questionPanel = new BPanel(true);
        questionPanel.add(questionBackground);
        questionPanel.add(imageOfQuestionPanel);
        mainGamePanel.add(questionPanel);

        root.getContentPane().add(mainGamePanel);

        //GameOverPanel
        gameOverPanel = new BPanel(false, "buzz_images/10.png");

        winnerLabel = new BLabel("", 30); //Επικεφαλίδα νικητή παιχνιδιού
        winnerLabel.setBounds(0, 15, root.getWidth(), 50);
        gameOverPanel.add(winnerLabel);

        returnButton = new BButton(messages.getString("backToMenuButton")); //Κουμπί επιστροφής στο μενού
        returnButton.setBounds((root.getWidth() - 300) / 2, 470, 300, 50);

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuSound.stop();
                menuSound = new MusicPlayer("menu.wav");
                menuSound.loop();
                refreshGame();
            }
        });

        gameOverPanel.add(returnButton);

        //PlayersKeysPanel
        playersAllowed = game.getMaxPlayers(); //Επιτρεπτό πλήθος παιχτών
        String textOnButton;

        playersKeysPanel = new BPanel(false);
        startGameButton = new BButton(messages.getString("playButton")); //Κουμπί έναρξης παιχνιδιού
        startGameButton.setBounds((root.getWidth() - startGameButton.getWidth()) / 2, 450, startGameButton.getWidth(), startGameButton.getHeight());
        startGameButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {

                game.start(); //Αρχικοποιούνται οι γύροι του παιχνιδιού
                mainGamePanel.setVisible(true);
                playersPanel.setVisible(false);

                ((BPanel) playersPanel).setImage("buzz_images/3.jpg");

                menuSound.stop();

                gameSound = new MusicPlayer("game_music.wav");
                gameSound.loop();

                nextQuestion();
                createScorePanel(); //Δημιουργία του πίνακα βαθμολογιών
                createThermoPanel(); //Δημιουργία του πάνελ με τα θερμόμετρα

            }
        });

        playersKeysPanel.add(startGameButton);

        chooseNumPlayers = new BButton[playersAllowed]; //Θα δημιουργηθούν τόσα κουμπιά παιχτών (Παίχτης 1 , Παίχτης 2 .. ) όσα  υποστηρίζει η game
        inputNamePlayer = new BTextField[playersAllowed]; // Πλαίσιο για την εισαγωγή όνομάτων.. θα δημιουργηθούν όσα υποστηρίζει η game
        playersKeysLabel = new BLabel[playersAllowed];
        playersScoresLabel = new BLabel[playersAllowed];

        //Δημιουργία των κουμπιών  και των πλαισιών εισαγωγής ονομάτων
        for (int i = 0; i < playersAllowed; i++) {

            inputNamePlayer[i] = new BTextField(messages.getString("playerLabel") + " " + String.valueOf(i + 1));
            inputNamePlayer[i].setBounds((root.getWidth() - inputNamePlayer[i].getWidth()) / 2, i * (inputNamePlayer[i].getHeight() + 20) + 200, inputNamePlayer[i].getWidth(), inputNamePlayer[i].getHeight());
            inputNamePlayer[i].setVisible(false);
            inputNamePlayer[i].addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent ae) {
                    okNamesButton.doClick();
                }
            });

            playersNamesPanel.add(inputNamePlayer[i]);

            playersKeysLabel[i] = new BLabel();
            playersKeysLabel[i].setBounds(0, i * 50 + 150, root.getWidth(), 100);

            playersKeysPanel.add(playersKeysLabel[i]);

            textOnButton = String.valueOf(i + 1) + " ";
            if (i == 0) {
                textOnButton += messages.getString("playerLabel");
            } else {
                textOnButton += messages.getString("playersLabel");
            }

            chooseNumPlayers[i] = new BButton(textOnButton);
            chooseNumPlayers[i].setBounds((root.getWidth() - chooseNumPlayers[i].getWidth()) / 2, i * 70 + 200, chooseNumPlayers[i].getWidth(), chooseNumPlayers[i].getHeight());
            chooseNumPlayers[i].setActionCommand(String.valueOf(i + 1));
            chooseNumPlayers[i].addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent ae) {

                    playersNumPanel.setVisible(false);

                    players = Integer.parseInt(ae.getActionCommand());
                    // System.out.println(players);

                    for (int i = 0; i < players; i++) {
                        ((BTextField) inputNamePlayer[i]).refresh();
                        inputNamePlayer[i].setVisible(true); //Εμφανίζει τόσα πλαίσια εισαγωγής ονόματος όσοι οι παίχτες που θα παίξουν
                    }

                    playersNamesPanel.setVisible(true);
                }
            });

            playersNumPanel.add(chooseNumPlayers[i]);

            //Σκορ πάνελ παιχνιδιού
            playersScoresLabel[i] = new BLabel();
            playersScoresLabel[i].setBounds(0, i * 50 + 130, root.getWidth(), 100);

            gameOverPanel.add(playersScoresLabel[i]); //Προστίθενται το σκορπανελ κάθε παίχτη στο πάνελ που θα εμφανίστει στο τέλος του παιχνιδιού

        }

        //PlayersKeysPanel
        playersKeysTitle = new BLabel(messages.getString("keysLabel"));
        playersKeysTitle.setBounds(0, 50, root.getWidth(), 100);

        playersKeysPanel.add(playersKeysTitle);

        //ok Button
        okNamesButton = new BButton(messages.getString("okButton"));
        okNamesButton.setBounds((root.getWidth() - okNamesButton.getWidth()) / 2, 450, okNamesButton.getWidth(), okNamesButton.getHeight());
        okNamesButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {

                boolean inputsAccepted = true;

                for (int i = 0; i < players; i++) {
                    if (game.addPlayer(inputNamePlayer[i].getText()) == null) { //Έλεγχος έγκυρων usernames
                        inputsAccepted = false;
                        break;
                    }
                }

                try {
                    game.initialize(); //Αρχικοποίηση παιχνιδιού
                } catch (FileNotFoundException e) {

                    JOptionPane.showMessageDialog(root, messages.getString("error00"), messages.getString("errorTitle00"), JOptionPane.ERROR_MESSAGE);
                    root.dispose();
                } catch (IOException ex) {

                    JOptionPane.showMessageDialog(root, messages.getString("error01"), messages.getString("errorTitle01"), JOptionPane.ERROR_MESSAGE);

                    root.dispose();
                }

                if (inputsAccepted) {

                    String keys;
                    for (int i = 0; i < players; i++) {
                        keys = game.addPlayer(inputNamePlayer[i].getText());
                        playersKeysLabel[i].setText(game.getNameByPlayerId(i + 1) + ": " + keys.replace("", " ").trim());
                        playersKeysLabel[i].setVisible(true);
                    }

                    playersNamesPanel.setVisible(false);
                    playersKeysPanel.setVisible(true);

                }
                /*else {

                    //System.out.println("wrong");
                }*/

                ((BPanel) playersPanel).setImage("buzz_images/3b.png");

            }
        });

        //Προσθήκη συστατικών στην PlayersPanel
        playersPanel.setLayout(null);
        ((BPanel) playersPanel).setFocusOnClick();

        playersNamesPanel.add(okNamesButton);
        playersNumPanel.add(playersNumTitle);

        playersPanel.add(playersNumPanel);
        playersPanel.add(playersNamesPanel);
        playersPanel.add(playersKeysPanel);

        // High Scores Panel
        highScoresPanel = new BPanel(false, "buzz_images/9.png");
        root.getContentPane().add(highScoresPanel);

        scoresTitle = new BLabel(messages.getString("scoreLabel"));
        scoresTitle.setBounds(0, 20, root.getWidth(), 60);
        highScoresPanel.add(scoresTitle);

        highScoresTitle = new BLabel(messages.getString("singleHighscoreLabel"));
        highScoresTitle.setBounds(0, 100, 500, 50);
        highScoresPanel.add(highScoresTitle);

        winsTitle = new BLabel(messages.getString("multiplayerHighscoreLabel"));
        winsTitle.setBounds(500, 100, 500, 50);
        highScoresPanel.add(winsTitle);

        //ReturnButton
        returnFromScoresButton = new BButton(messages.getString("backLabel"));
        returnFromScoresButton.setBounds((root.getWidth() - 180) / 2, 490, 180, 50);
        returnFromScoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuPanel.setVisible(true);
                highScoresPanel.setVisible(false);
            }
        });
        highScoresPanel.add(returnFromScoresButton);

        createHighScoresPanel(); //Δημιουργία του βαθμολογικού πίνακα

        //Κουμπιά του μενού
        startButton = new BButton(messages.getString("startButton"));

        startButton.setBounds(root.getWidth() - 340, 113, 300, 70);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                try {
                    game.initialize(); //Αρχικοποίηση των λειτουργιών του παιχνιδιού
                } catch (FileNotFoundException e) {

                    JOptionPane.showMessageDialog(root, messages.getString("error00"), messages.getString("errorTitle00"), JOptionPane.ERROR_MESSAGE);
                    root.dispose();
                } catch (IOException ex) {

                    JOptionPane.showMessageDialog(root, messages.getString("error01"), messages.getString("errorTitle01"), JOptionPane.ERROR_MESSAGE);

                    root.dispose();

                }

                playersNumPanel.setVisible(true);
                playersPanel.setVisible(true);
                menuPanel.setVisible(false);

            }

        });

        scoresButton = new BButton(messages.getString("scoreButton"));
        scoresButton.setBounds(root.getWidth() - 340, 213, 300, 70);
        scoresButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {

                menuPanel.setVisible(false);
                highScoresPanel.setVisible(true);
            }
        });

        exitButton = new BButton(messages.getString("exitButton"));
        exitButton.setBounds(root.getWidth() - 340, 313, 300, 70);

        exitButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {

                root.dispose();
            }
        });

        //Προσθήκη των κουμπιών στο μενού
        menuButtonsPanel = new BPanel(true);

        menuButtonsPanel.add(startButton);
        menuButtonsPanel.add(scoresButton);
        menuButtonsPanel.add(exitButton);

        menuPanel.add(menuButtonsPanel);

        // Δημιουργία γραφικών για τις επιλογές πονταρίσματος.
        betOptionsPanel = new BPanel(false);

        betOptionsTitle = new BLabel();
        betOptionsTitle.setBounds(0, 50, root.getWidth(), 100);
        ((BPanel) betOptionsPanel).setImage("buzz_images/6.png");
        betOptionsPanel.add(betOptionsTitle);

        betCategoryLabel = new BLabel();
        betCategoryLabel.setBounds(580, 145, 210, 50);
        betOptionsPanel.add(betCategoryLabel);

        betOptionsPanel.setFocusable(true);
        mainGamePanel.add(betOptionsPanel);

        //Δημιουργία του εισαγωγικού πάνελ με τις πληροφορίες και την έξηγηση του εκάστοτε γύρου
        roundInfoPanel = new BPanel(false);
        ((BPanel) roundInfoPanel).setImage("buzz_images/7.jpg");

        roundInfoTitle = new BLabel("", 40);
        roundInfoTitle.setBounds(0, 130, root.getWidth(), 50);
        roundInfoPanel.add(roundInfoTitle);

        roundDescription = new BLabel("", true, SwingConstants.CENTER);
        roundDescription.setSize(350, 100);
        roundDescription.setBounds((root.getWidth() - roundDescription.getWidth()) / 2, 230, 350, 150);
        roundInfoPanel.add(roundDescription);

        mainGamePanel.add(roundInfoPanel);

        root.getContentPane().add(gameOverPanel);

    }

    private void refreshGame() {
        //Ανανεώνεται το gui  όταν πατιέται το κουμπί "επιστροφή στο μενού"
        gameIsOver = false;
        menuPanel.setVisible(true); //εμφάνιση του αρχικού μενού
        gameOverPanel.setVisible(false); // εξαφάνιση του τελικού πάνελ
        playersKeysPanel.setVisible(false); // εξαφάνιση του πάνελ με τα πλήκτρα κάθε χρήστη
        playersNumPanel.setVisible(true); //εμφάνιση του πάνελ με τον αριθμό των παιχτών που θα παίξουν

        //Επαναδημιουργία του scorepanel και του thermopanel
        deleteScorePanel();
        deleteThermoPanel();
        deleteHighScoresPanel();
        createHighScoresPanel();

        //Ανανέωση των συστατικών όπως τα πλαίσια κειμένου κτλπ
        for (int i = 0; i < playersAllowed; i++) {
            ((BTextField) inputNamePlayer[i]).refresh();
            inputNamePlayer[i].setVisible(false);
            playersKeysLabel[i].setVisible(false);
            playersScoresLabel[i].setVisible(false);
        }

    }

    private void createHighScoresPanel() {

        displayTop = 5; //Εμφάνιση των 5 καλύτερων παιχτών

        highScoresLabel = new BLabel[displayTop];
        String[] names = game.getNamesForHighScores(displayTop); //Τα όνοματα των 5 παιχτών με το μεγαλύτερο highscore
        Integer[] scores = game.getHighScores(displayTop);  //Τα σκορς των 5 καλύτερων παιχτών

        //Αντιστοίχηση και μορφοποίηση των παραπάνω πληροφοριών σε labels 
        for (int i = 0; i < displayTop; i++) {
            if (names[i] == null) {
                break;
            }
            String score = String.valueOf(scores[i]);
            if (scores[i] == null) {
                score = "-";
            }
            highScoresLabel[i] = new BLabel(names[i] + ": " + score);
            highScoresLabel[i].setBounds(0, 190 + i * 51, 500, 50);
            highScoresPanel.add(highScoresLabel[i]);
            //  System.out.println(names[i] + ": " + scores[i]);
        }

        //Δημιουργία του πίνακα βαθμολόγησης ως προς τον αριθμό νικών
        winsLabel = new BLabel[displayTop];
        names = game.getNamesForWins(displayTop); //Τα όνοματα των 5 παιχτών με  τις περισσότερες νίκες
        Integer[] wins = game.getTopWins(displayTop);  //Οι νίκες των 5 καλύτερων παιχτών

        //Αντιστοίχηση και μορφοποίηση των παραπάνω πληροφοριών σε labels 
        for (int i = 0; i < displayTop; i++) {
            if (names[i] == null) {
                break;
            }
            winsLabel[i] = new BLabel(names[i] + ": " + wins[i]);
            winsLabel[i].setBounds(500, 190 + i * 51, 500, 50);
            highScoresPanel.add(winsLabel[i]);
        }

    }

    private void deleteHighScoresPanel() {

        for (int i = 0; i < displayTop; i++) {
            if (highScoresLabel[i] != null) {
                highScoresPanel.remove(highScoresLabel[i]);
            }
            if (winsLabel[i] != null) {
                highScoresPanel.remove(winsLabel[i]);
            }
        }
    }

    private void nextQuestion() {

        if (game.getCurRound() == null) { //Ξεκίνημα φόρτωσης του πρώτου γύρου

            if (game.canContinue() == false) { //Αν δεν υπάρχει επόμενος γύρος

                this.gameIsOver = true; //Το παιχνίδι τελείωσε
                questionPanel.setVisible(false); //Εξαφανίζεται το πάνελ ερωτήσεων
                try {
                    game.gameOver();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(root, messages.getString("error02"), messages.getString("errorTitle02"), JOptionPane.ERROR_MESSAGE);

                }

                gameSound.stop();
                new MusicPlayer("intro.wav").play();
                gameOverPanel.setVisible(true); //Εμφάνιση του gameOverPanel
                return;
            }

            curRound = game.getCurRound(); // Παίρνουμε τον τρέχοντα γύρο

            if (curRound instanceof BetRound) {
                loadBetOptions(); //Φορτίζει τα πονταρίσματα 
            }

            showRoundInfo(); //Εμφάνιση πληροφοριών του γύρου

        } else if (!game.inSameRound()) {

            questionPanel.requestFocus(); // Το φόκους φεύγει από το root ωστε να μην μετράει το πάτημα των πλήκτρων
            if (!game.canContinue()) { // Τερματική συνθήκη παιχνίδιου, όταν δηλαδή δεν είναι στον ίδιο γύρο και δεν έχει επόμενο γύρο
                this.gameIsOver = true;
                questionPanel.setVisible(false);
                try {
                    game.gameOver();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(root, messages.getString("error02"), messages.getString("errorTitle02"), JOptionPane.ERROR_MESSAGE);

                }

                String winnerText;

                int[] scores = game.getScoreTableScores(); //Παίρνουμε τα σκορ του συγκεκριμένου παιχνιδιού κατά φθήνουσα σειρά
                String[] names = game.getScoreTableNames(); //Παίρνουμε τα ονόματα των παιχτών που έπαιξαν κατά φθήνουσα σειρά ως προς τα σκορ τους

                if (players > 1) { //Αν έπαιξαν παραπάνω από 1 παίχτη

                    if (game.tie()) {//Αν το παιχνίδι βγήκε ισόπαλο
                        winnerText = messages.getString("tieLabel");
                    } else {
                        winnerText = messages.getString("winnerLabel") + " " + game.getWinnersName();
                    }
                } else {
                    winnerText = messages.getString("endGameLabel");
                }

                //Εμφάνιση του πάνελ 
                winnerLabel.setText(winnerText);
                for (int i = 0; i < players; i++) {
                    playersScoresLabel[i].setText(names[i] + ": " + scores[i]);
                    playersScoresLabel[i].setVisible(true);
                }

                gameSound.stop();

                new MusicPlayer("intro.wav").play();

                gameOverPanel.setVisible(true);
            } else {

                curRound = game.getCurRound(); // Παίρνουμε τον τρέχοντα γύρο

                if (curRound instanceof BetRound) {
                    loadBetOptions();
                }

                showRoundInfo();
            }
        } else {

            curQuestion = game.getCurQuestion();
            root.requestFocus(); // Επιστρέφει το φόκους στο root ωστε να μετράει το πάτημα των πλήκτρων
            loadQuestion(); //Φόρτωση ερώτησης στην οθόνη
        }
    }

    private void loadQuestion() {

        roundTypeLabel.setText(messages.getString(curRound.getTypeName())); //Το όνομα του γύρου
        categoryLabel.setText(messages.getString(curQuestion.getCategory())); // Η κατηγορία της ερώτησης

        String bgImage;

        if (curQuestion instanceof ImageQuestion) { //Αν η ερώτηση είναι εικόνα τότε αλλάζει το background
            bgImage = "buzz_images/5.png";
            ((BPanel) imageOfQuestionPanel).setImage("question_images/" + ((ImageQuestion) curQuestion).getImage());

        } else {
            bgImage = "buzz_images/4.png";
        }

        ((BPanel) questionBackground).setImage(bgImage); //Θέτει background την εικόνα ανάλογα το είδος της ερώτησης

        if (curRound instanceof BetRound) {

            //Εμφάνιση του πάνελ πονταρίσματος
            betOptionsTitle.setText(messages.getString("betLabel") + game.getNameByPlayerId(game.getNextID(false)) + " " + messages.getString("questionMark"));

            betCategoryLabel.setText(messages.getString(curQuestion.getCategory()));

            betOptionsPanel.setVisible(true);
            betOptionsPanel.requestFocus();
            questionPanel.setVisible(false);

        } else if (curRound instanceof CountDownRound) {

            //Εμφάνιση του ρολογιού
            questionPanel.setVisible(true);
            countDownLabel.setVisible(true);
            countDownLabel.setForeground(Color.DARK_GRAY);

            //CountDown
            if (clock == null) {
                clock = new CountDown(((CountDownRound) curRound).getSeconds());
                clock.addCountDownListener(new CountDownListener() {

                    @Override
                    public void tick() {

                        new MusicPlayer("tick.wav").play();
                        countDownLabel.setText(String.valueOf(clock.getSeconds())); //Εμφάνιση του δευτερολέπτου σε κάθε τικ (κλήση της tick() από την CountDown)

                    }

                    @Override
                    public void finished() {

                        new MusicPlayer("time_over.wav").play();
                        countDownLabel.setForeground(Color.RED);

                        //Όταν τελειώσει ο χρόνος , τότε είναι σαν να έπαιξαν όλοι, και έτσι εμφανίζεται η επόμενη ερώτηση
                        questionBackground.requestFocus();
                        for (int i = 0; i < players; i++) {
                            game.canPlay(i + 1);
                        }
                        game.allHavePlayed();

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        curRound.executed(); //Ο γύρος εκτελέστηκε
                        countDownLabel.setVisible(false);
                        nextQuestion();
                        root.requestFocus();

                    }
                });
            }

            threadclock = new Thread(clock);
            threadclock.start(); //Ξεκίνημα αντίστροφης μέτρησης

        } else if (curRound instanceof ThermometerRound) {

            questionPanel.setVisible(true);
            thermoPanel.setVisible(true);

            //Εμφάνιση των θερμόμετρων
            for (int i = 0; i < players; i++) {
                thermometerIcon[i].setVisible(true);
            }

        } else {
            questionPanel.setVisible(true);
        }

        randoms = loadAnswers(curQuestion); //Τυχαιότητα των απαντήσεων

    }

    private void loadBetOptions() {

        //Διαγραφή των κουμπιών πονταρίσματος
        if (betOptionButton != null) {
            for (JButton button : betOptionButton) {
                betOptionsPanel.remove(button);
            }
        }

        int numOfBets = (((BetRound) curRound).getBetOptions()).size(); //Δημιουργία κουμπιών ανάλογα το πλήθος των επιλογών πονταρίσματος

        //Δημιουργία κουμπιών
        betOptionButton = new BButton[numOfBets];
        for (int i = 0; i < numOfBets; i++) {

            betOptionButton[i] = new BButton(((BetRound) curRound).getBetOptions().get(i));
            betOptionButton[i].setBounds((root.getWidth() - betOptionButton[i].getWidth()) / 2, i * 70 + 240, betOptionButton[i].getWidth(), betOptionButton[i].getHeight());
            betOptionButton[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    curBet = ae.getActionCommand();
                    questionPanel.setVisible(true);
                    betOptionsPanel.setVisible(false);
                    root.requestFocus();
                }
            });

            betOptionsPanel.add(betOptionButton[i]);

        }

    }

    private void createScorePanel() {

        scoreNameLabel = new BLabel[players];
        scoreLabel = new BLabel[players];
        for (int i = 0; i < players; i++) {
            scoreNameLabel[i] = new BLabel(game.getNameByPlayerId(i + 1));
            scoreNameLabel[i].setBounds(782, i * 74 + 166, 90, 40);

            Integer score = game.getScoreByPlayerId(i + 1);
            if (score == null) {
                score = 0;
            }
            scoreLabel[i] = new BLabel(score.toString());
            scoreLabel[i].setBounds(782, i * 74 + 200, 90, 40);

            scorePanel.add(scoreNameLabel[i]);
            scorePanel.add(scoreLabel[i]);
        }
    }

    private void updateScorePanel() {

        //Αναβάθμιση του πίνακα βαθμολόγησης
        for (int i = 0; i < players; i++) {
            scoreNameLabel[i].setText(game.getNameByPlayerId(i + 1)); //Παίρνουμε το όνομα με βάση το id
            Integer score = game.getScoreByPlayerId(i + 1); //Παίρνουμε το σκορ του παίχτη με βάση το id
            if (score == null) {
                score = 0; //Αν είναι null , τότε θέτουμε 0 ως σκορ
            }
            scoreLabel[i].setText(score.toString());
        }

    }

    private void deleteScorePanel() {

        for (int i = 0; i < players; i++) {
            scorePanel.remove(scoreNameLabel[i]);
            scorePanel.remove(scoreLabel[i]);
        }
    }

    private void createThermoPanel() {

        thermometerIcon = new BPanel[players];
        for (int i = 0; i < players; i++) {
            thermometerIcon[i] = new BPanel("buzz_images/thermo_0.png"); //Αρχικοποιεί όλους τους παίχτες με άδεια θερμόμετρα
            thermometerIcon[i].setBounds(root.getWidth() - 105, i * 77 + 183, 30, 50);
            thermometerIcon[i].setVisible(false);
            thermoPanel.add(this.thermometerIcon[i]);
        }
    }

    private void hideThermoPanel() {

        for (int i = 0; i < players; i++) {
            ((BPanel) thermometerIcon[i]).setImage("buzz_images/thermo_0.png");//Αρχικοποιεί όλους τους παίχτες με άδεια θερμόμετρα
            thermometerIcon[i].setVisible(false);
        }
    }

    private void deleteThermoPanel() {

        for (int i = 0; i < players; i++) {
            thermoPanel.remove(thermometerIcon[i]);
        }
    }

    private void showRoundInfo() {
        new Thread(new Runnable() {

            @Override
            public void run() {

                roundInfoTitle.setText(messages.getString(curRound.getTypeName())); //Εμφάνιση του τίτλου του γύρου

                new MusicPlayer("intro.wav").play();

                questionPanel.setVisible(false); //Εξαφάνιση του πανελ ερωτήσεων
                betOptionsPanel.setVisible(false); //Εξαφάνιση του πάνελ πονταρίσματος
                roundInfoPanel.setVisible(true); //Εμφάνιση του πάνελ πληροφόρησης γύρου
                roundInfoPanel.requestFocus();
                roundDescription.setText(messages.getString(curRound.getDesc(), curRound.getDescInfo())); //Εμφάνιση περιγραφής γύρου και των χαρακτηριστικών του ( βαθμοί κτλπ)
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
                curQuestion = game.getCurQuestion(); //Παίρνει την επόμενη ερώτηση
                loadQuestion(); //Την φορτώνει στην οθόνη
                hideRoundInfo(); //Απόκρυψη του πανελ πληροφόρησης γύρου
            }
        }
        ).start();
    }

    private void hideRoundInfo() {
        roundInfoPanel.setVisible(false);
    }

    private ArrayList<Integer> loadAnswers(Question question) {
        ArrayList<Integer> randAnswerIds = new ArrayList<>();

        // Δημιουργία πίνακα ακεραίων με τους αριθμούς 0-3 και ανακάτεμα αυτών.
        for (int i = 0; i < curQuestion.getAllAnswers().length; i++) {

            randAnswerIds.add(i);
        }

        long seed = System.nanoTime();
        Collections.shuffle(randAnswerIds, new Random(seed)); //Ανακάτεμα απαντήσεων με βάση έναν τυχαίο αριθμό

        questionLabel.setText(question.getQuestion()); //Εμφάνιση ερώτησης

        for (int i = 0; i < curQuestion.getAllAnswers().length; i++) {

            answerLabel[i].setText(question.getAllAnswers()[randAnswerIds.get(i)]); //Εμφάνιση πιθανών απαντήσεων με τυχαία σειρά

        }

        root.update(root.getGraphics()); //Ανανέωση του GUI

        return randAnswerIds;
    }

    private void afterAnswer(String answer, String correctAnswer) {

        if (game.isLastCorrect()) {

            new MusicPlayer("correct.wav").play();
            //Parser.show("Μπράβο " + game.getNameOfPlayerByAnswer(answer) + ", απάντησες ΣΩΣΤΑ !!!");

        } else {

            new MusicPlayer("wrong.wav").play();

            //Parser.show("Λυπάμαι " + game.getNameOfPlayerByAnswer(answer) + ", απάντησες ΛΑΘΟΣ.");
            //Parser.show("Η σωστή απάντηση ήταν " + correctAnswer + ".");
        }

    }

    private void afterAnswerBonus() {

        int bonus;
        if (game.isLastCorrect()) {

            bonus = game.getLastBonus(); //Παίρνει την πιο πρόσφατη τιμή του βαθμού που προστέθηκε σε έναν παίχτη

            if (bonus != 0) {

                // Parser.show("Κέρδισες " + bonus + " πόντους!");
            }

        } else {

            bonus = game.getLastBonus();
            if (bonus != 0) {

                // Parser.show("Έχασες " + Math.abs(bonus) + " πόντους...");
            }

        }

        updateScorePanel(); //Ανανέωση του σκορ πάνελ 

    }

    /*private void showScores() {

        Parser.show("");
        Parser.show("ΣΚΟΡ:");

        Parser.show(game.getScores());

        Parser.show("");
    }*/
    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        Controller cont = new Controller();

    }
}
