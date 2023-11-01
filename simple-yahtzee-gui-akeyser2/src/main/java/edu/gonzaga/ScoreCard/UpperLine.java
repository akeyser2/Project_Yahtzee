package edu.gonzaga.ScoreCard;

public class UpperLine extends ScoreLine{
    
    private Integer dieNum;

    //calls the setup for ScoreLine as well as adds the dienum for counting
    public UpperLine(Integer num, String name, String code) {
        super(name, code);
        dieNum = num;
    }

    public UpperLine(Integer num, Integer cur, Integer pos, Boolean tf, String name, String code) {
        super(cur, pos, tf, name, code);
        dieNum = num;
    }

    //calculation for upper lines
    public void calculateVal(Integer[] finalDice) {

        Integer i = 0;
        posVal = 0;

        while(i < finalDice.length){
            if(finalDice[i] == dieNum)
                posVal += finalDice[i];
            i++;
        }

    }

    //Uses a template to print every top row
    public void printLine(){
        System.out.println("Count and add only " + lineName + "  \t| " + curVal);
    }

    public void linePredict(){
        if(isFilled == false) {
            System.out.println("(" + lineCode + ")Count and add only " + lineName + "  \t| " + posVal);
        }
        else if(isFilled == true) {
            System.out.println("(" + lineCode + ")Count and add only " + lineName + "  \t| Filled");
        }  
    }


}
