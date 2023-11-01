package edu.gonzaga;


/*
* Class for a players "hand" of dice
*/



public class DiceHand {

    private Integer numDice;
    private Die myDice[];
    private Integer myDiceVals[];
    private static final Integer DEFAULT_NUM_DICE = 5;

    public DiceHand(){
        this.numDice = DEFAULT_NUM_DICE;
        this.myDice = new Die[numDice];

        this.myDiceVals = new Integer [numDice];
    }

    public DiceHand(Integer numDice) {
        this.numDice = numDice;
        this.myDice = new Die[numDice];
        
        this.myDiceVals = new Integer [numDice];
    }

    public DiceHand(Integer numDice, Die die[]) {
        this.numDice = numDice;
        this.myDice = die;
        
        this.myDiceVals = new Integer [numDice];
    }


    public void fillHand() {

        for(int i = 0; i < numDice; i++) {
            this.myDice[i] = new Die(6);
        }
    }
    

    public void rollAll() {
        for(int i = 0; i < numDice; i++) {
            this.myDice[i].roll();
        }
    }

    public void rollSome(Boolean reroll[]) {
        for(int i = 0; i < numDice; i++) {
            if(reroll[i])
                this.myDice[i].roll();
        }
    }


    public void showAll() {

        for(int i = 0; i < numDice; i++) {
            this.myDiceVals[i] = this.myDice[i].getSideUp();
        }
        System.out.print("Here are your dice: ");
        for(int i = 0; i < numDice; i++) {
            System.out.print(this.myDiceVals[i] + " ");
        }
        System.out.println(" ");
    }

    public Integer[] getAll() {
        for(int i = 0; i < numDice; i++) {
            this.myDiceVals[i] = this.myDice[i].getSideUp();
        }
        return myDiceVals;
    }

}

