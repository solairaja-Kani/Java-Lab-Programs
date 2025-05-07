package Experiment2;

import java.util.Scanner;

class Employee
{
	String name,designation,id;
	int salary,age;
	Employee()
	{	
		this.name="Solairaja";
		this.salary=30000;
		this.id="EMP1001";
		this.designation="Team Leader";
		this.age=22;
		
		this.display();
	}
	
	Employee(String name,String id, int salary)
	{
		this.name=name;
		this.id=id;
		this.salary=salary;
		
		this.display();
	}
	Employee(String name,int salary,String id, int age, String designation)
	{
		this.name=name;
		this.salary=salary;
		this.id=id;
		this.age=age;
		this.designation=designation;
		this.display();
	}

	public void display()
	{
		System.out.println("Id: "+this.id);
		System.out.println("Name: "+this.name);
		System.out.println("Age: "+this.age);
		System.out.println("Designation: "+this.designation);
		System.out.println("Salary: "+this.salary);
		System.out.println();
	}
}

public class EmployeeConstructor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc=new Scanner(System.in);
		
		String name,id,desgn;
		int salary,age;
		
		System.out.println("Constructor Overloading");
		System.out.println("=======================");
		
		System.out.println("Default Constructor");
		Employee e=new Employee();
		
		System.out.println("---------------------------");
		
		System.out.println("Parameterized Constructor (Three arguments)");
		System.out.print("Enter the name of the employee:");
		name=sc.nextLine();
		System.out.print("Enter ID of the employee:");
		id=sc.nextLine();
		System.out.print("Enter salary of the employee:");
		salary=sc.nextInt();
		
		sc.nextLine();		
		System.out.println("---------------------------");
		
		
		Employee e1=new Employee(name,id,salary);
		
		System.out.println("Parameterized Constructor (Five arguments)");
		System.out.println("Enter the name of the employee:");
		name=sc.nextLine();
		System.out.print("Enter id of the employee:");
		id=sc.nextLine();
		System.out.println("Enter salary of the employee:");
		salary=sc.nextInt();
		System.out.println("Enter the age of the employee:");
		age=sc.nextInt();
		sc.nextLine();
		System.out.println("Enter the Designation of the employee:");
		desgn=sc.nextLine();
		
		Employee e2=new Employee(name,salary,id,age,desgn);
		
		sc.close();
		
		
	}

}




