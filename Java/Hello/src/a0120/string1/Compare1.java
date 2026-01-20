package a0120.string1;

public class Compare1 {
    public static void main(String[] args) {
        String str = new String("abcd");
        System.out.println("원본 문자열 : " + str);
        
        System.out.println(str.compareTo("bcef"));
        // 음수 : (유니코드, 아스키코드 값) a < b 이므로  // a(97) - b(98) = -1
        System.out.println(str.compareTo("abcd"));
        // 0 : 같다
        
        System.out.println(str.compareTo("Abcd"));
        // a(97) - A(65) = 32
        System.out.println(str.compareToIgnoreCase("Abcd"));
        // 대소문자 무시 비교
        System.out.println("compareTo() 메소드 호출 후 원본 문자열 : " + str);
    }
}
