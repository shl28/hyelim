package a1231;

import java.util.Scanner;
// 1. import 문장

public class Scanner2 {
    public static void main(String[] args) {
        // 2. Scanner 객체 생성(System.in) : 키보드입력
        Scanner sc = new Scanner(System.in);

        System.out.print("이름을 입력하세요: ");
        String name = sc.next();
        // 문자열 입력 (한 단어)
        // 한 줄 전체입력 (공백포함) : sc.nextLine();
        // char ch = sc.next(). charAt(0); -> 문자 1개 입력 받기

        System.out.print("나이를 입력하세요: ");
        int age = sc.nextInt(); 
        // 정수 입력: nextInt 
        // 실수 입력: nextdouble
        // Double avg = sc.nextDouble(); -> 실수입력

        System.out.println(name + "님의 나이는 " + age + "세 입니다.");

        sc.close();
        // 3. 사용이 끝난 후에는 닫아주는 것이 좋다.


    }
}
