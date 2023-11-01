package edu.gonzaga;

import java.util.Scanner;

import edu.gonzaga.ScoreCard.*;

/*
* Class for any given player, includes Hand, Name, Player#, etc.
*/



public class Player {
    
    Scanner sc = new Scanner(System.in);

    //private ScoreCard myCard = new ScoreCard(5);
    private FullCard myCard = new FullCard();
    private DiceHand myHand = new DiceHand(5);

    private Boolean gameOngoing = true;
    private String playerName;
    private Integer playerPlace;
    private Boolean tfReRoll[] = new Boolean[5]; 
    //might include these as like Guest 1,2,3,4,etc. Also to keep track of turn possibly
    //private Integer playerNo;
    private static final String DEFAULT_PLAYER_NAME = "Player";
    //private static final Integer DEFAULT_PLAYER_NO = 0;

    public Player() {
        this.playerName = DEFAULT_PLAYER_NAME;
        myHand.fillHand();
    }

    public Player(String name) {
        this.playerName = name;
        myHand.fillHand();
    }


    public String displayName() {
        return this.playerName;
    }


    public String playerResponse() {

        String reroll;
        System.out.println("Which values would you like to keep? (y/n)");
        reroll = sc.nextLine();
        
        return reroll;
    }

    public void strToBools(String response) {

        char[] ch = response.toCharArray();
        for(int i = 0; i < 5; i++){
            if(ch[i] == 'y')
                tfReRoll[i] = false;
            else   
                tfReRoll[i] = true;
        }

    }
    

    public Boolean takeTurn(Integer turnLength) {
    
        Boolean contTf = false;
        myHand.fillHand();
        System.out.println("Now rolling " + playerName + "'s dice!");
        // System.out.println("Test");
        myHand.rollAll();
        myHand.showAll();

        //this goes through the set amount of rerolls, unless input is all yes
        for(Integer i  = 0; i < turnLength; i++){
            String resp = playerResponse();

            if(resp.equals("q")) {
                i = turnLength;
                contTf = true;
                gameOngoing = false;
                myCard.printCard();
            }
            else if(resp.length() != 5) {
                System.out.println("Invalid Input, please try again.");
            }
            else if(resp.equals("yyyyy")) {
                i = turnLength;  
            }
            else {
                strToBools(resp);

                myHand.rollSome(tfReRoll);
                myHand.showAll();
                i++;
            }        
        }
        //this is a loop that prompts to see if player wants to see scorecard or continue
        while(contTf == false) {
            System.out.println("Input (s) to see your scoring options, or (c) to see your current scorecard!");
            String cont = sc.nextLine();

            if(cont.equals("s")) {
                myCard.predictFull(myHand.getAll());
                //myCard.getInput();
                contTf = true;
            }
            else if(cont.equals("c")) {
                myCard.printCard();
            }
            else if(cont.equals("q")) {
                contTf = true;
                gameOngoing = false;
                myCard.printCard();
            }
            else {
                System.out.println("Invalid input, try again.");
            }
            
        }
        //sets the total of the scorecard at the end of every turn
        myCard.setTotals();
        //if player hasn't quit, this will check to see if the card is full
        if(gameOngoing == true)
            gameOngoing = myCard.checkCont();

        return gameOngoing;

    }

    public void setPlace(Integer place) {
        playerPlace = place;
    }

    public Integer getPlace() {
        return playerPlace;
    }

    public Integer getScore() {
        myCard.setTotals();
        return myCard.getGrandTotal();
    }

    public void playerRoll(String reroll) {
        strToBools(reroll);
        myHand.rollSome(tfReRoll);
    }

    public Integer[] getHand() {
        return myHand.getAll();
    }

    public void changeName(String newName) {
        playerName = newName;
    }

    public String getName() {
        return playerName;
    }

    public void chooseLine(String code) {
        // this should work, just when button is pressed
        // call this and input code depending on button
        // then have something that goes through all the lines and sets them
        myCard.getInput(code);
        myCard.setTotals();
    }

    public ScoreLine[] getScorelineArray() {
        return myCard.getLineArray();
    }
    
    public Integer[] getMiscValuesArray() {
        return myCard.getMiscArray();
    }

    public void setPosVals() {
        myCard.predictFull(getHand());
    }

    public FullCard getCard() {
        return myCard;
    }
    
}
