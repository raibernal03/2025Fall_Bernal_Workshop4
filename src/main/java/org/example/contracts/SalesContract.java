package org.example.contracts;

public class SalesContract extends Contract {
    private double salesTaxAmount;
    private double recordingFee = 100;
    private double processingFee;
    private boolean financeOrNot;

    public SalesContract(String date, String customerName, String customerEmail, boolean vehicleSold, double salesTaxAmount, double recordingFee, double processingFee, boolean financeOrNot) {
        super(date, customerName, customerEmail, vehicleSold);
        this.salesTaxAmount = salesTaxAmount;
        this.financeOrNot = financeOrNot;
    }

    //getters & setters
    public double getSalesTaxAmount() {
        return salesTaxAmount;
    }

    public void setSalesTaxAmount(double salesTaxAmount) {
        this.salesTaxAmount = salesTaxAmount;
    }

    public double getRecordingFee() {
        return recordingFee;
    }

    public void setRecordingFee(double recordingFee) {
        this.recordingFee = recordingFee;
    }

    public double getProcessingFee() {
        if(getTotalPrice() < 10000){
            return processingFee = 295;
        }else{
            return processingFee = 495;
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
        double p = getTotalPrice();
        double r = 0;
        double n = 0;
        if(!financeOrNot) {
            return 0;
        }else{
            if(getTotalPrice() >= 10000){
                r =  .0425;
                n = 48;
                double top = p * r *(Math.pow((1+r), n));
                double bottom = (Math.pow((1+r), n)) -1;
                return top / bottom;
            }else{
                r =  0.0525;
                n = 24;
                double top = p * r *(Math.pow((1+r), n));
                double bottom = (Math.pow((1+r), n)) -1;
                return top / bottom;
            }
        }
    }
    @Override
    public double getTotalPrice() {

        return 0;
    }


}