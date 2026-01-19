package a0119;

public class ArrayCompareList1 {
    public static void main(String[] args) {
        
        Student[] students = new Student[3];
        students[0] = new Student("홍길동", 20);
        students[1] = new Student("김철수", 22);
        students[2] = new Student("홍철민", 30);

        // Student student1 = new Student("이아람", 19);
        // (수정 시) students[1] = new Student("이정익", 33);  
        // (삭제 시) students[1] = null;

        
    }
}

class Student {
    
    String name;
    int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

}