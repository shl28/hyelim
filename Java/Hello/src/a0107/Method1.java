package a0107;

public class Method1 {
  public static void main(String[] args) {
    // 메서드 사용이유
    // 코드 재사용
    // 가독성 향상
    // 유지보수 용이
    // 역할 분리(기능별 처리)

    int result = sum(10,20);
    int result1 = sum(30, 40);

    System.out.println(result);
    System.out.println(result1);

    // int result2 = sum(result, result1);
    // System.out.println(result2);

  }

  // static 객체 생성 없이 사용 가능
  static int sum(int a, int b) {
    // int : 반환값의 자료형
    // sum : 메서드(함수) 이름
    // int a, int b : 매개변수 (입력값 2개)  //내생각: x+y , x=20, y=10  ==   x,y 매개변수 20,10 인수

    return a + b;
    // return : a + b 결과를 호출한 곳으로 되돌려 줌 

  }

}
