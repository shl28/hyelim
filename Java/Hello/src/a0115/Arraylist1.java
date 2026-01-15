package a0115;

import java.util.ArrayList;
// ArrayList 사용 시 import 필요
// import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
// Iterator 사용 시 import 필요

public class Arraylist1 {
    public static void main(String[] args) {
        // 컬렉션
        // <데이터형식> : 제네릭  // 참조형 , 객체 쓸 수 있음
        // Integer : int 의 참조형
        ArrayList<Integer> arrList = new ArrayList<Integer>();
        // add() 메소드를 이용한 요소 저장
        arrList.add(40);
        arrList.add(20);
        arrList.add(30);
        arrList.add(10);

        // for 문과 get() 메소드 이용해서 요소 출력
        // 배열: arrList.length  |  리스트: arrList.size()
        for(int i = 0; i <arrList.size(); i++){
            System.out.print(arrList.get(i) + " ");
        }

        System.out.println();

        // remove() 메소드를 이용한 요소 제거
        arrList.remove(1);

        // Enhanced for문
        for(int e : arrList){
            System.out.print(e + " ");
        }

        System.out.println();

        // 컬렉션 정렬 제공
        Collections.sort(arrList);

        // iterator() 메소드와 get() 메소드를 이용한 요소의 출력
        Iterator<Integer> iter = arrList.iterator();
        while (iter.hasNext()) {
            System.out.print(iter.next() + " ");
        }
        System.out.println();

        // ArrayList 수정 가능
        // set() 메소드를 이용한 요소의 변경(수정)
        arrList.set(0, 20);

        for(int e : arrList){
            System.out.print(e + " ");
        }

        System.out.println();

        // size() 메소드를 이용한 요소의 총 크기
        System.out.println("리스트의 크기 : " + arrList.size());
        
    }
}
