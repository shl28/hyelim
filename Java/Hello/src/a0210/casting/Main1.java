package a0210.casting;

public class Main1 {
    public static void main(String[] args) {
        Parent p = new Child();
        System.out.println(p.x);  // 100 출력 : 변수는 다형성 적용되지 않음
        p.show();  // 메서드 다형성 적용, 실제 객체는 자식클래스 Child -> Child.show() 실행
    }
}

class Parent {
    int x = 100;
    
    void show() {
        System.out.println("Parent: " + x);
    }
}

class Child extends Parent {
    int x = 200;
    
    void show() {
        System.out.println("Child: " + x);
    }
}
