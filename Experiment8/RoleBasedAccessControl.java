
package Experiment8;
import java.util.*;

class User
{
	public String username;
	
	User(String name)
	{
		username = name;
	}
	
	public void accessSystem()
	{
		System.out.println("Access Denied: Insufficient permission");
	}
}


class Admin extends User
{
	
	
	Admin(String name)
	{
		super(name);		
	}
	
	@Override
	public void accessSystem()
	{
		System.out.println(username+" : Admin access -> Access to everything");
	}
}

class Manager extends User
{
	
	
	Manager(String name)
	{
		super(name);		
	}
	
	@Override
	public void accessSystem()
	{
		System.out.println(username+" : Manager access -> Access to management resources granted");
	}
}

class Employee extends User
{
	
	
	Employee(String name)
	{
		super(name);		
	}
	
	@Override
	public void accessSystem()
	{
		System.out.println(username+" : Employee access-> Access to general resources granted");
	}
}

public class RoleBasedAccessControl {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String role;
		
		Scanner sc = new Scanner(System.in);
		
		String name;
		User u;
		
		System.out.print("Enter the name : ");
		name = sc.nextLine();
		
		System.out.println("Please select the role from the following : ");
		System.out.println("1. Admin\n2. Manager\n3. Employee\n4. Other\n");
		System.out.print("Provide your role : ");
		role = sc.nextLine();
		
		if(role.equalsIgnoreCase("admin"))
			u = new Admin(name);
		else if(role.equalsIgnoreCase("manager"))
			u = new Manager(name);
		else if(role.equalsIgnoreCase("employee"))
			u = new Employee(name);
		else
			u = new User(name);
		u.accessSystem();

	}

}
