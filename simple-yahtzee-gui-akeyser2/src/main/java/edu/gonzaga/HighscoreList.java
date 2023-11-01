package edu.gonzaga;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public class HighscoreList {
    
    private Integer leaderboardLength;
    private static final Integer DEFAULT_LEADERBOARD_LENGTH = 10;
    private TopScorer topPlayers[];

    public HighscoreList() {
        leaderboardLength = DEFAULT_LEADERBOARD_LENGTH;
        topPlayers = new TopScorer[leaderboardLength+1];
    }

    public HighscoreList(Integer len) {
        leaderboardLength = len;
        topPlayers = new TopScorer[leaderboardLength+1];
    }

    @Override
    public String toString() {

        String ret = "\nTop " + leaderboardLength + " Leaderboard: \n\n";
        for(Integer i = 0; i < leaderboardLength; i++) {
            ret += topPlayers[i].getLine() + "\n";
        }
        return ret;
    }

    public void loadFile(String filename) {

        File fileHandle = new File(filename);
        try {
            Integer i = 0;
            Scanner sc = new Scanner(fileHandle);
            while(sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.trim().split(":");
                Integer score = Integer.parseInt(parts[0]);
                String name = parts[1];

                topPlayers[i] = new TopScorer(name, score);
                i++;
            } 
            //test for player spot
            topPlayers[i] = new TopScorer("tmp", 0);
            sc.close();
        }
        catch(FileNotFoundException e) {
            System.out.println(e);
        }
    }

    public void saveFile(String filename) throws IOException{

        Path fileName = Path.of(filename);
        String line = "";

        // Assigning the content of the file
        for(Integer i = 0; i < leaderboardLength-1; i++){
            line += topPlayers[i].getScore() + ":" + topPlayers[i].getName() + "\n";
        }
        Files.writeString(fileName, line);
    
    }

    public void sortBoard() {
        
        Integer i;
        Integer j;
        Integer temp;
        Integer len = leaderboardLength;
        //setting places 
        for(i = 0; i < len; i++) {
            topPlayers[i].setPlace(i+1);
        }
        
        //sorting (using bubble sort, probably an easier way to do this but we doing it in 223)
        for (i = 0; i < len; i++)
            for (j = 0; j < len; j++) {
                System.out.println(topPlayers[10].getScore());
                if (topPlayers[j].getScore() < topPlayers[j+1].getScore()){
                    temp = topPlayers[j].getPlace();
                    topPlayers[j].setPlace(topPlayers[j+1].getPlace());
                    topPlayers[j+1].setPlace(temp);
                    
                    TopScorer tmp = new TopScorer();
                    tmp = topPlayers[j];
                    topPlayers[j] = topPlayers[j+1];
                    topPlayers[j+1] = tmp;
                }
            }
                 
    }

    public String doAll(String file) {
        loadFile(file);
        sortBoard();
        return toString();
    }

    public void updateScore(Integer score, Integer place) {
        topPlayers[place].setScore(score);
    }

    public Integer findPlace(String name) {
        for(Integer i = 0; i < leaderboardLength + 1; i++) {
            if(topPlayers[i].getName().equals(name))
                return i;
                //System.out.println(i);
        }
        return -1;
    }

    public String printClose(String name) {
        Integer loc = findPlace(name);
        String close = "";
        if(loc == leaderboardLength) {
            close = "\nCurrent Standing: \n\n";
            close += topPlayers[loc - 1].getLine() + "\n";
            close += topPlayers[loc].getLine() + "\n";
        }
        else if(loc == 0) {
            close = "\nCurrent Standing: \n\n";
            close += topPlayers[loc].getLine() + "\n";
            close += topPlayers[loc + 1].getLine() + "\n";
        }
        else {
            close = "\nCurrent Standing: \n\n";
            close += topPlayers[loc - 1].getLine() + "\n";
            close += topPlayers[loc].getLine() + "\n";
            close += topPlayers[loc + 1].getLine() + "\n";
        }
        return close;
    }


    //made these to make testing easier
    public void addPlayer(String name, Integer score, Integer place) {
        topPlayers[place - 1].setPlace(place);
        topPlayers[place - 1].setName(name);
        topPlayers[place - 1].setScore(score);
    }

    public Integer getPlayerScore(Integer place) {
        return topPlayers[place].getScore();
    }

    public Integer getPlayerPlace(Integer place) {
        return topPlayers[place].getPlace();
    }

    public Integer getLength() {
        return leaderboardLength;
    }

}
