import java.util.Scanner;

// Custom Exception Class
class UnauthorizedAccessException extends Exception {
    public UnauthorizedAccessException(String message) {
        super(message);
    }
}

// Intrusion Detection System Class
class IntrusionDetection {
    private int invalidAttempts = 0;
    private final int threshold = 3;

    // Method to validate login
    public void validateLogin(String username, String password) throws UnauthorizedAccessException {
        // Simulated correct credentials
        String correctUsername = "admin";
        String correctPassword = "password123";

        if (username.equals(correctUsername) && password.equals(correctPassword)) {
            System.out.println("Login successful. Welcome " + username + "!");
            invalidAttempts = 0; // reset counter on success
            System.exit(0);
        } else {
            invalidAttempts++;
            System.out.println("Invalid login attempt #" + invalidAttempts);

            // Check if invalid attempts exceed threshold
            if (invalidAttempts >= threshold) {
                throw new UnauthorizedAccessException("Unauthorized access detected! Too many failed attempts.");
            }
        }
    }
}

// Main Class to run the program
public class CyberSecuritySystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        IntrusionDetection ids = new IntrusionDetection();

        // Allow continuous login attempts
        while (true) {
            try {
                // Get user input
                System.out.print("Enter username: ");
                String username = scanner.nextLine();

                System.out.print("Enter password: ");
                String password = scanner.nextLine();

                // Validate the login attempt
                ids.validateLogin(username, password);
            } catch (UnauthorizedAccessException e) {
                System.out.println(e.getMessage());
                break; // Exit after breach detection
            } finally {
                System.out.println("Attempt logged.\n");
            }
        }

        scanner.close();
    }
}
