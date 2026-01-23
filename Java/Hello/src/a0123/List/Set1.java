package a0123.List;

import java.util.HashSet;
import java.util.LinkedHashSet;

public class Set1 {
    public static void main(String[] args) {
        HashSet<String> set = new HashSet<>();

        // 요소 추가
        set.add("사과");
        set.add("바나나");
        set.add("오렌지");
        set.add("사과");  // 중복 추가 시도 (무시됨)

        for(String item : set){
            System.out.println(item);
        }

        System.out.println(set.size());  // 3

        // 요소 확인
        boolean hasApple = set.contains("사과");  // true
        System.out.println(hasApple);

        // 요소 삭제
        set.remove("바나나");

        // 반복문으로 순회
        for (String item : set) {
            System.out.println(item);
        }

        // 순서있는 입/출력
        LinkedHashSet<String> set1 = new LinkedHashSet<>();
        set1.add("사과");
        set1.add("바나나");
        set1.add("오렌지");

        System.out.println("=== 순서있는 입출력 ===");
        // 삽입 순서대로 출력됨  // 향상 for 문으로만 출력됨 // i 사용 for 문은 인덱스 넘버 저장하지 않기 때문에 사용 불가
        for (String item : set1) {
            System.out.println(item);
        }
    }
}
