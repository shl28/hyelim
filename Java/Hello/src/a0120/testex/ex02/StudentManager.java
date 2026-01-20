package a0120.testex.ex02;

import java.util.ArrayList;

public class StudentManager {

    public static void addStudent(ArrayList<Student> list, String name, int age, int score) {
        list.add(new Student(name, age, score));
    }

    public static Student findStudent(ArrayList<Student> list, String name) {

        for(Student s : list){
            if (s.name.equals(name)) {
                return s;
            }
        }
        return null;
    }

    public static double getAverageScore(ArrayList<Student> list) {
        // 빼먹었음    
        // if (list.isEmpty()) {
        //     return 0.0;
        // } 
        int sum = 0;
        for(Student s : list){
            sum += s.score;
        }
        return (double) sum / list.size();
    }

    public static void printAll(ArrayList<Student> list) {
        for(Student s : list){
            System.out.println(s);
        }
    }
    
}
