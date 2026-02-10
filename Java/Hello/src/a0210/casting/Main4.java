package a0210.casting;

class Shape {
    void draw() {
        System.out.println("도형 그리기");
    }
}

class Circle extends Shape {
    void draw() {
        System.out.println("원 그리기");
    }
}

public class Main4 {
    public static void main(String[] args) {
        Shape s = new Circle();
        s.draw();
    }
}

// 부모 타입 = 자식 객체 ○
// 자식 타입 = 부모 객체 X
// 메서드 실행 = 실제 객체 기준

// Parent p = new Child();   // 업캐스팅 (자동)
// Child c = (Child)p;      // 다운캐스팅 (명시)

