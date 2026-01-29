package a0128.ramda;

interface Calculator2 {
    int sum(int a, int b);
    // 인터페이스는 sum 추상메서드 하나만 가지고 있다.
}

public class Ramda2 {
    public static void main(String[] args) {
        // 익명 클래스
        // Calculator mc = (a, b) -> {return a + b; };
        Calculator2 mc = (a, b) ->  a + b; 
        // 수식 하나는 return , {} 생략 가능
        int result = mc.sum(3, 4);
        System.out.println("result = " + result);
    }
}
