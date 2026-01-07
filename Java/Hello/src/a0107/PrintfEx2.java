package a0107;

public class PrintfEx2 {
  public static void main(String[] args) {
    String name = "홍길동";
    int age = 25;
    double height = 175.5;
    System.out.printf("이름: %s, 나이: %d, 키: %.1f\n", name, age, height);
    
    double avg = 87.456;
    System.out.printf("평균점수 : %.2f점\n", avg);
    // % .2f  // .2 = 소수점 셋째자리에서 반올림해서 소수점 2자리까지만 출력

    // 자리 맞춤  
    // 음수 : 왼쪽정렬, 숫자는 칸의 수
    // 양수 : 오른쪽정렬, 숫자는 칸의 수 
    System.out.printf("%-10s\t %7d\n", "사과", 1200);
    System.out.printf("%-10s\t %7d\n", "바나나", 800);
    System.out.printf("%-10s\t %7d\n", "파인애플", 10000);

    // [Escape 문자] 
    // \n : 줄바꿈    
    // \t : 탭     
    // \" : 따옴표     
    // \\: 역슬래시

    System.out.println("===== 영수증 =====");
    System.out.println("상품명\t가격");
    System.out.println("커피\t3000원");
    System.out.println("케이크\t4500원");
    System.out.println("=================");
    
  }
}
