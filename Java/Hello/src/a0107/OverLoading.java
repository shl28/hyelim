package a0107;

public class OverLoading {
  public static void main(String[] args) {
    // 정수 2개 더하는 메서드
    int result1 = add(10, 20);
    System.out.println(result1);

    // 정수 3개 더하는 메서드
    int result2 = add(10, 20, 30);
    System.out.println(result2);
  
    // 실수 2개 더하는 메서드(매개변수 타입 다름)
    double result3 = add(10.5, 20.3);
    System.out.println(result3);

    // 정수와 실수를 더하는 메서드
    double result4 = add(10, 20.3);
    System.out.println(result4);

  }

  // 정수 2개 더하는 메서드
  private static int add(int i, int j) {
    return i + j;
  }
  // 정수 3개 더하는 메서드(메서드명 같으나 매개변수 개수 다름)
  private static int add(int i, int j, int k) {
    return i + j + k;
  }
  // 실수 2개 더하는 메서드(매개변수, 반환 타입 다름)
  private static double add(double d, double e) {
    return d + e;
  }
  // 정수와 실수를 더하는 메서드
  private static double add(int d, double e) {
    return d + e;
  }

}
