package a0105;

import java.util.Scanner;

public class Array4 {
  public static void main(String[] args) {
    // 사용자로 부터 정수 3개를 입력받아 저장 및 출력하시오.
    // for 문 이용

    // Scanner sc = new Scanner(System.in);
    // System.out.print("숫자 입력 : ");
    // int a = sc.nextInt();
    // System.out.print("숫자 입력 : ");
    // int b = sc.nextInt();
    // System.out.print("숫자 입력 : ");
    // int c = sc.nextInt();
    // int[] arr = {a, b, c};

    // System.out.println("==구분선==");
    //   for (int i = 0 ; i < arr.length ; i++){
    //     // 배열의 갯수 : nums.length 
    //   System.out.println(arr[i]);
    //   }

    Scanner sc = new Scanner(System.in);
    int[] arr = new int[3];
    for(int i = 0 ; i < arr.length; i++){
      arr[i] = sc.nextInt();
      // System.out.println(arr[i]);
      // arr[0] = 10 arr[1] = 20 arr[2] = 30
      // 역순으로 출력하려면
    }
    for(int i = arr.length - 1 ; i >= 0; i--){
      System.out.println(arr[i]);
    }
  }
}
