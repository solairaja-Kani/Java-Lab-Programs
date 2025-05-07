import java.util.*;

// SmartDevice Class (Represents IoT-enabled devices)
class SmartDevice {
    private String name;
    private boolean isOn;

    public SmartDevice(String name) {
        this.name = name;
        this.isOn = false; // Default state is OFF
    }

    public void toggle() {
        isOn = !isOn;
        System.out.println(name + " is now " + (isOn ? "ON" : "OFF"));
    }

    public String getStatus() {
        return name + " - " + (isOn ? "ON" : "OFF");
    }

    public String getName() {
        return name;
    }
}

// Room Class (Contains multiple SmartDevices - Composition)
class Room {
    private String name;
    private List<SmartDevice> devices;

    public Room(String name) {
        this.name = name;
        this.devices = new ArrayList<>();
    }

    public void addDevice(String deviceName) {
        devices.add(new SmartDevice(deviceName));
    }

    public void toggleDevice(String deviceName) {
        for (SmartDevice device : devices) {
            if (device.getName().equalsIgnoreCase(deviceName)) {
                device.toggle();
                return;
            }
        }
        System.out.println("Device not found in " + name);
    }

    public void displayDevices() {
        System.out.println("Room: " + name);
        for (SmartDevice device : devices) {
            System.out.println("  " + device.getStatus());
        }
    }

    public String getName() {
        return name;
    }
}

// SmartHome Class (Has multiple Rooms - Aggregation)
class SmartHome {
    private List<Room> rooms;

    public SmartHome() {
        this.rooms = new ArrayList<>();
    }

    public void addRoom(String roomName) {
        rooms.add(new Room(roomName));
    }

    public Room getRoom(String roomName) {
        for (Room room : rooms) {
            if (room.getName().equalsIgnoreCase(roomName)) {
                return room;
            }
        }
        return null;
    }

    public void displayHomeStatus() {
        System.out.println("Smart Home Status:");
        for (Room room : rooms) {
            room.displayDevices();
        }
    }
}

public class SmartHomeSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SmartHome smartHome = new SmartHome();

        while (true) {
            System.out.println("\n1. Add Room\n2. Add Device\n3. Toggle Device\n4. Show Status\n5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter room name: ");
                    String roomName = scanner.nextLine();
                    smartHome.addRoom(roomName);
                    break;
                case 2:
                    System.out.print("Enter room name: ");
                    roomName = scanner.nextLine();
                    Room room = smartHome.getRoom(roomName);
                    if (room != null) {
                        System.out.print("Enter device name: ");
                        String deviceName = scanner.nextLine();
                        room.addDevice(deviceName);
                    } else {
                        System.out.println("Room not found!");
                    }
                    break;
                case 3:
                    System.out.print("Enter room name: ");
                    roomName = scanner.nextLine();
                    room = smartHome.getRoom(roomName);
                    if (room != null) {
                        System.out.print("Enter device name: ");
                        String deviceName = scanner.nextLine();
                        room.toggleDevice(deviceName);
                    } else {
                        System.out.println("Room not found!");
                    }
                    break;
                case 4:
                    smartHome.displayHomeStatus();
                    break;
                case 5:
                    System.out.println("Exiting Smart Home System.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
