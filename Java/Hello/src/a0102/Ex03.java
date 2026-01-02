package a0102;

public class Ex03 {
  public static void main(String[] args) {
    
    for (int x = 1; x <=6; x++){
      for (int y = 1; y <= 6; y++){
        // 코드작성
        if(x + y == 6){
          System.out.println("(" + x + "," + y + ")");
        }
      }
    }
  }
  
  // 실행결과
  // (1,5)
  // (2,4)
  // (3,3)
  // (4,2)
  // (5,1)
  
}
