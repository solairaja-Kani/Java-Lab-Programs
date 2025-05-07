// package Experiment6;
import java.util.*;

// TrafficSignal class (Aggregation: Independent operation)
class TrafficSignal {
    private String signalId;
    private String status; // Red, Yellow, Green

    public TrafficSignal(String signalId) {
        this.signalId = signalId;
        this.status = "Red"; // Default status
    }

    public void changeSignal() {
        String[] signals = {"Red", "Yellow", "Green"};
        this.status = signals[new Random().nextInt(signals.length)];
    }

    public String getStatus() {
        return status;
    }

    public String getSignalId() {
        return signalId;
    }
}

// Junction class (Composition: Contains multiple TrafficSignals)
class Junction {
    private String junctionId;
    private List<TrafficSignal> signals;

    public Junction(String junctionId, int numSignals) {
        this.junctionId = junctionId;
        this.signals = new ArrayList<>();
        for (int i = 1; i <= numSignals; i++) {
            this.signals.add(new TrafficSignal(junctionId + "-Signal" + i));
        }
    }

    public void updateSignals() {
        for (TrafficSignal signal : signals) {
            signal.changeSignal();
        }
    }

    public void displayTrafficSignalStatus() {
        System.out.println("Junction: " + junctionId);
        for (TrafficSignal signal : signals) {
            System.out.println("  Signal " + signal.getSignalId() + " -> " + signal.getStatus());
        }
    }
}

// City class (Contains multiple Junctions)
class City {
    private String cityName;
    private List<Junction> junctions;

    public City(String cityName) {
        this.cityName = cityName;
        this.junctions = new ArrayList<>();
    }

    public void addJunction(Junction junction) {
        junctions.add(junction);
    }

    public void updateCityTraffic() {
        for (Junction junction : junctions) {
            junction.updateSignals();
        }
    }

    public void displayCityTrafficStatus() {
        System.out.println("City: " + cityName);
        for (Junction junction : junctions) {
            junction.displayTrafficSignalStatus();
        }
    }
}

// Main class to run the system
public class SmartCityTrafficManagement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter city name: ");
        String cityName = scanner.nextLine();
        City city = new City(cityName);

        System.out.print("Enter number of junctions: ");
        int numJunctions = scanner.nextInt();

        for (int i = 1; i <= numJunctions; i++) {
            System.out.print("Enter name for Junction " + i + ": ");
            String junctionName = scanner.next();

            System.out.print("Enter number of signals for " + junctionName + ": ");
            int numSignals = scanner.nextInt();

            Junction junction = new Junction(junctionName, numSignals);
            city.addJunction(junction);
        }

        System.out.println("\nInitial Traffic Status:");
        city.displayCityTrafficStatus();

        // Simulating real-time traffic signal changes
        System.out.println("\nUpdating Traffic Signals...");
        city.updateCityTraffic();
        city.displayCityTrafficStatus();

        scanner.close();
    }
}
