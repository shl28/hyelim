package a0211.sort.exam;

import java.util.ArrayList;

public class Main1 {
    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<>();

        // ===== 더미 데이터 =====
        students.add(new Student("홍길동", 20, 2023001));
        students.add(new Student("김철수", 22, 2023002));
        students.add(new Student("이영희", 21, 2023003));
        students.add(new Student("박민수", 23, 2023004));
        students.add(new Student("최지훈", 19, 2023005));
        students.add(new Student("강다은", 20, 2023006));
        // ======================

        //선택정렬
        selectionSort(students);

        // 정렬된 결과 출력
        System.out.println("정렬된 학생 목록:");
        for (Student student : students) {
            System.out.println(student);
        }
    }

    private static void selectionSort(ArrayList<Student> students) {
        int n = students.size();

        for(int i = 0; i < n -1; i++) {
            int minIndex = i;

            Student currentStudent = students.get(i);

            for(int j = i + 1; j < n; j++){
                if (students.get(j).getName().compareTo(currentStudent.getName()) < 0) {
                    minIndex = j;
                }
            }

            Student temp = students.get(minIndex);
            students.set(minIndex, currentStudent);
            students.set(i, temp);


        }
    }

}


class Student{
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
