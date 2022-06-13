import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Pillar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Pillar extends Actor
{
    public Pillar() {
        GreenfootImage image = new GreenfootImage("pillar.png");
        image.scale(image.getWidth() /2, image.getHeight() /2);
        setImage(image);
    }

    /**
     * Act - do whatever the Pillar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }

    public boolean touchesPlayer(Class<Player> playerClass) {
        return isTouching(playerClass);
    }
}
