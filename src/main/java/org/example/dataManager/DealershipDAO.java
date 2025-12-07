package org.example.dataManager;

import org.example.models.Dealership;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DealershipDAO {
    private final String dbUrl;
    private final String dbUser;
    private final String dbPass;

    //CRUD operations - Create, Read, Update, Delete
    public DealershipDAO(String dbUrl, String dbUser, String dbPass) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPass = dbPass;
    }

    public List<Dealership> getAllDealerships() {
        // Implementation for fetching all dealerships from the database
        List<Dealership> dealerships = new ArrayList<>();
        String sqlQuery = "SELECT * FROM fall2025_bernal_workshop8.dealerships";
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass)) {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int dealershipID = rs.getInt("DealershipID");
                String name = rs.getString("Name");
                String address = rs.getString("Address");
                String phone = rs.getString("Phone");

                Dealership dealership = new Dealership(dealershipID, name, address, phone);
                dealerships.add(dealership);
            }

        } catch (SQLException e) {
            System.out.println("Error with the database: " + e.getMessage());

        }
        return dealerships; // Placeholder return
    }

    public Optional<Dealership> getDealershipById(int id) {
        String sqlQuery = "SELECT * FROM dealerships WHERE DealershipID = ?";
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass)) {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);

            ps.setInt(1, id); // you are replacing the first question mark on your query with that id value
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int dealershipId = rs.getInt("DealershipID");
                String name = rs.getString("Name");
                String address = rs.getString("Address");
                String phone = rs.getString("Phone");

                return Optional.of(new Dealership(dealershipId, name, address, phone));
            }
        } catch (SQLException e) {
            System.out.println("Error with the database: " + e.getMessage());
        }

        return Optional.empty();

    }



}
