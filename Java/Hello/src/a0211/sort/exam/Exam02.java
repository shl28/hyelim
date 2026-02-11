package a0211.sort.exam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Exam02 {
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
                Collections.sort(students, new NameComparator());
                break;
        
            case 2:
                Collections.sort(students, new AgeComparator());
                break;

            case 3:
                Collections.sort(students, new IdComparator());
                break;

            case 4:
                sc.close();
                break;
                
            default:
                break;
        }

        for(Student student : students){
            System.out.println(student);
        }
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

class NameComparator implements Comparator<Student> {

    @Override
    public int compare(Student o1, Student o2) {
        return o1.getName().compareTo(o2.getName());
    }

}

class AgeComparator implements Comparator<Student> {

    @Override
    public int compare(Student o1, Student o2) {
        return Integer.compare(o1.getAge(), o2.getAge());
    }

}

class IdComparator implements Comparator<Student> {

    @Override
    public int compare(Student o1, Student o2) {
        return Integer.compare(o1.getStudentId(), o2.getStudentId());
    }

}
