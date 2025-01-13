// 2. Напишіть консольний додаток, використовуючи архітектурний шаблон MVC, який:
//  описує тип даних згідно таблиці;
//  створює набір даних описаного типу (масив розмірністю не менше 10
// елементів);
//  використовує роботу з меню для обробки створеного масиву даних згідно з
// таблицею;
//  використовує інтерфейс Comparator для впорядкування елементів масиву даних
// згідно з таблицею.

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class lab3 {
    public static void main(String[] args) {
        // Create a dataset of books
        List<Book> books = new ArrayList<>();
        String[] titles = {"Book A", "Book B", "Book C", "Book D", "Book E", "Book F", "Book G", "Book H", "Book I", "Book J"};
        String[] authors = {"Author 1", "Author 2", "Author 1", "Author 3", "Author 2", "Author 1", "Author 4", "Author 3", "Author 2", "Author 5"};
        String[] publishers = {"Publisher X", "Publisher Y", "Publisher X", "Publisher Z", "Publisher Y", "Publisher X", "Publisher Z", "Publisher Y", "Publisher X", "Publisher Z"};
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            books.add(new Book(
                titles[i],
                authors[i],
                publishers[i],
                random.nextInt(74) + 1950, // Random year between 1950 and 2024
                random.nextInt(700) + 100,  // Random number of pages between 100 and 800
                random.nextDouble() * 100    // Random price between 0 and 100
            ));
        }

        // Print the initial array of books
        System.out.println("Initial list of books:");
        for (Book book : books) {
            System.out.println(book);
        }
        System.out.println("------------------------------------------------------------------------------------------------------");
        

        // Create the controller and view
        Controller controller = new Controller(books);
        View view = new View(controller);

        // Display the menu
        view.displayMenu();
    }
}

