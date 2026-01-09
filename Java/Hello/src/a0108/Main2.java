package a0108;

public class Main2 {
  public static void main(String[] args) {

    Student hong = new Student();

    hong.name = "홍길동";
    hong.age = 20;

    hong.printInfo();

    // int v;
    // int result = v + 10;
    // System.out.println(result);

    Student s2 = new Student("이수만", 75);
    s2.printInfo();

  }

}

// 생성자(constructor)는 → 객체가 생성될 때 자동으로 실행되는 특별한 메서드
// 생성자 특징
// 1. 클래스 이름과 이름이 같다.
// 2. 리턴 타입이 없다. (void 안 씀)
// 3. new 키워드로 객체 생성 시 자동 호출
// 4. 주로 멤버 변수 초기화

class Student {
  String name;
  int age;

  // 생성자 : 생략(보이지 않음) 가능
  // 기본생성자는 생략되면 자바가 컴파일 시 알아서 만들어서 실행
  // 객체가 만들어지면서 실행됨
  Student(){
    System.out.println("기본 생성자 실행");
    // string: 빈문자열, int: 0 : 기본으로 들어감
  }


  public Student(String name, int age) {

    this.name = name;
    this.age = age;

  }

  void printInfo(){
    System.out.println("이름: " + name + ", 나이: " + age);
  }

}
