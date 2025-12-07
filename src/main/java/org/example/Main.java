package org.example;

import org.example.gui.GUI;
// import org.example.dataManager.LeaseContractsDAO;
// import org.example.dataManager.SalesContractsDAO;


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