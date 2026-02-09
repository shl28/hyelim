package a0209.string;

public class StringEx02 {
    public static void main(String[] args) {
        String text = "Hello World Java";
        
        // 검색
        System.out.println("'o'의 위치: " + text.indexOf('o'));  // 4
        System.out.println("'k'의 위치: " + text.indexOf('k'));  // -1
        System.out.println("'World' 포함: " + text.contains("World"));  // true
        
        // 추출
        System.out.println("0~5: " + text.substring(0, 5));  // "Hello"
        System.out.println("6부터: " + text.substring(6));    // "World Java"
        
        // 분리
        String[] words = text.split(" ");
        System.out.println("\n단어 분리:");
        for (String word : words) {
            System.out.println(word);
        }
        // 출력:
        // Hello
        // World
        // Java
    }
}
