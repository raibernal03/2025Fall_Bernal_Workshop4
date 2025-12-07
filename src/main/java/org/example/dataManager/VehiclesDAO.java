package org.example.dataManager;

import org.example.models.Dealership;
import org.example.models.Vehicle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VehiclesDAO {
    private final String dbUrl;
    private final String dbUser;
    private final String dbPass;

    //CRUD operations - Create, Read, Update, Delete
    public VehiclesDAO(String dbUrl, String dbUser, String dbPass) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPass = dbPass;
    }

    public List<Vehicle> getAllVehicles(int dealershipID) {
        List<Vehicle> vehicles = new ArrayList<>();
        String sqlQuery = "SELECT * FROM vehicles";
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass)) {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int vin = rs.getInt("VIN");
                int year = rs.getInt("Year");
                String make = rs.getString("Make");
                String model = rs.getString("Model");
                String vehicleType = rs.getString("VehicleType");
                String color = rs.getString("Color");
                int odometer = rs.getInt("Odometer");
                double price = rs.getDouble("Price");
                int dealershipId = rs.getInt("DealershipID");

                if (dealershipId == dealershipID) {
                    Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price, dealershipId);
                    vehicles.add(vehicle);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error with the database: " + e.getMessage());

        }

        return vehicles; // Placeholder return
    }

    // Create - add a new product
    public Vehicle createVehicle(Vehicle vehicle) {
        String sqlQuery = "INSERT INTO vehicles (VIN,Year, Make, Model, VehicleType, Color, Odometer, Price) VALUES (?, ?,?, ?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass)) {
            PreparedStatement ps = conn.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, vehicle.getVin());
            ps.setInt(2, vehicle.getYear());
            ps.setString(3, vehicle.getMake());
            ps.setString(4, vehicle.getModel());
            ps.setString(5, vehicle.getVehicleType());
            ps.setString(6, vehicle.getColor());
            ps.setInt(7, vehicle.getOdometer());
            ps.setDouble(8, vehicle.getPrice());


            //Execute Update us when you do Inserts ir updates
            int affectedRows = ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("There was a problem with the database");
            ex.printStackTrace();
        }
        return vehicle;
    }

    // Delete - delete a product by ID
    public void deleteVehicle(int id) {
        String sqlQuery = "DELETE FROM vehicles WHERE VIN = ?";
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass)) {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);

            ps.setInt(1, id);

            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("There was a problem with the database");
            ex.printStackTrace();
        }
    }

    /*select * from vehicles
    where Price between 10000 and 20000;*/
    public List<Vehicle> getByPriceRange(double minPrice, double maxPrice, int dealershipID) {
        List<Vehicle> vehicles = new ArrayList<>();
        String sqlQuery = "SELECT * FROM vehicles WHERE Price BETWEEN ? AND ?";
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass)) {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setDouble(1, minPrice);
            ps.setDouble(2, maxPrice);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int vin = rs.getInt("VIN");
                int year = rs.getInt("Year");
                String make = rs.getString("Make");
                String model = rs.getString("Model");
                String vehicleType = rs.getString("VehicleType");
                String color = rs.getString("Color");
                int odometer = rs.getInt("Odometer");
                double price = rs.getDouble("Price");
                int dealershipId = rs.getInt("DealershipID");

                if (dealershipId == dealershipID) {
                    Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price, dealershipId);
                    vehicles.add(vehicle);
                }

            }
        } catch (SQLException e) {
            System.out.println("Error with the database: " + e.getMessage());
        }
        return vehicles;
    }

    /*select * from vehicles
    where Make = 'Toyota' and Model = 'Camry';*/
    public List<Vehicle> getByMakeAndModel(String make, String model, int dealershipID) {
        List<Vehicle> vehicles = new ArrayList<>();
        String sqlQuery = "SELECT * FROM vehicles WHERE Make = ? AND Model = ?";
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass)) {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setString(1, make);
            ps.setString(2, model);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int vin = rs.getInt("VIN");
                int year = rs.getInt("Year");
                String make1 = rs.getString("Make");
                String model1 = rs.getString("Model");
                String vehicleType = rs.getString("VehicleType");
                String color = rs.getString("Color");
                int odometer = rs.getInt("Odometer");
                double price = rs.getDouble("Price");
                int dealershipId = rs.getInt("DealershipID");

                if (dealershipId == dealershipID) {
                    Vehicle vehicle = new Vehicle(vin, year, make1, model1, vehicleType, color, odometer, price, dealershipId);
                    vehicles.add(vehicle);
                }

            }
        } catch (SQLException e) {
            System.out.println("Error with the database: " + e.getMessage());
        }
        return vehicles;
    }

    /*select * from vehicles
    where Year between 2015 and 2020;*/
    public List<Vehicle> getByYearRange(int minYear,int maxYear, int dealershipID) {
        List<Vehicle> vehicles = new ArrayList<>();
        String sqlQuery = "SELECT * FROM vehicles WHERE Year between ? and ?";
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass)) {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setInt(1, minYear);
            ps.setInt(2, maxYear);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int vin = rs.getInt("VIN");
                int year1 = rs.getInt("Year");
                String make = rs.getString("Make");
                String model = rs.getString("Model");
                String vehicleType = rs.getString("VehicleType");
                String color = rs.getString("Color");
                int odometer = rs.getInt("Odometer");
                double price = rs.getDouble("Price");
                int dealershipId = rs.getInt("DealershipID");

                if (dealershipId == dealershipID) {
                    Vehicle vehicle = new Vehicle(vin, year1, make, model, vehicleType, color, odometer, price, dealershipId);
                    vehicles.add(vehicle);
                }

            }
        } catch (SQLException e) {
            System.out.println("Error with the database: " + e.getMessage());
        }
        return vehicles;
    }

    /*select * from vehicles
    where Color = 'Red';*/
    public List<Vehicle> getByColor(String color, int dealershipID) {
        List<Vehicle> vehicles = new ArrayList<>();
        String sqlQuery = "SELECT * FROM vehicles WHERE Color = ?";
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass)) {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setString(1, color);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int vin = rs.getInt("VIN");
                int year = rs.getInt("Year");
                String make = rs.getString("Make");
                String model = rs.getString("Model");
                String vehicleType = rs.getString("VehicleType");
                String color1 = rs.getString("Color");
                int odometer = rs.getInt("Odometer");
                double price = rs.getDouble("Price");
                int dealershipId = rs.getInt("DealershipID");

                if (dealershipId == dealershipID) {
                    Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color1, odometer, price, dealershipId);
                    vehicles.add(vehicle);
                }

            }
        } catch (SQLException e) {
            System.out.println("Error with the database: " + e.getMessage());
        }
        return vehicles;
    }

    /*select * from vehicles
    where Odometer between 0 and 50000;*/
    public List<Vehicle> getByOdometerRange(int minOdometer, int maxOdometer, int dealershipID) {
        List<Vehicle> vehicles = new ArrayList<>();
        String sqlQuery = "SELECT * FROM vehicles WHERE Odometer BETWEEN ? AND ?";
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass)) {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setInt(1, minOdometer);
            ps.setInt(2, maxOdometer);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int vin = rs.getInt("VIN");
                int year = rs.getInt("Year");
                String make = rs.getString("Make");
                String model = rs.getString("Model");
                String vehicleType = rs.getString("VehicleType");
                String color = rs.getString("Color");
                int odometer = rs.getInt("Odometer");
                double price = rs.getDouble("Price");
                int dealershipId = rs.getInt("DealershipID");

                if (dealershipId == dealershipID) {
                    Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price, dealershipId);
                    vehicles.add(vehicle);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error with the database: " + e.getMessage());
        }
        return vehicles;
    }

    /*select * from vehicles
    where VehicleType = 'SUV';*/
    public List<Vehicle> getByVehicleType(String vehicleType, int dealershipID) {
        List<Vehicle> vehicles = new ArrayList<>();
        String sqlQuery = "SELECT * FROM vehicles WHERE VehicleType = ?";
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass)) {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setString(1, vehicleType);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int vin = rs.getInt("VIN");
                int year = rs.getInt("Year");
                String make = rs.getString("Make");
                String model = rs.getString("Model");
                String vehicleType1 = rs.getString("VehicleType");
                String color = rs.getString("Color");
                int odometer = rs.getInt("Odometer");
                double price = rs.getDouble("Price");
                int dealershipId = rs.getInt("DealershipID");

                if (dealershipId == dealershipID) {
                    Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType1, color, odometer, price, dealershipId);
                    vehicles.add(vehicle);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error with the database: " + e.getMessage());
        }
        return vehicles;
    }

    public Optional<Vehicle> getVehicleById(int id) {
        String sqlQuery = "SELECT * FROM vehicles WHERE VIN = ?";
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass)) {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);

            ps.setInt(1, id); // you are replacing the first question mark on your query with that id value
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int vin = rs.getInt("VIN");
                int year = rs.getInt("Year");
                String make = rs.getString("Make");
                String model = rs.getString("Model");
                String vehicleType1 = rs.getString("VehicleType");
                String color = rs.getString("Color");
                int odometer = rs.getInt("Odometer");
                double price = rs.getDouble("Price");
                int dealershipId = rs.getInt("DealershipID");


                return Optional.of(new Vehicle(vin, year, make, model, vehicleType1, color, odometer, price, dealershipId));


            }
        } catch (SQLException e) {
            System.out.println("Error with the database: " + e.getMessage());
        }

        return Optional.empty();

    }

}
