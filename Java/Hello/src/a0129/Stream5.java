package a0129;

import java.util.Arrays;
import java.util.List;

public class Stream5 {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        int sum = numbers.stream().reduce(0, (a, b) -> a + b);
        // sum 0 초기값
        // a : 지금까지 누적값
        // b : 스트림에서 가져온 값
        // 초기값 = 0
        // 0 + 1 = 1
        // 1 + 2 = 3
        // 3 + 3 = 6
        // 6 + 4 = 10
        // 10 + 5 = 15
        // 결과: 15

        System.out.println(sum);
        
        // 최댓값
        int max = numbers.stream().reduce(Integer.MIN_VALUE, Integer::max);
        // 초기값: Integer.MIN_VALUE ->  int 가 가질 수 있는 가장 작은 값 : -21억
        // (a, b) -> Integer.max(a, b)  == Integer::max
        System.out.println(max);

        // 최솟값
        int min = numbers.stream().reduce(Integer.MAX_VALUE, Integer::min);
        // 초기값: Integer.MAX_VALUE ->  int 가 가질 수 있는 가장 큰 값 
        System.out.println(min);

        // 문자열 연결
        List<String> words = Arrays.asList("Hello", "World", "Java");
        String combined = words.stream().reduce("", (a, b) -> a + " " + b);
        // 초기값: 공란 ""
        System.out.println(combined);
    }
}
