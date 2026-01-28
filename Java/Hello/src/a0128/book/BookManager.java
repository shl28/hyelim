package a0128.book;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class BookManager {
    ArrayList<Book> books;
    String fileName;

    public BookManager() {
        this.books = new ArrayList<>();
        this.fileName = "c:/Users/TJ/memo/book.txt";
    }

    private void ensureDirectory(){
        File file = new File(fileName);
        File parentDir = file.getParentFile();

        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdir();
            System.out.println("디렉토리가 생성되었습니다." + parentDir.getPath());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookManager manager = new BookManager(); 

        while (true) {
            System.out.println("\n===== 도서 관리 시스템 =====");
            System.out.println("1. 도서 추가");
            System.out.println("2. 도서 삭제");
            System.out.println("3. 도서 검색");
            System.out.println("4. 도서 수정");
            System.out.println("5. 도서 목록 보기");
            System.out.println("6. 파일로 저장");
            System.out.println("7. 파일에서 불러오기");
            System.out.println("0. 종료");
            System.out.print("선택 > ");

            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("숫자로 입력하세요.");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("\n책 제목을 입력하세요 : ");
                    String title = scanner.nextLine();
                    System.out.print("책 저자를 입력하세요 : ");
                    String author = scanner.nextLine();
                    System.out.print("책 ISBN을 입력하세요 : ");
                    String isbn = scanner.nextLine();
                    System.out.print("책 가격을 입력하세요 : ");
                    double price;
                    try {
                        price = scanner.nextDouble();
                        scanner.nextLine();
                        manager.addBook(title, author, isbn, price);
                    } catch (Exception e) {
                        System.out.println("숫자로 입력하세요.");
                        scanner.nextLine();
                        continue;
                    }

                    break;

                case 2:
                    System.out.print("\n삭제할 책 제목을 입력하세요 : ");
                    String deleteTitle = scanner.nextLine();
                    manager.deleteBook(deleteTitle);
                    
                    break;

                case 3:
                    System.out.print("\n조회할 책 제목을 입력하세요 : ");
                    String searchTitle = scanner.nextLine();
                    manager.searchBook(searchTitle);
                    
                    break;

                case 4:
                    System.out.print("\n수정할 책 제목을 입력하세요 : ");
                    String updateTitle = scanner.nextLine();
                    if (manager.findBook(updateTitle) == null) {
                        System.out.println("찾는 도서가 없습니다.");
                        break; 
                    }

                    System.out.print("새로운 책 제목을 입력하세요 : ");
                    String newTitle = scanner.nextLine();
                    System.out.print("새로운 책 저자를 입력하세요 : ");
                    String newAuthor = scanner.nextLine();
                    System.out.print("새로운 책 ISBN을 입력하세요 : ");
                    String newISBN = scanner.nextLine();
                    System.out.print("새로운 책 가격을 입력하세요 : ");
                    double newPrice;
                    try {
                        newPrice = Double.parseDouble(scanner.nextLine());
                        manager.updateBook(updateTitle, newTitle, newAuthor, newISBN, newPrice);          
                    } catch (NumberFormatException e) {
                        System.out.println("숫자로 입력하세요.");
                    }

                    break;

                case 5:
                    manager.printAll();
                    
                    break;

                case 6:
                    manager.saveToFile();

                    break;

                case 7:
                    manager.loadFromFile();

                    break;

                case 0:
                    System.out.println("프로그램을 종료합니다.");
                    scanner.close();

                    return;
            
                default:
                    System.out.println("0~7번 중에 선택하세요.");
                    break;
            }
        }
    }

    private Book findBook(String title){
        for(Book book : books){
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    private void addBook(String title, String author, String isbn, double price) {
        if (findBook(title) != null) {
            System.out.println("\n이미 존재하는 도서입니다.");
            return;
        }
        int id = books.size() + 1;
        books.add(new Book(id, title, author, isbn, price));
        System.out.println("\n도서가 추가되었습니다.");
        saveToFile();
    }

    private void deleteBook(String deleteTitle) {
        Book book = findBook(deleteTitle);
        if (book != null) {
            books.remove(book);
            System.out.println("\n도서명을 제거하였습니다.");
            saveToFile();
        } else {
            System.out.println("\n찾는 도서가 없습니다.");
        }
    }

    private void searchBook(String searchTitle) {
        Book book = findBook(searchTitle);
        if (book != null) {
            System.out.println(book);
        } else {
            System.out.println("\n찾는 도서가 없습니다.");
        }
    }

    private void updateBook(String updateTitle, String newTitle, String newAuthor, String newISBN, double newPrice) {
        Book book = findBook(updateTitle);
        if (book != null) {
            book.setTitle(newTitle);
            book.setAuthor(newAuthor);
            book.setIsbn(newISBN);
            book.setPrice(newPrice);
            System.out.println("\n도서 정보가 수정되었습니다.");
            saveToFile();
        } 
    }

    private void printAll() {
        if (!books.isEmpty()) {
            System.out.println("\n=== 전체 도서 목록 ====");
            for(int i = 0; i < books.size(); i++){
                System.out.println((i+1) + ". " + books.get(i));
            }
        } else {
            System.out.println("\n등록된 도서가 없습니다.");
        }
    }

    private void saveToFile() {
        ensureDirectory();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))){
            for(Book book : books) {
                bw.write(book.toFileString());
                bw.newLine();
            }
            System.out.println("\n파일 저장 완료: " + fileName);
        } catch (IOException e) {
            System.out.println("\n파일이 없습니다. 새로 시작합니다. " + e.getMessage());
        }
    }

    private void loadFromFile() {
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("\n파일이 없습니다. 새로 시작합니다.");
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    Book book = Book.fromFileString(line);
                    if (book != null) {
                        books.add(book);
                    }
                }
            }
            System.out.println("\n파일 불러오기 완료! " + books.size() + "권");
        } catch (IOException e) {
            System.out.println("\n파일이 없습니다. 새로 시작합니다." + e.getMessage());
        }
    }

}
