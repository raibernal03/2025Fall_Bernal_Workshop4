package org.example.dataManager;

import org.example.models.SalesContract;
import org.example.models.Vehicle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalesContractsDAO {
    private final String dbUrl;
    private final String dbUser;
    private final String dbPass;

    //CRUD operations - Create, Read, Update, Delete
    public SalesContractsDAO(String dbUrl, String dbUser, String dbPass) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPass = dbPass;
    }

    public List<SalesContract> getAllSalesContracts(int dealershipID) {
        // Implementation for fetching all sales contracts from the database
        List<SalesContract> salesContracts = new ArrayList<>();
        String sqlQuery = "SELECT * FROM fall2025_bernal_workshop8.sales_contract";
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass)) {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int contractID = rs.getInt("ContractID");
                String date = String.valueOf(rs.getDate("ContractDate"));
                String customerName = rs.getString("CustomerName");
                String customerEmail = rs.getString("CustomerEmail");
                int vehicleID = rs.getInt("VIN");
                // boolean financeOrNot = rs.getBoolean("FinanceOrNot");
                boolean financeOrNot = Boolean.parseBoolean(String.valueOf(rs.getBoolean("FinanceOrNot")));

                //Looking through vehicles to assign the vehicle to the sales contract
                List<Vehicle> vehicles = new VehiclesDAO(dbUrl, dbUser, dbPass).getAllVehicles(dealershipID);

                var vehicle = vehicles.stream()
                        .filter(v -> v.getVin() == vehicleID)
                        .findFirst()
                        .orElse(null);
                SalesContract salesContract = new SalesContract(contractID, date, customerName, customerEmail, vehicle, financeOrNot);
                salesContracts.add(salesContract);
            }
        } catch (SQLException e) {
            System.out.println("Error with the database: " + e.getMessage());

        }
        return salesContracts; // Placeholder return
    }
}
