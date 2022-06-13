import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import java.util.Random;

/**
 * Write a description of class Gate here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Gate extends Actor
{
    private static final Random RANDOM = new Random();
    public enum CalculationType {
        SUM,
        DIFFERENCE,
        PRODUCT,
        QUOTIENT,
        MODULO,

        EQUALS,
    }

    public static <T extends Enum<?>> T randomEnum(Class<T> clazz){
        int x = RANDOM.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }

    public CalculationType choice;

    public int sumChoice;
    public int differenceChoice;

    public int productChoice;
    public int quotientChoice;
    public int moduloChoice;

    public int equalsChoice;

    public Gate(String pictureName) {
        GreenfootImage image = new GreenfootImage(pictureName);
        image.scale(image.getWidth() /2, image.getHeight() /2);
        setImage(image);

        choice = randomEnum(CalculationType.class);

        sumChoice = RANDOM.nextInt(70);
        differenceChoice = RANDOM.nextInt(20);

        productChoice = RANDOM.nextInt(10) + 1;
        quotientChoice = RANDOM.nextInt(10) + 1;

        moduloChoice = RANDOM.nextInt(10) + 1;

        equalsChoice = RANDOM.nextInt(50) + 1;
    }

    /**
     * Act - do whatever the Gate wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        // Add your action code here.
    }

    public boolean touchesPlayer(Class<Player> playerClass) {
        return isTouching(playerClass);
    }

    public int calculateNewScore(int score) {
        switch (choice) {
            case SUM:
                return score + sumChoice;

            case DIFFERENCE:
                return score - differenceChoice;

            case PRODUCT:
                return score * productChoice;

            case QUOTIENT:
                return score / quotientChoice;

            case MODULO:
                return score % moduloChoice;

            case EQUALS:
                return equalsChoice;

            default:
                return score;
        }
    }

    public String getCalculation() {
        switch (choice) {
            case SUM:
                return "x + " + sumChoice;

            case DIFFERENCE:
                return "x - " + differenceChoice;

            case PRODUCT:
                return "x * " + productChoice;

            case QUOTIENT:
                return "x / " + quotientChoice;

            case MODULO:
                return "x % " + moduloChoice;

            case EQUALS:
                return "x = " + equalsChoice;

            default:
                return "x";
        }
    }
}
