package org.example;

import com.github.aneureka.TabularStringifier;
import gui.GUI;
import org.example.dataManager.DealershipDAO;
// import org.example.dataManager.LeaseContractsDAO;
// import org.example.dataManager.SalesContractsDAO;
import org.example.dataManager.VehiclesDAO;
import org.example.models.LeaseContract;
import org.example.models.SalesContract;
import org.example.models.Vehicle;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    /*UserInterface ui = new UserInterface();
    ui.display();*/
        String dburl = args[0];
        String dbUser = args[1];
        String dbPass = args[2];


        GUI gui = new GUI(dburl, dbUser, dbPass);
        gui.start();
    }
}