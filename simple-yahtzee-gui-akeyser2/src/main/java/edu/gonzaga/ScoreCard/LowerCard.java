package edu.gonzaga.ScoreCard;



public class LowerCard {
    
    
    /********
    Variables
    *********/
    private String[] lowerNameArray = 
    {"3 of a kind", "4 of a kind", "Full House", "Low Straight", "High Straight", "Yahtzee", "Chance"};
    private String[] lowerCodeArray = {"3K", "4K", "FH", "LS", "SS", "YH", "CH"};
    private ScoreLine[] lowerLineArray = new ScoreLine[lowerNameArray.length];

    //these are private vals for the total of upper section
    private Integer baseTot;
    private Integer lowerTot;
    private static final Integer DEFAULT_BASE_TOT = 0;
    /********
    Variables
    *********/

    public LowerCard() {
        baseTot = DEFAULT_BASE_TOT;

        lowerLineArray[0] = new OakLine(3, lowerNameArray[0], lowerCodeArray[0]);
        lowerLineArray[1] = new OakLine(4, lowerNameArray[1], lowerCodeArray[1]);
        lowerLineArray[2] = new HouseLine(lowerNameArray[2], lowerCodeArray[2]);
        lowerLineArray[3] = new StraightLine(4, lowerNameArray[3], lowerCodeArray[3]);
        lowerLineArray[4] = new StraightLine(5, lowerNameArray[4], lowerCodeArray[4]);
        lowerLineArray[5] = new YahtzeeLine(lowerNameArray[5], lowerCodeArray[5]);
        lowerLineArray[6] = new ChanceLine(lowerNameArray[6], lowerCodeArray[6]);
    }

    public LowerCard(Integer tot) {
        baseTot = tot;
        
        lowerLineArray[0] = new OakLine(3, lowerNameArray[0], lowerCodeArray[0]);
        lowerLineArray[1] = new OakLine(4, lowerNameArray[1], lowerCodeArray[1]);
        lowerLineArray[2] = new HouseLine(lowerNameArray[2], lowerCodeArray[2]);
        lowerLineArray[3] = new StraightLine(4, lowerNameArray[3], lowerCodeArray[3]);
        lowerLineArray[4] = new StraightLine(5, lowerNameArray[4], lowerCodeArray[4]);
        lowerLineArray[5] = new YahtzeeLine(lowerNameArray[5], lowerCodeArray[5]);
        lowerLineArray[6] = new ChanceLine(lowerNameArray[6], lowerCodeArray[6]);
    }


    public void printLower() {

        System.out.println("\nLower Section");
        System.out.println("________________________");
        //prints out the predict line for the upper lines
        for(Integer i = 0; i < lowerLineArray.length; i++){
            lowerLineArray[i].printLine();
        }
        System.out.println("\n_________________________\n" );
        System.out.println("Total\t\t\t|" + baseTot);
    }
    

    //calls predict on all upper lines
    public void predictAll(Integer[] finalDice){
        
        for(Integer i = 0; i < lowerLineArray.length; i++){
            lowerLineArray[i].calculateVal(finalDice);
        }

    }


    //prompts user to choose what value to keep
    public void printPredict() {

        System.out.println("\nLower Section");
        System.out.println("________________________");
        //prints out the predict line for the upper lines
        for(Integer i = 0; i < lowerLineArray.length; i++){
            lowerLineArray[i].linePredict();
        }
        System.out.println("\n_________________________\n" );
    
    }
    
    public Boolean checkKey(String key){
        
        for(Integer i = 0; i < lowerLineArray.length; i++){
            if(key.equals(lowerLineArray[i].getLineCode())){
                if(lowerLineArray[i].getFilled() == true)
                    return false;
                lowerLineArray[i].setCurVal();
            }         
        }
        return true;
    }

    public Boolean checkFull() {
        for(Integer i = 0; i < lowerLineArray.length; i++){
            if(lowerLineArray[i].getFilled() != true){
                return false;
            }         
        }
        return true;
    }

    //sets up total of upper card
    public void setTotal() {

        Integer i = 0;
        lowerTot = baseTot;

        while(i < lowerLineArray.length){
            lowerTot += lowerLineArray[i].getCurVal();
            i++;
        }

    }

    public Integer getTot(){
        return lowerTot;
    }

    public ScoreLine[] getLowerArray(){
        return lowerLineArray;
    }

}
