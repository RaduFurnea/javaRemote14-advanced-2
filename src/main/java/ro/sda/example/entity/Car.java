package ro.sda.example.entity;

import ro.sda.example.entity.enums.CarShapeEnum;
import ro.sda.example.entity.enums.TransmissionTypeEnum;

public class Car extends Vehicle {

    private int topSpeed;

    private TransmissionTypeEnum transmissionTypeEnum;

    private CarShapeEnum carShapeEnum;

    public Car(String brand, String model, int price, int topSpeed, TransmissionTypeEnum transmissionTypeEnum, CarShapeEnum carShapeEnum) {
        super(brand, model, price);
        this.topSpeed = topSpeed;
        this.transmissionTypeEnum = transmissionTypeEnum;
        this.carShapeEnum = carShapeEnum;
    }

    public Car() { }

    public int getTopSpeed() {
        return topSpeed;
    }

    public void setTopSpeed(int topSpeed) {
        this.topSpeed = topSpeed;
    }

    public TransmissionTypeEnum getTransmissionTypeEnum() {
        return transmissionTypeEnum;
    }

    public void setTransmissionTypeEnum(TransmissionTypeEnum transmissionTypeEnum) {
        this.transmissionTypeEnum = transmissionTypeEnum;
    }

    public CarShapeEnum getCarShapeEnum() {
        return carShapeEnum;
    }

    public void setCarShapeEnum(CarShapeEnum carShapeEnum) {
        this.carShapeEnum = carShapeEnum;
    }

    @Override
    public String toString() {
        return "Car{" +
                "topSpeed=" + topSpeed +
                ", transmissionTypeEnum=" + transmissionTypeEnum +
                ", carShapeEnum=" + carShapeEnum +
                "} " + super.toString();
    }
}
