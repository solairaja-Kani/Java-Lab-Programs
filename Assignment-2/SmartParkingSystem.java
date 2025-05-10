import java.util.*;

class Vehicle {
    String vehicleNumber;
    String ownerName;
    String contact;
    String vehicleType; // 2W or 4W
    long entryTime;

    public Vehicle(String vehicleNumber, String ownerName, String contact, String vehicleType) {
        this.vehicleNumber = vehicleNumber;
        this.ownerName = ownerName;
        this.contact = contact;
        this.vehicleType = vehicleType;
        this.entryTime = System.currentTimeMillis();
    }
}

class Slot {
    String slotId;
    String type; // 2W or 4W
    boolean isOccupied;

    public Slot(String slotId, String type) {
        this.slotId = slotId;
        this.type = type;
        this.isOccupied = false;
    }
}

public class SmartParkingSystem {
    static Map<String, Vehicle> vehicleMap = new HashMap<>();
    static Map<String, Slot> slotMap = new HashMap<>();
    static Map<String, String> parkedVehicles = new HashMap<>(); // vehicleNumber -> slotId
    static List<Long> entryTimes = new ArrayList<>();

    static int earnings2W = 0;
    static int earnings4W = 0;
    static int totalVehicles = 0;

    public static void setupSlots() {
        for (int i = 1; i <= 10; i++) {
            slotMap.put("2W-" + i, new Slot("2W-" + i, "2W"));
        }
        for (int i = 1; i <= 10; i++) {
            slotMap.put("4W-" + i, new Slot("4W-" + i, "4W"));
        }
    }

    public static void registerVehicle(String number, String owner, String contact, String type) {
        if (vehicleMap.containsKey(number)) {
            System.out.println("Vehicle already registered.");
            return;
        }
        Vehicle v = new Vehicle(number, owner, contact, type);
        vehicleMap.put(number, v);
        entryTimes.add(v.entryTime);
        assignSlot(v);
    }

    public static void assignSlot(Vehicle v) {
        for (Slot s : slotMap.values()) {
            if (!s.isOccupied && s.type.equals(v.vehicleType)) {
                s.isOccupied = true;
                parkedVehicles.put(v.vehicleNumber, s.slotId);
                System.out.println("Slot assigned: " + s.slotId);
                return;
            }
        }
        System.out.println("No available slot for " + v.vehicleType);
    }

    public static void releaseSlot(String vehicleNumber) {
        if (!parkedVehicles.containsKey(vehicleNumber)) {
            System.out.println("Vehicle not found in parking.");
            return;
        }
        Vehicle v = vehicleMap.get(vehicleNumber);
        String slotId = parkedVehicles.get(vehicleNumber);
        Slot s = slotMap.get(slotId);

        long durationMillis = System.currentTimeMillis() - v.entryTime;
        long hours = Math.max(1, durationMillis / (1000 * 60 * 60));
        int fee = 0;
        if (v.vehicleType.equals("2W")) {
            fee = (int) hours * 10;
            earnings2W += fee;
        } else {
            fee = (int) hours * 20;
            earnings4W += fee;
        }

        s.isOccupied = false;
        parkedVehicles.remove(vehicleNumber);
        vehicleMap.remove(vehicleNumber);
        totalVehicles++;

        System.out.println("Vehicle exited: " + vehicleNumber);
        System.out.println("Duration: " + hours + " hours, Fee: ₹" + fee);
    }

    public static void viewParkedVehicles() {
        System.out.println("Currently Parked Vehicles:");
        for (String vNum : parkedVehicles.keySet()) {
            Vehicle v = vehicleMap.get(vNum);
            System.out.println(v.vehicleNumber + " | " + v.ownerName + " | " + v.vehicleType + " | Slot: " + parkedVehicles.get(vNum) + " | Entry: " + new Date(v.entryTime));
        }
    }

    public static void viewSlots() {
        System.out.println("\nSlot Status:");
        for (Slot s : slotMap.values()) {
            System.out.println(s.slotId + " | Type: " + s.type + " | Occupied: " + s.isOccupied);
        }
    }

    public static void searchByVehicleNumber(String number) {
        if (vehicleMap.containsKey(number)) {
            Vehicle v = vehicleMap.get(number);
            String slotId = parkedVehicles.getOrDefault(number, "Not Assigned");
            System.out.println("Found: " + v.vehicleNumber + ", Owner: " + v.ownerName + ", Slot: " + slotId);
        } else {
            System.out.println("Vehicle not found.");
        }
    }

