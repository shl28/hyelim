package a0106;

public class Array11 {
  public static void main(String[] args) {
    // 1~9 까지 저장하기

    int[][] arr = new int [3][3]; //크기 3행 3열 지정

    // 기본값
    // ┌─────┬─────┬─────┐
    // │  0  │  0  │  0  │
    // ├─────┼─────┼─────┤
    // │  0  │  0  │  0  │
    // ├─────┼─────┼─────┤
    // │  0  │  0  │  0  │
    // └─────┴─────┴─────┘

    // 원하는 값
    // ┌─────┬─────┬─────┐
    // │  1  │  2  │  3  │
    // ├─────┼─────┼─────┤
    // │  4  │  5  │  6  │
    // ├─────┼─────┼─────┤
    // │  7  │  8  │  9  │
    // └─────┴─────┴─────┘


    int num = 1;   // 배열 내 숫자 증가용 변수

    for(int i = 0; i < arr.length; i++){
      for(int j = 0; j < arr[i].length; j++){
        arr[i][j] = num++; // 변수++(후위): 값을 준 후 증가
      }
    }

    for(int i = 0; i < arr.length; i++){
      for(int j = 0; j < arr[i].length; j++){
        System.out.print(arr[i][j] + " ");
      }
      System.out.println();  // 행 출력 후 줄바꿈
    }


    // 위와 동일
    //   for(int i = 0; i < arr.length; i++){
    //   for(int j = 0; j < arr[i].length; j++){
    //     arr[i][j] = num++; // 변수++(후위): 값을 준 후 증가
    //     System.out.print(arr[i][j] + " ");
    //   }
    //   System.out.println(); 
    // }


  }
}
