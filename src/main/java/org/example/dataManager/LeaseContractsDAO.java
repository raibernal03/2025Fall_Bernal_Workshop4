package org.example.dataManager;

import org.example.models.LeaseContract;
import org.example.models.Vehicle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LeaseContractsDAO {
    private final String dbUrl;
    private final String dbUser;
    private final String dbPass;

    //CRUD operations - Create, Read, Update, Delete
    public LeaseContractsDAO(String dbUrl, String dbUser, String dbPass) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPass = dbPass;
    }

    public List<LeaseContract> getAllSalesContracts(int dealershipID) {
        // Implementation for fetching all sales contracts from the database
        List<LeaseContract> leaseContracts = new ArrayList<>();
        String sqlQuery = "SELECT * FROM fall2025_bernal_workshop8.lease_contacts";
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass)) {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int contractID = rs.getInt("ContractID");
                String date = String.valueOf(rs.getDate("ContractDate"));
                String customerName = rs.getString("CustomerName");
                String customerEmail = rs.getString("CustomerEmail");
                int vehicleID = rs.getInt("VIN");

                //Looking through vehicles to assign the vehicle to the sales contract
                List<Vehicle> vehicles = new VehiclesDAO(dbUrl, dbUser, dbPass).getAllVehicles(dealershipID);

                var vehicle = vehicles.stream()
                        .filter(v -> v.getVin() == vehicleID)
                        .findFirst()
                        .orElse(null);
                LeaseContract leaseContract = new LeaseContract(contractID, date, customerName, customerEmail, vehicle);
                leaseContracts.add(leaseContract);
            }
        } catch (SQLException e) {
            System.out.println("Error with the database: " + e.getMessage());

        }
        return leaseContracts; // Placeholder return
    }
}
