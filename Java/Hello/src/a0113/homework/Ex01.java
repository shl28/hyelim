package a0113.homework;

public class Ex01 {
  public static void main(String[] args) {
    
  }
}

class Overloading {
  String name;

  public Overloading(String name) {
    this.name = name;
  }
  public int add(int a, int b){
    return a + b;
  }
  public int add(int a, int b, int c){
    return a + b +c;
  }

  public void displayInfo(){
    System.out.println("이름은 " + name + " 입니다.");
  }
}
