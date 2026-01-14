package a0114.management1;

public class Student {
    private String studentId;  // 학번
    private String name;
    private int age;
    private String major;
    private int kor;
    private int eng;
    private int math;

    
    public Student(String studentId, String name, int age, String major, int kor, int eng, int math) {
        this.studentId = studentId;
        this.name = name;
        this.age = age;
        this.major = major;
        this.kor = kor;
        this.eng = eng;
        this.math = math;
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

    public int calculateTotal(int kor, int eng, int math){
        return kor + eng + math;
    }

    public double calculateAverage(){
        return calculateTotal(kor, eng, math)/3.0;
    }
}
