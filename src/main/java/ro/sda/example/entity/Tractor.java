package ro.sda.example.entity;

public class Tractor extends Vehicle {

    private int maxPullWeight;

    public Tractor(String brand, String model, int price, int maxPullWeight) {
        super(brand, model, price);
        this.maxPullWeight = maxPullWeight;
    }

    public Tractor() { }

    public int getMaxPullWeight() {
        return maxPullWeight;
    }

    public void setMaxPullWeight(int maxPullWeight) {
        this.maxPullWeight = maxPullWeight;
    }

    @Override
    public String toString() {
        return "Tractor{" +
                "maxPullWeight=" + maxPullWeight +
                "} " + super.toString();
    }
}
