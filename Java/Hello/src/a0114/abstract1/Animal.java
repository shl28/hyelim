package a0114.abstract1;

abstract class Animal { // public -> abstract
  String name;

  public Animal(String name) {
    this.name = name;
  }
  
  // 추상메서드  // 반드시 하위 클래스에서 구현
  abstract void sound(); 
  // 일반 메서드
  public void eat(){
    System.out.println(name + "이(가) 먹습니다.");
  }
  
}
