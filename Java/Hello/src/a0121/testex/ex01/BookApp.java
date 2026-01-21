package a0121.testex.ex01;

import java.util.ArrayList;
import java.util.Scanner;

public class BookApp {
    public static void main(String[] args) {
        ArrayList<Book> list = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        boolean run = true;

        while (run) {
            System.out.println("------------------------");
            System.out.println("1. 도서 등록");
            System.out.println("2. 도서 검색");
            System.out.println("3. 도서 수정");
            System.out.println("4. 도서 삭제");
            System.out.println("5. 전체 출력");
            System.out.println("6. 종료");
            System.out.print("선택 > ");
            
            int menu;
            try {
                menu = Integer.parseInt(scanner.nextLine());                
            } catch (Exception e) {
                System.out.println("숫자만 입력하세요");
                continue;  // 빼먹음 주의
            }

            switch (menu) {
                case 1:
                    System.out.print("제목 입력 : ");
                    String title = scanner.nextLine();
                    System.out.print("저자 입력 : ");
                    String author = scanner.nextLine();

                    list.add(new Book(title, author));

                    System.out.println("도서 등록 완료");
                    
                    break;

                case 2:
                    // 틀림 다시해
                    System.out.print("검색할 제목 : ");
                    title = scanner.nextLine();
                    if (findBook(list, title) != null) {
                        System.out.println(list);
                    } else {
                        System.out.println("도서가 없습니다");
                    }
                    break;

                case 3:
                    System.out.print("수정할 제목 : ");
                    title = scanner.nextLine();
                    System.out.print("새 저자 이름 : ");
                    author = scanner.nextLine();

                    if (updateBook(list, title, author)) {
                        System.out.println("수정 완료");
                    } else {
                        System.out.println("해당 도서가 없습니다");
                    }

                    break;

                case 4:
                    System.out.print("삭제할 제목 : ");
                    title = scanner.nextLine();
                    
                    if (deleteBook(list, title)) {
                        System.out.println("삭제 완료");
                    } else {
                        System.out.println("등록된 도서가 없습니다");
                    }

                    break;

                case 5:
                    if (list.isEmpty()) {
                        System.out.println("등록된 도서가 없습니다");
                    } else {
                        for(Book b : list){
                            System.out.println(b);
                        }
                    }
                    
                    break;

                case 6:
                    run = false;
                    System.out.println("프로그램 종료");

                    break;
            
                default:
                    System.out.println("1~6번 중에 선택하세요");
                    break;
            }
        }
        scanner.close();
    }

    private static Book findBook(ArrayList<Book> list, String title) {
        // if (list.isEmpty() || title == null) {
        //     return null;
        // }
        for(Book b : list){
            if (b.title.equals(title)) {
                return b;
            }
        }
        return null;
    }

    private static boolean updateBook(ArrayList<Book> list, String title, String newAuthor) {
        for(Book b : list){
            if (b.title.equals(title)) {
                b.author = newAuthor;
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
