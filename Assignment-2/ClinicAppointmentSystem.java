import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

class Patient {
    String patientId;
    String name;
    String contact;

    public Patient(String patientId, String name, String contact) {
        this.patientId = patientId;
        this.name = name;
        this.contact = contact;
    }
}

class Appointment {
    String appointmentId;
    String patientId;
    String doctorName;
    Date appointmentTime;

    public Appointment(String appointmentId, String patientId, String doctorName, Date appointmentTime) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.doctorName = doctorName;
        this.appointmentTime = appointmentTime;
    }
}

public class ClinicAppointmentSystem {
    static Map<String, Patient> patientMap = new HashMap<>();
    static Map<String, Appointment> appointmentMap = new HashMap<>();
    static int appointmentCount = 0;
    static int cancelCount = 0;
    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    // Register new patient
    public static void registerPatient(String id, String name, String contact) {
        if (patientMap.containsKey(id)) {
            System.out.println("Patient already registered.");
            return;
        }
        patientMap.put(id, new Patient(id, name, contact));
        System.out.println("Patient Registered Successfully!");
    }

    // Check if the doctor has an available slot at the requested time
    public static boolean isSlotAvailable(String doctorName, Date time) {
        for (Appointment a : appointmentMap.values()) {
            if (a.doctorName.equalsIgnoreCase(doctorName) && sdf.format(a.appointmentTime).equals(sdf.format(time))) {
                return false; // Slot is already booked
            }
        }
        return true;
    }

    // Book an appointment
    public static void bookAppointment(String patientId, String doctorName, Date time) {
        if (!patientMap.containsKey(patientId)) {
            System.out.println("Patient not found. Please register first.");
            return;
        }
        if (!isSlotAvailable(doctorName, time)) {
            System.out.println("Slot already booked with Doctor " + doctorName + " at " + sdf.format(time));
            return;
        }
        String appId = "APT" + (appointmentMap.size() + 1);
        Appointment appt = new Appointment(appId, patientId, doctorName, time);
        appointmentMap.put(appId, appt);
        appointmentCount++;
        System.out.println("Appointment Booked: " + appId + " at " + sdf.format(time));
    }

    // Cancel an appointment
    public static void cancelAppointment(String appointmentId) {
        if (!appointmentMap.containsKey(appointmentId)) {
            System.out.println("Appointment ID not found.");
            return;
        }
        appointmentMap.remove(appointmentId);
        cancelCount++;
        System.out.println("Appointment " + appointmentId + " canceled.");
    }

    // Search patient by name or contact keyword
    public static void searchPatient(String keyword) {
        for (Patient p : patientMap.values()) {
            if (p.name.contains(keyword) || p.contact.contains(keyword)) {
                System.out.println("Patient found: " + p.patientId + " | " + p.name + " | " + p.contact);
                return;
            }
        }
        System.out.println("No patient found with the provided keyword.");
    }

    // List all appointments
    public static void listAppointments() {
        for (Appointment a : appointmentMap.values()) {
            Patient p = patientMap.get(a.patientId);
            System.out.println(a.appointmentId + " | " + p.name + " | Doctor: " + a.doctorName + " | Time: " + sdf.format(a.appointmentTime));
        }
    }

    // Filter appointments by doctor
    public static void filterByDoctor(String doctorName) {
        boolean found = false;
        for (Appointment a : appointmentMap.values()) {
            if (a.doctorName.equalsIgnoreCase(doctorName)) {
                Patient p = patientMap.get(a.patientId);
                System.out.println(a.appointmentId + " | " + p.name + " | Doctor: " + a.doctorName + " | Time: " + sdf.format(a.appointmentTime));
                found = true;
            }
        }
        if (!found) {
            System.out.println("No appointments found for Doctor " + doctorName);
        }
    }

    // Show daily summary of appointments
    public static void dailySummary() {
        Map<String, Integer> doctorAppointments = new HashMap<>();
        for (Appointment a : appointmentMap.values()) {
            doctorAppointments.put(a.doctorName, doctorAppointments.getOrDefault(a.doctorName, 0) + 1);
        }

        System.out.println("Daily Summary of Appointments:");
        for (Map.Entry<String, Integer> entry : doctorAppointments.entrySet()) {
            System.out.println("Doctor: " + entry.getKey() + " | Appointments: " + entry.getValue());
        }
    }