    public static void searchBySlotId(String slotId) {
        for (Map.Entry<String, String> entry : parkedVehicles.entrySet()) {
            if (entry.getValue().equals(slotId)) {
                Vehicle v = vehicleMap.get(entry.getKey());
                System.out.println("Slot " + slotId + " Occupied by: " + v.vehicleNumber + ", Owner: " + v.ownerName);
                return;
            }
        }
        System.out.println("Slot is free or invalid.");
    }

    public static void filterByVehicleType(String type) {
        for (Vehicle v : vehicleMap.values()) {
            if (v.vehicleType.equals(type)) {
                System.out.println(v.vehicleNumber + " | " + v.ownerName);
            }
        }
    }

    public static void filterByDuration(long minHours) {
        long now = System.currentTimeMillis();
        for (Vehicle v : vehicleMap.values()) {
            long duration = now - v.entryTime;
            if (duration >= minHours * 60 * 60 * 1000) {
                System.out.println(v.vehicleNumber + " parked for over " + minHours + " hours");
            }
        }
    }

    public static void searchByOwnerContact(String contact) {
        for (Vehicle v : vehicleMap.values()) {
            if (v.contact.equals(contact)) {
                System.out.println("Vehicle: " + v.vehicleNumber + ", Owner: " + v.ownerName);
            }
        }
    }

    public static void sortByEntryTime() {
        List<Vehicle> list = new ArrayList<>(vehicleMap.values());
        list.sort(Comparator.comparingLong(v -> v.entryTime));
        for (Vehicle v : list) {
            System.out.println(v.vehicleNumber + " | Entry: " + new Date(v.entryTime));
        }
    }

    public static void sortByOwnerName() {
        List<Vehicle> list = new ArrayList<>(vehicleMap.values());
        list.sort(Comparator.comparing(v -> v.ownerName));
        for (Vehicle v : list) {
            System.out.println(v.ownerName + " | " + v.vehicleNumber);
        }
    }

    public static void dailySummary() {
        System.out.println("\n--- Daily Summary ---");
        System.out.println("Total Vehicles Parked Today: " + totalVehicles);
        System.out.println("Earnings from 2W: ₹" + earnings2W);
        System.out.println("Earnings from 4W: ₹" + earnings4W);
        System.out.println("Total Earnings: ₹" + (earnings2W + earnings4W));

        Map<Integer, Integer> hourFrequency = new HashMap<>();
        for (long time : entryTimes) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date(time));
            int hour = cal.get(Calendar.HOUR_OF_DAY);
            hourFrequency.put(hour, hourFrequency.getOrDefault(hour, 0) + 1);
        }
        int peakHour = Collections.max(hourFrequency.entrySet(), Map.Entry.comparingByValue()).getKey();
        System.out.println("Peak Hour: " + peakHour + ":00");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        setupSlots();

        while (true) {
            System.out.println("\n1. Register Vehicle\n2. Release Slot\n3. View Parked Vehicles\n4. View Slot Status\n5. Search by Vehicle Number\n6. Search by Slot ID\n7. Filter by Vehicle Type\n8. Filter by Duration\n9. Search by Owner Contact\n10. Sort by Entry Time\n11. Sort by Owner Name\n12. Daily Summary\n13. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Vehicle Number: ");
                    String vNum = sc.nextLine();
                    System.out.print("Owner Name: ");
                    String owner = sc.nextLine();
                    System.out.print("Contact: ");
                    String contact = sc.nextLine();
                    System.out.print("Vehicle Type (2W/4W): ");
                    String type = sc.nextLine();
                    registerVehicle(vNum, owner, contact, type);
                    break;
                case 2:
                    System.out.print("Vehicle Number to Exit: ");
                    String exitNum = sc.nextLine();
                    releaseSlot(exitNum);
                    break;
                case 3:
                    viewParkedVehicles();
                    break;
                case 4:
                    viewSlots();
                    break;
                case 5:
                    System.out.print("Vehicle Number: ");
                    searchByVehicleNumber(sc.nextLine());
                    break;
                case 6:
                    System.out.print("Slot ID: ");
                    searchBySlotId(sc.nextLine());
                    break;
                case 7:
                    System.out.print("Vehicle Type (2W/4W): ");
                    filterByVehicleType(sc.nextLine());
                    break;
                case 8:
                    System.out.print("Minimum Hours: ");
                    filterByDuration(sc.nextLong());
                    break;
                case 9:
                    System.out.print("Owner Contact: ");
                    searchByOwnerContact(sc.nextLine());
                    break;
                case 10:
                    sortByEntryTime();
                    break;
                case 11:
                    sortByOwnerName();
                    break;
                case 12:
                    dailySummary();
                    break;
                case 13:
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }
}