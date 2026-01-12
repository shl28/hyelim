package a0112.monster.practice;

public class Monster {
  private String name;
  private int hp;
  private static int maxHp = 30;

  public String getName() {
    return name;
  }

  public int getHp() {
    return hp;
  }

  public static int getMaxHp() {
    return maxHp;
  }

  public Monster(String s){
    //  생성자를 완성하시오
    s = name;
  }

  public static void attack(Monster enemy){
    // 인스턴스 메서드를 완성하시오
    enemy.hp -= 10;
  }

  public static void battle(Monster a, Monster b){
    while (a.hp > 0 && b.hp > 0) {
      Monster attacker = (Math.random() < 0.5) ? a:b ;
      Monster defender = (attacker == a) ? b : a ;
      // 클래스 메소드를 완성하시오
      b.hp = maxHp -10;
      
    }
  }
}
