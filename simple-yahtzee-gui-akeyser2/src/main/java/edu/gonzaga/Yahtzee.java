package edu.gonzaga;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

import edu.gonzaga.ScoreCard.ScoreLine;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;


// Java Swing based Yahtzee frontend
// Add/edit/change as you see fit to get it to play Yahtzee!
class Yahtzee {
    // If you have your HW3 code, you could include it like this
    Boolean cont = true;
    Integer[] gameDie = {null,null,null,null,null};
    private Timer timer;
        
    //prints Leaderboard
    HighscoreList leaderboard = new HighscoreList();
    String myFile = System.getProperty("user.dir") + "\\yahtzeeSavedScores.dat";
    // System.out.println(leaderboard.doAll(myFile));
        
    // makePlayers(1);
    // System.out.println("Welcome to Yahtzee! What would you like your name to be?");
    // String playerName = playerNameTextField.getName();
    String playerName = "Player 1";
    Player player1 = new Player(playerName);
    //leaderboard.addPlayer(playerName, 0, leaderboard.getLength() + 1);        
    
    private static Integer rerolls = 2;


    // Main game GUI window and two main panels (left & right)
    JFrame mainWindowFrame;
    JPanel mainGamePanel;
    JPanel startPanel;
    JPanel endPanel;
    JPanel controlPanel;
    JPanel scorecardPanel;

    // Player name - set it to your choice
    JTextField playerNameTextField;

    // Dice view, user input, reroll status, and reroll button
    JPanel diceKeepButtons;
    JPanel scoreButtonPanel;
    JPanel scoreTextboxPanel;
    JButton diceRerollBtn;
    JTextField rollsLeftTextField;

    // Dice button array 
    JButton myDiceButtons[];
    // Scorecard button array
    JButton myScoreButtons[];
    Integer scorelineCounter;
    // Scorecard text array
    JTextArea myScoreTextboxes[];
    // ScoreLine array
    ScoreLine scoreline[];
    // Icon array?
    ImageIcon diceIcons[];
    ImageIcon pressedIcons[];
    
    // Scorecard view and controls
    JTextArea scorecardTextArea;
    JComboBox<String> scorecardLineSelectComboBox;
    JButton scorecardSelectBtn;

    // Game over
    JDialog gameOverScreen;


    public static void main(String [] args) {
        Yahtzee app = new Yahtzee();    // Create, then run GUI
        app.runGUI();        
    }

    // Constructor for the actual Yahtzee object
    public Yahtzee() {
        
    }

    // Sets up the full Swing GUI, but does not do any callback code
    void setupGUI() {
        // Sets text for all 
        UIManager.put("Label.font", new Font("Press Start 2P", Font.BOLD, 12));
        UIManager.put("Button.font", new Font("Press Start 2P", Font.BOLD, 12));
        UIManager.put("TextField.font", new Font("Press Start 2P", Font.BOLD, 12));
        UIManager.put("TextArea.font", new Font("Press Start 2P", Font.BOLD, 12));
        //
        this.mainWindowFrame = new JFrame("Simple GUI Yahtzee");
        this.mainWindowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainWindowFrame.setSize(1100, 600);
        this.mainWindowFrame.setLocation(100,100);

        this.startPanel = new JPanel();
        this.mainGamePanel = new JPanel();        
        this.controlPanel = new JPanel();
        this.scorecardPanel = new JPanel();

        // start screen setup
        this.startPanel = genStartScreen();

        // Control panel setup
        this.controlPanel = genControlPanel();


        // Scorecard panel setup
        this.scorecardPanel = genScorecardPanel();


        // Window add panels and layout
        mainGamePanel.add(BorderLayout.WEST, controlPanel);
        mainGamePanel.add(BorderLayout.EAST, scorecardPanel);
        
        mainWindowFrame.getContentPane().add(mainGamePanel);
    }

