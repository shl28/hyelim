package a0113.homework;

public class OverrideMainEx1 {
  public static void main(String[] args) {
    OverrideEx1 student1 = new OverrideEx1("홍길동", 20);
    student1.showInfo();

    OverrideEx1_1 student2 = new OverrideEx1_1("김민지", 25, "Female");
    student2.showInfo();
  }
}
