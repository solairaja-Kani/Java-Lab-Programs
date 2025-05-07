import java.io.*;
import java.util.*;

// DigitalCertificate class implementing Serializable
class DigitalCertificate implements Serializable {
    private static final long serialVersionUID = 1L;

    private String holderName;
    private String certificateID;
    private String expiryDate;

    public DigitalCertificate(String holderName, String certificateID, String expiryDate) {
        this.holderName = holderName;
        this.certificateID = certificateID;
        this.expiryDate = expiryDate;
    }

    public String getHolderName() {
        return holderName;
    }

    public String getCertificateID() {
        return certificateID;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void displayCertificate() {
        System.out.println("Holder Name   : " + holderName);
        System.out.println("Certificate ID: " + certificateID);
        System.out.println("Expiry Date   : " + expiryDate);
    }
}

public class DigitalCertificateManager {
    private static final String FILE_NAME = "certificates.dat";

    // Method to save a certificate by updating the list
    public static void saveCertificate(DigitalCertificate cert) {
        List<DigitalCertificate> certList = new ArrayList<>();

        // If file exists, load existing certificates first
        File file = new File(FILE_NAME);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                certList = (List<DigitalCertificate>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                certList = new ArrayList<>();
            }
        }

        // Add new cert and write back entire list
        certList.add(cert);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(certList);
            System.out.println("\nCertificate saved successfully!\n");
        } catch (IOException e) {
            System.out.println("Error saving certificate.");
            e.printStackTrace();
        }
    }

    // Method to load and display all certificates
    public static void loadCertificates() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("\nNo certificates found. Please issue some certificates first.\n");
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            List<DigitalCertificate> certList = (List<DigitalCertificate>) ois.readObject();
            if (certList.isEmpty()) {
                System.out.println("\nNo certificates available.\n");
                return;
            }

            int count = 0;
            for (DigitalCertificate cert : certList) {
                System.out.println("\n----- Certificate " + (++count) + " -----");
                cert.displayCertificate();
                System.out.println("------------------------------");
            }
            System.out.println("\nAll certificates loaded successfully.\n");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading certificates.");
            e.printStackTrace();
        }
    }

    // Main menu
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("=======================================");
            System.out.println("      Digital Certificate System     ");
            System.out.println("=======================================");
            System.out.println("1. Issue New Certificate");
            System.out.println("2. View All Certificates");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            while (!scanner.hasNextInt()) {
                System.out.print("Please enter a valid number (1-3): ");
                scanner.next();
            }
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Holder Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Certificate ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter Expiry Date (YYYY-MM-DD): ");
                    String expiry = scanner.nextLine();
                    DigitalCertificate cert = new DigitalCertificate(name, id, expiry);
                    saveCertificate(cert);
                    break;

                case 2:
                    loadCertificates();
                    break;

                case 3:
                    System.out.println("\nExiting system. Goodbye!\n");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.\n");
            }

        } while (choice != 3);

        scanner.close();
    }
}
