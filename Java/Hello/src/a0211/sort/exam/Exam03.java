package a0211.sort.exam;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Exam03 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();

        // ===== 더미 데이터 =====
        students.add(new Student("홍길동", 20, 2023001));
        students.add(new Student("김철수", 22, 2023002));
        students.add(new Student("이영희", 21, 2023003));
        students.add(new Student("박민수", 23, 2023004));
        students.add(new Student("최지훈", 19, 2023005));
        students.add(new Student("강다은", 20, 2023006));
        // ======================

        // 정렬 옵션 선택
        System.out.println("정렬 기준을 선택하세요");
        System.out.println("1. 이름");
        System.out.println("2. 나이");
        System.out.println("3. 학번");
        System.out.println("4. 종료");
        System.out.print("선택 > ");

        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 1:
                students.sort(Comparator.comparing(Student::getName));
                break;

            case 2:
                students.sort(Comparator.comparingInt(Student::getAge));
                break;
        
            case 3:
                students.sort(Comparator.comparingInt(Student::getStudentId));
                break;

            case 4:
                sc.close();
                break;

            default:
                break;
        }
    
        students.forEach(System.out::println);
    }
}

class Student {
    private String name;
    private int age;
    private int studentId;
    
    public Student(String name, int age, int studentId) {
        this.name = name;
        this.age = age;
        this.studentId = studentId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public int getStudentId() {
        return studentId;
    }
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
    @Override
    public String toString() {
        return "Student [ name = "+name+", age = "+age+", studentId = "+studentId+"]";
    }
    
}
