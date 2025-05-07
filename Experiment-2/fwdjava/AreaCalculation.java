package Experiment2;
import java.util.*;

class Area
{
	// Area of the Square
	int area(int side)
	{
		return 4*side;
	}
	
	// Area of the rectangle
	int area(int length,int breadth)
	{
		return length*breadth;
	}
	
	// Area of the Circle
	float area(float radius)
	{
		return 3.14f*radius*radius;
	}
}

public class AreaCalculation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Scanner class
		Scanner sc= new Scanner(System.in);
		
		// Method Overloading
		Area a = new Area();
		
		int l,b,side;
		float r;
		
		// For area of the square
		System.out.println("Area of the Square");
		System.out.println("==================");
		System.out.print("Enter the value of  Side : ");
		side=sc.nextInt();
		
		System.out.println("Area of the Square : "+a.area(side)+" Sq.units\n");
		
		// For area of the square
		System.out.println("Area of the Rectangle");
		System.out.println("==================");
		System.out.print("Enter the value of Length : ");
		l=sc.nextInt();
		System.out.print("Enter the value of Breadth : ");
		b=sc.nextInt();
				
		System.out.println("Area of the Rectangle : "+a.area(l,b)+" Sq.units\n");
			
		// For area of the square
		System.out.println("Area of the Circle");
		System.out.println("==================");
		System.out.print("Enter the value of Radius : ");
		r=sc.nextInt();
						
		System.out.println("Area of the Rectangle : "+a.area(r)+" Sq.units\n");
						
		

	}

}
