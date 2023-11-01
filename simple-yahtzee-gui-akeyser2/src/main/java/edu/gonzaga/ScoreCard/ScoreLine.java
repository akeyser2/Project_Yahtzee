package edu.gonzaga.ScoreCard;

public abstract class ScoreLine {
    protected Integer curVal;
    protected Integer posVal;
    protected Boolean isFilled;
    protected String lineName;
    protected String lineCode;
    private static final Integer DEFAULT_CUR_VAL = 0;
    private static final Integer DEFAULT_POS_VAL = 0;
    private static final Boolean DEFAULT_IS_FILLED = false;

    /*
     * Constructor 
     */
    public ScoreLine(String name, String code) {
        curVal = DEFAULT_CUR_VAL;
        posVal = DEFAULT_POS_VAL;
        isFilled = DEFAULT_IS_FILLED;
        lineName = name;
        lineCode = code;
    }

    /*
     * Customizable constructor (For testing)
     */
    public ScoreLine(Integer cur, Integer pos, Boolean tf, String name, String code) {
        curVal = cur;
        posVal = pos;
        isFilled = tf;
        lineName = name;
        lineCode = code;
    }

    //returns possible value
    public Integer getPosVal() {
        return posVal;
    }
    
    //returns current value
    public Integer getCurVal() {
        return curVal;
    }

    //sets current val to possible val
    public void setCurVal() {
        curVal = posVal;
        isFilled = true;
    }

    //returns true or false weather line is full
    public Boolean getFilled() {
        return isFilled;
    }

    //returns name of the line
    public String getLineName() {
        return lineName;
    }

    //returns code of the line
    public String getLineCode() {
        return lineCode;
    }

    /*
     * will be different calculations for all types of lines
     */
    public abstract void calculateVal(Integer[] finalDice);

    /*
     * will print out the prediction line with the available code 
     * for the player to select
     */
    public abstract void linePredict();

    /*
     * will print the normal score line output, with the current value
     */
    public abstract void printLine();

}


//basically this is guna be the parent to all other lines
//good luck soldier o7