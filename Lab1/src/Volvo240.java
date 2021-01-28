import java.awt.*;

/**
 * A class for the cars of model Volvo240. Volvo240 is a subclass of Car, it inherits the methods and
 * variables of the abstract class Car. Volvo240 also implements the interface Movable, which describes the desired
 * methods for which the car should implement, to be able to move.
 * @author Valter Miari, Oskar Sturebrand, Clara Josefsson
 */

public class Volvo240 extends Car implements Movable {

    public final static double trimFactor = 1.25;
    private double xCord;
    private double yCord;
    private Direction dir;

    /**
     * The constructor that is called upon on creation of a Volvo240. It inherits the variables; nrDoors, enginePower,
     * currentSpeed, color and modelName; from its super class Car. With exception of color, all these variables and the
     * variable dir, have default values for a Volvo240, and are assigned a final default value upon
     * creation.
     * @param c The color of the car.
     * @param point The starting position of the car. An x- and a y-coordinate.
     */
    public Volvo240(Color c, Point point){
        super(4, 100, 0, c, "Volvo240");
        this.xCord = point.getX();
        this.yCord = point.getY();
        dir = Direction.NORTH;
        stopEngine();
    }

    /**
     *
     * @return returns the x-coordinate of the Volvo.
     */
    public double getXCord() {
        return this.xCord;
    }

    /**
     *
     * @return returns the y-coordinate of the Volvo.
     */
    public double getYCord() {
        return this.yCord;
    }


    /**
     * A method that moves the Volvo, with its current speed, in the direction that it's pointing at.
     */
    @Override
    public void move() {
        switch (dir) {
            case EAST -> this.xCord += getCurrentSpeed();
            case WEST -> this.xCord -= getCurrentSpeed();
            case NORTH -> this.yCord += getCurrentSpeed();
            case SOUTH -> this.yCord -= getCurrentSpeed();
        }
    }


    /**
     * Turns the car left, by changing the latitude or the longitude, depending on where the car is pointing.
     */
    @Override
    public void turnLeft() {
        switch (dir) {
            case EAST -> this.dir = Direction.NORTH;
            case WEST -> this.dir = Direction.SOUTH;
            case NORTH -> this.dir = Direction.EAST;
            case SOUTH -> this.dir = Direction.WEST;
        }
    }


    /**
     * Turns the car right, by changing the latitude or the longitude, depending on where the car is pointing.
     */
    @Override
    public void turnRight() {
        switch (dir) {
            case EAST -> this.dir = Direction.SOUTH;
            case WEST -> this.dir = Direction.NORTH;
            case NORTH -> this.dir = Direction.EAST;
            case SOUTH -> this.dir = Direction.WEST;
        }
    }


    /**
     * Increases the speed of the Saab by the input amount times the speed factor, and makes sure the
     * speed doesn't exceed the engine power of the car.
     * @param amount the amount at which the speed should increase by.
     */
    @Override
    public void incrementSpeed(double amount){
        setCurrentSpeed(Math.min(getCurrentSpeed() + speedFactor() * amount, getEnginePower()));
    }

    /**
     * Increases the speed of the Saab by the input amount times the speed factor, and makes sure the
     * speed doesn't go below 0.
     * @param amount amount the amount at which the speed should decrease by.
     */
    @Override
    public void decrementSpeed(double amount){
        setCurrentSpeed(Math.max(getCurrentSpeed() - speedFactor() * amount,0));
    }


    /**
     * Makes the car go faster by increasing the speed via the incrementSpeed method.
     * @param amount amount the amount at which the speed should increase by.
     */
    @Override
    public void gas(double amount) {
        double gasFactor = Math.max(Math.min(amount, 1), 0);
        incrementSpeed(gasFactor);
    }

    /**
     * Makes the car go slower by decreasing the speed via the decrementSpeed method.
     * @param amount amount amount the amount at which the speed should decrease by.
     */
    @Override
    public void brake(double amount) {
            double brakeFactor = Math.max(Math.min(amount, 1), 0);
            decrementSpeed(brakeFactor);
    }

    /**
     * The speed factor of the car, which depends on the turbo of the car and the engine power.
     * @return returns the current speed factor.
     */
    @Override
    public double speedFactor(){
        return getEnginePower() * 0.01 * trimFactor;
    }


}
