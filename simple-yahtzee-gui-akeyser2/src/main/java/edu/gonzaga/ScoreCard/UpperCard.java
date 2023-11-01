package edu.gonzaga.ScoreCard;

public class UpperCard {


    /********
    Variables
    *********/
    private String[] upperNameArray = {"Aces", "Twos", "Threes", "Fours", "Fives", "Sixes"};
    private String[] upperCodeArray = {"1S", "2S", "3S", "4S", "5S", "6S"};
    private ScoreLine[] upperLineArray = new ScoreLine[upperNameArray.length];

    //these are private vals for the total of upper section
    private Integer baseTot;
    private Integer upperTot;
    private Integer bonusVal;
    private Integer upperBonusTot;
    private static final Integer DEFAULT_BASE_TOT = 0;
    private static final Integer DEFAULT_BONUS_VAL = 35;
    /********
    Variables
    *********/

    public UpperCard() {
        baseTot = DEFAULT_BASE_TOT;
        bonusVal = DEFAULT_BONUS_VAL;
        for(Integer i = 0; i < upperLineArray.length; i++){
            upperLineArray[i] = new UpperLine(i+1, upperNameArray[i], upperCodeArray[i]);
        }
    }

    public UpperCard(Integer base, Integer bon) {
        baseTot = base;
        bonusVal = bon;
        for(Integer i = 0; i < upperLineArray.length; i++){
            upperLineArray[i] = new UpperLine(i, upperNameArray[i], upperCodeArray[i]);
        }
    }

    public void printUpper() {

        System.out.println("\nUpper Section");
        System.out.println("________________________");
        //prints out the predict line for the upper lines
        for(Integer i = 0; i < upperLineArray.length; i++){
            upperLineArray[i].printLine();
        }
        System.out.println(
            "\n_________________________\n" +
            "Total\t\t\t| " + baseTot +
            "\n 63 + scores a 35 Bonus\t| " + bonusVal +
            "\n Total of Upper Section\t| " + upperBonusTot +
            "\n_________________________\n"
        );

    }

    //calls predict on all upper lines
    public void predictAll(Integer[] finalDice){
        
        for(Integer i = 0; i < upperLineArray.length; i++){
            upperLineArray[i].calculateVal(finalDice);
        }

    }


    //prompts user to choose what value to keep
    public void printPredict() {

        System.out.println("\nUpper Section");
        System.out.println("________________________");
        //prints out the predict line for the upper lines
        for(Integer i = 0; i < upperLineArray.length; i++){
            upperLineArray[i].linePredict();
        }
        System.out.println("\n_________________________\n" );

    }
        
    public Boolean checkKey(String key){
    
        for(Integer i = 0; i < upperLineArray.length; i++){
            if(key.equals(upperLineArray[i].getLineCode())){
                if(upperLineArray[i].getFilled() == true)
                    return false;
                upperLineArray[i].setCurVal();
            }         
        }
        return true;
    }

    public Boolean checkFull() {
        //if any of the lines aren't full, it returns false
        for(Integer i = 0; i < upperLineArray.length; i++){
            if(upperLineArray[i].getFilled() != true){
                return false;
            }         
        }
        return true;
    }

    //sets up total of upper card
    public void setTotal() {

        Integer i = 0;
        upperTot = baseTot;

        while(i < upperLineArray.length){
            upperTot += upperLineArray[i].getCurVal();
            i++;
        }
        if(baseTot >= 63)
            upperBonusTot = upperTot + bonusVal;
        else
            upperBonusTot = 0;

    }

    public Integer getUpperTot(){
        return upperTot;
    }

    public Integer getBonusVal(){
        return bonusVal;
    }
    
    public Integer getUpperBonusTot(){
        return upperBonusTot;
    }
    
    public ScoreLine[] getUpperArray(){
        return upperLineArray;
    }
}

