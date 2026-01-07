package a0107;

public class Ramyun {
  public static void main(String[] args) {
    buy();
    boil();
    put();
    eat();
    
  }

  private static void buy() {
    System.out.println("라면을 사온다.");
    // static 객체 만들 필요없이 호출 가능
    // void 반환값이 없을 때 사용
  }
  
  static void boil() {
    System.out.println("라면을 끓인다.");
  }

  static void put() {
    System.out.println("라면을 넣는다.");
  }

  static void eat() {
    System.out.println("라면을 먹는다.");
  }

}
