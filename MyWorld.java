import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import java.io.*;
import java.util.LinkedList;

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    public static final String HIGHSCORE_FILE = "highscore.dat";

    private Scroller scroller;
    public Player player;

    public LinkedList<Player> players = new LinkedList<Player>();
    public LinkedList<Gate> gates = new LinkedList<Gate>();
    public LinkedList<Pillar> pillars = new LinkedList<Pillar>();
    public LinkedList<Text> texts = new LinkedList<Text>();

    public int score = 1;
    public int highScore = readHighScore();
    public Text scoreText;
    public Text highScoreText;
    private boolean created = false;

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld() {
        // Create a new world with 1_000x500 cells with a cell size of 1x1 pixels.
        super(1_000, 500, 1, false);

        GreenfootImage backgroundImage = new GreenfootImage("background.png");
        backgroundImage.scale(backgroundImage.getWidth() / 2, backgroundImage.getHeight() / 2);
        scroller = new Scroller(this, backgroundImage, 1_000, 500);

        createGates(1_300);

        scoreText = new Text("Score: " + score, true);
        addObject(scoreText, 50, 15);
        highScoreText = new Text("Best: " + highScore, true);
        addObject(highScoreText, 50, 40);

        player = new Player(scroller, this);
        addObject(player, 250, 250);
    }

    public void run() {
        for (int i = 0; i < gates.toArray().length; i++) {
            Gate gate = gates.get(i);

            if (gate.touchesPlayer(Player.class) && !created) {
                // Create new gate, pillar, gate on getX + 750
                createGates(gate.getX() + 750);

                created = true;

                // Recalculate score
                score = gate.calculateNewScore(score);
                scoreText.update("Score: " + score);

                if (score > highScore) {
                    highScore = score;
                    highScoreText.update("Best: " + highScore);

                    writeHighScore(highScore);
                }

                if (score <= 0) {
                    // Stop the game, since it is over
                    GameOverScreen gameOver = new GameOverScreen();
                    addObject(gameOver, 500, 250);

                    Greenfoot.stop();
                }
            }

            else if (gate.getX() <= -50) {
                // Remove gate
                gates.remove(gate);
                created = false;
            }
        }

        for (int i = 0; i < pillars.toArray().length; i++) {
            Pillar pillar = pillars.get(i);

            if (pillar.getX() <= -50) {
                // Remove pillar
                pillars.remove(pillar);
            }

            else if (pillar.touchesPlayer(player)) {
                // Stop the game, since it is over
                GameOverScreen gameOver = new GameOverScreen();
                addObject(gameOver, 500, 250);

                Greenfoot.stop();
            }

        }

        for (int j = 0; j < texts.toArray().length; j++) {
            Text text = texts.get(j);

            if (text.getX() <= -50) {
                // Remove text
                texts.remove(text);
            }
        }
    }

    public void createGates(int x) {
        Gate gate1 = new Gate("gate1.png");
        addObject(gate1, x, 113);
        gates.push(gate1);

        Gate gate2 = new Gate("gate2.png");
        addObject(gate2, x, 387);
        gates.push(gate2);

        Pillar pillar = new Pillar();
        addObject(pillar, x, 253);
        pillars.push(pillar);

        Text text1 = new Text(gate1.getCalculation(), false);
        addObject(text1, x, gate1.getY());

        Text text2 = new Text(gate2.getCalculation(), false);
        addObject(text2, x, gate2.getY());
    }

    public static int readHighScore() {
        try {
            FileReader reader = new FileReader(HIGHSCORE_FILE);

            int output = (int) reader.read();
            reader.close();

            if (output == -1) {
                writeHighScore(1);
                return 1;
            }

            return output;
        }

        catch (IOException e) {
            writeHighScore(1);
            return 1;
        }
    }

    public static void writeHighScore(int score) {
        try {
            FileWriter writer = new FileWriter(HIGHSCORE_FILE);
            writer.write(score);
            writer.close();
        }

        catch (IOException e) {  }
    }

    // Stage 1

    // TODO -> Add highscore

    // Stage 2


}
