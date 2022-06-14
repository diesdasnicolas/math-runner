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

        LOG,
        FAC,
        NCR,
    }

    public static CalculationType randomCalcType(){
        double x = RANDOM.nextInt(82) + 1;

        if (x <= 25) {
            return CalculationType.SUM;
        }

        else if (x <= 45) {
            return CalculationType.DIFFERENCE;
        }

        else if (x <= 55) {
            return CalculationType.PRODUCT;
        }

        else if (x <= 65) {
            return CalculationType.QUOTIENT;
        }

        else if (x <= 70) {
            return CalculationType.MODULO;
        }

        else if (x <= 75) {
            return CalculationType.EQUALS;
        }

        else if (x <= 78f) {
            return CalculationType.LOG;
        }

        else if (x <= 81) {
            return CalculationType.FAC;
        }

        else {
            return CalculationType.NCR;
        }
    }

    private int factorial(int n) {
        int factorial = 1;

        for (int i = 2; i <= n; i++)
            factorial += i;

        return factorial;
    }

    private int nCr(int n, int r) {
        return factorial(n) / (factorial(r) * factorial(n - r));
    }

    public CalculationType choice;

    public int sumChoice;
    public int differenceChoice;

    public int productChoice;
    public int quotientChoice;
    public int moduloChoice;

    public int equalsChoice;

    public int cChoice;

    public Gate(String pictureName) {
        GreenfootImage image = new GreenfootImage(pictureName);
        image.scale(image.getWidth() /2, image.getHeight() /2);
        setImage(image);

        choice = randomCalcType();

        sumChoice = RANDOM.nextInt(70);
        differenceChoice = RANDOM.nextInt(20);

        productChoice = RANDOM.nextInt(10) + 1;
        quotientChoice = RANDOM.nextInt(10) + 1;

        moduloChoice = RANDOM.nextInt(10) + 1;

        equalsChoice = RANDOM.nextInt(50) + 1;

        cChoice = RANDOM.nextInt(6) + 1;
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

            case LOG:
                return (int) Math.log(score);

            case FAC:
                for (int i = 1; i < score; i++) {
                    score *= i;
                }

                return score;

            case NCR:


                return nCr(score, cChoice);

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

            case LOG:
                return "log(x)";

            case FAC:
                return "x!";

            case NCR:
                return "nCr; n = x; r = " + cChoice;

            default:
                return "x";
        }
    }
}
