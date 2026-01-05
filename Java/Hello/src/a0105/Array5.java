package a0105;

public class Array5 {
  public static void main(String[] args) {
    // 배열 {70, 85, 90, 60, 75}
    // 평균을 구하고
    // 평균점수 이상인 점수만 출력

    // int[] arr = {70, 85, 90, 60, 75};
    // int sum = 0 ;

    // for(int i = 0; i < arr.length; i++){
    //   sum += arr[i];
    // }
    // System.out.println("arr의 합계 : " + sum);

    // double avg = sum / 5

    int[] score = {70, 85, 90, 66, 75};
    int sum = 0 ;
    for(int i = 0 ; i < score.length; i++){
      sum += score[i]; //sum = sum + score[i];
    }
    System.out.println(sum); //386
    double avg = (double) sum / score.length;
    // 계산을 실수로 하려면 (double) 형변환, 캐스팅 필요
    System.out.println(avg); //386

    System.out.println("===구분선===");
    for (int i = 0 ; i < score.length; i++){
      if (score[i] >= avg) {
        // 배열 들어있는 값을 순회하면서 평균보다 크면 출력
        System.out.println("평균보다 큰 "+ i + " : " + score[i]);
      }
    }


  }
}
