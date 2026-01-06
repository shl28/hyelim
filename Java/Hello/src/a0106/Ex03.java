package a0106;

public class Ex03 {
  public static void main(String[] args) {
    int [] score = {90, 80, 60, 100};

    int totalScore= 0;
    double avgScore = 0;

    // 코드작성
    for(int i = 0; i < score.length; i++){
      totalScore += score[i];
      // avgScore =(double)totalScore / score.length ; //i가 바뀔때마다 totalScore 가 쌓임
    }

    avgScore =(double)totalScore / score.length ; //답지
    // 내 생각: 평균은 최종을 구하므로 for 문 밖에 입력

    System.out.println("합계 점수 : " + totalScore);
    System.out.println("평균 점수 : " + avgScore);

  }
}
