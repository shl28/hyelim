package a0114.abstract1;

public class AbstractMain {
    public static void main(String[] args) {
        Animal dog = new Dog("뽀삐");
        Animal cat = new Cat("나비");

        dog.eat();
        dog.sound();

        cat.eat();
        cat.sound();

    }
}

// 추상 클래스
// 공통 상태(필드)가 필요할 때 : name
// 공통 기능 코드를 물려주고 싶을 때 : sound()