package org.example.models;

public class SalesContract extends Contract {
    private boolean financeOrNot;

    public SalesContract(int contractID, String date, String customerName, String customerEmail, Vehicle vehicle, boolean financeOrNot) {
        super(contractID, date, customerName, customerEmail, vehicle);
        this.financeOrNot = financeOrNot;
    }


    //getters
    public double getSalesTaxAmount() {
        return getVehicle().getPrice() * 0.05;
    }

    public double getRecordingFee() {
        return 100;
    }

    public double getProcessingFee() {
        if (getVehicle().getPrice() < 10000) {
            return 295;
        } else {
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
    public double getTotalPrice() {
        return (getVehicle().getPrice() + getSalesTaxAmount() + getRecordingFee() + getProcessingFee());
    }

    @Override
    public double getMonthlyPayment() {
        double p = getTotalPrice();
        double r = 0;
        double n = 0;
        if (!financeOrNot) {
            return 0;
        } else {
            if (p >= 10000) {
                r = .0425 / 12;
                n = 48;

            } else {
                r = 0.0525 / 12;
                n = 24;
            }
            double top = r * (Math.pow((1 + r), n));
            double bottom = (Math.pow((1 + r), n)) - 1;
            double solution = p * (top / bottom);
            return solution;
        }
    }

}