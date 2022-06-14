import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import java.io.IOException;
import java.util.regex.Pattern;

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Actor
{
    public static final int SPEED = 4;
    private int speed = SPEED;
    private int atime = 1;
    private Scroller scroller;
    private MyWorld world;
    private GreenfootImage playerImage;
    private int playedFrames = 0;

    private enum MoveDirection {
        STAND,
        UP,
        DOWN,
        LEFT,
        RIGHT,
        UP_LEFT,
        UP_RIGHT,
        DOWN_LEFT,
        DOWN_RIGHT,
    }

    public Player(Scroller newScroller, MyWorld world) {
        playerImage = new GreenfootImage("player_standing_right.png");
        playerImage.scale(playerImage.getWidth() /6, playerImage.getHeight() /6);

        setImage(playerImage);

        scroller = newScroller;
        this.world = world;
    }

    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        playedFrames++;

        // Every second, change the speed of the player
        if (playedFrames % 60 == 0) {
            speed = SPEED + (int) (playedFrames /600);
        }

        if (Greenfoot.isKeyDown("w") && Greenfoot.isKeyDown("s")) {
            movePlayer(MoveDirection.RIGHT);
        }

        else if (Greenfoot.isKeyDown("w")) {
            movePlayer(MoveDirection.UP_RIGHT);
        }

        else if (Greenfoot.isKeyDown("s")) {
            movePlayer(MoveDirection.DOWN_RIGHT);
        }

        else {
            movePlayer(MoveDirection.RIGHT);
        }

        world.run();

    }

    private void movePlayer(MoveDirection direction) {
        switch (direction) {
            case STAND:
                playerImage = new GreenfootImage("player_standing.png");
                playerImage.scale(playerImage.getWidth() /4, playerImage.getHeight() /4);

                atime++;
                if (atime == 28) {
                    atime = 0;

                    setImage(playerImage);
                }

                else if (atime == 14)
                    setImage(playerImage);
                break;

            case UP:
                setRotation(-90);
                scroller.scroll(0, -speed);
                move(speed);
                setRotation(0);

                handleAnimation("player_standing_up.png", "player_move_up.png",
                        "player_move_up_2.png");
                break;

            case DOWN:
                setRotation(90);
                scroller.scroll(0, speed);
                move(speed);
                setRotation(0);

                handleAnimation("player_standing.png", "player_move_down.png",
                        "player_move_down_2.png");
                break;

            case LEFT:
                setRotation(180);
                scroller.scroll(-speed, 0);
                move(speed);
                setRotation(0);

                handleAnimation("player_standing_left.png", "player_move_left.png",
                        "player_move_left_2.png");
                break;

            case RIGHT:
                scroller.scroll(speed, -speed);

                move(speed);
                handleAnimation("player_standing_right.png", "player_move_right.png",
                        "player_move_right_2.png");
                break;

            case UP_LEFT:
                scroller.scroll(-speed, -speed);
                setRotation(-90);
                move(speed);
                setRotation(180);
                move(speed);
                setRotation(0);

                handleAnimation("player_standing_up.png", "player_move_up.png",
                        "player_move_up_2.png");
                break;

            case UP_RIGHT:
                scroller.scroll(speed, -speed);
                setRotation(-90);
                move(speed);
                setRotation(0);
                move(speed);

                handleAnimation("player_standing_right.png", "player_move_right.png",
                        "player_move_right_2.png");
                break;

            case DOWN_LEFT:
                scroller.scroll(-speed, speed);
                setRotation(90);
                move(speed);
                setRotation(180);
                move(speed);
                setRotation(0);

                handleAnimation("player_standing.png", "player_move_down.png",
                        "player_move_down_2.png");
                break;

            case DOWN_RIGHT:
                scroller.scroll(speed, speed);
                setRotation(90);
                move(speed);
                setRotation(0);
                move(speed);

                handleAnimation("player_standing_right.png", "player_move_right.png",
                        "player_move_right_2.png");
                break;
        }
    }

    private void handleAnimation(String standingImageName, String firstMovingImageName, String secondMovingImageName) {
        atime++;
        if (atime == 28) {
            atime = 0;
        }

        if (world.score >= 1_000) {
            String[] parts = standingImageName.split(Pattern.quote("."));
            standingImageName = parts[0] + "_1000." + parts[1];

            parts = firstMovingImageName.split(Pattern.quote("."));
            firstMovingImageName = parts[0] + "_1000." + parts[1];

            parts = secondMovingImageName.split(Pattern.quote("."));
            secondMovingImageName = parts[0] + "_1000." + parts[1];
        }

        else if (world.score >= 500) {
            String[] parts = standingImageName.split(Pattern.quote("."));
            standingImageName = parts[0] + "_500." + parts[1];

            parts = firstMovingImageName.split(Pattern.quote("."));
            firstMovingImageName = parts[0] + "_500." + parts[1];

            parts = secondMovingImageName.split(Pattern.quote("."));
            secondMovingImageName = parts[0] + "_500." + parts[1];
        }

        else if (world.score >= 100) {
            String[] parts = standingImageName.split(Pattern.quote("."));
            standingImageName = parts[0] + "_100." + parts[1];

            parts = firstMovingImageName.split(Pattern.quote("."));
            firstMovingImageName = parts[0] + "_100." + parts[1];

            parts = secondMovingImageName.split(Pattern.quote("."));
            secondMovingImageName = parts[0] + "_100." + parts[1];
        }

        else if (world.score >= 50) {
            String[] parts = standingImageName.split(Pattern.quote("."));
            standingImageName = parts[0] + "_50." + parts[1];

            parts = firstMovingImageName.split(Pattern.quote("."));
            firstMovingImageName = parts[0] + "_50." + parts[1];

            parts = secondMovingImageName.split(Pattern.quote("."));
            secondMovingImageName = parts[0] + "_50." + parts[1];
        }

        else if (world.score >= 10) {
            String[] parts = standingImageName.split(Pattern.quote("."));
            standingImageName = parts[0] + "_10." + parts[1];

            parts = firstMovingImageName.split(Pattern.quote("."));
            firstMovingImageName = parts[0] + "_10." + parts[1];

            parts = secondMovingImageName.split(Pattern.quote("."));
            secondMovingImageName = parts[0] + "_10." + parts[1];
        }

        switch (atime) {
            case 0:
                playerImage = new GreenfootImage(firstMovingImageName);
                playerImage.scale(playerImage.getWidth() /6, playerImage.getHeight() /6);

                setImage(playerImage);
                break;

            case 7:

            case 21:
                playerImage = new GreenfootImage(standingImageName);
                playerImage.scale(playerImage.getWidth() /6, playerImage.getHeight() /6);

                setImage(playerImage);
                break;

            case 14:
                playerImage = new GreenfootImage(secondMovingImageName);
                playerImage.scale(playerImage.getWidth() /6, playerImage.getHeight() /6);

                setImage(playerImage);
                break;

        }
    }
}
