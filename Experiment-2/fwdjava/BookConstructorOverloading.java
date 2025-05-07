package Experiment2;
import java.util.*;

class Book {
   
    private String bookName;
    private String author;
    private double price;
    private int yearPublished;

    // Constructor 1: Default book details
    public Book() {
        this("Unknown Book", "Unknown Author", 0.0, 2000);
    }

    // Constructor 2: Partial details (Book name and Author)
    public Book(String bookName, String author) {
        this(bookName, author, 299.99, 2021);
    }

    // Constructor 3: Full book details
    public Book(String bookName, String author, double price, int yearPublished) {
        this.bookName = bookName;
        this.author = author;
        this.price = price;
        this.yearPublished = yearPublished;
    }

    // Method to display book details
    public void displayInfo() {
        System.out.println("\n Book Information:");
        System.out.println("Title: " + bookName);
        System.out.println("Author: " + author);
        System.out.println("Price: Rs. " + price);
        System.out.println("Year Published: " + yearPublished);
    }

    // Getter for book name
    public String getBookName() {
        return bookName;
    }
}
public class BookConstructorOverloading {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner scanner = new Scanner(System.in);
        Book[] bookCollection = new Book[5]; // Array to store 5 books
        int bookCount = 0;

        while (true) {
            System.out.println("\n Book Management System");
            System.out.println("1️. Add a new book");
            System.out.println("2️. Display all books");
            System.out.println("3️. Search for a book by title");
            System.out.println("4️. Exit");

            System.out.print("\nEnter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    if (bookCount < bookCollection.length) {
                        System.out.println("\nChoose how to add the book:");
                        System.out.println("1️. Enter all details");
                        System.out.println("2️. Enter only Title and Author");
                        System.out.println("3️. Use default book details");

                        System.out.print("Enter your choice: ");
                        int option = scanner.nextInt();
                        scanner.nextLine(); 

                        Book book = null;

                        if (option == 1) {
                            System.out.print("Enter Book Title: ");
                            String title = scanner.nextLine();
                            System.out.print("Enter Author: ");
                            String author = scanner.nextLine();
                            System.out.print("Enter Price: ");
                            double price = scanner.nextDouble();
                            System.out.print("Enter Year Published: ");
                            int year = scanner.nextInt();
                            book = new Book(title, author, price, year);
                        } else if (option == 2) {
                            System.out.print("Enter Book Title: ");
                            String title = scanner.nextLine();
                            System.out.print("Enter Author: ");
                            String author = scanner.nextLine();
                            book = new Book(title, author);
                        } else {
                            book = new Book();
                        }

                        bookCollection[bookCount] = book;
                        bookCount++;
                        System.out.println(" Book added successfully!");
                    } else {
                        System.out.println(" Book collection is full!");
                    }
                    break;

                case 2:
                    if (bookCount == 0) {
                        System.out.println(" No books available!");
                    } else {
                        System.out.println("\n List of Books:");
                        for (int i = 0; i < bookCount; i++) {
                            bookCollection[i].displayInfo();
                        }
                    }
                    break;

                case 3:
                    if (bookCount == 0) {
                        System.out.println(" No books available to search!");
                    } else {
                        System.out.print(" Enter the book title to search: ");
                        String searchTitle = scanner.nextLine();
                        boolean found = false;

                        for (int i = 0; i < bookCount; i++) {
                            if (bookCollection[i].getBookName().equalsIgnoreCase(searchTitle)) {
                                bookCollection[i].displayInfo();
                                found = true;
                                break;
                            }
                        }

                        if (!found) {
                            System.out.println(" Book not found!");
                        }
                    }
                    break;

                case 4:
                    System.out.println(" Exiting the Book Management System. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println(" Invalid choice! Try again.");
            }
        }

	}

}





