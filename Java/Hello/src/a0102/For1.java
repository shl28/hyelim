package a0102;

public class For1 {
  public static void main(String[] args) {
    for(int i = 1 ; i<=10; i++){
      System.out.println(i);
    }

    for(int j = 10 ; j>=1; j--){
      System.out.println(j);
    }

    for(int k = 0 ; k<=10; k+=2){ //k=k+2 
      System.out.println(k);
    }

    int sum = 0;
    for(int m = 1 ; m<=10; m++){ 
      sum = sum + m; 
    }
    System.out.println("1~10 합: "+ sum);

    // 연습 1-20 까지 짝수

    // System.out.println("\n1~20까지 짝수");
    // for(int o = 0 ; o<=20; o+=2){  
    //   System.out.println(o);

    System.out.println("==답==");
    for(int i=1; i<=20; i++){
      if (i % 2== 0) {
        System.out.println(i);
      }
    }

    System.out.println("==연습==");
    for(int n=1; n<=5; n++){
      System.out.println("2 X " + n + " = " + 2 * n);
    }

    System.out.println("==구구단 2단==");
    for(int i=1; i<=9; i++){
      System.out.println("2 X " + i + " = " + (2*i));
    }

    // 변수 선언 위치
    // for문 바깥에서 선언하면 for문 종료 후에도 사용 가능

    int j; //for문 외부 변수 = 전역변수 //for문 내부 변수 = 지역변수
    for(j=1; j<=9; j++){
      System.out.print(j + " "); //ln 없는 print 는 옆으로 나열 
    }
    System.out.println("\nfor문 종료 후 j 값: " + j);

  }
}

