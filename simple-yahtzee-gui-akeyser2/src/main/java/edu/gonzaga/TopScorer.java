package edu.gonzaga;

public class TopScorer {
    
    private String playerName;
    private Integer playerScore;
    private Integer playerPlace;
    private static final String DEFAULT_PLAYER_NAME = "AAA";
    private static final Integer DEFAULT_PLAYER_SCORE = 0;
    private static final Integer DEFAULT_PLAYER_PLACE = 0;

    public TopScorer() {
        playerScore = DEFAULT_PLAYER_SCORE;
        playerName = DEFAULT_PLAYER_NAME;
        playerPlace = DEFAULT_PLAYER_PLACE;
    }

    public TopScorer(String name, Integer score) {
        playerName = name;
        playerScore = score;
        playerPlace = DEFAULT_PLAYER_PLACE;
    }

    public TopScorer(String name, Integer score, Integer place) {
        playerName = name;
        playerScore = score;
        playerPlace = place;
    }

    public String getLine() {
        String line = "#" + playerPlace + "\t" + playerScore + "pts\t" + playerName;
        return line;
    }

    public Integer getScore() {
        return playerScore;
    }

    public Integer getPlace() {
        return playerPlace;
    }

    public String getName() {
        return playerName;
    }

    public void setPlace(int place) {
        playerPlace = place;
    }

    public void setScore(int score) {
        playerScore = score;
    }

    public void setName(String name) {
        playerName = name;
    }


    
}
