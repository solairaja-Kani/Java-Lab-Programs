
import java.util.*;

class Candidates implements Comparable<Candidates>
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
        System.out.println("Name         : " + this.name);
        System.out.println("Skills       : " + this.skills);
        System.out.println("Score        : " + this.score);
        System.out.println("Suitability  : " + this.suitability);
        System.out.println("===========================");
    }

    @Override
    public int compareTo(Candidates other) {
        return Float.compare(other.score, this.score); // Sort in descending order
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

        System.out.print("Enter the Hiring Job Role: ");
        jobRole = sc.nextLine();

        System.out.print("Enter the Minimum no. of years of Experience: " );
        minExperience = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter the required skills (separated by commas): ");
        String[] s = sc.nextLine().split(",");
        skills = new ArrayList<>();
        for (String skill : s) {
            skills.add(skill.trim());
        }

        System.out.println("---------------------------------------------------");
        System.out.print("Enter the number of candidates: ");
        int num = sc.nextInt();
        sc.nextLine();

        Candidates[] users = getInput(num);
        evaluateCandidates(users, jobRole, minExperience, skills, num);

        Arrays.sort(users); // Sorting candidates based on their scores

        System.out.println("----- Ranking List -----");
        System.out.println("===========================");
        for (int i = 0; i < num; i++) {
            System.out.println("Rank " + (i + 1) + ":");
            users[i].display();
        }

        sc.close();
    }

    public static Candidates[] getInput(int num)
    {
        Scanner sc = new Scanner(System.in);
        Candidates[] users = new Candidates[num];

        for (int i = 0; i < num; i++) {
            System.out.println("For Candidate: " + (i + 1));
            System.out.println("=======================");
            System.out.print("Enter your name: ");
            String name = sc.nextLine();

            System.out.print("Enter your experience: ");
            int userExperience = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter your skills (separated by commas): ");
            String[] s2 = sc.nextLine().split(",");
            List<String> userSkills = new ArrayList<>();
            for (String skill : s2) {
                userSkills.add(skill.trim());
            }

            users[i] = new Candidates(name, userExperience, userSkills);
        }
        return users;
    }

    public static void evaluateCandidates(Candidates[] users, String jobRole, int minExperience, List<String> skills, int num)
    {
        for (int i = 0; i < num; i++) {
            users[i].score += getExperienceScore(users[i].minExperience, minExperience);
            users[i].score += getSkillScore(users[i].skills, skills);
            users[i].suitability = getSuitability(users[i].score);
        }
    }

    public static int getExperienceScore(int exp, int min)
    {
        if (min <= exp) {
            if (exp > min + 5) return 50;
            else if (exp > min + 3) return 40;
            else if (exp > min + 1) return 30;
            else return 20;
        }
        return 10;
    }

    public static float getSkillScore(List<String> userSkills, List<String> skills)
    {
        float individualScore = (50.0f / skills.size());
        float skillScore = 0.0f;
        for (String skill : userSkills) {
            if (skills.contains(skill)) {
                skillScore += individualScore;
            }
        }
        return skillScore;
    }

    public static String getSuitability(float score)
    {
        if (score > 80.0f) return "Best suitable";
        else if (score > 50.0f) return "Moderate suitable";
        else return "Not suitable";
    }
}
