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
public class MessageBundle_en_EN extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return contents;
    }

    private Object[][] contents = {
        {"startButton", "Start"},
        {"scoreButton", "Score"},
        {"exitButton", "Quit"},
        {"settingsButton", "Settings"},
        {"howManyPlayersLabel", "How many players will play?"},
        {"playerLabel", "Player"},
        {"playersLabel", "Players"},
        {"enterNamesLabel", "Enter the names of the players"},
        {"playButton", "Play"},
        {"okButton", "OK"},
        {"welcomeLabel", "Welcome to Buzz Game!"},
        {"scoreLabel", "Score"},
        {"singleHighscoreLabel", "Single Game"},
        {"multiplayerHighscoreLabel", "Multiplayer Game"},
        {"backLabel", "Back"},
        {"winnerLabel", "The winner is"},
        {"tieLabel", "It's a tie !"},
        {"endGameLabel", "End!"},
        {"languageLabel", "Language"},
        {"yourScoreLabel", "Your Score is"},
        {"HISTORY", "History"},
        {"SPORT", "Sport"},
         {"OTHERS", "Others"},
        {"TECHNOLOGY", "Technology"},
        {"SCIENCE", "Science"},
        {"keysLabel", "The keys are"},
        {"saveLabel", "Save"},
        {"betLabel", "How much do you bet "},
        {"backToMenuButton", "Back to menu"},
        {"newGameButton", "New Game"},
        {"congratsLabel", "Congratulations"},
        {"questionMark", "?"},
        {"betroundLabel", "Bet"},
        {"betroundDesc", "You choose how many points you bet. If you answer correctly, you earn these points otherwise you lose them."},
        {"correctanswerLabel", "Correct Answer"},
        {"correctanswerDesc", "Whoever manages to answer and answers correctly wins %s points."},
        {"countdownLabel", "Count Down"},
        {"countdownDesc", "You have 5 seconds. The faster you answer the more points you gain."},
        {"fastanswerLabel", "Fast Answer"},
        {"fastanswerDesc", "The first to answer correctly wins %s points. The second wins %s points."},
        {"thermometerLabel", "Thermometer"},
        {"thermometerDesc", "The first player to correctly answer %s questions wins %s points."},
        {"error02", "Scores file could not be saves. Check user's permissions on the game's folder."},
        {"errorTitle02", "Could not write the scores file"},
         {"error01", "Question file can not be opened. Check user's permissions on the game's folder."},
         {"errorTitle01", "Question file"},
          {"error00", "Question file does not exist or has been modified incorrectly. Check if file exists on the questions's folder."},
         {"errorTitle00", "Question file not found"},
    };
    

}
