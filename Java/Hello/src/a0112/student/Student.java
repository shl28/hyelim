package a0112.student;

public class Student {
  private String name;
  private int score;
  static String schoolName = "한국대학교";

  public Student(String name, int score) {
    this.name = name;
    // this.score = score;
    setScore(score);
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public int getScore() {
    return score;
  }
  public void setScore(int score) {
    if (score < 0 || score > 100) {
      System.out.println("====================");
      System.out.println("잘못된 점수입니다.");
      System.out.println("====================\n");
      this.score = 0;
    } else{
      this.score = score;
    }
  }

}
