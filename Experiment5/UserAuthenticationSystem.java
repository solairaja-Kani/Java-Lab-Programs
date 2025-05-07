// package Experiment5;
import java.util.*;
class UserAuthentication
{
	private String username,password;
	private String email,otp;
	boolean isBioMetricEnabled;
	
	public UserAuthentication(String username,String pwd)
	{
		this.username = username;
		this.password = pwd;
		System.out.println("User registered with Username and Password..");
	}
	
	public UserAuthentication(String email)
	{
		this.email=email;
		this.otp=generateOTP();
		System.out.println("User registered with Email "+this.email+" and OTP : "+this.getOTP());
	}
	
	public UserAuthentication(boolean isBiometricEnabled)
	{
		this.isBioMetricEnabled=isBiometricEnabled;
		System.out.println("User registered with their Biometric");
	}	
	
	public String generateOTP()
	{
		Random rand = new Random();
		return String.format("%06d", rand.nextInt(1000000));
	}
	
	public String getOTP()
	{
		return this.otp;
	}
	
	public boolean login(String uname, String pwd)
	{
		if(this.username!=null && this.username.equals(uname) && this.password.equals(pwd))
		{
			System.out.println("Login Successful: Valid Username & Password");
			return true;
		}
		System.out.println("Login failed: Invalid Username / Password");
		return false;
	}
	
	public boolean login(String email, int otp)
	{
		if(this.email!=null && this.otp.equals(String.valueOf(otp)))
		{
			System.out.println("Login Successful: Valid Email & OTP");
			return true;
		}
		System.out.println("Login failed: Invalid Email / OTP");
		return false;
	}
	
	public boolean login()
	{
		if(this.isBioMetricEnabled)
		{
			System.out.println("Login Successful: valid biometric");
			return true;
		}
		System.out.println("Login failed: Invalid biometric");
		return false;
	}
	
	
}

public class UserAuthenticationSystem {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		UserAuthentication ua ;
		
		Scanner sc = new Scanner(System.in);
		String uname,pwd,email;
		
		System.out.println("----User Authentication System----");
		System.out.println("==================================");
		
		System.out.println("1. Login using Username & Password\n2. Login using Email & OTP\n3. Login using Biometric");
		System.out.println("Enter your choice : ");
		
		int choice = sc.nextInt();
		sc.nextLine();
		
		switch(choice)
		{
			case 1:
				ua = new UserAuthentication("solai@123","12345");
				System.out.println("Enter Username : ");
				uname = sc.nextLine();
				System.out.println("Enter Password : ");
				pwd = sc.nextLine();
				ua.login(uname,pwd);
				break;
			case 2:
				
				System.out.println("Enter Email : ");
				email = sc.nextLine();
				ua = new UserAuthentication(email);
				System.out.println("Enter OTP to verify : ");
				int otp = sc.nextInt();
				ua.login(email,otp);
				break;
			case 3:
				ua = new UserAuthentication(false);
				ua.login();
				break;
			
			default:
				System.out.println("Please select the valid choice");
				break;
			
		}
		
		System.out.println("Exiting...");
	}

}
