package a0113.homework;

// 부모 클래스
class Calculator {
    String owner;

    public Calculator(String owner) {
        this.owner = owner;
    }

    // [1] 오버로딩 (Overloading): 이름은 같지만 입력값(매개변수)이 다름
    // "정수 두 개를 더하는 도구"
    public int add(int a, int b) {
        return a + b;
    }

    // "정수 세 개를 더하는 도구" (이름은 같은 add지만 과적함)
    public int add(int a, int b, int c) {
        return a + b + c;
    }

    public void showDisplay() {
        System.out.println(owner + "의 계산기입니다.");
    }
}

// 자식 클래스
class SmartCalculator extends Calculator {
    
    public SmartCalculator(String owner) {
        super(owner);
    }

    // [2] 오버라이딩 (Overriding): 부모의 기능을 내 입맛에 맞게 덮어씀(재정의)
    @Override
    public void showDisplay() {
        System.out.println("--- " + owner + "의 스마트 계산기 구동중 ---");
    }
}

public class OverridingVsOverloading {
    public static void main(String[] args) {
        SmartCalculator sc = new SmartCalculator("민수");

        System.out.println("==== 1. 오버로딩 테스트 (입력값에 따라 다름) ====");
        System.out.println("2개 더하기: " + sc.add(10, 20));      // 부모의 1번 메서드 사용
        System.out.println("3개 더하기: " + sc.add(10, 20, 30));  // 부모의 2번 메서드 사용

        System.out.println("\n==== 2. 오버라이딩 테스트 (재정의된 기능) ====");
        sc.showDisplay(); // 자식에서 덮어쓴 기능이 실행됨
    }
}