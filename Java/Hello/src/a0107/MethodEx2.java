package a0107;

public class MethodEx2 {
  public static void main(String[] args) {
    // 삼겹살 1인분 무게: 180g
    // 1g 당 칼로리: 5.179kcal
    // 삼겹살 n인분의 칼로리 계산
    // 총 칼로리는 메소드의 정의와 호출 통해 계산
    // 출력 예: 삼겹살 3인분의 칼로리: 2796.66 kcal

    int n = 3;
    double k = kcal(n);

    System.out.printf("삼겹살 %d인분의 칼로리: %.2f kcal", n, k);

    // double n = 3;
    // System.out.printf("삼겹살 %.0f인분의 칼로리: %.2f kcal", n, k);

  }

  private static double kcal(int n) {

    return 180 * n * 5.179;

  }
}
