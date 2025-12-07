package gui;

import org.example.dataManager.DealershipDAO;
import org.example.dataManager.VehiclesDAO;
import org.example.models.Dealership;
import org.example.models.Vehicle;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;

public class GUI {
    Scanner sc = new Scanner(System.in);
    String dbUrl;
    String dbUser;
    String dbPass;

    public GUI(String dbUrl, String dbUser, String dbPass) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPass = dbPass;
    }

    public void init() {

    }

    public void start() {
        DealershipDAO dealershipDAO = new DealershipDAO(dbUrl, dbUser, dbPass);
        System.out.println("Choose a Dealership to see its vehicles:");
        List<Dealership> dealerships = dealershipDAO.getAllDealerships();
        System.out.printf("%-5s|%-20s|%-30s|%-10s\n", "ID", "Name", "Address", "Phone");

        dealerships.stream().forEach(x -> System.out.printf("%-5d|%-20s|%-30s|%-10s\n", x.getDealershipID(), x.getName(), x.getAddress(), x.getPhone()));

        System.out.print("↪Enter Dealership ID:");
        int dealershipID = Integer.parseInt(sc.nextLine());
        Dealership selectedDealership = dealershipDAO.getDealershipById(dealershipID).orElse(null);
    }

    public void displayMenu(Dealership dealership) {
        VehiclesDAO vdao = new VehiclesDAO(dbUrl, dbUser, dbPass);
        System.out.printf("%-5d|%-20s|%-30s|%-10s\n", dealership.getDealershipID(), dealership.getName(), dealership.getAddress(), dealership.getPhone());
        var vehicles = vdao.getAllVehicles(dealership.getDealershipID());

        System.out.println("""
                1. View Vehicles by Price\
                
                2. View Vehicles by Make and Model\
                
                3. View Vehicles by Year\
                
                4. View Vehicles by Color\
                
                5. View Vehicles by Mileage\
                
                6. View Vehicles by Vehicle Type\
                
                7. View All Vehicles\
                
                0. Exit""");
        System.out.print("↪ Enter your choice: ");
        int choice = Integer.parseInt(sc.nextLine());

        switch (choice){
            case 1:
                System.out.print("↪ Enter maximum price: ");
                double maxPrice = Double.parseDouble(sc.nextLine());
                System.out.print("↪ Enter minimum price: ");
                double minPrice = Double.parseDouble(sc.nextLine());
                var vehiclesByPrice = vdao.getByPriceRange(minPrice, maxPrice, dealership.getDealershipID());
                //print it out
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:

                break;
            case 0:
                System.out.println("Exiting...");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

}
