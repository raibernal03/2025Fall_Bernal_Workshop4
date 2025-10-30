package org.example;

import org.example.dealership.Dealership;
import org.example.dealership.DealershipFileManager;
import org.example.dealership.Vehicle;

import java.util.List;
import java.util.Scanner;

public class UserInterface {
    static Scanner scanner = new Scanner(System.in);

    private Dealership dealership;

    public UserInterface() {
    }

    public void display() {
        init();
        boolean running = true;
        while (running) {
            try {
                System.out.println("=".repeat(100));
                System.out.printf("%38s %-23s \n"," ", dealership.getName());
                System.out.printf("%35s %-20s\n"," ", dealership.getAddress());
                System.out.printf("%34s %22s\n"," ", dealership.getPhone());
                System.out.println("=".repeat(100));

                System.out.println("Please select an option:");
                System.out.println("1. Get Vehicles by Price"
                        + "\n2. Get Vehicles by Make and Model"
                        + "\n3. Get Vehicles by Year"
                        + "\n4. Get Vehicles by Color"
                        + "\n5. Get Vehicles by Mileage"
                        + "\n6. Get Vehicles by Vehicle Type"
                        + "\n7. Get All Vehicles"
                        + "\n8. Add Vehicle"
                        + "\n9. Remove Vehicle"
                        + "\n0. Exit");

                System.out.print("--> ");
                int input = Integer.parseInt(scanner.nextLine());
                switch (input) {
                    case 1:
                        processGetByPriceRequest();
                        break;
                    case 2:

                        processGetByMakeModelRequest();
                        break;
                    case 3:
                        processGetByYearRequest();
                        break;
                    case 4:
                        processGetByColorRequest();
                        break;
                    case 5:
                        processGetByMileageRequest();
                        break;
                    case 6:
                        processGetByVehicleTypeRequest();
                        break;
                    case 7:
                        processGetAllVehicleRequest();
                        break;
                    case 8:
                        processAddVehicleRequest();
                        break;
                    case 9:
                        processRemoveVehicleRequest();
                        break;
                    case 0:
                        running = false;
                        System.out.println("Exiting the system. Goodbye!👋");
                        break;
                    default:
                        System.out.println("❌Invalid option. Please try again.");
                        break;
                }
            } catch(Exception e){
                System.out.println("❌Invalid input. Please choose a number between '0' and '9'");
            }
        }
    }

    private void init() {
        DealershipFileManager fileManager = new DealershipFileManager();
        this.dealership = fileManager.getDealership();
    }

    public void processGetByPriceRequest() {
        boolean running = true;
        while (running) {
            System.out.println("=".repeat(100));
            System.out.printf("%40s %20s %40s\n"," ", "Get Vehicles by Price Range"," ");
            System.out.println("=".repeat(100));
            System.out.print("Enter min: $");
            double min = Double.parseDouble(scanner.nextLine());
            System.out.print("Enter max: $");
            double max = Double.parseDouble(scanner.nextLine());
            if (min > max) {
                System.out.println("Invalid price range. Minimum price cannot be greater than maximum price.");
            } else {
                running = false;
                displayList(dealership.getVehiclesByPrice(min, max));
            }
        }
    }

    public void processGetByMakeModelRequest() {
        System.out.println("=".repeat(100));
        System.out.printf("%40s %20s %40s\n"," ", "Get Vehicles by Make and Model"," ");
        System.out.println("=".repeat(100));
        System.out.print("Enter Make: ");
        String make = scanner.nextLine();
        System.out.print("Enter Model: ");
        String model = scanner.nextLine();
        displayList(dealership.getVehiclesByMakeModel(make, model));
    }

    public void processGetByYearRequest() {
        System.out.println("=".repeat(100));
        System.out.printf("%40s %20s %40s\n"," ", "Get Vehicles by Year Range"," ");
        System.out.println("=".repeat(100));
        boolean running = true;
        while (running) {
            System.out.print("Enter min year: ");
            int minYear = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter max year: ");
            int maxYear = Integer.parseInt(scanner.nextLine());
            if (minYear > maxYear) {
                System.out.println("Invalid year range. Minimum year cannot be greater than maximum year.");
            } else {
                running = false;
                displayList(dealership.getVehiclesByYear(minYear, maxYear));
            }
        }
    }

    public void processGetByColorRequest() {
        System.out.println("=".repeat(100));
        System.out.printf("%40s %20s %40s\n"," ", "Get Vehicles by Color"," ");
        System.out.println("=".repeat(100));

        System.out.print("Enter Color: ");
        String color = scanner.nextLine();
        displayList(dealership.getVehiclesByColor(color));
    }

