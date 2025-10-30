package org.example.contracts;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ContractFileManager {
    public static void saveContract(Contract contract){
        try {
            FileWriter fw = new FileWriter("src/main/resources/contracts.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            if (contract instanceof SalesContract) {
                bw.write(String.format("Sale|%s|%s|%s|%s|%d|%s|%s|%s|%s|%d|%.2f|%.2f|%.2f|%.2f|%.2f|%s|%.2f",
                        contract.getDate(),
                        contract.getCustomerName(),
                        contract.getCustomerEmail(),
                        contract.getVehicle().getVin(),
                        contract.getVehicle().getYear(),
                        contract.getVehicle().getMake(),
                        contract.getVehicle().getModel(),
                        contract.getVehicle().getVehicleType(),
                        contract.getVehicle().getColor(),
                        contract.getVehicle().getOdometer(),
                        contract.getVehicle().getPrice(),
                        ((SalesContract) contract).getSalesTaxAmount(),
                        ((SalesContract) contract).getRecordingFee(),
                        ((SalesContract) contract).getProcessingFee(),
                        contract.getTotalPrice(),
                        ((SalesContract) contract).isFinanceOrNot()? "Yes" : "No",
                        contract.getMonthlyPayment()));

            } else if(contract instanceof LeaseContract){
                bw.write(String.format("LEASE|%s|%s|%s|%s|%s|%d|%s|%s|%s|%s|%d|%.2f|%.2f|%.2f|%.2f|%.2f",
                        contract.getDate(),
                        contract.getCustomerName(),
                        contract.getCustomerEmail(),
                        contract.getVehicle().getVin(),
                        contract.getVehicle().getYear(),
                        contract.getVehicle().getMake(),
                        contract.getVehicle().getModel(),
                        contract.getVehicle().getVehicleType(),
                        contract.getVehicle().getColor(),
                        contract.getVehicle().getOdometer(),
                        contract.getVehicle().getPrice(),
                        ((LeaseContract) contract).getExpectedEndingValue(),
                        ((LeaseContract) contract).getLeaseFee(),
                        contract.getTotalPrice(),
                        contract.getMonthlyPayment()));

            }

            bw.close();
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
