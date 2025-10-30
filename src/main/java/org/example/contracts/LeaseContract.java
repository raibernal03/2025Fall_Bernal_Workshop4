package org.example.contracts;

import org.example.dealership.Vehicle;

public class LeaseContract extends Contract {


    public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicle) {
        super(date, customerName, customerEmail, vehicle);
    }

    public double getExpectedEndingValue() {
        return (getVehicle().getPrice() * 0.50);
    }

    public double getLeaseFee() {
        return (getVehicle().getPrice() * 0.07);
    }

    @Override
    public double getTotalPrice() {
        return (getVehicle().getPrice() - getExpectedEndingValue()) + getLeaseFee();
    }

    @Override
    public double getMonthlyPayment() {
        double rate = 0.04;
        int months = 36;
        double top = getTotalPrice() * (rate / 12);
        double bottom = 1 - (Math.pow((1 + (rate / 12)), -months));
        return top / bottom;
    }
}
