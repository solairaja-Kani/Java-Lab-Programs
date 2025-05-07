package Experiment6;
import java.util.*;

class Candidates
{
	
	public String name, suitability;
	public int minExperience;
	public List<String> skills;
	public float score;
	
	
	public Candidates(String name, int minExp, List<String> skills)
	{
	
		this.minExperience = minExp;
		this.skills = skills;
		this.name = name;
	}
	
	public void display()
	{
		
		System.out.println("Name         : "+this.name);
		System.out.println("Skills       : "+this.skills);
		System.out.println("Score        : "+this.score);
		System.out.println("Suitability  : "+this.suitability);
		System.out.println("===========================\n");

	}
}

public class ResumeScreeningSystem
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		String jobRole;
		int minExperience;
		List<String> skills;
		
		// set up the hiring role information
		System.out.print("Enter the Hiring Job Role : ");
		jobRole = sc.nextLine();
		
		System.out.print("Enter the Minimum no. of years of Experience: " );
		minExperience = sc.nextInt();
		sc.nextLine();
		
		System.out.print("Enter the required skills(seperated by commas) : ");
		String[] s = sc.nextLine().split(",");
		skills =  new ArrayList<>();
		
		for(String skill : s)
		{
			skills.add(skill.trim());
		}		
				
		System.out.println("---------------------------------------------------");
		System.out.print("Enter the number of candidates : ");
		int num = sc.nextInt();
		sc.nextLine();
		
		// get the user input
		Candidates[] users = getInput(num);
		
		// evaluating the scores and suitability score
		evaluateCandidates(users,jobRole,minExperience,skills,num);
		
		// displaying the result
		System.out.println("-----Evaluation Result-----");
		System.out.println("===========================");
		
		for(int i=0;i<num;i++)
			users[i].display();
		
		sc.close();
	}
	
	// function to get the user input
	public static Candidates[] getInput(int num)
	{
		Scanner sc = new Scanner(System.in);
		Candidates[] users = new Candidates[num];
		String name;
		int userExperience;
		List<String> userSkills;
		
		for(int i=0;i<num;i++)
		{
			System.out.println("For Candidate : "+(i+1));
			System.out.println("=======================");
			System.out.print("Enter your name : ");
			name=sc.nextLine();
			
			System.out.print("Enter your experience : ");
			userExperience = sc.nextInt();
			sc.nextLine();
			
			System.out.print("Enter your skils(seperated by commas) : ");
			String[] s2 = sc.nextLine().split(",");
			
			userSkills = new ArrayList<>();
			
			for(String skill:s2)
				userSkills.add(skill);			
			
			users[i] = new Candidates(name,userExperience,userSkills);			
		}
		sc.close();
		return users;
		
	}

	// function to evaluate the candidates
	public static void evaluateCandidates(Candidates[] users, String jobRole,int minExperience, List<String> skills, int num)
	{
		for(int i=0;i<num;i++)
		{
			// experience score
			users[i].score += getExperienceScore(users[i].minExperience,minExperience);
			
			// skill score
			users[i].score += geSkillScore(users[i].skills , skills);
			
			// get the suitability score
			users[i].suitability = getSuitability(users[i].score);
		}
	}
		
	public static int getExperienceScore(int exp, int min)
	{
		if(min<=exp)
		{
			if(exp>min+5)
				return 50;
			else if(exp>min+3)
				return 40;
			else if(exp>min+1)
				return 30;
			else
				return 20;
		}
		
		return 10;
		
	}
	
	public static float geSkillScore(List<String> userSkills, List<String> skills)
	{
		float individualScore = (50.0f/skills.size());
		float skillScore=0.0f;
		for(String skill:userSkills)
		{
			if(skills.contains(skill))
			{
				skillScore+=individualScore;
			}
				
		}
	
		return skillScore;
	}
	
	public static String getSuitability(float score)
	{
		if(score>80.0f)
			return "Best suitable";
		else if(score>50.00f)
			return "Moderate suitable";
		else
			return "Not suitable";
	}
	

}