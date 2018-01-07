package logic;

import java.util.ArrayList;
import java.util.List;

/**
 *Αυτή η κλάση υλοποιεί τον τύπο γύρου Γρήγορη Απάντηση. Κληρονομεί από την
 * κλάση Round.
 * 
 * @author leaderq
 * @author Thanasis
 * @since 5/1/17
 * @version 1.0
 */
public class FastAnswerRound extends Round {

    private List<Integer> bonuses;
    private int curBonusId;

    public FastAnswerRound(int times, List<Integer> bonuses) {

        super(times,"fastanswerLabel","fastanswerDesc");
        this.curBonusId = 0;
        this.bonuses = new ArrayList<>(bonuses);
        
    }

    public FastAnswerRound(List<Integer> bonuses) {
        
        this(5, bonuses);

    }
    
    
    @Override
    public String[] getDescInfo() {
        String[] descInfo;
        int numOfBonuses = bonuses.size();
        
        descInfo = new String[numOfBonuses];
        for (int i=0; i<numOfBonuses; i++){
            descInfo[i] = String.valueOf(bonuses.get(i)); //Παίρνει κάθε bonus της λίστας bonuses
        }
        
        return descInfo;
    }

    public int getNextBonus(){
        if (curBonusId == bonuses.size()){
            curBonusId = 0;
        }
        return bonuses.get(curBonusId++);
    }
    
    
    @Override
    public void executed() {
        super.executed();
        curBonusId = 0;
    }
    
    
}
