package a0107;

public class Pork {
  public static void main(String[] args) {
    int n = 3;
    double x = calc(n);

    System.out.printf("삼겹살 %d인분의 칼로리: %.2fkcal", n, x);

  }

  private static double calc(int n) {

    int totalGram = n * 180 ; // 1인분당 180g
    double totalKcal = totalGram * 5.179;  // 1g 당 5.179kcal
    return totalKcal;

  }
}
