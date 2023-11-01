package edu.gonzaga.ScoreCard;

public class OakLine extends ScoreLine{

    private Integer oakAmount;

    //calls the setup for ScoreLine as well as adds the oakAmount for 3 or 4 for counting
    public OakLine(Integer num, String name, String code) {
        super(name, code);
        oakAmount = num;
    }

    public OakLine(Integer num, Integer cur, Integer pos, Boolean tf, String name, String code) {
        super(cur, pos, tf, name, code);
        oakAmount = num;
    }

    //calculation for upper lines
    public void calculateVal(Integer[] finalDice) {

        Integer total = 0;
        Integer numOfone = 0;

        for(Integer i = 1; i <= finalDice.length; i++){
            numOfone = 0;
            for(Integer j = 0; j < finalDice.length; j++){
                if(finalDice[j] == i)
                    numOfone += 1;
            }
            
            if(numOfone >= oakAmount){
                for(Integer j = 0; j < finalDice.length; j++)
                    total += finalDice[j];
                i = finalDice.length;
            }
        }
        
        posVal = total;

    }


    //Uses a template to print 3 and 4
    @Override
    public void printLine(){
        System.out.println(oakAmount + " of a kind\t\tSum of Dice| "  + curVal);
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
