package a0113.person1.answer;

public class InheritanceExample2 {
  public static void main(String[] args) {

        Student student = new Student("민수", 20, "컴퓨터공학");
        Teacher teacher = new Teacher("김선생", 40, "자바");

        student.introduce();
        student.work();      // 오버라이딩
        student.study();     // 자식 전용
        student.displayInfo();

        System.out.println();

        teacher.introduce();
        teacher.work();      // 오버라이딩
        teacher.teach();     // 자식 전용
        teacher.displayInfo();
    }
}
