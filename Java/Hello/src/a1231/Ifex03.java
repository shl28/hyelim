package a1231;

public class Ifex03 {
    public static void main(String[] args) {
       
    // ## 문제 10: 복합 조건 - 입장료 계산
    // 나이와 학생 여부에 따라 입장료를 계산하는 프로그램을 작성하세요.

    // **입장료 기준:**
    // - 65세 이상: 무료
    // - 학생(초등학생~대학생): 50% 할인 (기본 요금 10,000원)
    // - 일반 성인: 10,000원
    // - 7세 미만: 무료

    // **요구사항:**
    // - if-else if-else 문 사용
    // - 나이와 학생 여부를 변수로 저장
    // - 최종 입장료를 출력

    int age = 15;
    boolean isStudent = age >=8 && age <= 19;
    int price = 10000;

    if (age>=65) {
        System.out.println("가격은 무료입니다.");
    } else if (age>=20) {
        System.out.println("가격은 " + price + "원 입니다.") ;
    } else if (isStudent) {
       System.out.println("가격은 " + (int)(price * 0.5) + "원 입니다.");
    } else {
        System.out.println("가격은 무료입니다.");
    }


    }
}
