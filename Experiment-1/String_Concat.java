import java.util.Scanner;

public class String_Concat {

    static String concatenate(String str1, String str2) {

        StringBuilder sb = new StringBuilder();
        sb.append(str1);
        sb.append(str2);
        return sb.toString();
        
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter first string: ");
        String str1 = sc.nextLine();
        System.out.print("Enter second string: ");
        String str2 = sc.nextLine();
        System.out.println("Concatenated String: " + concatenate(str1, str2));
        sc.close();
    }
}
    

