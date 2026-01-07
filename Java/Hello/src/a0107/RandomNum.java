package a0107;

public class RandomNum {
  public static void main(String[] args) {
    int n = rollDie();
    System.out.printf("주사위의 눈: %d", n);
  }

  static int rollDie() {

    double x = 6 * Math.random(); // 결과: 실수(double)
    int temp = (int) x; // 형변환 (down casting) : 정수(int) 소수점 제거
    return temp + 1;

  }
}
