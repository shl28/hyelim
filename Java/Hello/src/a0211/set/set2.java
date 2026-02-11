package a0211.set;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class set2 {
    public static void main(String[] args) {
        List<String> items = new ArrayList<>();

        items.add("apple");
        items.add("banana");
        items.add("orange");
        items.add("apple");
        items.add("grape");

        // HashSet 사용해 중복 제거
        Set<String> uniqueItems = new HashSet<>(items);
        System.out.println("중복을 제거한 리스트 : " + uniqueItems);
    }
}
