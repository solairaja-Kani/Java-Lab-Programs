import java.util.Random;

// Base Exception for all car-related issues
class CarException extends Exception {
    public CarException(String message) {
        super(message);
    }
}

// Specific Exceptions
class SensorFailureException extends CarException {
    public SensorFailureException(String message) {
        super(message);
    }
}

class GPSFailureException extends CarException {
    public GPSFailureException(String message) {
        super(message);
    }
}

class LowBatteryException extends CarException {
    public LowBatteryException(String message) {
        super(message);
    }
}

// AutonomousCar class with multiple safety checks
class AutonomousCar {
    private Random random = new Random();
    private int batteryLevel = 100;

    public void navigate() throws SensorFailureException, GPSFailureException, LowBatteryException {
        System.out.println("\nNavigating...");

        checkSensors();
        checkGPS();
        checkBattery();

        System.out.println("Navigation successful. Car is moving safely.");
    }

    private void checkSensors() throws SensorFailureException {
        if (random.nextInt(100) < 20) { // 20% chance of failure
            throw new SensorFailureException("Obstacle Sensor Failure Detected!");
        }
    }

    private void checkGPS() throws GPSFailureException {
        if (random.nextInt(100) < 10) { // 10% chance of failure
            throw new GPSFailureException("GPS Signal Lost!");
        }
    }

    private void checkBattery() throws LowBatteryException {
        batteryLevel -= random.nextInt(15); // Simulate battery drain
        if (batteryLevel < 20) {
            throw new LowBatteryException("Low Battery: " + batteryLevel + "% remaining.");
        }
        System.out.println("Battery Level: " + batteryLevel + "%");
    }
}

// Main simulation class
public class AutonomousCarSystem {

    public static void main(String[] args) {
        AutonomousCar car = new AutonomousCar();

        // Simulating continuous navigation attempts
        for (int i = 1; i <= 5; i++) {
            System.out.println("\n========= Journey Attempt #" + i + " =========");

            try {
                car.navigate();
            } catch (SensorFailureException e) {
                System.out.println("ERROR: " + e.getMessage());
                System.out.println("Initiating emergency braking and rerouting.");
            } catch (GPSFailureException e) {
                System.out.println("ERROR: " + e.getMessage());
                System.out.println("Switching to offline navigation mode.");
            } catch (LowBatteryException e) {
                System.out.println("ERROR: " + e.getMessage());
                System.out.println("Redirecting to nearest charging station.");
                break; // Stop further attempts on low battery
            } finally {
                System.out.println("Status report sent to control center.");
            }
        }

        System.out.println("\nSimulation Ended.");
    }
}
