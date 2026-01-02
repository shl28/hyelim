package a0102;

public class Println {
  public static void main(String[] args) {
    // println() 메서드: 출력 후 줄바꿈
    // print() 메서드: 출력 후 줄바꿈 안 함
    // printf: 형식 지정 출력

    int age = 20;
    System.out.println("나이는 " + age + "살 입니다.");
    System.out.printf("나이는 %d살 입니다.\n" , age);
    // %d : 정수 -> int & %d 

    double avg = 87.456;
    System.out.printf("평균: %.1f점\n" , avg);
    // %.1f : 실수 + 소수 첫째자리에서 반올림

  }
}
