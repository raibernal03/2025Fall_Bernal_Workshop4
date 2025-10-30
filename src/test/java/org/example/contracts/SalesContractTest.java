package org.example.contracts;

import org.example.dealership.Vehicle;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class SalesContractTest {

    @ParameterizedTest
    @CsvSource({
            "12345,2026,Toyota,GR86,Sports,Black,0,30000.0,2025-10-30,Naarai Bernal, nbernal0326@gmail.com, true, 1500, 100, 495,32095,728.27",
            "12345,2026,Toyota,GR86,Sports,Black,0,5000.0,2025-10-30,Naarai Bernal, nbernal0326@gmail.com, true, 250, 100, 295,5645,248.29","12345,2026,Toyota,GR86,Sports,Black,0,30000.0,2025-10-30,Naarai Bernal, nbernal0326@gmail.com, false, 1500, 100, 495,32095,0",
            "12345,2026,Toyota,GR86,Sports,Black,0,5000.0,2025-10-30,Naarai Bernal, nbernal0326@gmail.com, false, 250, 100, 295,5645,0"
    })
    public void testingSalesContract(int vin, int year, String make, String model, String vehicleType, String color, int odometer, double vehiclePrice, String date, String customerName, String customerEmail, boolean financeOption, double expectedSalesTax, double expectedRecordingFee, double expectedProcessingFee, double expectedTotalPrice, double expectedMonthlyPrice) {
        // Test data setup
        Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, vehiclePrice);
        SalesContract salesContract = new SalesContract(date, customerName, customerEmail, vehicle, financeOption);

        assertEquals(expectedSalesTax, salesContract.getSalesTaxAmount(), 0.01);
        assertEquals(expectedRecordingFee, salesContract.getRecordingFee(), 0.01);
        assertEquals(expectedProcessingFee, salesContract.getProcessingFee(), 0.01);
        assertEquals(expectedTotalPrice, salesContract.getTotalPrice(), 0.01);
        assertEquals(expectedMonthlyPrice, salesContract.getMonthlyPayment(), 0.01);


    }

}