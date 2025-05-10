import java.util.*;
import java.text.*;

class Expense {
    String category;
    double amount;
    String date;
    String description;

    public Expense(String category, double amount, String date, String description) {
        this.category = category;
        this.amount = amount;
        this.date = date;
        this.description = description;
    }
}

class FinanceTracker {
    List<Expense> expenses;
    Map<String, Double> categoryTotals;
    Map<String, Double> categoryBudgets;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public FinanceTracker() {
        expenses = new ArrayList<>();
        categoryTotals = new HashMap<>();
        categoryBudgets = new HashMap<>();
    }

    // Add expense to the tracker
    public void addExpense(String category, double amount, String date, String description) {
        Expense expense = new Expense(category, amount, date, description);
        expenses.add(expense);
        categoryTotals.put(category, categoryTotals.getOrDefault(category, 0.0) + amount);
        System.out.println("Expense added: " + description + " | Amount: " + amount);
    }

    // View total expenses by category
    public void viewTotalByCategory() {
        if (categoryTotals.isEmpty()) {
            System.out.println("No expenses recorded yet.");
            return;
        }
        System.out.println("Total Expenses by Category:");
        for (Map.Entry<String, Double> entry : categoryTotals.entrySet()) {
            System.out.println("Category: " + entry.getKey() + " | Total: " + entry.getValue());
        }
    }

    // View all expenses
    public void viewAllExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses recorded yet.");
            return;
        }
        System.out.println("All Expenses:");
        for (Expense expense : expenses) {
            System.out.println("Date: " + expense.date + " | Category: " + expense.category + " | Amount: " + expense.amount + " | Description: " + expense.description);
        }
    }

    // View total spending
    public void viewTotalSpending() {
        double totalSpending = expenses.stream().mapToDouble(e -> e.amount).sum();
        System.out.println("Total Spending: " + totalSpending);
    }

    // Set monthly budget for a category
    public void setMonthlyBudget(String category, double budget) {
        categoryBudgets.put(category, budget);
        System.out.println("Monthly budget set for " + category + ": " + budget);
    }

    // Check if budget is exceeded for a category
    public void checkBudget(String category) {
        double totalCategorySpending = categoryTotals.getOrDefault(category, 0.0);
        double budget = categoryBudgets.getOrDefault(category, 0.0);
        if (totalCategorySpending > budget) {
            System.out.println("You have exceeded your budget for " + category + ". You have spent " + totalCategorySpending + " out of " + budget);
        } else {
            System.out.println("You are within your budget for " + category + ". You have spent " + totalCategorySpending + " out of " + budget);
        }
    }

    // Filter expenses by date range
    public void filterExpensesByDateRange(String startDate, String endDate) {
        try {
            Date start = dateFormat.parse(startDate);
            Date end = dateFormat.parse(endDate);
            System.out.println("Expenses from " + startDate + " to " + endDate + ":");
            for (Expense expense : expenses) {
                Date expenseDate = dateFormat.parse(expense.date);
                if (expenseDate.after(start) && expenseDate.before(end)) {
                    System.out.println("Date: " + expense.date + " | Category: " + expense.category + " | Amount: " + expense.amount + " | Description: " + expense.description);
                }
            }
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
        }
    }

    // Monthly report of total spending
    public void generateMonthlyReport(String month) {
        double totalMonthlySpending = 0.0;
        System.out.println("Monthly Report for " + month + ":");
        for (Expense expense : expenses) {
            if (expense.date.startsWith(month)) {
                totalMonthlySpending += expense.amount;
            }
        }
        System.out.println("Total spending for " + month + ": " + totalMonthlySpending);
    }

    // Remove an expense by description
    public void removeExpense(String description) {
        expenses.removeIf(expense -> expense.description.equalsIgnoreCase(description));
        System.out.println("Expense removed: " + description);
    }

    // Sort expenses by amount
    public void sortExpensesByAmount() {
        expenses.sort(Comparator.comparingDouble(e -> e.amount));
        System.out.println("Expenses sorted by amount:");
        viewAllExpenses();
    }

    // Sort expenses by date
    public void sortExpensesByDate() {
        expenses.sort(Comparator.comparing(e -> e.date));
        System.out.println("Expenses sorted by date:");
        viewAllExpenses();
    }

    // Edit an expense details by description
    public void editExpense(String description, String newCategory, double newAmount, String newDate, String newDescription) {
        for (Expense expense : expenses) {
            if (expense.description.equalsIgnoreCase(description)) {
                // Update the expense details
                expense.category = newCategory;
                expense.amount = newAmount;
                expense.date = newDate;
                expense.description = newDescription;
                System.out.println("Expense edited: " + newDescription);
                return;
            }
        }
        System.out.println("Expense with description '" + description + "' not found.");
    }
}