    // Sort patients by name
    public static void sortByPatientName() {
        List<Patient> patientList = new ArrayList<>(patientMap.values());
        patientList.sort(Comparator.comparing(p -> p.name));
        System.out.println("Patients sorted by name:");
        for (Patient p : patientList) {
            System.out.println(p.patientId + " | " + p.name + " | " + p.contact);
        }
    }

    // View all appointments for a specific patient
    public static void viewPatientAppointments(String patientId) {
        if (!patientMap.containsKey(patientId)) {
            System.out.println("Patient not found.");
            return;
        }
        boolean found = false;
        for (Appointment a : appointmentMap.values()) {
            if (a.patientId.equals(patientId)) {
                System.out.println("Appointment: " + a.appointmentId + " | Doctor: " + a.doctorName + " | Time: " + sdf.format(a.appointmentTime));
                found = true;
            }
        }
        if (!found) {
            System.out.println("No appointments found for this patient.");
        }
    }

    // Check availability of appointment slots for a specific doctor on a given date and time
    public static void checkAppointmentSlotAvailability(String doctorName, String dateTime) {
        try {
            Date appointmentTime = sdf.parse(dateTime);
            if (isSlotAvailable(doctorName, appointmentTime)) {
                System.out.println("Slot is available with Dr. " + doctorName + " at " + dateTime);
            } else {
                System.out.println("Slot is already booked with Dr. " + doctorName + " at " + dateTime);
            }
        } catch (ParseException e) {
            System.out.println("Invalid date/time format. Please use the format yyyy-MM-dd HH:mm.");
        }
    }

    // Main method for interactive user interface
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- Clinic Appointment System ---");
            System.out.println("1. Register Patient");
            System.out.println("2. Book Appointment");
            System.out.println("3. Cancel Appointment");
            System.out.println("4. Search Patient");
            System.out.println("5. List All Appointments");
            System.out.println("6. Filter Appointments by Doctor");
            System.out.println("7. Daily Summary");
            System.out.println("8. Sort Patients by Name");
            System.out.println("9. View Patient's Appointments");
            System.out.println("10. Check Appointment Slot Availability");
            System.out.println("11. Exit");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Patient ID: ");
                    String pid = sc.nextLine();
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Contact: ");
                    String contact = sc.nextLine();
                    registerPatient(pid, name, contact);
                    break;
                case 2:
                    System.out.print("Patient ID: ");
                    String bookPid = sc.nextLine();
                    System.out.print("Doctor Name: ");
                    String doc = sc.nextLine();
                    System.out.print("Enter appointment datetime (yyyy-MM-dd HH:mm): ");
                    String dt = sc.nextLine();
                    try {
                        Date apptTime = sdf.parse(dt);
                        bookAppointment(bookPid, doc, apptTime);
                    } catch (ParseException e) {
                        System.out.println("Invalid date/time format.");
                    }
                    break;
                case 3:
                    System.out.print("Appointment ID: ");
                    String aid = sc.nextLine();
                    cancelAppointment(aid);
                    break;
                case 4:
                    System.out.print("Enter name/contact keyword: ");
                    String key = sc.nextLine();
                    searchPatient(key);
                    break;
                case 5:
                    listAppointments();
                    break;
                case 6:
                    System.out.print("Doctor Name: ");
                    String dname = sc.nextLine();
                    filterByDoctor(dname);
                    break;
                case 7:
                    dailySummary();
                    break;
                case 8:
                    sortByPatientName();
                    break;
                case 9:
                    System.out.print("Enter Patient ID: ");
                    String viewId = sc.nextLine();
                    viewPatientAppointments(viewId);
                    break;
                case 10:
                    System.out.print("Enter Doctor Name: ");
                    String doctor = sc.nextLine();
                    System.out.print("Enter appointment datetime (yyyy-MM-dd HH:mm): ");
                    String checkTime = sc.nextLine();
                    checkAppointmentSlotAvailability(doctor, checkTime);
                    break;
                case 11:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
        sc.close();
    }
}
