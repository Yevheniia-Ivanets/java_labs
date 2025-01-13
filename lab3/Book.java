public class Book {
    private String title;
    private String author;
    private String publisher;
    private int year;
    private int numberOfPages;
    private double price;

    public Book(String title, String author, String publisher, int year, int numberOfPages, double price) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.year = year;
        this.numberOfPages = numberOfPages;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getYear() {
        return year;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        // return "Book{" +
        //         "title='" + title + '\'' +
        //         ", author='" + author + '\'' +
        //         ", publisher='" + publisher + '\'' +
        //         ", year=" + year +
        //         ", numberOfPages=" + numberOfPages +
        //         ", price=" + price +
        //         '}';
        return String.format("Title: %s | Author: %s | Publisher: %s | Year: %d | Pages: %d | Price: $%.2f", 
        title, author, publisher, year, numberOfPages, price);
    }
}