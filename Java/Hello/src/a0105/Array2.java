package a0105;

public class Array2 {
  public static void main(String[] args) {
    // 배열 {10, 20, 30, 40, 50}의 모든 값의 합계를 구하시오.

    // int[] nums = {10, 20, 30, 40, 50};
    // int sum = 0;
    // for (int i = 0 ; i < nums.length ; i++){
    //   int a = nums[i];
    //   sum += a;
     
    // }
    //  System.out.println(sum);


    int[] arr = {10, 20, 30, 40, 50};
    int sum = 0;
    for(int i=0 ; i <arr.length; i++){
      sum += arr[i];
      // i= 10 sum = 0 -> sum = 10
      // i= 20 sum =10 -> sum = 30
      // i= 30 sum =30 -> sum = 60
    }
    System.out.println("arr의 합계 : " + sum);

  }
}
