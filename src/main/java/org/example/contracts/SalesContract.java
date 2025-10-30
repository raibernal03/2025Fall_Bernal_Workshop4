package org.example.contracts;

import org.example.dealership.Vehicle;

public class SalesContract extends Contract {
    private boolean financeOrNot;

    public SalesContract(String date, String customerName, String customerEmail, Vehicle vehicle, boolean financeOrNot) {
        super(date, customerName, customerEmail, vehicle);
        this.financeOrNot = financeOrNot;
    }



    //getters & setters
    public double getSalesTaxAmount() {
        return getVehicle().getPrice() * 0.05;
    }

    public double getRecordingFee() {
        return 100;
    }

    public double getProcessingFee() {
        if(getTotalPrice() < 10000){
            return 295;
        }else{
            return 495;
        }
    }

    public boolean isFinanceOrNot() {
        return financeOrNot;
    }

    public void setFinanceOrNot(boolean financeOrNot) {
        this.financeOrNot = financeOrNot;
    }

    @Override
    public double getMonthlyPayment() {
        double p = getVehicle().getPrice();
        double r = 0;
        double n = 0;
        if (!financeOrNot) {
            return 0;
        } else {
            if (p >= 10000) {
                r = .0425;
                n = 48;

            } else {
                r = 0.0525;
                n = 24;
            }
            double top = p * r * (Math.pow((1 + r), n));
            double bottom = (Math.pow((1 + r), n)) - 1;
            return top / bottom;
        }
    }

    @Override
    public double getTotalPrice() {

        return 0;
    }


}