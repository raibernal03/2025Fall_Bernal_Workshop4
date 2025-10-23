package org.example;

import java.util.ArrayList;
import java.util.List;

public class Dealership {
    private String name;
    private String address;
    private String phone;
    private List<Vehicle> inventory;

    public Dealership() {
    }

    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    //min and max price
    public List<Vehicle> getVehiclesByPrice(double min, double max) {
        List<Vehicle> vehicles = new ArrayList<>();

        for (Vehicle v : inventory) {
            if (v.getPrice() >= min && v.getPrice() <= max) {
                vehicles.add(v);
            }
        }

        return vehicles;
    }

    //make and model
    public List<Vehicle> getVehiclesByMakeModel(String make, String model) {
        List<Vehicle> vehicles = new ArrayList<>();

        for (Vehicle v : inventory) {
            if (v.getMake().toLowerCase().contains(make.toLowerCase()) && v.getModel().toLowerCase().contains(model.toLowerCase())) {
                vehicles.add(v);
            }
        }

        return vehicles;
    }

    //min and max year
    public List<Vehicle> getVehiclesByYear(int min, int max) {
        List<Vehicle> vehicles = new ArrayList<>();
        for (Vehicle v : inventory) {
            if (v.getYear() >= min && v.getYear() <= max) {
                vehicles.add(v);
            }
        }
        return vehicles;
    }

    //vehicle color
    public List<Vehicle> getVehiclesByColor(String color) {
        List<Vehicle> vehicles = new ArrayList<>();

        for (Vehicle v : inventory) {
            if (v.getColor().toLowerCase().contains(color.toLowerCase())) {
                vehicles.add(v);
            }
        }

        return vehicles;
    }

    //vehicle by mileage
    public List<Vehicle> getVehiclesByMileage(int min, int max) {
        List<Vehicle> vehicles = new ArrayList<>();

        for (Vehicle v : inventory) {
            if(v.getOdometer() >= min && v.getOdometer() <= max) {
                vehicles.add(v);
            }
        }

        return vehicles;
    }

    //vehicle type
    public List<Vehicle> getVehiclesByType(String type) {
        List<Vehicle> vehicles = new ArrayList<>();

        for (Vehicle v : inventory) {
            if (v.getVehicleType().equalsIgnoreCase(type)) {
                vehicles.add(v);
            }
        }

        return vehicles;
    }

    //all vehicles
    public List<Vehicle> getAllVehicles() {
        return inventory;
    }

    //add vehicle
    public void addVehicle(Vehicle vehicle) {
        inventory.add(vehicle);
    }

    //remove vehicle
    public void removeVehicle(Vehicle vehicle) {
        inventory.remove(vehicle);
    }

}
