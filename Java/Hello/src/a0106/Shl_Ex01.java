package a0106;

public class Shl_Ex01 {
  public static void main(String[] args) {
    int[][] arr = {
      {1, 2, 3},
      {4, 5, 6}
    };
    // 출력: 1열 합: 5, 2열 합: 7, 3열 합: 9

    int sum = 0;

    System.out.print("1열 합: ");
    for(int i = 0; i < arr.length; i++){
      for(int j = 0; j<arr[i].length; j++){
        if (j == 0) {
          sum += arr[i][j];
        }
      }
    }
    System.out.print(sum);
    System.out.print(", 2열 합: ");

    for(int i = 0; i < arr.length; i++){
      for(int j = 0; j<arr[i].length; j++){
        if (j == 1) {
          sum += arr[i][j];
        }
      }
    }
    System.out.print(sum);
    System.out.print(", 3열 합: ");

    for(int i = 0; i < arr.length; i++){
      for(int j = 0; j<arr[i].length; j++){
        if (j == 2) {
          sum += arr[i][j];
        }
      }
    }
    System.out.print(sum);
  }
}
