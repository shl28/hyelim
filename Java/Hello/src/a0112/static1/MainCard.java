package a0112.static1;

public class MainCard {
  public static void main(String[] args) {
    
    Card c1 = new Card("하트", 7);
    Card c2 = new Card("스페이드", 1);
    System.out.println("카드모양 : " + c1.shape + " - 카드 숫자 : " + c1.number);
    System.out.println("카드모양 : " + c2.shape + " - 카드 숫자 : " + c2.number);

    System.out.println("카드넓이 : " + Card.width + " - 카드 높이 : " + Card.height);
    // 공용변수(클래스변수, static(정적) 변수) 이므로 객체를 만들지 않고 출력 가능
    System.out.println("카드넓이 : " + c1.width + " - 카드 높이 : " + c1.height);
    // 노란줄 : 가능은 하지만 추천 X

    // static 변수 변경
    Card.width = 70;
    Card.height = 100;
    System.out.println("=== 카드크기 변경 후 ===");
    System.out.println("카드넓이 : " + Card.width + " - 카드 높이 : " + Card.height);
    
  }
}
