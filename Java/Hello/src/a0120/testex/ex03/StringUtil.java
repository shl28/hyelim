package a0120.testex.ex03;

public class StringUtil {

    public static int countWords(String text1) {
        if (text1 == null || text1.trim().isEmpty()) {
            return 0;
        }
        String[] words = text1.trim().split("\\s+");
        return words.length;

    }

    public static String reverseString(String text2) {
        if (text2 == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for(int i = text2.length() - 1; i >= 0; i--){
            sb.append(text2.charAt(i));
        }
        return sb.toString();
    }

    public static int findMax(int[] arr) {
        // if (arr == null) { // 틀림
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int max = arr[0];
        for(int i = 1; i < arr.length; i++){
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    public static boolean contains(int[] arr, int value) {
        if (arr == null) {
            return false;
        }
        for(int i = 0; i < arr.length; i++){
            if (arr[i] == value) {
                return true;
            }
        }
        return false;
    }
    
}
