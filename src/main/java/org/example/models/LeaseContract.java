package org.example.models;

public class LeaseContract extends Contract {


    public LeaseContract(int contractID, String date, String customerName, String customerEmail, Vehicle vehicle) {
        super(contractID, date, customerName, customerEmail, vehicle);
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
        double rate = 0.04/12;
        int months = 36;
        double top = getTotalPrice() * (rate );
        double bottom = 1 - (Math.pow((1 + (rate)), -months));
        return top / bottom;
    }
}
