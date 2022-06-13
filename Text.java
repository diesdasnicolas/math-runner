import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Text here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Text extends Actor
{
    // Moving means it does not stick to its position and stays on the screen
    private boolean moving = false;

    private final int SPEED = Player.SPEED;
    private int speed = SPEED;
    private int playedFrames = 0;

    public Text(String text, boolean moving) {
        GreenfootImage textImage = new GreenfootImage(text, 24, new Color(255, 255, 255), new Color(0, 0, 0, 255));
        GreenfootImage image = new GreenfootImage(textImage.getWidth() + 12, 36);

        image.fill();
        image.fillRect(3, 3, image.getWidth() - 6, 30);
        image.drawImage(textImage, 6, 6);

        setImage(image);

        this.moving = moving;
    }

    public void update(String text) {
        GreenfootImage textImage = new GreenfootImage(text, 24, new Color(255, 255, 255), new Color(0, 0, 0, 255));
        GreenfootImage image = new GreenfootImage(textImage.getWidth() + 12, 36);

        image.fill();
        image.fillRect(3, 3, image.getWidth() - 6, 30);
        image.drawImage(textImage, 6, 6);

        setImage(image);
    }

    /**
     * Act - do whatever the Text wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        if (moving) {
            playedFrames++;

            // Every second, change the speed of the player
            if (playedFrames % 60 == 0) {
                speed = SPEED + (int) (playedFrames /600);
            }

            move(speed);
        }
    }
}
