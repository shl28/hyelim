package a0120.ex01;

public class Student {
    String name;
    int age;
    int score;

    public Student(){
        // String name = null;
        // int age = 0;
        // int score = 0;
        // 기본생성자 : 생략되어 있지만 기본값으로 위와 같이 세팅이 됨
    }

    public Student(String name, int age, int score) {
        this.name = name;
        this.age = age;
        this.score = score;
    }

    @Override
    public String toString() {
        return "이름: " + name + ", 나이: " + age + ", 점수: " + score ;
    }

    public String getGrade() {
        if (score >= 90) {
            return "A";
        } else if (score >= 80) {
            return "B";
        } else if (score >= 70) {
            return "C";
        } else if (score >= 60) {
            return "D";
        } else {
            return "F";
        }
    }

}
