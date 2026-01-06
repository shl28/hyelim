package a0106;

public class Array8 {
  public static void main(String[] args) {

    int[][] arr = {
          {10, 20},
          {30, 40},
          {50, 60}
      };

      // sum 변수 주기
      // 모든 배열의 합을 구하시오.

      int sum = 0 ;

      for(int i = 0; i < arr.length; i++){
        for(int j = 0; j< arr[i].length; j++){
          sum  += arr[i][j]; // sum = sum + arr[i][j];
        }
      }
      System.out.println("모든 수 합계 : " + sum);

  }
}
