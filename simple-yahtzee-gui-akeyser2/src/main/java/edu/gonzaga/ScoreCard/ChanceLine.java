package edu.gonzaga.ScoreCard;

public class ChanceLine extends ScoreLine{
    
    public ChanceLine(String name, String code) {
        super(name, code);
    }

    public ChanceLine(Integer cur, Integer pos, Boolean tf, String name, String code) {
        super(cur, pos, tf, name, code);
    }

    //calculation for upper lines
    public void calculateVal(Integer[] finalDice) {

        Integer total = 0;

        for(Integer i = 0; i < finalDice.length; i++)
            total += finalDice[i];
        posVal = total;

    }


    //Using the same template for consistency, if I want to change the name I could.
    @Override
    public void printLine(){
        System.out.println(lineName + "\t\t\tSum of Dice| "  + curVal);
    }

    public void linePredict(){
        if(isFilled == false) {
            System.out.println("(" + lineCode + ")" + lineName + "\t\tSum of Dice| "  + posVal);
        }
        else if(isFilled == true) {
            System.out.println("(" + lineCode + ")" + lineName + "\t\tSum of Dice| Filled");
        }  
    }

}
