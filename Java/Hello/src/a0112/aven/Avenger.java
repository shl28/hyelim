package a0112.aven;

public class Avenger {
  String name; // 이름 - 멤버변수
  int hp; // 체력

  public Avenger(String s, int i) {  // s, i 매개변수
    name = s;
    hp = i;
    // 매개변수 이름과 멤버 변수 이름이 다르면 this. 빼도 됨  
    // this.name 매개변수와 변수명이 같을 때 , 지금은 name 과 s 다르므로 this 빼도 됨
  }

  void punch(Avenger enemy){
    System.out.printf("[%s]의 펀치", name);
    enemy.hp -= 10; // enemy.hp = enemy.hp - 10
    System.out.printf(" → %s의 체력 %d\n", enemy.name, enemy.hp);
  }

}
