package org.example.oldFileManagers;

import org.example.models.Vehicle;
import org.example.models.Contract;
import org.example.models.LeaseContract;
import org.example.models.SalesContract;

import java.util.ArrayList;
import java.util.List;

public class ContractDataManager {
    List<Contract> contracts = new ArrayList<>();
    List<Vehicle> vehicles = DealershipFileManager.getDealership().getAllVehicles();

    //create sales contract
    public static void createSalesContract(String date, String customerName, String customerEmail, Vehicle vehicle, boolean financeOrNot){
        SalesContract salesContract = new SalesContract(date, customerName, customerEmail, vehicle, financeOrNot);
        System.out.println("=".repeat(100));
        System.out.println("---- Vehicle Details ----");
        System.out.println("VIN: " + vehicle.getVin());
        System.out.println("Year: " + vehicle.getYear());
        System.out.println("Make: " + vehicle.getMake());
        System.out.println("Model: " + vehicle.getModel());
        System.out.println("Type: " + vehicle.getVehicleType());
        System.out.println("Color: " + vehicle.getColor());
        System.out.println("Odometer: " + vehicle.getOdometer() + " miles");
        System.out.printf("Price: $%.2f\n", vehicle.getPrice());
        System.out.println("---- Customer Details ----");
        System.out.println("Name: " + customerName);
        System.out.println("Email: " + customerEmail);
        System.out.println("Date: " + date);
        System.out.println("Finance: " + (financeOrNot ? "Yes" : " No"));

        if(financeOrNot){
            System.out.println("---- Finance Details ----");
            System.out.println("Sales Tax Amount: $" + salesContract.getSalesTaxAmount());
            System.out.println("Recording Fee: $" + salesContract.getRecordingFee());
            System.out.println("Processing Fee: $" + salesContract.getProcessingFee());
            System.out.printf("Total Price: $%.2f\n", salesContract.getTotalPrice());
            System.out.printf("Monthly Payment: $%.2f\n", salesContract.getMonthlyPayment());
        } else {
            System.out.printf("Total Price: $%.2f\n", salesContract.getTotalPrice());
        }
        System.out.println("=".repeat(100));
    }

    //create lease contract
    public static void createLeaseContract(String date, String customerName, String customerEmail, Vehicle vehicle){
        LeaseContract leaseContract = new LeaseContract(date, customerName, customerEmail, vehicle);
        System.out.println("=".repeat(100));
        System.out.println("---- Vehicle Details ----");
        System.out.println("VIN: " + vehicle.getVin());
        System.out.println("Year: " + vehicle.getYear());
        System.out.println("Make: " + vehicle.getMake());
        System.out.println("Model: " + vehicle.getModel());
        System.out.println("Type: " + vehicle.getVehicleType());
        System.out.println("Color: " + vehicle.getColor());
        System.out.println("Odometer: " + vehicle.getOdometer() + " miles");
        System.out.printf("Price: $%.2f\n", vehicle.getPrice());
        System.out.println("---- Customer Details ----");
        System.out.println("Name: " + customerName);
        System.out.println("Email: " + customerEmail);
        System.out.println("Date: " + date);
        System.out.println("---- Lease Details ----");
        System.out.printf("Expected Ending Value: $%.2f\n", leaseContract.getExpectedEndingValue());
        System.out.printf("Lease Fee: $%.2f\n", leaseContract.getLeaseFee());
        System.out.printf("Total Price: $%.2f\n", leaseContract.getTotalPrice());
        System.out.printf("Monthly Payment: $%.2f\n", leaseContract.getMonthlyPayment());
        System.out.println("=".repeat(100));
    }

    //SAVE CONTRACT TO FILE
    public static void finalizeContract(Contract contract){
        ContractFileManager.saveContract(contract);
    }

}
