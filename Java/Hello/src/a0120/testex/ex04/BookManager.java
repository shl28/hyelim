package a0120.testex.ex04;

import java.util.ArrayList;

public class BookManager {

    public static void addBook(ArrayList<Book> list, String title, String author, int price) {
        list.add(new Book(title, author, price));
    }

    public static ArrayList<Book> findBooksByAuthor(ArrayList<Book> list, String author) {
        ArrayList<Book> newList = new ArrayList<>();
        // newList = null; 틀림 필요없는 부분

        if (list == null) {
            // if (list == null || author == null) {이게 답.
            return newList;
        }

        for(Book b : list){
            if (b.author.equals(author)) {
                // return newList.add(b);  → return 삭제해야함
                newList.add(b);
            }
        }
        return newList;
    }

    public static int getTotalPrice(ArrayList<Book> list) {
        // if (list == null) {
        //     return 0;
        // }
        // int sum = 0;
        // 순서틀림
        int sum = 0;
        if (list == null) {
            return sum;
        }
        for(Book b : list){
            sum += b.price;
        }
        return sum;
    }

    public static boolean removeBook(ArrayList<Book> list, String title) {
        if (list == null || title == null) {
            return false;
        }
        for(int i = 0; i < list.size(); i++){
            if (list.get(i).title.equals(title)) {
                list.remove(i);
                return true;
            }
        }
        return false;
    }
    
}
