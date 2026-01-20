package a0120.string1;

public class IndexOf1 {
    public static void main(String[] args) {
        String str = new String("Oracle Java");
        System.out.println("원본 문자열 : " + str);
        
        System.out.println(str.indexOf('o'));
        // o 포함되어 있지 않음 : -1
        System.out.println(str.indexOf('a'));
        // a 포함되어 있음 : index number 출력
        System.out.println(str.indexOf("Java"));
        // Java 포함되어 있음 : 첫 글자의 index number 출력
        System.out.println("indexOf() 메소드 호출 후 원본 문자열 : " + str);
    }
}
