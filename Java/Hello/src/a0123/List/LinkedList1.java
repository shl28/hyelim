package a0123.List;

import java.util.LinkedList;

public class LinkedList1 {
    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>();

        list.add("첫번째");
        list.add("두번째");
        list.add("세번째");
        list.add("세번째");

        System.out.println("초기 출력");

        // 출력
        for(int i = 0; i < list.size(); i++){
            System.out.println(list.get(i));
        }

        // 앞에 추가
        list.add("네번째");

        System.out.println("출력2");
        for(int i = 0; i < list.size(); i++){
            System.out.println(list.get(i));
        }

        // 앞에 추가
        list.addFirst("맨 앞");

        // 뒤에 추가
        list.addLast("맨 뒤");

        System.out.println("출력3");
        for(int i = 0; i < list.size(); i++){
            System.out.println(list.get(i));
        }

        // 첫 번째 / 마지막 요소 가져오기
        String first = list.getFirst();
        String last = list.getLast();

        System.out.println("\n첫 번째 요소: " + first);
        System.out.println("마지막 요소: " + last);

        // // 첫 번째 요소 삭제
        // list.removeFirst();

        // // 마지막 요소 삭제
        // list.removeLast();

        list.remove(2);

        System.out.println("출력4");
        for(String e : list){
            System.out.println(e);
        }


    }
}