public class FinanceTrackerSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FinanceTracker tracker = new FinanceTracker();
        boolean exit = false;

        while (!exit) {
            System.out.println("\nPersonal Finance Tracker Menu:");
            System.out.println("1. Add Expense");
            System.out.println("2. View All Expenses");
            System.out.println("3. View Total Spending");
            System.out.println("4. View Total By Category");
            System.out.println("5. Set Monthly Budget");
            System.out.println("6. Check Budget");
            System.out.println("7. Remove Expense");
            System.out.println("8. Filter Expenses by Date Range");
            System.out.println("9. Generate Monthly Report");
            System.out.println("10. Sort Expenses by Amount");
            System.out.println("11. Sort Expenses by Date");
            System.out.println("12. Edit Expense");
            System.out.println("13. Exit");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline

            switch (choice) {
                case 1:
                    System.out.print("Enter category: ");
                    String category = scanner.nextLine();
                    System.out.print("Enter amount: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine(); // consume the newline
                    System.out.print("Enter date (yyyy-MM-dd): ");
                    String date = scanner.nextLine();
                    System.out.print("Enter description: ");
                    String description = scanner.nextLine();
                    tracker.addExpense(category, amount, date, description);
                    break;

                case 2:
                    tracker.viewAllExpenses();
                    break;

                case 3:
                    tracker.viewTotalSpending();
                    break;

                case 4:
                    tracker.viewTotalByCategory();
                    break;

                case 5:
                    System.out.print("Enter category for budget: ");
                    String budgetCategory = scanner.nextLine();
                    System.out.print("Enter budget amount: ");
                    double budgetAmount = scanner.nextDouble();
                    scanner.nextLine(); // consume the newline
                    tracker.setMonthlyBudget(budgetCategory, budgetAmount);
                    break;

                case 6:
                    System.out.print("Enter category to check budget: ");
                    String checkCategory = scanner.nextLine();
                    tracker.checkBudget(checkCategory);
                    break;

                case 7:
                    System.out.print("Enter description of the expense to remove: ");
                    String removeDescription = scanner.nextLine();
                    tracker.removeExpense(removeDescription);
                    break;

                case 8:
                    System.out.print("Enter start date (yyyy-MM-dd): ");
                    String startDate = scanner.nextLine();
                    System.out.print("Enter end date (yyyy-MM-dd): ");
                    String endDate = scanner.nextLine();
                    tracker.filterExpensesByDateRange(startDate, endDate);
                    break;

                case 9:
                    System.out.print("Enter month (yyyy-MM): ");
                    String month = scanner.nextLine();
                    tracker.generateMonthlyReport(month);
                    break;

                case 10:
                    tracker.sortExpensesByAmount();
                    break;

                case 11:
                    tracker.sortExpensesByDate();
                    break;

                case 12:
                    System.out.print("Enter description of the expense to edit: ");
                    String editDescription = scanner.nextLine();
                    System.out.print("Enter new category: ");
                    String newCategory = scanner.nextLine();
                    System.out.print("Enter new amount: ");
                    double newAmount = scanner.nextDouble();
                    scanner.nextLine(); // consume the newline
                    System.out.print("Enter new date (yyyy-MM-dd): ");
                    String newDate = scanner.nextLine();
                    System.out.print("Enter new description: ");
                    String newDescription = scanner.nextLine();
                    tracker.editExpense(editDescription, newCategory, newAmount, newDate, newDescription);
                    break;

                case 13:
                    exit = true;
                    System.out.println("Exiting the Finance Tracker...");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }
}
