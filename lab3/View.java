import java.util.List;
import java.util.Scanner;

public class View {
    private Scanner scanner = new Scanner(System.in);
    private Controller controller;

    public View(Controller controller) {
        this.controller = controller;
    }

    public void displayMenu() {
        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Get a list of books by author");
            System.out.println("2. Get a list of books by publisher");
            System.out.println("3. Get a list of books published after a certain year");
            System.out.println("4. Sort books by publisher");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter author name: ");
                    String author = scanner.nextLine();
                    List<Book> booksByAuthor = controller.getBooksByAuthor(author);
                    displayBooks(booksByAuthor);
                    break;
                case 2:
                    System.out.print("Enter publisher name: ");
                    String publisher = scanner.nextLine();
                    List<Book> booksByPublisher = controller.getBooksByPublisher(publisher);
                    displayBooks(booksByPublisher);
                    break;
                case 3:
                    System.out.print("Enter year: ");
                    int year = scanner.nextInt();
                    List<Book> booksAfterYear = controller.getBooksPublishedAfter(year);
                    displayBooks(booksAfterYear);
                    break;
                case 4:
                    controller.sortBooksByPublisher();
                    System.out.println("Books sorted by publisher.");
                    displayBooks(controller.getBooks());
                    break;
                case 5:
                    System.out.println("Bye bye bye...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void displayBooks(List<Book> books) {
        if (books.isEmpty()) {
            System.out.println("No books found.");
        } else {
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }
}