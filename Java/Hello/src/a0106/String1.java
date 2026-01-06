package a0106;

public class String1 {
  public static void main(String[] args) {
    String str1 = "Hello"; // 주소 100번지에 Hello 넣음
    String str2 = "Hello"; // 동일한 것 찾아서 100번지에 Hello 넣음
    String str3 = new String("Hello"); // 새롭게 200번지에 Hello 넣음

    // == 연산자 : 참조(주소) 비교 (주소(번지)를 비교하는 것)
    System.out.println(str1 == str2); // 100 == 100 : true //Hello 가 아닌 번지 수를 비교하는 것 
    System.out.println(str2 == str3); // 100 == 200 : false

    // equals() 메서드:  내용 비교
    System.out.println(str1.equals(str2)); // Hello .equals Hello
    System.out.println(str2.equals(str3)); // Hello .equals Hello

  }
}
