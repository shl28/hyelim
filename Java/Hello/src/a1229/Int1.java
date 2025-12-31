package a1229;

public class Int1 { 
  public static void main(String[] args) {
    //정수와 실수
    int var1 = 10;
    System.out.println(var1);
    // int 2var= 20; 숫자로 시작하면 안됨
    int _var2=20;
    int $var= 30;
    // int #var4= 40; 맨 앞 자리 특수문자는 $, _ 말고 안쓰는게 좋음
    // int var5 = 1000000000; //int 범위 초과
    long var6 = 100000000000L; //Long 접미사 L or l
    double doubleVar = 3.141592653589793;
    float floatvar = 3.14F; //float 접미사 F or f
    System.out.println(doubleVar);
    byte byteVar = 127; // -128 ~ 
    System.out.println(byteVar);
    short shortVar = 32767; // -32768 ~ 32767
    System.out.println(shortVar);

    //문자형
    char charVar = 'A';
    System.out.println(charVar);
    char koreasChar = '가';
    System.out.println(koreasChar);
    char unicodeChar = '\u0041'; //유니코드로 A 표현
    System.out.println(unicodeChar);

    //논리형
    boolean isTrue = true;
    boolean isFalse = false;
    System.out.println(isTrue);
    System.out.println(isFalse);
    boolean result = (5>3); //true
    System.out.println(result);

  }
  
}
