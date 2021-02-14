package ro.sda.example.entity;

import ro.sda.example.entity.enums.MotorcycleShapeEnum;

public class Motorcycle extends Vehicle {

    private int topSpeed;

    private MotorcycleShapeEnum motorcycleShapeEnum;

    public Motorcycle(String brand, String model, int price, int topSpeed, MotorcycleShapeEnum motorcycleShapeEnum) {
        super(brand, model, price);
        this.topSpeed = topSpeed;
        this.motorcycleShapeEnum = motorcycleShapeEnum;
    }

    public Motorcycle() { }

    public int getTopSpeed() {
        return topSpeed;
    }

    public void setTopSpeed(int topSpeed) {
        this.topSpeed = topSpeed;
    }

    public MotorcycleShapeEnum getMotorcycleShapeEnum() {
        return motorcycleShapeEnum;
    }

    public void setMotorcycleShapeEnum(MotorcycleShapeEnum motorcycleShapeEnum) {
        this.motorcycleShapeEnum = motorcycleShapeEnum;
    }

    @Override
    public String toString() {
        return "Motorcycle{" +
                "topSpeed=" + topSpeed +
                ", motorcycleShapeEnum=" + motorcycleShapeEnum +
                "} " + super.toString();
    }
}
