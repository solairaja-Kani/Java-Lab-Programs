import java.util.Scanner;
public class MissingNumber {
    static int findMissingNumber(int[] arr, int n) {
        int expectedSum = n * (n + 1) / 2;
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        return expectedSum - sum;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the value of n: ");
        int n = sc.nextInt();
        int[] arr = new int[n-1];
        System.out.println("Enter " + (n-1) + " numbers:");
        for (int i = 0; i < n-1; i++) {
            arr[i] = sc.nextInt();
        }
        System.out.println("Missing number: " + findMissingNumber(arr, n));
        sc.close();
    }
}