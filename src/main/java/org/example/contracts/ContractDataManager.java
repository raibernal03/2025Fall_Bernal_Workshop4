package org.example.contracts;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ContractDataManager {

    public static  void saveContract(Contract contract){
        try {
            FileWriter fw = new FileWriter("contracts.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            if (contract instanceof SalesContract) {
                bw.write(String.format(""));
            } else {


            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception ex) {

        }
    }
}
