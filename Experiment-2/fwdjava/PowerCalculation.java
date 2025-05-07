package Experiment2;
import java.util.*;
class Power
{
	public double calculatePower(int base,int exponent)
	{
		return Math.pow(base, exponent);
	}
	
	public double calculatePower(double base,double exponent)
	{
		return Math.pow(base, exponent);
	}
}

public class PowerCalculation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc=new Scanner(System.in);
		
		Power p=new Power();
		
		System.out.println("Power Calculation Using Method Overloading");
		System.out.println("==========================================\n");
		System.out.println("Power Calculation among Integer values");
		System.out.println("--------------------------------------\n");
		System.out.print("Enter the base value:");
		int base=sc.nextInt();
		
		System.out.print("Enter the exponent value:");
		int exponent=sc.nextInt();
		
		System.out.println(base+"^"+exponent+" = "+p.calculatePower(base, exponent));
		
		System.out.println();
		
		System.out.println("Power Calculation among Double values");
		System.out.println("-------------------------------------\n");
		System.out.print("Enter the base value:");
		double base1=sc.nextDouble();
		
		System.out.print("Enter the exponent value:");
		double exponent1=sc.nextDouble();
		
		System.out.println(base1+"^"+exponent1+" = "+String.format("%.2f",p.calculatePower(base1, exponent1)));
		
		
		sc.close();
		
	}

}



