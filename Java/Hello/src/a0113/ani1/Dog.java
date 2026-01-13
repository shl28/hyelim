package a0113.ani1;

// 자식 클래스 dog (Animal 상속)
public class Dog extends Animal {

  String breed; //자식클래스만의 추가 필드

  public Dog(String name, int age, String breed) {
    super(name, age);  // 부모클래스의 생성자를 호출
    this.breed = breed;
  }

  // 메서드 오버라이딩 : 부모클래스의 메서드를 재정의
  @Override // Override 는 안써도 됨
  public void makeSound() {
    System.out.println(name + "이(가) 멍멍 짖습니다.");
  }

  @Override
  public void displayInfo() {
    super.displayInfo();
    System.out.print(", 품종: " + breed);
  }
  
  // 자식 클래스만의 메서드
  public void bark(){
    System.out.println(name + "이(가) 왈왈 짖습니다.");
  }
}

// ctrl + . : 소스작업 불러오는 단축키