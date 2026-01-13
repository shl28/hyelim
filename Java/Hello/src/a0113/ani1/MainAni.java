package a0113.ani1;

public class MainAni {
  public static void main(String[] args) {
    Dog dog = new Dog("뽀삐", 3, "골든 리트리버");
    Cat cat = new Cat("나비", 2, "검은색");

    // 부모클래스의 메서드 사용
    dog.eat(); // 상속받은 메서드
    dog.sleep();  // 상속받은 메서드
    dog.makeSound();  //오버라이딩한 메서드
    dog.bark();  // 자식 클래스만의 메서드
    dog.displayInfo();

    System.out.println();
    System.out.println();

    cat.eat();
    cat.sleep();
    cat.makeSound();
    cat.scratch();
    cat.displayInfo();
  }
}

// 상속의 특징
// 단일 상속만 가능 : 자바는 하나의 부모클래스만 상속 가능
// super 키워드 : 부모 클래스의 생성자, 메서드, 필드 호출
// 메서드 오버라이딩: 자식클래스에서 부모클래스의 메서드 재정의
// 모든 클래스는 Object 클래스 상속