package org.example.oldFileManagers;

import org.example.models.Dealership;
import org.example.models.Vehicle;

import java.io.*;
import java.util.List;

public class DealershipFileManager {

    //read from file
    public static Dealership getDealership() {
        Dealership dealership = new Dealership();
        try{

            FileReader fr = new FileReader("src/main/resources/inventory.csv");
            BufferedReader reader = new BufferedReader(fr);

            String dealershipInfo = reader.readLine();
            if (dealershipInfo == null || dealershipInfo.isBlank()) {
                System.err.println("No dealership info found in file.");
                return dealership;
            }

            String[] dealerParts = dealershipInfo.split("\\|");
            dealership.setName(dealerParts[0].trim());
            dealership.setAddress(dealerParts[1].trim());
            dealership.setPhone(dealerParts[2].trim());

            String line;
            while((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length < 8) continue;

                try {
                    var vehicle = new Vehicle(
                            Integer.parseInt(parts[0].trim()),
                            Integer.parseInt(parts[1].trim()),
                            parts[2].trim(),
                            parts[3].trim(),
                            parts[4].trim(),
                            parts[5].trim(),
                            Integer.parseInt(parts[6].trim()),
                            Double.parseDouble(parts[7].trim())
                    );
                    dealership.addVehicle(vehicle);
                } catch (NumberFormatException e) {
                    System.err.println("Skipping invalid vehicle entry: " + line);
                }
            }

            reader.close();
            fr.close();


        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return dealership;
    }

    public static void saveDealership(Dealership dealership){
        //implement saving dealership inventory to file if needed
        try {
            FileWriter fw = new FileWriter("src/main/resources/inventory.csv");
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write( dealership.getName() + "|" + dealership.getAddress() + "|" + dealership.getPhone());
            List<Vehicle> vehicles = dealership.getAllVehicles();
            for (Vehicle v : vehicles) {
                bw.write("\n" + v.getVin() + "|" + v.getYear() + "|" + v.getMake() + "|" + v.getModel() + "|" + v.getVehicleType() + "|" + v.getColor() + "|" + v.getOdometer() + "|" + v.getPrice());
            }

            bw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
}
