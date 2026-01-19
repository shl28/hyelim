package a0119.BookApp;

import java.util.ArrayList;

public class BookApp {
    public static void main(String[] args) {

        ArrayList<Book> list = new ArrayList<>();
        list.add(new Book("자바의 정석", "남궁성"));
        list.add(new Book("혼자 공부하는 자바", "신용권"));
        list.add(new Book("이것이 자바다", "신용권"));
        
        printAll(list);

        System.out.println("=== 수정 ===");
        boolean result1 = updateBook(list, "자바의 정석", "남궁성(개정판)");
        if (result1) {
            System.out.println("수정 완료");
        } else {
            System.out.println("수정 실패");
        }

        printAll(list);

        System.out.println("=== 삭제 ===");
        boolean result2 = deleteBook(list, "혼자 공부하는 자바");

        if (result2) {
            System.out.println("삭제 완료");
        } else {
            System.err.println("삭제 실패");
        }
        printAll(list);

        System.out.println("=== 검색 ===");
        Book b = findBook(list, "이것이 자바다");
        System.out.println(b);

    }

    

    



    private static void printAll(ArrayList<Book> list) {
        for(Book i : list){
            System.out.println(i);
        }
    }

    private static Book findBook(ArrayList<Book> list, String title) {
        for(Book i : list){
            if (i.title.equals(title)) {
                return i;
            }
        }
        return null;
    }

    private static boolean updateBook(ArrayList<Book> list, String title, String newAuthor) {
        for(Book i : list){
            if (i.title.equals(title)) {
                i.author = newAuthor;
                return true;
            }
        }
        return false;
    }

    private static boolean deleteBook(ArrayList<Book> list, String title) {
        for(int i = 0; i < list.size(); i++){
            if (list.get(i).title.equals(title)) {
                list.remove(i);
                return true;
            }
        }
        return false;
    }
}
