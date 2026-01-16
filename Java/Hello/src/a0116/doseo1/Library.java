package a0116.doseo1;

public class Library {
    
    private String title;
    private String author;
    private String location;
    private String isbn;
    private boolean available;  // 대여 가능 여부

    public Library() {
    }

    public Library(String title, String author, String location, String isbn) {
        this.title = title;
        this.author = author;
        this.location = location;
        this.isbn = isbn;
        this.available = true;  // 기본값으로 대여가능 초기값 → true
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public boolean isAvailable() {  // boolean 일 때는 자동으로 변수 앞에 is 붙음 (원래는 get)
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "책이름 : " + title + ", 저자 : " + author + ", 책 위치 : " + location + ", ISBN : " + isbn
                + ", 대출 여부 : " + (available ? "대출 가능" : "대출 불가") ;
                // (available ? "대출 가능" : "대출 불가")  : 삼항연산자    available 이 true → "대출가능"  false → "대출불가"
    } 

    // 도서 대출 후 대출 불가능 베서드 작성
    public void book(){
        this.available = false;
    }

    
}
