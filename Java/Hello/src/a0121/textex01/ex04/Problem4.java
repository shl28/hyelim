package a0121.textex01.ex04;

public class Problem4 {
    public static void main(String[] args) {
        // 1. 모음 개수 세기
        System.out.println("모음 개수: " + StringUtil.countVowels("Hello"));

        // 2. 대문자 변환
        System.out.println("대문자: " + StringUtil.toUpperCase("hello world"));

        // 3. 뒤집기
        String text2 = "자바";
        System.out.println("뒤집기: " + StringUtil.reverseString(text2));

        // 4. 공백제거
        System.out.println("공백 제거: " + StringUtil.toRemove("hel lo wor ld")); 
    }
}
