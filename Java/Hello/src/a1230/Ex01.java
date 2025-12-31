package a1230;

public class Ex01 {
    public static void main(String[] args) {
        
        System.out.println("\n--- 복합 연산 예제 ---");
        int score1 = 85;
        int score2 = 90;
        int score3 = 78;

        // 1. 각 점수를 화면 인쇄
        // 2. 총점과 평균을 구해서 인쇄
        // 3. 평균 60점 이상이면 합격(true)
        // 4. 평균 90점 이상이면 우수(true)
        
        // 답

        System.out.println("score1: " + score1);
        System.out.println("score2: " + score2);
        System.out.println("score3: " + score3);

        System.out.println("총점: " + (score1 + score2 + score3));
        System.out.println("평균: " + ((score1 + score2 + score3)/3));

        System.out.println("합격: " + (((score1 + score2 + score3)/3)>=60));
        System.out.println("우수: " + (((score1 + score2 + score3)/3)>=90));


        System.out.println("\n답안지");
        // 강사님 답안
        // 1.
        System.out.println("점수: " + score1 + ", " + score2 + ", " + score3);
        // 2.
        int sum = score1 + score2 + score3;
        double avg = (double) sum / 3;
        System.out.println("총점: " + sum);
        System.out.println("평균: " + avg);
        // 3.
        boolean isPass = avg >= 60;
        System.out.println("합격 여부(>=60): "+isPass);
        // 4.
        boolean isExcellent = avg >= 90;
        System.out.println("우수 여부(>=90): "+isExcellent);
    }
}
