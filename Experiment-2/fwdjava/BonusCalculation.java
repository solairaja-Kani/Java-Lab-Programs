package Experiment2;

import java.util.*;

class BonusCalculator
{
	int calculation(String role)
	{
		switch(role.toLowerCase())
		{
			case "manager":
				return 5000;
			case "team leader":
				return 4000;
			case "developer":
				return 3000;
			case "tester":
				return 2000;
			default:
				return 1500;
		}
	}
	
	int calculation(String role, String dept)
	{
		int role_salary = this.calculation(role);
		
		if(dept.toLowerCase().equals("it"))
			role_salary+=3000;
		else if(dept.toLowerCase().equals("hr"))
			role_salary+=2000;
		else
			role_salary+=500;
		
		return role_salary;
	}
	
	int calculation(String role, String dept, int exp)
	{
		int dept_salary =  this.calculation(role,dept);
		
		if(exp>10)
			dept_salary+=5000;
		else if(exp>5)
			dept_salary+=3000;
		else if(exp>2)
			dept_salary+=2000;
		else
			dept_salary+=500;
		
		return dept_salary;
	}
	
	int calculation(String role, String dept, int exp, int score)
	{
		int final_salary = this.calculation(role,dept,exp);
		
		if(score>8)
			final_salary+=5000;
		else if(score>5)
			final_salary+=3000;
		else if(score>2)
			final_salary+=2000;
		else 
			final_salary+=500;
		
		return final_salary;
	}
}

public class BonusCalculation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		
		BonusCalculator b = new BonusCalculator();
		
		String role,dept;
		int exp,score;
		
		System.out.println("Bonus Calculation using Method Overloading");
		System.out.println("==========================================");
		
		System.out.print("Enter the employee role(Manager, Team leader, Developer, Tester, Others..) :");
		role = sc.next();
		
		System.out.print("Enter the employee department (IT, HR, Other..) : ");
		dept = sc.next();
		
		System.out.print("Enter the employee experience : ");
		exp = sc.nextInt();
		
		System.out.print("Enter the employee performance score (out of 10) : ");
		score = sc.nextInt();
		
		System.out.println("Bonus based on the Role : "+b.calculation(role));
		System.out.println("Bonus based on the Role, Department : "+b.calculation(role,dept));
		System.out.println("Bonus based on the role, Department, Experience : "+b.calculation(role,dept,exp));
		System.out.println("Bonus based on the role, Department, Experience, Score : "+b.calculation(role,dept,exp,score));

	}

}
