package a0128.book;

public class Book {
    private int id;
    private String title;
    private String author;
    private String isbn;
    private double price;

    public Book(int id, String title, String author, String isbn, double price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ID : " + id + ", 제목 : " + title + ", 저자 : " + author + ", ISBN : " + isbn + ", 가격 : " + price + "원";
    }

    public String toFileString() {
        return id + "|" + title + "|" + author + "|" + isbn + "|" +price;
    }

    public static Book fromFileString(String line) {
        try {
            String[] parts = line.split("\\|");
            if (parts.length == 5) {
                int id = Integer.parseInt(parts[0].trim());
                String title = parts[1].trim();
                String author = parts[2].trim();
                String isbn = parts[3].trim();
                double price = Double.parseDouble(parts[4].trim());
                return new Book(id, title, author, isbn, price);
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    
    
}
