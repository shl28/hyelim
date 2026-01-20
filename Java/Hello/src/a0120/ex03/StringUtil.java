package a0120.ex03;

public class StringUtil {

    // public : 어디서나 호출 가능
    // static : 객체 생성없이 SrtingUtil.countWords() 처럼 메서드 사용 가능
    // static : StringUtil 객체 만들지 않고 class명으로 메서드 사용 가능
    public static int countWords(String text1) {
        // [중요] 단어의 개수를 반환해야 하므로 반환 타입 int 로 설정
        // String text : 검사(단어의 갯수를 셈)할 문자열 , 대상

        // 1점
        if (text1 == null || text1.trim().isEmpty()) {
            return 0;
        }

        // 4점
        String[] words = text1.trim().split("\\s+");
        // 공백문자 s+ : 공백 1개 이상
        return words.length;

        // 기초 버전 - 2점
        // String[] words = text1.split(" ");
        // // 공란을 기준으로 배열을 나눈다.
        // System.out.println(words[0]);  // 안녕하세요
        // System.out.println(words[1]);  // 자바입니다
        // return words.length;

    }

    public static String reverseString(String text2) {
        // 1점
        if (text2 == null) return null;

        // 4점
        StringBuilder sb = new StringBuilder();
        for (int i = text2.length() - 1; i >= 0; i--) {
            sb.append(text2.charAt(i));  
            // sb 하나로 관리
            // append : StringBuilder 객체의 맨 끝에 새로운 데이터를 추가
        }
        return sb.toString();

        // 4점
        // if (text2 == null) return null;
        // return new StringBuilder(text2).reverse().toString();

        // 2점
        // String result = "";
        // for(int i = text2.length() - 1; i >= 0; i--){
        //     result += text2.charAt(i);
        // }
        // return result;
        // String 불변객체 
        // → 기존 문자열 버리고 새로운 문자열 생성, 새 문자열에 값 복사 : result를 계속 만듦
        // 반복문 안에서 계속 발생 - 성능 매우 나쁨

    }

    public static int findMax(int[] arr) {
        // 반환타입 int 로 수정
        if (arr == null || arr.length == 0) {
            return -1; // 신호값
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
        // 반환타임 boolean 으로 설정
        if (arr == null) {
            return false;
        }
        for (int num : arr) {
            if (num == value) {
                return true;
            }
        }
        return false;
    }

}
