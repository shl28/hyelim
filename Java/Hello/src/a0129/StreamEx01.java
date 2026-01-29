package a0129;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamEx01 {
    public static void main(String[] args) {
        List<Student1> students = Arrays.asList(
            new Student1("Alice", 20, 85), new Student1("Bob", 22, 90), new Student1("Charlie", 19, 75), new Student1("David", 21, 95));

        // 20세 이상이고 점수가 80점 이상의 학생 이름을 대문자로 변환
        List<String> result = students.stream().filter(s -> s.getAge() >= 20).filter(s -> s.getScore() >= 80).map(Student1::getName).map(String::toUpperCase).collect(Collectors.toList());
            // .map(s -> s.getName())

        System.out.println(result);

    }
}

class Student1 {
    private String name;
    private int age;
    private int score;
    
    public Student1(String name, int age, int score) {
        this.name = name;
        this.age = age;
        this.score = score;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Student [name=" + name + ", age=" + age + ", score=" + score + "]";
    }
    
}
