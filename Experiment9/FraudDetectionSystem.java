import java.util.Scanner;
import java.util.Random;

// Abstract class
abstract class FraudDetection {
    public abstract String analyzeTransaction(double amount, String location);
}

// Credit card fraud subclass
class CreditCardFraud extends FraudDetection {
    @Override
    public String analyzeTransaction(double amount, String location) {
        double score = simulateFraudScore(amount, location);
        StringBuilder result = new StringBuilder();
        result.append("\n[Credit Card] Transaction of Rs.").append(amount)
              .append(" at ").append(location).append("\n");
        result.append(String.format("AI Fraud Probability: %.2f%%\n", score * 100));
        if (score > 0.7) {
            result.append("ALERT: Credit Card Fraud Detected!\n");
        } else {
            result.append("Transaction is safe.\n");
        }
        return result.toString();
    }

    private double simulateFraudScore(double amount, String location) {
        Random rand = new Random();
        return (amount > 1000 ? 0.6 : 0.2) + rand.nextDouble() * 0.4;
    }
}

// Bank transfer fraud subclass
class BankTransferFraud extends FraudDetection {
    @Override
    public String analyzeTransaction(double amount, String location) {
        double score = simulateFraudScore(amount, location);
        StringBuilder result = new StringBuilder();
        result.append("\n[Bank Transfer] Transaction of Rs.").append(amount)
              .append(" at ").append(location).append("\n");
        result.append(String.format("AI Fraud Probability: %.2f%%\n", score * 100));
        if (score > 0.6) {
            result.append("ALERT: Bank Transfer Fraud Detected!\n");
        } else {
            result.append("Transaction is safe.\n");
        }
        return result.toString();
    }

    private double simulateFraudScore(double amount, String location) {
        Random rand = new Random();
        return (location.equalsIgnoreCase("international") ? 0.5 : 0.1) + rand.nextDouble() * 0.5;
    }
}

// Main class
public class FraudDetectionSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean run = true;

        System.out.println("=== AI-Based Fraud Detection System ===");

        while (run) {
            System.out.println("\nChoose Transaction Type:");
            System.out.println("1. Credit Card");
            System.out.println("2. Bank Transfer");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            if (choice == 3) {
                System.out.println("Exiting Fraud Detection System. Stay Safe!");
                break;
            }

            System.out.print("Enter transaction amount: Rs.");
            double amount = sc.nextDouble();
            sc.nextLine(); // consume newline

            System.out.print("Enter transaction location: ");
            String location = sc.nextLine();

            FraudDetection detector = null;

            if (choice == 1) {
                detector = new CreditCardFraud();
            } else if (choice == 2) {
                detector = new BankTransferFraud();
            } else {
                System.out.println("Invalid choice. Please try again.");
                continue;
            }

            // Analyze transaction and display result
            String result = detector.analyzeTransaction(amount, location);
            System.out.println(result);
        }

        sc.close();
    }
}
