package exam.ex04;

public class StringUtil {

    public static int countChar(String text, char ch) {
        int count = 0;
        String lowerText = text.toLowerCase();
        for(int i = 0; i < lowerText.length(); i++){
            if (ch == lowerText.charAt(i)) {
                count++;
            }
        }
        return count;
    }

    public static String reverseString(String text) {
        if (text == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for(int i = text.length() - 1; i >= 0 ; i--){
            char ch = text.charAt(i);
            sb.append(ch);
        }
        return sb.toString();
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

    public static String removeSpaces(String text) {
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
