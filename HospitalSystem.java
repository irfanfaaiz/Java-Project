import java.io.*;
import java.util.*;

public class HospitalSystem {
    static LinkedList<Appointment> appointments = new LinkedList<>();
    static HashMap<Integer, Patient> patients = new HashMap<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        loadFromFile();
        int choice = -1;
        while (choice != 0) {
            System.out.println("\n--- HOSPITAL APPOINTMENT SYSTEM ---");
            System.out.println("1. Register patient");
            System.out.println("2. Schedule appointment");
            System.out.println("3. Display appointments");
            System.out.println("4. Search patient");
            System.out.println("5. Sort by time");
            System.out.println("6. Sort by priority");
            System.out.println("7. Update appointment");
            System.out.println("8. Cancel appointment");
            System.out.println("9. Save records");
            System.out.println("0. Save and exit");
            System.out.print("Enter choice: ");
            
            try {
                choice = sc.nextInt();
                switch (choice) {
                    case 1: registerPatient(); break;
                    case 2: scheduleAppointment(); break;
                    case 3: displayAppointments(); break;
                    case 4: searchPatient(); break;
                    case 5: sortByTime(); break;
                    case 6: sortByPriority(); break;
                    case 7: updateAppointment(); break;
                    case 8: cancelAppointment(); break;
                    case 9: saveToFile(); break;
                    case 0: 
                        saveToFile();
                        System.out.println("Bye!");
                        break;
                    default: System.out.println("Wrong choice");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.next(); 
            }
        }
    }

    static void registerPatient() {
        System.out.print("Patient ID: ");
        int id = sc.nextInt();
        if (patients.containsKey(id)) {
            System.out.println("Patient ID already exists!");
            return;
        }
        System.out.print("Name (single word): ");
        String name = sc.next();
        System.out.print("Age: ");
        int age = sc.nextInt();
        System.out.print("Contact (10 digits): ");
        String contact = sc.next();
        
        if (age <= 0 || age > 120 || !contact.matches("\\d{10}")) {
            System.out.println("Invalid age or contact number");
            return;
        }
        patients.put(id, new Patient(id, name, age, contact));
        System.out.println("Patient registered");
    }

    static void scheduleAppointment() {
        System.out.print("Appointment ID: ");
        String aid = sc.next();
        if (findAppointment(aid) != null) {
            System.out.println("Appointment ID already exists!");
            return;
        }
        System.out.print("Patient ID: ");
        int pid = sc.nextInt();
        if (!patients.containsKey(pid)) {
            System.out.println("Register the patient first");
            return;
        }
        System.out.print("Date (YYYY-MM-DD): ");
        String date = sc.next();
        System.out.print("Time (HH:MM): ");
        String time = sc.next();
        System.out.print("Priority (1-Emergency, 2-High, 3-Normal): ");
        int priority = sc.nextInt();
        
        if (!date.matches("\\d{4}-\\d{2}-\\d{2}") ||
            !time.matches("([01]\\d|2[0-3]):[0-5]\\d") ||
            priority < 1 || priority > 3) {
            System.out.println("Invalid date, time or priority format");
            return;
        }
        appointments.add(new Appointment(aid, pid, date, time, priority));
        System.out.println("Appointment scheduled");
    }

    static Appointment findAppointment(String id) {
        Iterator<Appointment> it = appointments.iterator();
        while (it.hasNext()) {
            Appointment a = it.next();
            if (a.appointmentId.equalsIgnoreCase(id)) return a;
        }
        return null;
    }

    static void displayAppointments() {
        if (appointments.isEmpty()) {
            System.out.println("No appointments");
            return;
        }
        System.out.println("ID\tPatient\tDate\tTime\tPriority\tStatus");
        Iterator<Appointment> it = appointments.iterator();
        while (it.hasNext()) System.out.println(it.next());
    }

    static void searchPatient() {
        System.out.print("Patient ID: ");
        int id = sc.nextInt();
        Patient p = patients.get(id);
        if (p == null) System.out.println("Patient not found");
        else System.out.println("ID\tName\tAge\tContact\n" + p);
    }

    static void sortByTime() {
        Collections.sort(appointments, new AppointmentTimeComparator());
        displayAppointments();
    }

    static void sortByPriority() {
        Collections.sort(appointments, new PriorityComparator());
        displayAppointments();
    }

    static void updateAppointment() {
        System.out.print("Appointment ID: ");
        String id = sc.next();
        ListIterator<Appointment> it = appointments.listIterator();
        while (it.hasNext()) {
            Appointment a = it.next();
            if (a.appointmentId.equalsIgnoreCase(id)) {
                System.out.print("New status (Scheduled/Completed): ");
                String status = sc.next();
                System.out.print("Consultation notes (single word): ");
                String notes = sc.next();
                Appointment updated = new Appointment(a.appointmentId, a.patientId, a.date, a.time, a.priority);
                updated.status = status;
                updated.notes = notes;
                it.set(updated);
                System.out.println("Appointment updated");
                return;
            }
        }
        System.out.println("Appointment not found");
    }

    static void cancelAppointment() {
        System.out.print("Appointment ID: ");
        String id = sc.next();
        Iterator<Appointment> it = appointments.iterator();
        while (it.hasNext()) {
            Appointment a = it.next();
            if (a.appointmentId.equalsIgnoreCase(id)) {
                it.remove();
                System.out.println("Appointment cancelled");
                return;
            }
        }
        System.out.println("Appointment not found");
    }

    static void saveToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("hospital.dat"))) {
            out.writeObject(patients);
            out.writeObject(appointments);
            System.out.println("Saved " + patients.size() + " patients and " + appointments.size() + " appointments");
        } catch (IOException e) {
            System.out.println("Error while saving: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    static void loadFromFile() {
        File f = new File("hospital.dat");
        if (!f.exists()) return;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(f))) {
            patients = (HashMap<Integer, Patient>) in.readObject();
            appointments = (LinkedList<Appointment>) in.readObject();
            System.out.println("Loaded " + patients.size() + " patients and " + appointments.size() + " appointments");
        } catch (Exception e) {
            System.out.println("Error while loading: " + e.getMessage());
        }
    }
}
