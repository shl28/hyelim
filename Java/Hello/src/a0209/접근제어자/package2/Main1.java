package a0209.접근제어자.package2;

// import a0209.접근제어자.package1.Parent;

public class Main1 {
    public static void main(String[] args) {
        Child child = new Child();
        child.display();
        // 상속받은 자식클래스 내부에서 접근 가능

        // Parent parent = new Parent();
        // System.out.println(parent.message);
        // parent.showMessage();
        // 상속 없이 protected 변수, 메서드 직접 호출 불가능

        //1. public : 모든 패키지에서 접근 가능

        //2. default : 같은 패키지 내에서 접근 가능(패키지 전용) - 같은 패키지에서 접근 가능, 다른 패키지에서 접근 불가능

        //3. private : 같은 클래스 내에서 접근 가능 - 같은 클래스에서 접근 가능, 다른 클래스에서 접근 불가능(getter, setter 사용시 접근 가능)

        //4. protected : 상속관계에서 접근 가능 - 같은 패키지에서 접근 가능, 다른 패키지에서 접근 불가능(상속 받은 경우 가능)
    }
}
