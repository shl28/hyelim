package exam.ex01;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentApp {
    public static void main(String[] args) {
        ArrayList<Student> list = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        boolean run =true;

        while (run) {
            System.out.println("------------------------");
            System.out.println("1. 학생 등록");
            System.out.println("2. 학생 검색");
            System.out.println("3. 학생 수정");
            System.out.println("4. 학생 삭제");
            System.out.println("5. 전체 출력");
            System.out.println("6. 종료");
            System.out.print("선택 > ");

            int menu;
            try {
                menu = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("숫자만 입력하세요");
                continue;
            }

            switch (menu) {
                case 1:
                    System.out.print("이름 입력 : ");
                    String name = scanner.nextLine();
                    System.out.print("나이 입력 : ");
                    int age;
                    try {
                        age = Integer.parseInt(scanner.nextLine());
                    } catch (Exception e) {
                        System.out.println("나이는 숫자로 입력하세요");
                        continue;
                    }

                    list.add(new Student(name, age));
                    System.out.println("학생 등록 완료");

                    break;

                    case 2:
                    System.out.print("검색할 이름 입력 : ");
                    name = scanner.nextLine();

                    Student s = findStudent(list, name);

                    if (s != null) {
                        for(Student s1 : list){
                            System.out.println(s1);
                        }
                    } else {
                        System.out.println("학생이 없습니다.");
                    }
                    
                    break;

                case 3:
                    System.out.print("수정할 이름 입력 : ");
                    name = scanner.nextLine();
                    System.out.print("새로운 나이 입력 : ");
                    int newAge;
                    try {
                        newAge = Integer.parseInt(scanner.nextLine());
                    } catch (Exception e) {
                        System.out.println("나이는 숫자로 입력하세요");
                        continue;
                    }
                    
                    if (updateStudent(list, name, newAge)) {
                        System.out.println("수정 완료");
                    } else{
                        System.out.println("해당 학생이 없습니다.");
                    }
                    
                    break;

                case 4:
                    System.out.print("삭제할 이름 입력 : ");
                    name = scanner.nextLine();
                    
                    if (deleteStudent(list, name)) {
                        System.out.println("삭제 완료");
                    } else{
                        System.out.println("삭제할 학생이 없습니다.");
                    }
                    
                    break;

                case 5:
                    if (list.isEmpty()) {
                        System.out.println("등록된 학생이 없습니다");
                    } else {
                        for(Student s2 : list){
                            System.out.println(s2);
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
    }

    private static Student findStudent(ArrayList<Student> list, String name) {
        for(Student s : list){
            if (s.name.equals(name)) {
                return s;
            }
        }
        return null;
    }

    private static boolean updateStudent(ArrayList<Student> list, String name, int newAge) {
        for(Student s : list){
            if (s.name.equals(name)) {
                s.age = newAge;
                return true;
            }
        }
        return false;
    }

    private static boolean deleteStudent(ArrayList<Student> list, String name) {
        for(int i = 0; i < list.size(); i++){
            if (list.get(i).name.equals(name)) {
                list.remove(i);
                return true;
            }
        }
        return false;
    }

}
