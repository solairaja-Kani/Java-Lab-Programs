import java.io.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// CryptoTransaction class implementing Serializable
class CryptoTransaction implements Serializable {
    private String walletID;
    private double transactionAmount;
    private String timestamp;

    public CryptoTransaction(String walletID, double transactionAmount) {
        this.walletID = walletID;
        this.transactionAmount = transactionAmount;
        this.timestamp = getCurrentTimestamp();
    }

    private String getCurrentTimestamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.now().format(formatter);
    }

    public String toString() {
        return "Wallet ID: " + walletID + ", Amount: " + transactionAmount + ", Timestamp: " + timestamp;
    }
}

public class CryptoLedgerList {

    static final String FILE_NAME = "transactions_list.dat";

    // Method to add a transaction to the file
    public static void addTransaction(CryptoTransaction transaction) {
        ArrayList<CryptoTransaction> transactions = readTransactions();
        transactions.add(transaction);
        saveTransactions(transactions);
        System.out.println("Transaction added successfully.\n");
    }

    // Method to save the transaction list to file
    public static void saveTransactions(ArrayList<CryptoTransaction> transactions) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(transactions);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to read all transactions from file
    public static ArrayList<CryptoTransaction> readTransactions() {
        ArrayList<CryptoTransaction> transactions = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            transactions = (ArrayList<CryptoTransaction>) ois.readObject();
        } catch (FileNotFoundException e) {
            // Ignore if file doesn't exist yet
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    // Method to display all transactions
    public static void displayTransactions() {
        ArrayList<CryptoTransaction> transactions = readTransactions();
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.\n");
        } else {
            for (CryptoTransaction t : transactions) {
                System.out.println(t);
            }
            System.out.println();
        }
    }

    // Main method with a menu
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("=== Crypto Transaction Ledger ===");
            System.out.println("1. Add New Transaction");
            System.out.println("2. View All Transactions");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Wallet ID: ");
                    String walletID = sc.nextLine();
                    System.out.print("Enter Transaction Amount: ");
                    double amount = sc.nextDouble();

                    CryptoTransaction transaction = new CryptoTransaction(walletID, amount);
                    addTransaction(transaction);
                    break;

                case 2:
                    displayTransactions();
                    break;

                case 3:
                    System.out.println("Exiting program.");
                    break;

                default:
                    System.out.println("Invalid choice! Try again.");
            }

        } while (choice != 3);

        sc.close();
    }
}
