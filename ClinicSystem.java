import java.util.*;
import java.io.*;

// Simple classes (CO-4)
class Patient {
    int id;
    String name;
    Patient(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

class Doctor {
    int id;
    String name;
    Doctor(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

public class ClinicSystem {

    // CO-1 & CO-3: Arrays + basic data
    static int[] patientIds = {101, 102, 103};
    static String[] patientNames = {"Rahul", "Priya", "Amit"};
    static int[] doctorIds = {10, 20};
    static String[] doctorNames = {"Dr. Rao", "Dr. Mehta"};

    // CO-6: Collections
    static List<String> prescriptions = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice = 0;

        // CO-2: Menu with while + switch
        while (choice != 5) {
            System.out.println("\n=== Clinic Appointment System ===");
            System.out.println("1. Book Appointment");
            System.out.println("2. Add Prescription");
            System.out.println("3. View All Prescriptions");
            System.out.println("4. Search Patient");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    bookAppointment(sc);
                    break;
                case 2:
                    addPrescription(sc);
                    break;
                case 3:
                    viewPrescriptions();
                    break;
                case 4:
                    searchPatient(sc);
                    break;
                case 5:
                    System.out.println("Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
        sc.close();
    }

    // CO-1 + CO-2 + CO-3: Validation + method + array search
    static void bookAppointment(Scanner sc) {
        System.out.print("Enter Patient ID: ");
        int pid = sc.nextInt();
        System.out.print("Enter Doctor ID: ");
        int did = sc.nextInt();

        boolean valid = pid > 0 && did > 0;   // CO-1

        if (valid && exists(patientIds, pid) && exists(doctorIds, did)) {
            System.out.println("Appointment booked successfully!");
        } else {
            System.out.println("Invalid Patient or Doctor ID!");
        }
    }

    // CO-3: Reusable method with array
    static boolean exists(int[] arr, int id) {
        for (int x : arr) {
            if (x == id) return true;
        }
        return false;
    }

    // CO-4 + CO-5: Object + StringBuilder + File
    static void addPrescription(Scanner sc) {
        try {
            System.out.print("Enter Patient Name: ");
            sc.nextLine(); // clear buffer
            String name = sc.nextLine();
            System.out.print("Enter Medicine: ");
            String med = sc.nextLine();

            // CO-5: StringBuilder
            StringBuilder sb = new StringBuilder();
            sb.append("Patient: ").append(name)
              .append(" | Medicine: ").append(med);

            String record = sb.toString();
            prescriptions.add(record);          // CO-6
            System.out.println(record);

            // Save to file (CO-5)
            FileWriter fw = new FileWriter("prescriptions.txt", true);
            fw.write(record + "\n");
            fw.close();
            System.out.println("Prescription saved to file!");

        } catch (Exception e) {                 // CO-4
            System.out.println("Error: " + e.getMessage());
        }
    }

    // CO-6: Collections + Stream
    static void viewPrescriptions() {
        if (prescriptions.isEmpty()) {
            System.out.println("No prescriptions yet.");
            return;
        }
        System.out.println("\n--- All Prescriptions ---");
        prescriptions.stream()
                     .forEach(System.out::println);
    }

    // CO-3: Search using method
    static void searchPatient(Scanner sc) {
        System.out.print("Enter Patient ID to search: ");
        int id = sc.nextInt();

        if (exists(patientIds, id)) {
            for (int i = 0; i < patientIds.length; i++) {
                if (patientIds[i] == id) {
                    System.out.println("Patient found: " + patientNames[i]);
                    break;
                }
            }
        } else {
            System.out.println("Patient not found!");
        }
    }
}