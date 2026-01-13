package a0113.homework;

public class OverloadEx1 {
// 오버로딩 오버라이딩 차이를 설명하는 예제 한개 만들어오기 숙제

// 오버로딩: 한 메서드가 여러 역할 수행
public static void main(String[] args) {
  int result1 = add(1, 2);

  System.out.println("==== 예시1 ====");
  System.out.println("결과: " + result1);

  System.out.println();

  int result2 = add(1, 2, 3);

  System.out.println("==== 예시2 ====");
  System.out.println("결과: " + result2);


}

private static int add(int i, int j) {
  return i + j;
}

private static int add(int i, int j, int k) {
  return i + j + k;
}

}
