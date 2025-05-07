package Experiment2;
import java.util.*;

public class VehicleConstructorOverloading {

	 String brand;
	    String model;
	    String licenseNumber;
	    String ownerName;
	    int registrationYear;

	    // Constructor 1: Only brand and model (Basic Registration)
	    VehicleConstructorOverloading(String brand, String model) {
	        this.brand = brand;
	        this.model = model;
	        this.licenseNumber = "Not Assigned";
	        this.ownerName = "Unknown";
	        this.registrationYear = 0;
	    }

	    // Constructor 2: Brand, model, and license number (Mid-Level Registration)
	    VehicleConstructorOverloading(String brand, String model, String licenseNumber) {
	        this.brand = brand;
	        this.model = model;
	        this.licenseNumber = licenseNumber;
	        this.ownerName = "Unknown";
	        this.registrationYear = 0;
	    }

	    // Constructor 3: Brand, model, license number, and owner name (Full Registration)
	    VehicleConstructorOverloading(String brand, String model, String licenseNumber, String ownerName, int registrationYear) {
	        this.brand = brand;
	        this.model = model;
	        this.licenseNumber = licenseNumber;
	        this.ownerName = ownerName;
	        this.registrationYear = registrationYear;
	    }

	    // Method to display vehicle details
	    void displayDetails() {
	        System.out.println("\n Vehicle Registration Details:");
	        System.out.println("Brand: " + brand);
	        System.out.println("Model: " + model);
	        System.out.println("License Number: " + licenseNumber);
	        System.out.println("Owner Name: " + ownerName);
	        System.out.println("Registration Year: " + (registrationYear > 0 ? registrationYear : "Not Registered"));
	    }
	    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

        System.out.print("Enter Vehicle Brand: ");
        String brand = sc.nextLine();

        System.out.print("Enter Vehicle Model: ");
        String model = sc.nextLine();

        System.out.print("Do you have a license number? (yes/no): ");
        String licenseInput = sc.nextLine();

        VehicleConstructorOverloading vehicle;

        if (licenseInput.equalsIgnoreCase("yes")) {
            System.out.print("Enter License Number: ");
            String licenseNumber = sc.nextLine();

            System.out.print("Do you want to enter owner details? (yes/no): ");
            String ownerInput = sc.nextLine();

            if (ownerInput.equalsIgnoreCase("yes")) {
                System.out.print("Enter Owner Name: ");
                String ownerName = sc.nextLine();
                
                System.out.print("Enter Registration Year: ");
                int registrationYear = sc.nextInt();

                vehicle = new VehicleConstructorOverloading(brand, model, licenseNumber, ownerName, registrationYear); 
            } else {
                vehicle = new VehicleConstructorOverloading(brand, model, licenseNumber); 
            }
        } else {
            vehicle = new VehicleConstructorOverloading(brand, model); 
        }

        vehicle.displayDetails();
        sc.close();
	}

}



