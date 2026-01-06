package a0106;

public class Ex04 {
  public static void main(String[] args) {
    int [] num1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    int [] num2 = new int[10];
    // num2 제곱 출력    
    // 코드 작성

    for(int i = 0; i < num1.length; i++){ //계속 for (1;2;3) 1 자리에 int 빼먹고 i 만 쓰는데 주의할 것.
      num2[i] = num1[i] * num1[i] ;
      // num2[i] += num1[i] * num1[i] ; //답지
      // 내가 생각한 이유: 어차피 대입 전 num2[변수] = 0 으로 초기화 했으므로 0에 num1 제곱값을 더하는 것으로 값의 차이가 없음
    }

    // num2 출력
    for(int i=0; i<num2.length; i++){
      System.out.println(num2[i]);
    }
   } 
}
