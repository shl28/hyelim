package a0210.casting;

public class Main3 {
    public static void main(String[] args) {
        Parent1 p = new Child1();
        p.method();
        // 생성자 : 부모 > 자식 순서로 실행
        // 메서드 : 오버라이딩, 실제 객체 기준으로 실행, 자식 메서드만 실행
    }
}

class Parent1 {
    Parent1() {
        System.out.println("Parent 생성자");
    }

    void method() {
        System.out.println("Parent method");
    }
}

class Child1 extends Parent1 {
    Child1() {
        System.out.println("Child 생성자");
    }

    // 오버라이딩 된 메서드 실제 객체(Child) 기준으로 결정
    // @Override 
    void method() {
        System.out.println("Child method");
    }
}