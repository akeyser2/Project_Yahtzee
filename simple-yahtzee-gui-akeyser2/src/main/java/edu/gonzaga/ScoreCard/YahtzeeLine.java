package edu.gonzaga.ScoreCard;

public class YahtzeeLine extends ScoreLine{
    
    private Integer numOfYahtzees;
    private Integer yahtzeeVal;
    private Integer bonusYahtzeeVal;
    private Integer posBonusVal;
    private final static Integer DEFAULT_NUM_YAHTZEES = 0;
    private final static Integer DEFAULT_YAHTZEE_VAL = 50;
    private final static Integer DEFAULT_BONUS_YAHTZEE_VAL = 100;


    public YahtzeeLine(String name, String code) {
        super(name, code);
        numOfYahtzees = DEFAULT_NUM_YAHTZEES;
        yahtzeeVal = DEFAULT_YAHTZEE_VAL;
        bonusYahtzeeVal = DEFAULT_BONUS_YAHTZEE_VAL;
    }

    public YahtzeeLine(Integer cur, Integer pos, Boolean tf, String name, 
                        String code, Integer numYat, Integer yatVal, Integer bonVal) {

        super(cur, pos, tf, name, code);
        numOfYahtzees = numYat;
        yahtzeeVal = yatVal;
        bonusYahtzeeVal = bonVal;
    }

    //calculation for upper lines
    public void calculateVal(Integer[] finalDice) {

        Integer numOfone;

        for(Integer i = 1; i <= finalDice.length+1; i++){
            numOfone = 0;
            for(Integer j = 0; j < finalDice.length; j++){
                if(finalDice[j] == i)
                    numOfone += 1;
            }

            if(numOfone >= 5){
                posVal = yahtzeeVal;
                posBonusVal = numOfYahtzees * bonusYahtzeeVal;
            }
        }
    }   

    @Override
    public void setCurVal() {
        curVal = posVal + (numOfYahtzees * bonusYahtzeeVal);
        if(curVal == yahtzeeVal)
            numOfYahtzees++;
        isFilled = true;
    }


    //Using the same template for consistency, if I want to change the name I could.
    @Override
    public void printLine() {
        System.out.println(lineName + "!\t\t\t " + yahtzeeVal + "| "  + curVal);
        System.out.println(lineName + " Bonus\t\t\t" + bonusYahtzeeVal + "| "  + bonusYahtzeeVal * numOfYahtzees);
    }

    public void linePredict() {
        if(isFilled == false) {
            System.out.println("(" + lineCode + ")" + lineName + "!\t\t\t " + yahtzeeVal + "| "  + posVal);
            System.out.println("\t" + lineName + " Bonus\t\t" + bonusYahtzeeVal + "| 0");
        }
        else if(isFilled == true) {
            System.out.println("(" + lineCode + ")" + lineName + "!\t\t\t " + yahtzeeVal + "| Filled");
            System.out.println("\t" + lineName + " Bonus\t\t " + bonusYahtzeeVal + "| "  + posBonusVal);
        }  
    }


}
