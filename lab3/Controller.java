import java.util.ArrayList;
// import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Controller {
    private List<Book> books;

    public Controller(List<Book> books) {
        this.books = books;
    }

    public List<Book> getBooksByAuthor(String author) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                result.add(book);
            }
        }
        return result;
    }

    public List<Book> getBooksByPublisher(String publisher) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getPublisher().equalsIgnoreCase(publisher)) {
                result.add(book);
            }
        }
        return result;
    }

    public List<Book> getBooksPublishedAfter(int year) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getYear() > year) {
                result.add(book);
            }
        }
        return result;
    }

    public void sortBooksByPublisher() {
        books.sort(Comparator.comparing(Book::getPublisher));
    }

    public List<Book> getBooks() {
        return books;
    }
}