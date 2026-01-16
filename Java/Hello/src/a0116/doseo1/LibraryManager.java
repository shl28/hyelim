package a0116.doseo1;

import java.util.ArrayList;
import java.util.Scanner;

public class LibraryManager {
    private ArrayList <Library> librarys;
    // Library를 객체로 리스트로 만듦

    private ArrayList <Library> bookLocation;
    // Library를 객체로 대여한 개체를 담음

    public LibraryManager(){
        librarys = new ArrayList<>();
        bookLocation = new ArrayList<>();
        librarys.add(new Library("This Is Java", "Shin", "Section A", "979-11-691-229-8"));
        librarys.add(new Library("First Encounter with React", "Lee Inje", "Section B", "979-11-6921-169-7"));
        librarys.add(new Library("The Principles of Web Standards", "Ko Kyunghee", "Section C", "979-11-6303-622-7"));
        // 더미데이터
    }

    public void allLibrary() {
        System.out.println("대출 가능한 도서 보기");
        for(int i = 0; i < librarys.size(); i++){
            Library library = librarys.get(i);
            if (library.isAvailable()) { // true 가 저장 되어있다면
                System.out.println(library); //toString 걸려있기에 출력 가능
            }
        }
    }

    public boolean bookLocations(String libraryName) {  // if 문으로 되돌아가기 때문에 boolean 으로 반환
        for(Library library : librarys){
            if (library.getTitle().equalsIgnoreCase(libraryName) && library.isAvailable()) { // equalsIgnoreCase: 대소문자 관계없이 비교
                library.book(); // 대출 처리 : false 함으로서 앞으로 대출 불가능한 상태로 만듦
                bookLocation.add(library); // 대출된 도서 목록(bookLocation ArrayList) 에 대출한 도서를 추가함
                return true;
            }
        }
        return false;
    }

    // 대출 도서보기
    public void bookLocations() {
        System.out.println("대출한 도서 보기");
        for(Library location : bookLocation){
            System.out.println(location);
        }
    }

    public void addLibrary(String newTitle, String newAuthor, String newLocation, String newIsbn) {

        // 정석
        // Library library = new Library(newTitle, newAuthor, newLocation, newIsbn);
        // librarys.add(library);
        librarys.add(new Library(newTitle, newAuthor, newLocation, newIsbn));
    }

    public void delLibrary(String dname) {
        boolean result = false;
        for(Library library : librarys){
            if (library.getTitle().equalsIgnoreCase(dname)) {
                if (library.isAvailable()) { // 대여중이 아닐 때만 삭제되도록 함
                    librarys.remove(library);
                    result = true;
                    break;
                } else {
                    result = false;
                    break;
                } 
            }
        }
        if (result) {
            System.out.println("삭제 되었습니다.");
        } else {
            System.out.println("삭제되지 않았습니다.");
        }
    }

    public void updateLibrary(String uname) {
        Scanner sc = new Scanner(System.in);
        boolean found = false;

        for (Library a : librarys) {
            if (a.getTitle().equalsIgnoreCase(uname)) {

                System.out.print("뭘 수정할건데?\n"
                    + "1.도서 이름\t2.도서 저자\t3.도서 위치\t4.도서 ISBN\n>> ");
                int menu = sc.nextInt();
                sc.nextLine(); // 버퍼 제거

                switch (menu) {
                    case 1:
                        System.out.print("수정할 이름: ");
                        a.setTitle(sc.nextLine());
                        break;
                    case 2:
                        System.out.print("수정할 저자: ");
                        a.setAuthor(sc.nextLine());
                        break;
                    case 3:
                        System.out.print("수정할 위치: ");
                        a.setLocation(sc.nextLine());
                        break;
                    case 4:
                        System.out.print("수정할 ISBN: ");
                        a.setIsbn(sc.nextLine());
                        break;
                    default:
                        System.out.println("1~4번 중에 입력하세요");
                        return;
                }

                found = true;
                break; // 찾았으면 반복 종료
            }
        }

        if (found) { // 원래는 !found 라고 작성하셨는데 내가 고쳐버림 아닐수도있으니 주의
            System.out.println("찾는 도서가 없어서 업데이트할 수 없습니다.");
        }

        // int i = 0;
        // int index = -1;
        // int menu = -1;
        // boolean flag = true;
        // Scanner sc = new Scanner(System.in);
        // Library newA = new Library();  // 빈 라이브러리 객체생성(1개) 
        // System.out.println(uname);
        // for(Library library : librarys){
        //     i++;  // i는 1부터 시작
        //     if (library.getTitle().equalsIgnoreCase(uname)) {
        //         index = i - 1;   // 인덱스 번호 (i-1) : 0, 1, 2 실제 찾은 인덱스 번호
        //         newA = library;  // 이름이 같으면 생성된 객체 newA에 넣는다.
        //     }
        //     System.out.println(library.getTitle().equalsIgnoreCase(uname) + " " + library.getTitle() + " " + uname);
        // }
        // if (index != -1) {  // index 가 -1 아니면 수정하려는 도서 이름을 찾은거임
        //     System.out.print("뭘 수정할건데?\n 1.도서 제목 \t 2.도서 저자 \t 3.도서 위치 \t 4.도서ISBN \n >>");  
        //     menu = sc.nextInt();
        //     sc.nextLine();  // \n 같은 불필요한 내용을 지운다.
        //     while (flag) {
        //         switch (menu) {
        //             case 1:
        //                 System.out.println("수정할 도서 제목");
        //                 // ("This Is Java", "Shin", "Section A", "979-11-691-229-8"));
        //                 break;
                
        //             default:
        //                 break;
        //         }
        //     }
        // }
    }

    public void showLibrary(String sname) {
        for(Library library : librarys){
            if (library.getTitle().equalsIgnoreCase(sname)) {
                System.out.println(library);
                break;
            }
        }
    }
}