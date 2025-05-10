package Experiment2;

import java.util.*;

class Currency
{
	private String[] currencies = {"INR","USD","EUR"};
	private double[][] prices= {
		  //  Ind   USD        EUR
			{1,    1/74.25, 0.0114}, // INR
			{74.25,  1,     0.85  }, 	// USD
			{87.5,  1.18,   1    }		// EUR
		};
	
	int currencyIndex(String country)
	{
		for(int i=0;i<currencies.length;i++)
		{
			if(currencies[i].equals(country))
			{
				return i;
			}
		}
		
		return -1;
	}
	
	void convert(String from, String to,int amt)
	{
		int from_index = currencyIndex(from);
		int to_index = currencyIndex(to);
		
		if(from_index !=-1 && to_index!=-1)		
			System.out.println(amt+" "+from+" = "+(String.format("%.2f", amt*prices[from_index][to_index]))+" "+to);
		else
			System.out.println("No such currencies found...");
	}
	
	void convert(String from, String to,double amt)
	{
		int from_index = currencyIndex(from);
		int to_index = currencyIndex(to);
		
		if(from_index !=-1 && to_index!=-1)
			System.out.println(amt+" "+from+" = "+(String.format("%.2f", amt*prices[from_index][to_index]))+" "+to);
		else
			System.out.println("No such currencies found...");
	}
}

public class CurrencyConverter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Currency c = new Currency();
		
		Scanner sc = new Scanner(System.in);
		
		int amt;
		double amnt;
		String from,to;
		
		System.out.println("Currency Converter ");
		System.out.println("==================");
		
		System.out.print("Enter the amount(in Integer) : ");
		amt=sc.nextInt();
		System.out.print("Enter the source currency(Eg. INR, EUR, USD) : ");
		from=sc.next();
		System.out.print("Enter the target currency(Eg. INR, EUR, USD) : ");
		to=sc.next();
		
		c.convert(from, to, amt);
		
		System.out.print("Enter the amount(in Double) : ");
		amnt=sc.nextDouble();
		System.out.print("Enter the source currency(Eg. INR, EUR, USD) : ");
		from=sc.next();
		System.out.print("Enter the target currency(Eg. INR, EUR, USD) : ");
		to=sc.next();
		
		c.convert(from, to, amnt);
		

	}

}
