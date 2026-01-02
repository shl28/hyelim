package a0102;

public class While {
  public static void main(String[] args) {
    int i = 1;
    while (i <=5) {
      System.out.println("Hello World " + i);
      i++;
    }
    System.out.println();

    // 10부터 역순으로 출력

    int j = 10;
    while (j >= 1) {
      System.out.println(j);
      j--;
    }

    System.out.println("===1~100까지 합===");
    int sum = 0;
    int num = 1;
    while (num <=100) {
      sum = sum + num;
      num++;
      }
      System.out.println("1~100까지 합: " + sum);
    

  }
}
