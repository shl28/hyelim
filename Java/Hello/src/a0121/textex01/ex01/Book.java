package a0121.textex01.ex01;

public class Book {
    String title;
    String author;
    
    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book {title='" + title + ", author='" + author + "'}";
    }

}