    private void genEndScreen(String finalScore){
        JDialog gameOverScreen = new JDialog();
        gameOverScreen.setTitle("Game Over");
        gameOverScreen.setSize(400, 200);

        // makes label for screen
        JLabel finalScoreLabel = new JLabel("Final Score: " + finalScore);

        // exit screen
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            gameOverScreen.dispose();
        }
        });
    
        // hopefully this works
        gameOverScreen.add(finalScoreLabel, BorderLayout.CENTER);
        gameOverScreen.add(closeButton, BorderLayout.SOUTH);
        gameOverScreen.setVisible(true);
    }

    // Create the left panel with the game controls/status
    private JPanel genControlPanel() {
        JPanel newControlPanel = new JPanel();
        newControlPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        // Player name row widgets
        JPanel playerNamePanel = new JPanel();
        JLabel playerNameLabel = new JLabel("Player name:");
        this.playerNameTextField = new JTextField(10);
        playerNamePanel.add(playerNameLabel);
        playerNamePanel.add(playerNameTextField);

        // Die keep user selection
        JPanel diceKeepPanel = new JPanel(null, false);
        JLabel diceKeepInputLabel = new JLabel("Dice keep:");
        diceKeepPanel.add(diceKeepInputLabel);
        this.diceKeepButtons = new JPanel();

        // Rolls left and roll button
        JPanel rollsLeftPanel = new JPanel();
        JLabel rollsLeftLabel = new JLabel("Rolls left:");
        ////////////////////////        Ended up not using
        // String tstFile = System.getProperty("user.dir");
        // tstFile += "\\src\\media\\RollsLeft.png.jpg";
        // try {
        // // load and resize
        // BufferedImage image = ImageIO.read(new File(tstFile));
        // Image scaledTstImage = image.getScaledInstance(400, 100, Image.SCALE_SMOOTH);;
        // ImageIcon tstImage = new ImageIcon(scaledTstImage);
        // rollsLeftLabel.setIcon(tstImage);
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }
        ///////////////////////
        this.rollsLeftTextField = new JTextField(2); // Only 2 wide
        this.rollsLeftTextField.setEditable(false);
        rollsLeftPanel.add(rollsLeftLabel);
        rollsLeftPanel.add(this.rollsLeftTextField);

        this.diceRerollBtn = new JButton("Start");

        // making array of buttons
        String dieFile;
        String pressedFile;
        this.myDiceButtons = new JButton[5];
        for(int i = 0; i < myDiceButtons.length; i++) {
            myDiceButtons[i] = new JButton();
        }
        // makes array of dice icons
        this.diceIcons = new ImageIcon[7];
        this.pressedIcons = new ImageIcon[7];
        for(Integer i=0; i < diceIcons.length; i++) {
            // Get new dice images these are ugly
            dieFile = System.getProperty("user.dir");
            dieFile += "\\simple-yahtzee-gui-akeyser2\\src\\media\\D6-0" + (i);
            pressedFile = dieFile;
            if(i != 0)
                pressedFile += "P.png";
            else  
                pressedFile += ".png";
            dieFile += ".png";
            try {
                // load and resize
                BufferedImage dieImage = ImageIO.read(new File(dieFile));
                BufferedImage pressedImage = ImageIO.read(new File(pressedFile));
                Image scaledDieImage = dieImage.getScaledInstance(125, 125, Image.SCALE_SMOOTH);
                Image scaledPressedImage = pressedImage.getScaledInstance(125, 125, Image.SCALE_SMOOTH);
    
                // add to diceIcon array for later use
                diceIcons[i] = new ImageIcon(scaledDieImage);
                pressedIcons[i] = new ImageIcon(scaledPressedImage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Add all of our control panel widgets to the panel
        newControlPanel.add(playerNamePanel);

        newControlPanel.add(diceKeepPanel);
        newControlPanel.add(this.diceKeepButtons);
    
        for(Integer i = 0; i < myDiceButtons.length; i++)
            this.diceKeepButtons.add(myDiceButtons[i]);

        //rollsLeftPanel.setBackground(Color.decode("#27273a"));
        newControlPanel.add(rollsLeftPanel);
        newControlPanel.add(this.diceRerollBtn);

        // Tell panel to make a grid (like a spreadsheet) layout n rows, 2 columns
        newControlPanel.setLayout(new GridLayout(5, 1));    // Making it pretty
        newControlPanel.setBackground(Color.decode("#27273a"));
        return newControlPanel;
    }

    private JPanel genStartScreen() {
        JPanel newStartScreen = new JPanel();

        JLabel intro = new JLabel("Yahtzee!");
        JButton start = new JButton("Start!");

        newStartScreen.add(start, intro, 0);

        return newStartScreen;
    }

    // Create the right panel with the scorecard and chooser for lines
    private JPanel genScorecardPanel() {
        JPanel newScorecardPanel = new JPanel();
        newScorecardPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        // Tell panel to put widgets in vertically with a BoxLayout
        newScorecardPanel.setLayout(new GridLayout(1,1));

        // A simple label to title the right scorecard area
        Border scorecardTitleBorder = BorderFactory.createTitledBorder("Score Card");

        this.scoreButtonPanel = new JPanel(new GridLayout(20, 2, 5, 5));
        newScorecardPanel.add(this.scoreButtonPanel);

        scoreline = player1.getScorelineArray();
        // Setup the actual scorecard text area to add the scorecard output to
        this.myScoreButtons = new JButton[13];
        for(Integer i=0; i < myScoreButtons.length; i++) {
            myScoreButtons[i] = new JButton(scoreline[i].getLineName());
            myScoreButtons[i].setEnabled(false);
        }

        this.myScoreTextboxes = new JTextArea[20];
        for(Integer i=0; i < myScoreTextboxes.length; i++) {
            myScoreTextboxes[i] = new JTextArea("0");
            myScoreTextboxes[i].setEditable(false);
        }

        // Add the title, text area, and small panel to the right panel
        
        newScorecardPanel.setBorder(scorecardTitleBorder);


        //make this 2 for loops, one for upper section and one for lower. Add some textboxes in between for totals
        //also set textboxes to non editable
        // Full setup of the Scorecard
        for(Integer i=0; i < 6; i++) {
            scoreButtonPanel.add(myScoreButtons[i]);
            scoreButtonPanel.add(myScoreTextboxes[i]);
        }
        scoreButtonPanel.add(new JLabel("Upper Score"));
        scoreButtonPanel.add(myScoreTextboxes[6]);
        scoreButtonPanel.add(new JLabel("Bonus (If Total > 63)"));
        scoreButtonPanel.add(myScoreTextboxes[7]);
        scoreButtonPanel.add(new JLabel("Total of Upper"));
        scoreButtonPanel.add(myScoreTextboxes[8]);
        for(Integer i=6; i < myScoreButtons.length; i++) {
            scoreButtonPanel.add(myScoreButtons[i]);
            scoreButtonPanel.add(myScoreTextboxes[i+3]);
        }
        scoreButtonPanel.add(new JLabel("Yahtzee Bonus"));
        scoreButtonPanel.add(myScoreTextboxes[16]);
        scoreButtonPanel.add(new JLabel("Upper Total"));
        scoreButtonPanel.add(myScoreTextboxes[17]);
        scoreButtonPanel.add(new JLabel("Lower Total"));
        scoreButtonPanel.add(myScoreTextboxes[18]);    
        scoreButtonPanel.add(new JLabel("Grand Total"));
        scoreButtonPanel.add(myScoreTextboxes[19]);          


        return newScorecardPanel;
    }

    /*
     *  This is a method to show you how you can set/read the visible values
     *   in the various text widgets.
     */
    private void putDemoDefaultValuesInGUI() {
        // Example setting of player name
        this.playerNameTextField.setText("Player One");

        // Example of player entered dice keep string
        // this.diceKeepButtons

        // Example of setting the rerolls left
        this.rollsLeftTextField.setText("3");

        for(int i = 0; i < myDiceButtons.length; i++) {
            // using icon array to set dice (replace with icon 0 when I add it later)
            myDiceButtons[i].setIcon(diceIcons[0]);
            //myDiceButtons[i].setBorderPainted(false);
            myDiceButtons[i].setFocusPainted(false);
            myDiceButtons[i].setContentAreaFilled(false);
            myDiceButtons[i].setPreferredSize(new Dimension(100, 100));
        }

        // The scorecard can be "printed" to the text area widget
        //this.scorecardTextArea.setText("Scorecard text goes here");

    }

    /*
     * This is a demo of how to add callbacks to the buttons
     *  These callbacks can access the class member variables this way
     */
    private void addButtonCallbackHandlers() {
        // reroll array that will pass into reroll func
        char[] reroll = {'n','n','n','n','n'};
        //changes name
        this.playerNameTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               player1.changeName(playerNameTextField.getText());
               System.out.println(player1.getName());
            }
        });

        // REROLL BUTTON - does a lot, changes button text and dice values, continues game, its a good button
        // theres some weird stuff here with the rolls left text as to why the val is -1 but you can fix that later
        this.diceRerollBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(rerolls == -1) {
                    // takes away ability to roll or change die
                    for(Integer i = 0; i < myDiceButtons.length; i++) {
                        if(myDiceButtons[i].getBackground() == Color.GRAY)
                            myDiceButtons[i].doClick();
                        myDiceButtons[i].setEnabled(false);
                    }
                        
                    // gives ability to select a scoreline
                    for(Integer i=0; i < myScoreButtons.length; i++) {
                        if(!scoreline[i].getFilled())
                            myScoreButtons[i].setEnabled(true);
                    }
                    diceRerollBtn.setEnabled(false);
                    rerolls = 2;
                    diceRerollBtn.setText("Roll");

                    // Integer[] misc;
                    player1.setPosVals();
                    // misc = player1.getMiscValuesArray();

                    // imports scorecard values and sets the values to the correct sections
                    setScoreCard("Pos");
                }
                else{
                    diceRerollBtn.setText("Reroll");
                    System.out.println("They clicked the reroll button!");
                    String rerollString = new String(reroll);
                    System.out.println(rerollString);
                    rollsLeftTextField.setText(rerolls.toString());
                    player1.playerRoll(rerollString);

                    //I am going to be honest, I kinda have no idea how this works,
                    //I get the gist but it took a lot of trial and error to finally get it working
                    if (timer == null || !timer.isRunning()) {
                        timer = new Timer(100, new ActionListener() {
                            int counter = 0;
                            public void actionPerformed(ActionEvent e) {
                                for(int i = 0; i < myDiceButtons.length; i++){
                                    int randomIndex = (int) (Math.random() * 6);
                                    if(myDiceButtons[i].getBackground() != Color.GRAY){
                                        myDiceButtons[i].setIcon(diceIcons[randomIndex+1]);
                                    }
                                }
                                counter++;
                                if (counter == 10) {
                                    timer.stop();
                                    for(Integer i = 0; i < gameDie.length; i++) {
                                        gameDie[i] = player1.getHand()[i];
                                        System.out.println(gameDie[i]);
                                        //myDiceButtons[i].setText(gameDie[i].toString());
                                        if(myDiceButtons[i].getBackground() == Color.GRAY)
                                            myDiceButtons[i].setIcon(pressedIcons[gameDie[i]]);
                                        else
                                            myDiceButtons[i].setIcon(diceIcons[gameDie[i]]);
                                    }
                                }
                            }
                        });
                    }

                    timer.start();
                    rerolls--;
                    if(rerolls == -1)
                        diceRerollBtn.setText("Continue");
                    
                }
            }
            
        });

        // sets up all the dice buttons
        for(Integer i  = 0; i < myDiceButtons.length; i++) {
            final Integer insideI = i;
            this.myDiceButtons[insideI].addActionListener(new ActionListener() {
                Boolean pressed = false;
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(pressed == false) {
                        System.out.println("Dice " + insideI + " wants to be rerolled");
                        myDiceButtons[insideI].setBackground(Color.GRAY);
                        myDiceButtons[insideI].setIcon(pressedIcons[gameDie[insideI]]);
                        pressed = true;
                        reroll[insideI] = 'y';
                    }
                    else if(pressed == true) {
                        System.out.println("Dice " + insideI + " does not want to be rerolled");
                        myDiceButtons[insideI].setBackground(null);
                        myDiceButtons[insideI].setIcon(diceIcons[gameDie[insideI]]);
                        pressed = false;
                        reroll[insideI] = 'n';
                    }
                }
            });
        }

        // sets up all the scoreline buttons to have actions!
        for(Integer i  = 0; i < myScoreButtons.length; i++) {
            final Integer insideI = i;
            this.myScoreButtons[insideI].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    player1.chooseLine(scoreline[insideI].getLineCode());
                    setScoreCard("Cur");
                    myScoreButtons[insideI].setBackground(Color.GRAY);
                    for(Integer i=0; i < myScoreButtons.length; i++) 
                        myScoreButtons[i].setEnabled(false);
                    for(Integer i = 0; i < myDiceButtons.length; i++) {
                        myDiceButtons[i].setEnabled(true);
                        // Have a default blank die
                        myDiceButtons[i].setIcon(diceIcons[0]);
                    }
                    diceRerollBtn.setEnabled(true);
                    if(player1.getCard().checkCont() == false) {
                        System.out.println("Put end screen here");
                        genEndScreen(myScoreTextboxes[19].getText());
                    }

                }
            });
        }
        
    }

    /*
     *  Builds the GUI frontend and allows you to hook up the callbacks/data for game
     */
    void runGUI() {
        System.out.println("Starting GUI app");
        setupGUI();

        // These methods are to show you how it works
        // Once you get started working, you can comment them out so they don't
        //  mess up your own code.
        putDemoDefaultValuesInGUI();
        addButtonCallbackHandlers();

        // Right here is where you could methods to add your own callbacks
        // so that you can make the game actually work.

        // Run the main window - begins GUI activity
        mainWindowFrame.setVisible(true);
        // Make start menu just a title and a start button.
        //mainWindowFrame.getContentPane().add(startPanel);
        System.out.println("Done in GUI app");
    }

    void setScoreCard(String type) { 
        Integer[] misc;
        misc = player1.getMiscValuesArray();

        // imports scorecard values and sets the values to the correct sections
        for(Integer i=0; i < 6; i++) {
            if(type == "Pos")
                myScoreTextboxes[i].setText(scoreline[i].getPosVal().toString());
            else if(type == "Cur")
                myScoreTextboxes[i].setText(scoreline[i].getCurVal().toString());
        }
        myScoreTextboxes[6].setText(misc[0].toString());
        myScoreTextboxes[7].setText(misc[1].toString());
        myScoreTextboxes[8].setText(misc[2].toString());
        for(Integer i=6; i < myScoreButtons.length; i++) {
            if(type == "Pos")
                myScoreTextboxes[i+3].setText(scoreline[i].getPosVal().toString());
            else if(type == "Cur")
                myScoreTextboxes[i+3].setText(scoreline[i].getCurVal().toString());
        }
        myScoreTextboxes[16].setText(misc[3].toString());
        myScoreTextboxes[17].setText(misc[4].toString());
        myScoreTextboxes[18].setText(misc[5].toString());
        myScoreTextboxes[19].setText(misc[6].toString());
    }

}
