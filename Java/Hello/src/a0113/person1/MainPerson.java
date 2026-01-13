package a0113.person1;

public class MainPerson {
  public static void main(String[] args) {
    
    Student student = new Student("민수", 20, "컴퓨터공학");
    Teacher teacher = new Teacher("김선생", 40, "자바");

    student.introduce();
    student.work();
    student.study();
    student.displayInfo();

    System.out.println();
    
    teacher.introduce();
    teacher.work();
    teacher.teach();
    teacher.displayInfo();

  }
}
