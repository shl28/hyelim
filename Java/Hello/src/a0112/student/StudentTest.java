package a0112.student;

public class StudentTest {
  public static void main(String[] args) {
    Student s1 = new Student("김철수", 90);
    Student s2 = new Student("이영희", -10);

    System.out.printf("==== 학교명(%s) 시험 결과 ====\n", Student.schoolName);
    System.out.printf("이름: %s, 점수: %d점\n", s1.getName(), s1.getScore());
    System.out.printf("이름: %s, 점수: %d점\n", s2.getName(), s2.getScore());

    System.out.println();

    s2.setScore(95);
    System.out.printf("=== %s 학생 시험 결과 수정 ===\n", s2.getName());
    System.out.printf("이름: %s, 점수: %d점\n", s2.getName(), s2.getScore());
  }
}
