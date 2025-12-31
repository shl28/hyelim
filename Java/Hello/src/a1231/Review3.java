package a1231;

public class Review3 {
    public static void main(String[] args) {
        
        // 평균 구하기

        int kor = 84;
        int eng = 90;
        int math = 70;

        // double avg = (double)(kor + eng + math)/3;
        double avg = (kor + eng + math)/3.0;
        // 상수인 3을 실수 형태인 3.0으로 계산하면 실수 계산이 된다.
        System.out.println("평균: " + avg);


    }
}
