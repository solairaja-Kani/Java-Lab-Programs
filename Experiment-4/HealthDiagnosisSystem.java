import java.util.*;

class HealthDiagnosis {
    private static final Map<String, Integer> symptomRisk = new HashMap<>();

    // Initialize some risk scores for symptoms
    static {
        symptomRisk.put("fever", 20);
        symptomRisk.put("cough", 15);
        symptomRisk.put("headache", 10);
        symptomRisk.put("fatigue", 25);
        symptomRisk.put("chest pain", 30);
        symptomRisk.put("shortness of breath", 35);
        symptomRisk.put("nausea", 15);
        symptomRisk.put("dizziness", 20);
    }

    public double analyzeSymptoms(String[] symptoms, int age, boolean hasChronicDisease, boolean hasFamilyHistory) {
        int riskScore = 0;
        
        for (String symptom : symptoms) {
            riskScore += symptomRisk.getOrDefault(symptom.toLowerCase(), 5); // Default low risk
        }

        // Adjust based on age
        if (age > 60) {
            riskScore += 20;
        } else if (age < 18) {
            riskScore += 10;
        }
        
        // Increase risk if patient has chronic disease
        if (hasChronicDisease) {
            riskScore += 25;
        }
        
        // Increase risk if there is a family history of disease
        if (hasFamilyHistory) {
            riskScore += 15;
        }

        // Normalize risk score to a probability percentage
        return Math.min(100, riskScore);
    }
}

public class HealthDiagnosisSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HealthDiagnosis diagnosis = new HealthDiagnosis();

        System.out.println("Enter your age:");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.println("Do you have a chronic disease? (yes/no):");
        boolean hasChronicDisease = scanner.nextLine().equalsIgnoreCase("yes");
        
        System.out.println("Do you have a family history of disease? (yes/no):");
        boolean hasFamilyHistory = scanner.nextLine().equalsIgnoreCase("yes");

        System.out.println("Enter your symptoms separated by commas:");
        String[] symptoms = scanner.nextLine().split(",");
        
        double probability = diagnosis.analyzeSymptoms(symptoms, age, hasChronicDisease, hasFamilyHistory);
        
        System.out.println("Computed Health Risk: " + probability + "% chance of disease.");
        scanner.close();
    }
}
