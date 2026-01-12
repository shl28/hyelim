package a0112.static1;

public class Card {

  // static 변수 (모든 카드 공통) - 클래스 변수
  static int width = 60;
  static int height = 80;
  // 모든 카드의 모양은 동일 → 공유데이터
  
  // 멤버 변수, 인스턴스 변수 (카드마다 다름)
  String shape;  //무늬(하트, 스페이드, 클로버 등)
  int number;  // 숫자(1~13)


  public Card(String shape, int number) {
    this.shape = shape;
    this.number = number;
  }

  
  
}
