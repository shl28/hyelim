package a0128.ramda;

// import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ramda4 {
    public static void main(String[] args) {
        // List<String> name = new ArrayList<>();
        // name.add("apple");
        // name.add("banana");
        // name.add("orange");
        // for(int i = 0; i < name.size(); i++) {
        //     System.out.println(name.get(i));
        // }

        List<String> names = Arrays.asList("apple", "banana", "orange");
        System.out.println();
        for(String name : names){
            System.out.println(name);
        }

        System.out.println();

        // 람다 for each
        names.forEach(name -> System.out.println(name));
        
        System.out.println();

        // Stream : 스펠링 갯수(길이)가 6이상 요소만 필터링 
        names.stream().filter(name -> name.length() >= 6).forEach(System.out::println);
        // stream() : 리스트를 순회
        // filter(조건) = if(조건)
        // forEach(출력) = System.out.print

        System.out.println();

        for(int i = 0; i < names.size(); i++){
            String name = names.get(i);
            if (name.length() >= 6) {
                System.out.println(name);
            }
        }

        System.out.println();
        for(String name : names){
            if (name.length() >= 6) {
                System.out.println(name);
            }
        }
    }
}
