package a0121.textex01.ex04;

public class StringUtil {

    public static int countVowels(String text) {
        if (text == null) {
            return 0;
        }
        int count = 0;
        String lowerText = text.toLowerCase();
        for(int i = 0; i < text.length(); i++) {
            char ch = lowerText.charAt(i);
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
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
                sb.append((char)(ch-32));
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
        
    }

    public static String reverseString(String text2) {
        if (text2 == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for(int i = text2.length() - 1; i >= 0 ; i--){
            sb.append(text2.charAt(i));
        }
        return sb.toString();
    }

    public static String toRemove(String text) {
        if (text == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < text.length(); i++){
            char ch = text.charAt(i);
            if (ch != ' ') {
                sb.append(ch);
            }
        }
        return sb.toString();
    }


}
