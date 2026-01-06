package a0106;

public class Ex02_1 {
  public static void main(String[] args) {
    int[][] arr = {
        {1},
        {1,2},
        {1,2,3},
        {1,2,3,4}
      };

    System.out.println("arr의 길이 : "+arr.length); //행의 수
    System.out.println("arr[2]의 길이 : "+arr[2].length); //2번 행 열의 수
  }
}
