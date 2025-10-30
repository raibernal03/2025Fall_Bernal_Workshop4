package org.example.contracts;

import org.example.dealership.Vehicle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class LeaseContractTest {
    @ParameterizedTest
    @CsvSource({
            "12345,2026,Toyota,GR86,Sports,Black,0,30000.0,2025-10-30,Naarai Bernal,nbernal0326@gmail.com,15000, 2100, 17100,504.86",
            "12345,2026,Toyota,GR86,Sports,Black,0,5000.0,2025-10-30,Naarai Bernal,nbernal0326@gmail.com,2500,350,2850,84.14"

    })
    public void testLeaseContract(int vin, int year, String make, String model, String vehicleType, String color, int odometer, double vehiclePrice, String date, String customerName, String customerEmail, double expectedEndingValue, double expectedLeaseFee, double expectedTotalPrice, double expectedMonthlyPrice) {
        Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, vehiclePrice);
        LeaseContract leaseContract = new LeaseContract(date, customerName, customerEmail, vehicle);

        assertEquals(expectedEndingValue, leaseContract.getExpectedEndingValue(), 0.01);
        assertEquals(expectedLeaseFee, leaseContract.getLeaseFee(), 0.01);
        assertEquals(expectedTotalPrice, leaseContract.getTotalPrice(), 0.01);
        assertEquals(expectedMonthlyPrice, leaseContract.getMonthlyPayment(), 0.01);
    }

}