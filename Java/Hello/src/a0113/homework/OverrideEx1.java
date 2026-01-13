package a0113.homework;

public class OverrideEx1 {
// 오버로딩 오버라이딩 차이를 설명하는 예제 한개 만들어오기 숙제

// 오버라이딩: 부모 클래스의 메서드를 자식 클래스에서 재정의
  String name;
  int age;
  
  public OverrideEx1(String name, int age) {
    this.name = name;
    this.age = age;
  }

  public void showInfo() {
    System.out.println();
    System.out.println("==== 학생 정보 ====");
    System.out.printf("이름: %s, 나이: %d", this.name, this.age);
  }
  
}
