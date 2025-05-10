import java.time.LocalDate;
import java.util.*;

class Task {
    String title;
    String description;
    String priority;
    String deadline;
    String status;

    public Task(String title, String description, String priority, String deadline) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.deadline = deadline;
        this.status = "Not Completed";
    }

    public void markCompleted() {
        status = "Completed";
    }

    public void updateStatus(String newStatus) {
        status = newStatus;
    }

    public void displayTask(int taskId) {
        System.out.println("Task ID: " + taskId);
        System.out.println("Title: " + title);
        System.out.println("Description: " + description);
        System.out.println("Priority: " + priority);
        System.out.println("Deadline: " + deadline);
        System.out.println("Status: " + status);
        System.out.println("-------------------------------");
    }

    public boolean isToday() {
        return deadline.equals(LocalDate.now().toString());
    }
}

public class TaskManagementSystem {
    private HashMap<Integer, Task> taskDatabase;
    private int taskIdCounter;

    public TaskManagementSystem() {
        taskDatabase = new HashMap<>();
        taskIdCounter = 1;
    }

    public void addTask(String title, String description, String priority, String deadline) {
        Task task = new Task(title, description, priority, deadline);
        taskDatabase.put(taskIdCounter++, task);
        System.out.println("Task added successfully!");
    }

    public void updateTaskStatus(int taskId, String newStatus) {
        Task task = taskDatabase.get(taskId);
        if (task != null) {
            task.updateStatus(newStatus);
            System.out.println("Task status updated!");
        } else {
            System.out.println("Task not found!");
        }
    }

    public void markTaskAsCompleted(int taskId) {
        Task task = taskDatabase.get(taskId);
        if (task != null) {
            task.markCompleted();
            System.out.println("Task marked as completed!");
        } else {
            System.out.println("Task not found!");
        }
    }

    public void deleteTask(int taskId) {
        if (taskDatabase.containsKey(taskId)) {
            taskDatabase.remove(taskId);
            System.out.println("Task deleted successfully!");
        } else {
            System.out.println("Task not found!");
        }
    }

    public void viewTasksByPriority(String priority) {
        boolean found = false;
        for (Map.Entry<Integer, Task> entry : taskDatabase.entrySet()) {
            if (entry.getValue().priority.equalsIgnoreCase(priority)) {
                entry.getValue().displayTask(entry.getKey());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No tasks found with priority: " + priority);
        }
    }

    public void viewTasksByDeadline(String deadline) {
        boolean found = false;
        for (Map.Entry<Integer, Task> entry : taskDatabase.entrySet()) {
            if (entry.getValue().deadline.equals(deadline)) {
                entry.getValue().displayTask(entry.getKey());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No tasks found with deadline: " + deadline);
        }
    }

    public void viewTodayTasks() {
        String today = LocalDate.now().toString();
        boolean found = false;
        for (Map.Entry<Integer, Task> entry : taskDatabase.entrySet()) {
            if (entry.getValue().deadline.equals(today)) {
                entry.getValue().displayTask(entry.getKey());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No tasks for today!");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskManagementSystem system = new TaskManagementSystem();

        while (true) {
            System.out.println("\n----- Task Management System -----");
            System.out.println("1. Add Task");
            System.out.println("2. Update Task Status");
            System.out.println("3. Mark Task as Completed");
            System.out.println("4. View Tasks by Priority");
            System.out.println("5. View Tasks by Deadline");
            System.out.println("6. View Today\'s Tasks");
            System.out.println("7. Delete Task");
            System.out.println("8. Exit");
            System.out.print("Please enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter Task Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Task Description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter Task Priority (Low, Medium, High): ");
                    String priority = scanner.nextLine();
                    System.out.print("Enter Task Deadline (yyyy-mm-dd): ");
                    String deadline = scanner.nextLine();
                    system.addTask(title, description, priority, deadline);
                    break;

                case 2:
                    System.out.print("Enter Task ID: ");
                    int taskIdToUpdate = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter new Status (In Progress, Completed, Pending): ");
                    String newStatus = scanner.nextLine();
                    system.updateTaskStatus(taskIdToUpdate, newStatus);
                    break;

                case 3:
                    System.out.print("Enter Task ID to mark as completed: ");
                    int taskIdToComplete = scanner.nextInt();
                    system.markTaskAsCompleted(taskIdToComplete);
                    break;

                case 4:
                    System.out.print("Enter Task Priority (Low, Medium, High): ");
                    String taskPriority = scanner.nextLine();
                    system.viewTasksByPriority(taskPriority);
                    break;

                case 5:
                    System.out.print("Enter Task Deadline (yyyy-mm-dd): ");
                    String taskDeadline = scanner.nextLine();
                    system.viewTasksByDeadline(taskDeadline);
                    break;

                case 6:
                    system.viewTodayTasks();
                    break;

                case 7:
                    System.out.print("Enter Task ID to delete: ");
                    int taskIdToDelete = scanner.nextInt();
                    system.deleteTask(taskIdToDelete);
                    break;

                case 8:
                    System.out.println("Exiting Task Management System...");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
