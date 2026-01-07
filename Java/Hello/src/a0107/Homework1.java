package a0107;

public class Homework1 {
  public static void main(String[] args) {
// 휘발유 8.86리터(L)를 충전한 자동차의 총 주행거리가 182.736km일 때, 해당 자동차의 연비를 계산하는 프로그램을 작성하시오. 
// 단, 연비 계산 과정은 메소드의 호출과 정의를 통해 구한다.
// 연비 = 이동거리/사용량

    double i = 8.86;  // 사용량
    double j = 182.736;  // 이동거리
    double result = calc(i, j);

    System.out.println("자동차 연비: " + result);

  }

  private static double calc(double i, double j) {
    return j / i;
  }

}