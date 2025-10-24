package org.example;

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
                System.out.println("Welcome to the Dealership Management System");
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
        System.out.print("Enter Make: ");
        String make = scanner.nextLine();
        System.out.print("Enter Model: ");
        String model = scanner.nextLine();
        displayList(dealership.getVehiclesByMakeModel(make, model));
    }

    public void processGetByYearRequest() {
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
        System.out.print("Enter Color: ");
        String color = scanner.nextLine();
        displayList(dealership.getVehiclesByColor(color));
    }

    public void processGetByMileageRequest() {
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
        System.out.print("Enter Vehicle Type: ");
        String vehicleType = scanner.nextLine();
        displayList(dealership.getVehiclesByType(vehicleType));
    }

    public void processGetAllVehicleRequest() {
        displayList(dealership.getAllVehicles());
    }

    public void processAddVehicleRequest() {

    }

    public void processRemoveVehicleRequest() {
    }

    public void displayList(List<Vehicle> vehicles) {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles found matching the criteria.");
            System.out.print("Press Enter to continue...");
            scanner.nextLine();
            return;
        }
        for (Vehicle vehicle : vehicles) {
            System.out.printf("%d | %d | %s | %s | %s | %s | %d | %.2f\n",
                    vehicle.getVin(),
                    vehicle.getYear(),
                    vehicle.getMake(),
                    vehicle.getModel(),
                    vehicle.getVehicleType(),
                    vehicle.getColor(),
                    vehicle.getOdometer(),
                    vehicle.getPrice());
        }
        System.out.print("Press Enter to continue...");
        scanner.nextLine();
    }


}
