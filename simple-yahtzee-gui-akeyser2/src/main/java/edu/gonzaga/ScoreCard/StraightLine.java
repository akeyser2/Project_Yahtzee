package edu.gonzaga.ScoreCard;

public class StraightLine extends ScoreLine{

    private Integer straightLength;

     //calls the setup for ScoreLine as well as adds the dienum for counting
     public StraightLine(Integer num, String name, String code) {
        super(name, code);
        straightLength = num;
    }

    public StraightLine(Integer num, Integer cur, Integer pos, Boolean tf, String name, String code) {
        super(cur, pos, tf, name, code);
        straightLength = num;
    }

    //calculation for upper lines
    public void calculateVal(Integer[] finalDice) {

        Integer next;
        Integer foundNext;

        for(Integer start = 1; start <= finalDice.length+1; start++) {
            next = start;
            foundNext = 0;

            for(Integer i = 0; i < finalDice.length; i++) {
                if(finalDice[i] == next) {
                    next++;
                    foundNext++;
                    i = -1;
                }
            }

            if(foundNext >= straightLength) 
                posVal = (straightLength - 1) * 10;   
        }
    }

    //Prints different line 
    @Override
    public void printLine(){
        System.out.println(lineName + "\t\t\t " + ((straightLength - 1) * 10) + "| " + curVal);
    }

    public void linePredict(){
        if(isFilled == false) {
            System.out.println("(" + lineCode + ")" + lineName + "\t\t " + ((straightLength - 1) * 10) + "| " + posVal);
        }
        else if(isFilled == true) {
            System.out.println("(" + lineCode + ")" + lineName + "\t\t " + ((straightLength - 1) * 10) + "| Filled");
        }  
    }

}
