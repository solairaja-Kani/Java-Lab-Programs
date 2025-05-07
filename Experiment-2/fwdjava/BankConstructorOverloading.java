package Experiment2;
import java.util.*;

class Account {
    private String holderName;
    private String accountID;
    private double balance;

    // Constructor 1: Default account
    public Account() {
        this("Unnamed", "000000", 0.0);
    }

    // Constructor 2: Account with name and number
    public Account(String holderName, String accountID) {
        this(holderName, accountID, 500.0); 
    }

    // Constructor 3: Full account details
    public Account(String holderName, String accountID, double balance) {
        this.holderName = holderName;
        this.accountID = accountID;
        this.balance = balance;
    }

    // Deposit method
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: Rs. " + amount);
        } else {
            System.out.println("Invalid deposit amount!");
        }
    }

    // Withdraw method
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: Rs. " + amount);
        } else {
            System.out.println("Insufficient balance or invalid amount!");
        }
    }

    // Display account details
    public void displayInfo() {
        System.out.println("\nAccount Holder: " + holderName);
        System.out.println("Account Number: " + accountID);
        System.out.println("Balance: Rs. " + balance);
    }
}


public class BankConstructorOverloading {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner scanner = new Scanner(System.in);
        Account account = null;

        System.out.println("Welcome to the Bank System");
        System.out.println("Choose an option to create an account:");
        System.out.println("1. Default Account");
        System.out.println("2. Enter Name & Account Number");
        System.out.println("3. Enter Full Details (Name, Account Number, Balance)");
        System.out.print("Enter your choice (1/2/3): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                account = new Account();
                break;

            case 2:
                System.out.print("Enter Account Holder Name: ");
                String name2 = scanner.nextLine();
                System.out.print("Enter Account Number: ");
                String accNum2 = scanner.nextLine();
                account = new Account(name2, accNum2);
                break;

            case 3:
                System.out.print("Enter Account Holder Name: ");
                String name3 = scanner.nextLine();
                System.out.print("Enter Account Number: ");
                String accNum3 = scanner.nextLine();
                System.out.print("Enter Initial Balance: ");
                double balance3 = scanner.nextDouble();
                account = new Account(name3, accNum3, balance3);
                break;

            default:
                System.out.println("Invalid choice! Creating a default account.");
                account = new Account();
                break;
        }

        // Performing transactions
        while (true) {
            System.out.println("\nChoose an operation:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Show Account Details");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    break;

                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;

                case 3:
                    account.displayInfo();
                    break;

                case 4:
                    System.out.println("Exiting the system. Thank you!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option! Try again.");
            }
        }

	}

}


