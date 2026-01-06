package a0106;

public class String2 {
  public static void main(String[] args) {
    String firstName = "홍";
    String lastName = "길동";
    String fullName = firstName + " " + lastName; //홍 길동
    System.out.println(fullName);

    // += 연산자
    String message = "Hello";
    message += " World"; // Hello World
    System.out.println(message);

    // concat() 메서드
    String str = "Hello";
    str = str.concat(" World"); // Hello World
    System.out.println(str); 

    // 길이와 빈 문자열 확인
    String str1 = "Hello";
    // length : 배열의 길이
    int len = str1.length();
    System.out.println("문자열 길이 : " + len);

    // isEmpty() : 빈 문자열
    boolean empty = str1.isEmpty(); // false
    System.out.println("비어있니? " + empty);

    String emptyStr = "";
    boolean isEmp = emptyStr.isEmpty(); // true

    System.out.println("비어있니? " + isEmp);

  }
}
