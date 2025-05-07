package Experiment3;
import java.util.*;

class Student
{
	String name,regno;
	int tot;
	char grade;
	float avg;
	
	char findGrade(float avg)
	{
		
		char grade;
		
		if(avg>90)
			grade='A';
		else if(avg>70 && avg<=90)
			grade='B';
		else if(avg>60 && avg<=70)
			grade='C';
		else if(avg>50)
			grade='D';
		else
			grade='E';
		
		return grade;
	}
	
	void setStudentInfo(String name,String regno, int tot)
	{		
		float avg=tot/5.0f;
		char grade=this.findGrade(avg);
		this.name=name;
		this.regno = regno;
		this.tot = tot;
		this.avg=avg;
		this.grade=grade;	
		
	}
	
	
	void displayStudentInfo(Student s)
	{
		System.out.println("***Displaying Student's Information***");
		System.out.println("--------------------------------------");
		
		System.out.println("Name                 : "+s.name);
		System.out.println("Register Number      : "+s.regno);
		System.out.println("Total Mark           : "+s.tot);
		System.out.println("Average Mark         : "+s.avg);
		System.out.println("Grade                : "+s.grade);
		
		System.out.println("--------------------------------------");
	}
	
	void updateStudentInfo(Student s, int tot)
	{
		float avg = tot/5.0f;
		char grade = this.findGrade(avg);
		
		s.avg=avg;
		s.grade=grade;
		
		System.out.println("Updated Successfully...!");
	}
}

public class PassByReference {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		
		Student std  = new Student();
		
		System.out.println("****Student information management****");
		System.out.println("--------------------------------------");
		
		System.out.print("Enter the Student Name : ");
		String name = sc.nextLine();
		
		System.out.print("Enter the Register Number : ");
		String regno = sc.nextLine();
		
		System.out.print("Enter the Total Mark(Out of 500) : ");
		int tot = sc.nextInt();
		
		std.setStudentInfo(name, regno, tot);
		
		std.displayStudentInfo(std);
		
		System.out.println("Update Student Details");
		System.out.println("----------------------");
		
		System.out.print("Enter the correct Total mark(Out of 500) : ");
		int tot2=sc.nextInt();
		
		std.updateStudentInfo(std, tot2);
		std.displayStudentInfo(std);
		
		sc.close();

	}

}
