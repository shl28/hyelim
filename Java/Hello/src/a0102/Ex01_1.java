package a0102;

public class Ex01_1 {
  public static void main(String[] args) {
    
    int sum = 0 ;
    // for  문을 이용하여 반복 합계 연산
    // for (int i=1; i<=100; i++){
    //   // 코드작성
    //   if(i % 5 == 0){
    //     sum = sum + i;
    //   }
    
      for (int i = 5; i <= 100 ; i += 5){
        sum += i;
      }

    System.out.println("5의 배수의 합계는 " + sum);

    // 실행결과
    // 5의 배수의 합계는 1050

  }
}
