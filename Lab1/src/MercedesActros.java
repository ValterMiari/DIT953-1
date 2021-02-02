import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;

public class MercedesActros implements ITruck{

    private double xCord;
    private double yCord;
    private Direction dir;
    private Color color;
    private final String name = "Volvo240";
    private double currentSpeed = 0;
    private final double enginePower = 100;
    private final int nrDoors = 4;
    private int capacity;
    private final Deque<ICar> load;

    private boolean rampOpen;

    public MercedesActros(Color c, Point point, Direction dir, int capacity){
        this.xCord = point.getX();
        this.yCord = point.getY();
        this.color = c;
        this.dir = dir;
        this.capacity = capacity;
        this.load = new ArrayDeque<>(capacity);
        this.rampOpen = false;
        stopEngine();
    }


    // Specific to MercedesActros
    public void rampDown(){
        if (currentSpeed != 0)
            System.out.println("Car must not move!");
        else
            rampOpen = true;
    }

    public void loadTransport(ICar car){
        if (!rampOpen)
            System.out.println("Ramp must be open!");
        else{
            if (yCord - car.getXCord() > 1 | load.size() - 1 >= capacity)
                System.out.println("Must move car closer");
            else
                load.add(car);
        }
    }

    //public void dumpLoad();


    // From ITruck
    @Override
    public int getNrDoors(){ return this.nrDoors; }

    @Override
    public double getEnginePower(){ return enginePower; }

    @Override
    public void startEngine(){ currentSpeed = 1; }

    @Override
    public void stopEngine(){ currentSpeed = 0; }


    // From IVehicle
    @Override
    public String getName(){ return this.name; }

    @Override
    public String getColor() {return color.toString(); }

    @Override
    public Direction getDir() { return this.dir; }

    @Override
    public void move () {
        if (rampOpen) {
            System.out.println("Kan inte köra med flaket öppet");
        }
        else {
            switch (dir) {
                case EAST -> this.xCord += getSpeed();
                case WEST -> this.xCord -= getSpeed();
                case NORTH -> this.yCord += getSpeed();
                case SOUTH -> this.yCord -= getSpeed();
            }
        }
    }

    @Override
    public void turnRight() {
        if (rampOpen) {
            System.out.println("Kan inte köra med flaket öppet");
        }
        else {
            switch (dir) {
                case EAST -> this.dir = Direction.SOUTH;
                case WEST -> this.dir = Direction.NORTH;
                case NORTH -> this.dir = Direction.EAST;
                case SOUTH -> this.dir = Direction.WEST;
            }
        }
    }

    @Override
    public void turnLeft() {
        if (rampOpen) {
            System.out.println("Kan inte köra med flaket öppet");
        }
        else {
            switch (dir) {
                case EAST -> this.dir = Direction.NORTH;
                case WEST -> this.dir = Direction.SOUTH;
                case NORTH -> this.dir = Direction.WEST;
                case SOUTH -> this.dir = Direction.EAST;
            }
        }
    }

    @Override
    public double getXCord() { return this.xCord; }

    @Override
    public double getYCord() {
        return this.yCord;
    }

    @Override
    public void setSpeed(double speed){ currentSpeed = Math.min(speed, this.enginePower); }

    @Override
    public double getSpeed(){ return currentSpeed; }

    @Override
    public double speedFactor() {
        return getEnginePower() * 0.01;
    }

    @Override
    public void gas(double amount) {
        double gasFactor = Math.max(Math.min(amount, 1), 0);
        incrementSpeed(gasFactor);
    }

    @Override
    public void incrementSpeed(double amount){
        setSpeed(Math.min(getSpeed() + speedFactor() * amount, getEnginePower()));
    }

    @Override
    public void brake(double amount) {
        double brakeFactor = Math.max(Math.min(amount, 1), 0); // 0 <= breakFactor <= 1
        decrementSpeed(brakeFactor);
    }

    @Override
    public void decrementSpeed(double amount){
        setSpeed(Math.max(getSpeed() - speedFactor() * amount,0));
    }

}