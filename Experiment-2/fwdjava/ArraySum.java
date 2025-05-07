package Experiment3;
import java.util.*;

public class ArraySum {
	
	private static int sumOfArray(int[] arr)
	{
		int sum=0;
		
		for(int i=0;i<arr.length;i++)
				sum+=arr[i];
		
		return sum;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Sum of Array");
		System.out.println("============");
		
		System.out.print("Enter the size of an array : ");
		int size=sc.nextInt();		
		sc.nextLine(); // consume nextline
		
		int[] arr = new int[size]; //declaring an array
		
		for(int i=0;i<size;i++) // defining an array
		{
			System.out.print("Enter the value for arr["+(i+1)+"] : ");
			arr[i] = sc.nextInt();
		}
		
		System.out.println("The sum of an array = "+sumOfArray(arr));
		
		sc.close();

	}

}
