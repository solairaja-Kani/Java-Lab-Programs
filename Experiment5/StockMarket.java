
import java.util.Scanner;

class StockAnalysis {
    private String stockName;
    private String stockType;
    private double investmentAmount;
    private double annualReturnRate;

    // Constructor 1: Initialize stockName and stockType (Default investment amount)
    public StockAnalysis(String stockName, String stockType) {
        this.stockName = stockName;
        this.stockType = stockType;
        this.investmentAmount = 1000; 
        this.annualReturnRate = assignReturnRate(stockType);
       
    }

    // Constructor 2: Initialize stockName, stockType, and investmentAmount
    public StockAnalysis(String stockName, String stockType, double investmentAmount) {
        this(stockName, stockType); 
        this.investmentAmount = investmentAmount;
        
    }

    // Assign return rate based on stock type
    private double assignReturnRate(String stockType) {
        switch (stockType.toLowerCase()) {
            case "high":
                return 15.0;
            case "medium":
                return 10.0;
            case "low":
                return 5.0;
            default:
                return 3.0; 
        }
    }

    // Method Overloading: Predict returns for 1 year (Short-term)
    public double predictReturns() {
        return investmentAmount * Math.pow(1 + (annualReturnRate / 100), 1);
    }

    // Method Overloading: Predict returns for multiple years
    public double predictReturns(int years) {
        return investmentAmount * Math.pow(1 + (annualReturnRate / 100), years);
    }

    // Display stock performance
    public void displayStockPerformance(int years) {
        double futureValue = (years == 1) ? predictReturns() : predictReturns(years);
        System.out.printf("Your future investment value on "+this.stockName+" after %d years: Rs. %.2f\n", years, futureValue);
    }
}


public class StockMarket{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Stock Market Prediction System");
        System.out.println("==============================\n\n");
        System.out.print("Enter stock name: ");
        String stockName = scanner.nextLine();
        System.out.print("Enter stock type (High/Medium/Low): ");
        String stockType = scanner.nextLine();

        System.out.print("Do you want to enter an investment amount? (yes/no): ");
        String choice = scanner.nextLine().toLowerCase();

        StockAnalysis stock; 

        if (choice.equals("yes")) {
            System.out.print("Enter investment amount: ");
            double investmentAmount = scanner.nextDouble();
            stock = new StockAnalysis(stockName, stockType, investmentAmount); 
        } else {
            stock = new StockAnalysis(stockName, stockType); 
        }

        System.out.print("Enter investment duration (1, 3, or 5+ years): ");
        int years = scanner.nextInt();

        stock.displayStockPerformance(years);

        scanner.close();
    }
}
