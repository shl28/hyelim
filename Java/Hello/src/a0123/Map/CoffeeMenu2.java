package a0123.Map;

import java.util.HashMap;
import java.util.Scanner;

public class CoffeeMenu2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // 커피 메뉴 초기화
        HashMap<String, Integer> menu = new HashMap<>();
        menu.put("아메리카노", 4000);
        menu.put("카페라떼", 4500);
        menu.put("카푸치노", 5000);
        menu.put("에스프레소", 3500);
        menu.put("바닐라라떼", 5500);
        menu.put("카라멜마키아토", 6000);

        // 메뉴 가격 조회
        System.out.print("조회할 커피 메뉴를 입력하세요 : ");
        String coffeName = scanner.nextLine();
        
        if (menu.containsKey(coffeName)) {
            int price = menu.get(coffeName);
            System.out.println(coffeName + "의 가격은 " + price + "원 입니다.");
        } else {
            System.out.println("해당 메뉴가 없습니다.");
        }
        scanner.close();
        
    }
}
