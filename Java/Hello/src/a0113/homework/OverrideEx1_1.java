package a0113.homework;

public class OverrideEx1_1 extends OverrideEx1 {
  String gender;

  public OverrideEx1_1(String name, int age, String gender) {
    super(name, age);
    this.gender = gender;
  }

  @Override
  public void showInfo() {
    super.showInfo();
    System.out.printf(" 성별: %s", gender);
  }
    
}
