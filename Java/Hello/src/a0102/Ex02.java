package a0102;

public class Ex02 {
  public static void main(String[] args) {
    int evenSum = 0;
    int oddSum = 0;

    // for 문을 이용하여 반복 합계 연산
    for (int i=1; i<=100; i++){
      // 코드작성
      if (i % 2 == 0) {
        evenSum = evenSum + i;
      }
    }
    System.out.println("짝수의 합계는 " + evenSum);

    for (int i=1; i<=100; i++){
      // 코드작성
      if (i % 2 == 1) {
        oddSum = oddSum + i;
      }
    }

    System.out.println("홀수의 합계는 " + oddSum);

    // 실행 결과
    // 짝수의 합계는 2550
    // 홀수의 합계는 2500


  }
}
