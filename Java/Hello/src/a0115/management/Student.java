package a0115.management;

public class Student {
    private String studentId;  // 학번
    private String name;
    private int age;
    private String major;
    private int kor;
    private int eng;
    private int math;
    // private int sum; // 총점

    public Student (){}; // 기본 생성자

    public Student(String studentId, String name, int age, String major, int kor, int eng, int math) {
        this.studentId = studentId;
        this.name = name;
        this.age = age;
        this.major = major;
        this.kor = kor;
        this.eng = eng;
        this.math = math;
        // this.sum = kor + eng +math; 
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
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

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getKor() {
        return kor;
    }

    public void setKor(int kor) {
        this.kor = kor;
    }

    public int getEng() {
        return eng;
    }

    public void setEng(int eng) {
        this.eng = eng;
    }

    public int getMath() {
        return math;
    }

    public void setMath(int math) {
        this.math = math;
    }

    // 총점 계산 메서드
    public int calculateTotal(){
        return kor + eng + math;
    }

    // 평균 계산 메서드
    public double calculateAverage(){
        return calculateTotal() / 3.0;
    }

    @Override
    public String toString() {
        return "Student [studentId=" + studentId + ", name=" + name + ", age=" + age + ", major=" + major + ", kor="
                + kor + ", eng=" + eng + ", math=" + math + ", 총점: " + calculateTotal() + ", 평균: " + calculateAverage() + "]";
    }

}
