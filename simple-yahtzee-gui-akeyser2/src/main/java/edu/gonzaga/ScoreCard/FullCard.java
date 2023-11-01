package edu.gonzaga.ScoreCard;

import java.util.Arrays;
import java.util.Scanner;

public class FullCard {

    Scanner sc = new Scanner(System.in);

    private UpperCard myUpper = new UpperCard();
    private LowerCard myLower = new LowerCard();
    private Integer baseTot;
    private Integer grandTotal;
    private static final Integer DEFAULT_BASE_TOT = 0;

    public FullCard() {
        baseTot = DEFAULT_BASE_TOT;
    }

    public FullCard(Integer num) {
        baseTot = num;
    }

    public void printCard() {
        myUpper.printUpper();
        myLower.printLower();
        System.out.println("Grand Total\t\t|" + grandTotal);
    }

    public void predictFull(Integer[] finalDice) {
        myUpper.predictAll(finalDice);
        myLower.predictAll(finalDice);
    }

    public void getInput(String key) {

        myUpper.printPredict();
        myLower.printPredict();

        Boolean isEmpty = false;
        while(isEmpty == false) {

            // System.out.println("What category would you like to score?");
            // String key = sc.nextLine();

            isEmpty = myUpper.checkKey(key);
            if(isEmpty == false) 
                System.out.println("Category is already full, please choose another.");
            isEmpty = myLower.checkKey(key);
            if(isEmpty == false) 
                System.out.println("Category is already full, please choose another.");
            
        }
    }

    public Boolean checkCont() {
        if(myUpper.checkFull() && myLower.checkFull())
            return false;
        return true;
    }

    public void setTotals() {
        myUpper.setTotal();
        myLower.setTotal();
        grandTotal = baseTot;
        Integer upperTot;
        if(myUpper.getUpperBonusTot() != 0)
            upperTot = myUpper.getUpperBonusTot();
        else
            upperTot = myUpper.getUpperTot();
        Integer lowerTot = myLower.getTot();
        grandTotal += upperTot; 
        grandTotal += lowerTot;
    }

    public Integer getGrandTotal() {
        return grandTotal;
    }

    public Integer[] getMiscArray() {
        setTotals();
        Integer[] misc = new Integer[7];
        misc[0] = myUpper.getUpperTot(); // upper tot
        if(misc[0] >= 63) {
            misc[1] = myUpper.getBonusVal(); //upper bonus val
            misc[2] = myUpper.getBonusVal() + myUpper.getUpperTot(); //upper + bonus
        }
        else {
            misc[1] = 0; // no bonus
            misc[2] = myUpper.getUpperTot(); // just up tot
        }
        misc[3] = 0; // yahtzee bonus
        misc[4] = misc[2]; // upper again
        misc[5] = myLower.getTot(); // lower tot
        misc[6] = grandTotal; // grand total

        return misc;
    }

    public ScoreLine[] getLineArray(){
        ScoreLine[] tmp = Arrays.copyOf(myUpper.getUpperArray(), myUpper.getUpperArray().length + myLower.getLowerArray().length);
        System.arraycopy(myLower.getLowerArray(), 0, tmp, myUpper.getUpperArray().length, myLower.getLowerArray().length);
        return tmp;
    }
    

}
