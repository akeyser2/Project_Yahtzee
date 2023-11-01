package edu.gonzaga.ScoreCard;

public class HouseLine extends ScoreLine{

    public HouseLine(String name, String code) {
        super(name, code);
    }

    public HouseLine(Integer cur, Integer pos, Boolean tf, String name, String code) {
        super(cur, pos, tf, name, code);
    }

    //calculation for upper lines
    public void calculateVal(Integer[] finalDice) {

        Integer p1 = 0;
        Integer p2 = 0;
        //Integer firstNum = 0;
        Integer numOfone;

        for(Integer i = 1; i <= finalDice.length+1; i++){
            numOfone = 0;
            for(Integer j = 0; j < finalDice.length; j++){
                if(finalDice[j] == i)
                    numOfone += 1;
            }
            if(numOfone == 3)
                p1 = 1;
            else if(numOfone == 2)
                p2 = 1;
        }

        if(p1 + p2 == 2)
            posVal = 25;
        

    }


    //Using the same template for consistency, if I want to change the name I could.
    //Honestly make the value of FH and straights customizable?
    @Override
    public void printLine(){
        System.out.println(lineName + "\t\t\t 25| "  + curVal);
    }

    public void linePredict(){
        if(isFilled == false) {
            System.out.println("(" + lineCode + ")" + lineName + "\t\t\t 25| "  + posVal);
        }
        else if(isFilled == true) {
            System.out.println("(" + lineCode + ")" + lineName + "\t\t\t 25| Filled");
        }  
    }

}
