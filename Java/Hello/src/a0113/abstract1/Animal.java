package a0113.abstract1;

// 추상 (abstract) 클래스 : 추상 메서드 하나라도 있으면 추상클래스가 되어야 함
// 미완성 설계도
// 객체생성 불가
// 상속해서 완성해야 사용 가능
// 일반 메서드 + 추상 메서드 둘다 가질 수 있음
abstract class Animal {
  String name;

  public Animal(String name) {
    this.name = name;
  }
  
  // 추상 메서드 : 몸체가 없는 메서드
  // 선언()만 있고 구현{}이 없음
  // 자식 클래스에서 반드시 오버라이딩 해야 함 - 책의 차례와 비슷함
  abstract void makeSound();

  // 일반 메서드
  public void eat(){
    System.out.println(name + "이(가) 먹습니다.");
  }

}
