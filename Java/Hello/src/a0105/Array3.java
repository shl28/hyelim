package a0105;

public class Array3 {
  public static void main(String[] args) {
    //  문제
    int[] arr = {5, 8, 3, 10, 2};
    int max = arr[0];
    // 배열에 들어있는 5개 수 중에 가장 큰 수를 출력해보세요.
    // 힌트 arr[i] max 값 비교해서 arr[i] 가 크면 arr[i]값을 max에 대입

    // for(int i=0 ; i <arr.length; i++){
    //   if (arr[i] > max) {
    //     System.out.println(arr[i]);
    //   }
    // }

    for(int i = 1; i < arr.length; i++){
      if (arr[i] > max) {
        max = arr[i];
        // arr[1] = 8 > 5 => max = 8 
        // arr[2] = 3 > 8 => max = 8  : 대입없이 8 그대로 저장
        // arr[3] = 10 > 8 => max = 10  : 10 대입
        // arr[4] = 2 > 8 => max = 10 : 대입없이 10 그대로 저장
      }

    }
  
    System.out.println("최대값 : " + max);
  }
}
