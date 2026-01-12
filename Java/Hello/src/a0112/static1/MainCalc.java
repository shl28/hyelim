package a0112.static1;

public class MainCalc {
  public static void main(String[] args) {

    // 평소 쓰던 내용
    // Calc cc1 = new Calc();
    // int result = cc1.add(3,5);

    // 객체 생성 없이 사용가능 (static 메서드)
    int result = Calc.add(3,5);
    System.out.println(result);

  }
}
