// package Experiment9;

import java.util.*;
import java.text.DecimalFormat;

abstract class PaymentGateway
{
	private static final DecimalFormat df = new DecimalFormat("0.00");
	
	abstract public void processPayment(double amount,float rate);
	
	public  double chargeAmount(double amt,float rate)
	{
		return Double.parseDouble(df.format(amt*(rate/100)));
	}
	
	public void verifyPayment()
	{
		System.out.println("Payment verified successfully..!");
	}
	
}

class UPIPayment extends PaymentGateway
{
	@Override
	public void processPayment(double amt, float rate)
	{
		verifyPayment();
		
		double charge = chargeAmount(amt,rate);
		
		System.out.println("Processing UPI Payment.....");
		System.out.println("Payment Amount : Rs. "+amt);
		System.out.println("Charge Amount : Rs. "+charge);
		System.out.println("Total Deducted Amount : Rs. "+(amt+charge));
		System.out.println("UPI payment successful....!");
	}
}

class CreditCardPayment extends PaymentGateway
{
	@Override
	public void processPayment(double amt, float rate)
	{
		verifyPayment();
		
		double charge = chargeAmount(amt,rate);
		
		System.out.println("Processing Credit card Payment.....");
		System.out.println("Payment Amount : Rs. "+amt);
		System.out.println("Charge Amount : Rs. "+charge);
		System.out.println("Total Deducted Amount : Rs. "+(amt+charge));
		System.out.println("Credit card payment successful....!");
	}
}

class NetBankingPayment extends PaymentGateway
{
	@Override
	public void processPayment(double amt, float rate)
	{
		verifyPayment();
		
		double charge = chargeAmount(amt,rate);
		
		System.out.println("Processing Net Banking Payment.....");
		System.out.println("Payment Amount : Rs. "+amt);
		System.out.println("Charge Amount : Rs. "+charge);
		System.out.println("Total Deducted Amount : Rs. "+(amt+charge));
		System.out.println("Net Banking Payment successful....!");
	}
}

class WalletPayment extends PaymentGateway
{
	@Override
	public void processPayment(double amt, float rate)
	{
		verifyPayment();
		
		double charge = chargeAmount(amt,rate);
		
		System.out.println("Processing Wallet Payment.....");
		System.out.println("Payment Amount : Rs. "+amt);
		System.out.println("Charge Amount : Rs. "+charge);
		System.out.println("Total Deducted Amount : Rs. "+(amt+charge));
		System.out.println("Wallet Payment successful....!");
	}
}

public class DigitalWalletSystem {
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		PaymentGateway payment;
		
		System.out.println("*****Digital Wallet System*****");
		System.out.println("_______________________________");
		
		double amt;
		
		System.out.print("Enter the amount : ");
		amt = sc.nextDouble();
		
		System.out.println("Choose your payment methods : ");
		System.out.println("1. UPI Payment\n2. Credit card Payment\n3. Net banking Payment\n4. Wallet Payment");
		System.out.print("Enter the option : ");
		int option = sc.nextInt();
		
		switch(option)
		{
			case 1:
				payment = new UPIPayment();
				payment.processPayment(amt, 1.0f);
				break;
			
			case 2:
				payment = new CreditCardPayment();
				payment.processPayment(amt, 2.5f);
				break;
				
			case 3:
				payment = new NetBankingPayment();
				payment.processPayment(amt, 1.0f);
				break;
			
			case 4:
				payment = new WalletPayment();
				payment.processPayment(amt, 0.5f);
				break;
		}
		
		System.out.println("Exiting...");
		
	}

}
