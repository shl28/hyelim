package a0120.ex02;

import java.util.ArrayList;

public class StudentManager {

    // 학생 추가 메서드
    public static void addStudent(ArrayList<Student> list, String name, int age, int score) {
        list.add(new Student(name, age, score));
    }

    public static void printAll(ArrayList<Student> list) {
        for(Student s : list){
            System.out.println(s);
        }
    }

    // 학생 검색 메서드
    public static Student findStudent(ArrayList<Student> list, String name) {
        for(Student s : list){
            if (s.name.equals(name)) {
                return s;
            }
        }
        return null;
    }

    public static double getAverageScore(ArrayList<Student> list) {
        if (list.isEmpty()) {
            return 0.0;
        } 
        // isEmpty 문은 안해도 되나 하면 + 점수
        int sum = 0;
        for(Student s : list){
            sum += s.score;
        }
        return (double) sum / list.size();
        // 총점을 인원수로 나눔
    }
    
}
