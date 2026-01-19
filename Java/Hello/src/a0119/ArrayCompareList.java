package a0119;

import java.util.ArrayList;
import java.util.Arrays;

public class ArrayCompareList {
    public static void main(String[] args) {
        // array : 크기 고정
        int [] arr = new int[3];
        arr[0] = 10;
        arr[1] = 20;
        arr[2] = 30;

        for(int i = 0; i < arr.length; i++){
            System.out.println(arr[i]);
        }

        for(int n : arr){
            System.out.println(n);
        }

        // array : 삭제 불가
        // arr[1] = 0 넣는 등 초기화만 가능
        // 굳이 한다면 새배열 생성 후 복사해서 삭제 함


        // array 수정 가능
        // arr[1] = 99;

        // 검색 
        for(int i = 0; i < arr.length; i++){
            if (arr[1] == 20) {
                System.out.println("찾음");
            } 
        }

        // ArrayList : 크기 고정 X, 자동 증가
        // 컬렉션<제네릭>
        ArrayList <Integer> list = new ArrayList<>();
        list.add(10);
        list.add(20);
        list.add(30);

        for(int i = 0; i < list.size(); i++){
            System.out.println(list.get(i));
        }

        System.out.println();

        for(int n : list){
            System.out.println(n);
        }

        System.out.println();

        System.out.println(list);

        // ArrayList : 삭제 가능
        list.remove(1);   // index 번호로 삭제
        // list.remove("사과");    // 값으로 삭제
        // 삭제하면 자동으로 앞으로 당겨짐

        // ArrayList : 수정 가능
        list.set(1, 99);

        // ArrayList 검색
        if (list.contains(10)) {
            System.out.println("있음");
        }
        
        // ArrayList 검색2 (참고만) : 인덱스 값을 반환, 없을 경우 '-1'을 반환
        int index = list.indexOf(50);
        System.out.println(index);

        // array → ArrayList 변환 (Arrays.asList) - import 필수
        String[] arr1 = {"사과", "배"};
        ArrayList <String> list1 = new ArrayList<>(Arrays.asList(arr1));
        System.out.println(list1);
        
    }
}
