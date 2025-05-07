
import java.util.*;

public class InsuranceCalculator {
	
	static int basePremium = 500;
	static double  rawPremium,finalPremium,taxAmt;
	static float ageFactor,medicalHistoryFactor,vehicleTypeFactor,coveragePlanFactor;
	static float taxRate = 0.18f;	
	
	public static double calculatePremium(int age, boolean medicalHistory, String vehicleType, String coveragePlan)
	{
		
		// finding the age factor
		if(age<25)
			ageFactor=1.2f;
		else if(age>=25 && age<40)
			ageFactor = 1.5f;
		else if(age>=40 && age<60)
			ageFactor = 2.0f;
		else
			ageFactor = 2.5f;
		
		// finding the medical history factor
		if(medicalHistory)
				medicalHistoryFactor = 1.5f;
		else
				medicalHistoryFactor = 1.0f;
		
		// Vehicle Type Factor
		switch(vehicleType.toLowerCase())
		{
			case "sedan":
				vehicleTypeFactor=1.2f;
				break;
			
			case "suv":
				vehicleTypeFactor=1.5f;
				break;
				
			case "sports car":
				vehicleTypeFactor=2.0f;
				break;	
			
			default:
				vehicleTypeFactor=1.0f;
				break;
		}
		
		// coverage plan factor
		switch(coveragePlan.toLowerCase())
		{
			case "basic":
				coveragePlanFactor=1.0f;
				break;
				
			case "standard":
				coveragePlanFactor=1.3f;
				break;
				
			case "premium":
				coveragePlanFactor=1.7f;
				break;
			
			default:
				break;			
		}
		
		// Raw premium calculation
		rawPremium = basePremium * ageFactor * medicalHistoryFactor * vehicleTypeFactor * coveragePlanFactor;
		
		// tax amount calculation
		taxAmt = rawPremium * taxRate;
		
		// final premium calculation
		finalPremium = rawPremium +taxAmt;
		
		return finalPremium;
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("```Health Insurance Premium Calculator```");
		System.out.println("-----------------------------------------");
		
		Scanner sc = new Scanner(System.in);
		
		String name,vehicleType,coveragePlan;
		int age;
		boolean medicalHistory;
		
		System.out.print("Tell me your name : ");
		name = sc.nextLine();
		
		System.out.print("Tell me your age : ");
		age = sc.nextInt();
		sc.nextLine();
		
		System.out.print("Choose your Vehicle Type (Sedan/SUV/Sports car/Others) : ");
		vehicleType = sc.nextLine();
		
//		if(vehicleType.toLowerCase().equals("others")))
//			vehicleType="";
		System.out.print("Choose your Coverage Plan (Basic/Standard/Premium) : ");
		coveragePlan = sc.nextLine();
		
		System.out.print("Did you have/had any Medical History (Yes/No) ? :  ");
		String medHis = sc.next();
		
		if(medHis.toLowerCase().equals("yes"))
			medicalHistory = true;
		else
			medicalHistory = false;
		
		System.out.println("==================================================");
		
		System.out.println("Hi "+name+" , Your Health insurance premium amount is Rs. "+String.format("%.2f",calculatePremium(age,medicalHistory,vehicleType,coveragePlan)));
		
		
		
		

	}

}