    public void processGetByMileageRequest() {
        System.out.println("=".repeat(100));
        System.out.printf("%40s %20s %40s\n"," ", "Get Vehicles by Mileage Range"," ");
        System.out.println("=".repeat(100));

        boolean running = true;
        while (running) {
            System.out.print("Enter min mileage: ");
            int minMileage = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter max mileage: ");
            int maxMileage = Integer.parseInt(scanner.nextLine());
            if (minMileage > maxMileage) {
                System.out.println("Invalid mileage range. Minimum mileage cannot be greater than maximum mileage.");
            } else {
                running = false;
                displayList(dealership.getVehiclesByMileage(minMileage, maxMileage));
            }
        }
    }

    public void processGetByVehicleTypeRequest() {
        System.out.println("=".repeat(100));
        System.out.printf("%40s %20s %40s\n"," ", "Get Vehicles by Vehicle Type"," ");
        System.out.println("=".repeat(100));
        System.out.print("Enter Vehicle Type: ");
        String vehicleType = scanner.nextLine();
        displayList(dealership.getVehiclesByType(vehicleType));
    }

    public void processGetAllVehicleRequest() {
        System.out.println("=".repeat(100));
        System.out.printf("%40s %20s %40s\n"," ", "All Vehicles in Inventory"," ");
        System.out.println("=".repeat(100));
        displayList(dealership.getAllVehicles());
    }

    public void processAddVehicleRequest() {
        System.out.println("=".repeat(100));
        System.out.printf("%40s %20s %40s\n"," ", "Adding a New Vehicle"," ");
        System.out.println("=".repeat(100));
        System.out.print("Enter VIN: ");
        int vin = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter Year: ");
        int year = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter Make: ");
        String make = scanner.nextLine();
        System.out.print("Enter Model: ");
        String model = scanner.nextLine();
        System.out.print("Enter Vehicle Type: ");
        String vehicleType = scanner.nextLine();
        System.out.print("Enter Color: ");
        String color = scanner.nextLine();
        System.out.print("Enter Odometer: ");
        int odometer = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter Price: $");
        double price = Double.parseDouble(scanner.nextLine());
        Vehicle newVehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
        dealership.addVehicle(newVehicle);
        DealershipFileManager.saveDealership(dealership);
        System.out.println("✅ Vehicle added successfully!");

    }

    public void processRemoveVehicleRequest() {
        System.out.println("=".repeat(100));
        System.out.printf("%40s %20s %40s\n"," ", "Removing a Vehicle"," ");
        System.out.println("=".repeat(100));
        System.out.print("Enter VIN of the vehicle to remove: ");
        int vin = Integer.parseInt(scanner.nextLine());
        for (Vehicle v : dealership.getAllVehicles()) {
            if (v.getVin() == vin) {
                System.out.println("Vehicle Found:");
                System.out.printf("%-5d | %-6d | %-10s | %-10s | %-15s | %-10s | %-10d | $%-10.2f\n",
                        v.getVin(),
                        v.getYear(),
                        v.getMake(),
                        v.getModel(),
                        v.getVehicleType(),
                        v.getColor(),
                        v.getOdometer(),
                        v.getPrice());

                dealership.removeVehicle(v);
                System.out.println("✅ Vehicle removed successfully!");
                break;
            }
        }
        DealershipFileManager.saveDealership(dealership);

    }

    public void displayList(List<Vehicle> vehicles) {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles found matching the criteria.");
            System.out.print("Press Enter to continue...");
            scanner.nextLine();
            return;
        }
        System.out.printf("%-5s | %-6s | %-10s | %-10s | %-15s | %-10s | %-10s | $%-10s\n",
                "VIN", "Year", "Make", "Model", "Type", "Color", "Odometer", "Price");
        System.out.println("-".repeat(100));
        for (Vehicle vehicle : vehicles) {
            System.out.printf("%-5d | %-6d | %-10s | %-10s | %-15s | %-10s | %-10d | $%-10.2f\n",
                    vehicle.getVin(),
                    vehicle.getYear(),
                    vehicle.getMake(),
                    vehicle.getModel(),
                    vehicle.getVehicleType(),
                    vehicle.getColor(),
                    vehicle.getOdometer(),
                    vehicle.getPrice());
        }
        System.out.println("-".repeat(100));
        System.out.print("Press Enter to continue...");
        scanner.nextLine();
    }
}
