package a0123.Map;

import java.util.HashMap;
import java.util.Map;

public class Map1 {
    public static void main(String[] args) {
        HashMap<String, Integer> scores = new HashMap<>();

        // 요소 추가
        scores.put("홍길동", 85);
        scores.put("김철수", 90);
        scores.put("이영희", 88);

        // 모든 키를 순회하는 방법
        for(String name : scores.keySet()){  // keySet() : "홍길동 김철수 이영희" key들만 set 으로 반환
            System.out.println(name + " : " + scores.get(name));
            // 해당 점수 조회 : scores.get(name)  = value
        }

        // 모든 값(value) 순회
        for(Integer score : scores.values()){
            System.out.println(score);
        }

        // 키-값 쌍 순회
        for(Map.Entry<String,Integer> entry : scores.entrySet()){
            System.out.println(entry.getKey() + " : " + entry.getValue());
            // [entry] key : String  value : Integer
        }
    }
}
