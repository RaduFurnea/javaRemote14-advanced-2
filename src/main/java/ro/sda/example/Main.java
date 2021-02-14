package ro.sda.example;

import ro.sda.example.entity.Car;
import ro.sda.example.entity.Motorcycle;
import ro.sda.example.entity.Tractor;
import ro.sda.example.entity.Vehicle;
import ro.sda.example.entity.enums.CarShapeEnum;
import ro.sda.example.entity.enums.MotorcycleShapeEnum;
import ro.sda.example.entity.enums.TransmissionTypeEnum;

import java.io.*;
import java.util.*;

public class Main {

    private final static String BASE_FILE_PATH = "C:\\radu\\";
    private final static String SOURCE_FILE = "vehicles.txt";
    private final static String CARS_TARGET_FILE = "cars.txt";
    private final static String MOTORCYCLE_TARGET_FILE = "motorcycles.txt";
    private final static String TRACTOR_TARGET_FILE = "tractors.txt";

    private static List<Vehicle> vehicles = new ArrayList<>();

    private static List<Car> cars = new ArrayList<>();
    private static List<Tractor> tractors = new ArrayList<>();
    private static List<Motorcycle> motorcycles = new ArrayList<>();

    public static void main(String[] args) {
        readFromFileAndConvert();

        System.out.println("---------------- Vehicles sorted in their arrays --------------");
        sortVehicles();

        System.out.println("---------------- Cars sorted by price -----------------");
        sortCarsByPrice();

        System.out.println("---------------- Choppers sorted by top speed --------------");
        sortChoppersByTopSpeed();

        System.out.println("---------------- Count vehicles per brand --------------");
        countVehiclesPerBrand();

        System.out.println("---------------- Writing cars to file --------------");
        FileWriterUtils<Car> carFileWriter = new FileWriterUtils<>();
        carFileWriter.writeVehicleToFile(CARS_TARGET_FILE, cars);

        FileWriterUtils<Motorcycle> motorcycleFileWriterUtils = new FileWriterUtils<>();
        motorcycleFileWriterUtils.writeVehicleToFile(MOTORCYCLE_TARGET_FILE, motorcycles);

        FileWriterUtils<Tractor> tractorFileWriterUtils = new FileWriterUtils<>();
        tractorFileWriterUtils.writeVehicleToFile(TRACTOR_TARGET_FILE, tractors);

    }

    private static void countVehiclesPerBrand() {
        Map<String, Integer> vehicleCountPerBrand = new HashMap<>();

        vehicles.forEach(v -> {
            if (vehicleCountPerBrand.containsKey(v.getBrand())) {
                Integer count = vehicleCountPerBrand.get(v.getBrand());
                count++;
                vehicleCountPerBrand.put(v.getBrand(), count);
            } else {
                vehicleCountPerBrand.put(v.getBrand(), 1);
            }
        });

        for (Map.Entry<String, Integer> entry : vehicleCountPerBrand.entrySet()) {
            System.out.println("Found " + entry.getValue() + " vehicles for brand " + entry.getKey());
        }
    }

    private static void sortChoppersByTopSpeed() {
        motorcycles.stream()
                .filter(m -> MotorcycleShapeEnum.CHOPPER.equals(m.getMotorcycleShapeEnum()))
                .sorted(Comparator.comparingInt(Motorcycle::getTopSpeed))
                .forEach(System.out::println);
    }

    private static void sortCarsByPrice() {
        // two options for dynamically sorting the cars
//        cars.stream().sorted((c1, c2) -> c1.getPrice() - c2.getPrice()).forEach(System.out::println);
        cars.stream().sorted(Comparator.comparingInt(Car::getPrice)).forEach(System.out::println);
    }

    private static void sortVehicles() {
        vehicles.forEach(v -> {
            if (v instanceof Car) {
                cars.add((Car) v);
            }
            if (v instanceof Motorcycle) {
                motorcycles.add((Motorcycle) v);
            }
            if (v instanceof Tractor) {
                tractors.add((Tractor) v);
            }
        });

        System.out.println("There are " + cars.size() + " cars.");
        System.out.println("There are " + tractors.size() + " tractors.");
        System.out.println("There are " + motorcycles.size() + " motorcycles.");
    }

    private static void readFromFileAndConvert() {
        try (BufferedReader reader = new BufferedReader(new FileReader(BASE_FILE_PATH + SOURCE_FILE))) {

            String line = reader.readLine();

            while (line != null) {
                Vehicle v = convertLineToVehicle(line);
                if (v != null) {
                    vehicles.add(v);
                }
                line = reader.readLine();
            }
        } catch (IOException ioe) {
            System.out.println("Error reading from file: " + ioe.getMessage());
        }
    }

    private static Vehicle convertLineToVehicle(String line) {
        String[] properties = line.split(", ");

        switch (properties[0]) {
            case "Car":
                return convertLineToCar(properties);
            case "Motorcycle":
                return convertLineToMotorcycle(properties);
            case "Tractor":
                return convertLineToTractor(properties);
            default:
                return null;
        }
    }

    private static Tractor convertLineToTractor(String[] properties) {
        Tractor tractor = new Tractor();
        tractor.setBrand(properties[1]);
        tractor.setModel(properties[2]);
        try {
            tractor.setPrice(Integer.parseInt(properties[3]));
        } catch (NumberFormatException e) {
            System.out.println("Could not set price for tractor. Error is " + e.getMessage());
        }
        tractor.setMaxPullWeight(Integer.parseInt(properties[4]));
        return tractor;
    }

    private static Motorcycle convertLineToMotorcycle(String[] properties) {
        Motorcycle motorcycle = new Motorcycle();
        motorcycle.setBrand(properties[1]);
        motorcycle.setModel(properties[2]);
        motorcycle.setPrice(Integer.parseInt(properties[3]));
        motorcycle.setTopSpeed(Integer.parseInt(properties[4]));
        motorcycle.setMotorcycleShapeEnum(MotorcycleShapeEnum.valueOf(properties[5]));
        return motorcycle;
    }

    private static Car convertLineToCar(String[] properties) {
        Car car = new Car();
        car.setBrand(properties[1]);
        car.setModel(properties[2]);
        car.setPrice(Integer.parseInt(properties[3]));
        car.setTopSpeed(Integer.parseInt(properties[4]));
        car.setTransmissionTypeEnum(TransmissionTypeEnum.valueOf(properties[5]));
        car.setCarShapeEnum(CarShapeEnum.valueOf(properties[6]));
        return car;
    }


}
