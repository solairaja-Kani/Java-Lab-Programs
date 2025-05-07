import java.util.Scanner;
public class FibonacciRecursion {
    static void fibonacci(int n, int first, int second) {
        if (n == 0) return;
        System.out.print(first + " ");
        fibonacci(n - 1, second, first + second);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of terms: ");
        int terms = sc.nextInt();
        System.out.print("Fibonacci Series: ");
        fibonacci(terms, 0, 1);
        sc.close();
    }
}