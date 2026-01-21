package a0121.ex04;

public class StringUtil {

    public static int countVowels(String text) {
        int count = 0;

        String lowerText = text.toLowerCase();  // 매개변수 text 를 소문자로 변환
        // Hello → hello
        
        for(int i = 0; i < lowerText.length(); i++){
            char ch = lowerText.charAt(i); 
            // charAt(0) : 첫번째 글자   charAt(1) : 두번째 글자

            if (ch == 'a' || ch =='e' || ch =='i' || ch =='o' || ch =='u') {
                count++;
            }
        }
        return count;
    }

    public static String toUpperCase(String text) {
        if (text == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < text.length(); i++){
            char ch = text.charAt(i);
            if (ch >= 'a' && ch <= 'z') {
                sb.append((char)(ch-32));  // 대문자가 소문자 보다 아스키코드 -32 임
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    public static boolean containsWord(String text, String word) {
        if (text == null || word == null) {
            return false;
        }
        return text.contains(word);  // contains() : 단어 포함 여부 메서드  →  text 에 word 포함되어 있으면 true 반환
    }

    public static String replaceChar(String text, char oldChar, char newChar) {
        if (text == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < text.length(); i++){
            char ch = text.charAt(i);
            if (ch == oldChar) {
                sb.append(newChar);
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

}
