package a0102;

public class Ex06 {
  public static void main(String[] args) {
    // 이중 for 문 이용하여 실행결과 출력
    // 실행결과
    //     *  
    //    *** 
    //   *****
    //  *******
    // *********

    for(int i = 1; i <= 5; i++){
      for(int j = 5 - i; j > 0; j--){
        System.out.print(" ");}
      for(int k = 1; k <= i * 2 - 1; k++){
        System.out.print("*");
      }
      System.out.println();
      
    }
    
  }
}

