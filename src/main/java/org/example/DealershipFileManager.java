package org.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DealershipFileManager {
    //read from file
    public static Dealership getDealership() {
        Dealership dealership = new Dealership();
        try{

            FileReader fr = new FileReader("src/main/resources/inventory.csv");
            BufferedReader reader = new BufferedReader(fr);

            String line;
            while((line = reader.readLine()) != null) {
                //parse line to create Vehicle objects and add to Dealership inventory
                String[] data = line.split("\\|");

                int vin = Integer.parseInt(data[0]);
                int year = Integer.parseInt(data[1]);
                String make = data[2];
                String model = data[3];
                String vehicleType = data[4];
                String color = data[5];
                int odometer = Integer.parseInt(data[6]);
                double price = Double.parseDouble(data[7]);
                Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);

                dealership.addVehicle(vehicle);

            }

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return dealership;
    }

    public static void saveDealership(Dealership dealership) {
        //implement saving dealership inventory to file if needed
    }
}
