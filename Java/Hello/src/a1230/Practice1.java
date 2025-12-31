package a1230;

public class Practice1 {
    public static void main(String[] args) {
        /*
        1. **산술 연산자**
        - 두 정수를 입력받아 사칙연산과 나머지를 계산하는 프로그램을 작성하세요.
        2. **비교 연산자**
        - 나이를 입력받아 성인(18세 이상) 여부를 판단하는 프로그램을 작성하세요.
        3. **논리 연산자**
        - 점수를 입력받아 60점 이상 100점 이하인지 확인하는 프로그램을 작성하세요.
        4. **증감 연산자**
        - 전위와 후위 증감 연산자의 차이를 보여주는 예제를 작성하세요.
        5. **연산자 우선순위**
        - 다음 식의 결과를 계산하고, 괄호를 사용하여 우선순위를 명확히 하세요:
        `result = 10 + 5 * 2 - 3 / 1`
        */
         
        // 1.
        int a = 10;
        int b = 5;
        int result1= a + b; //15
        int result2= a - b;//5
        int result3= a * b; //50
        int result4= a / b; //2
        int result5= a % b; //0

        System.out.println("\n===답안 1===");
        System.out.println("결과1: " + result1);
        System.out.println("결과2: " + result2);
        System.out.println("결과3: " + result3);
        System.out.println("결과4: " + result4);
        System.out.println("결과5: " + result5);

        // 2.
        int age1 = 15;
        int age2 = 25;
     System.out.println("\n===답안 2===");
        System.out.println("나이1: " +age1);
        System.out.println("성인여부1: " + (age1>=18));
        System.out.println("나이2: " +age2);
        System.out.println("성인여부2: " + (age2>=18));

        // 3.
        int score1 = 50;
        int score2 = 80;
        System.out.println("\n===답안 3===");
        System.out.println("점수: " +score1);
        System.out.println("점수여부1: " + (score1>=60&&score1<=100));
        System.out.println("점수2: " +score2);
        System.out.println("성인여부2: " + (score2>=60&&score2<=100));

        // 4.
        int d = 5;
        int f = ++d;
        int g = d++;

        System.out.println("\n===답안 4===");
        System.out.println("결과f: "+ f);
        System.out.println("결과d: "+ d);
        System.out.println("결과g: "+ g);

        // 5. result = 10 + 5 * 2 - 3 / 1`  -> 17

        int result_1 = 10 + (5*2) - (3/1);
         System.out.println("\n===답안 5===");
        System.out.println("결과: " + result_1);
    }
}
