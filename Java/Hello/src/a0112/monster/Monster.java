package a0112.monster;

public class Monster {
  private String name;
  private int hp;
  private static int maxHp = 30;

  public Monster(String s) {
    name = s;
    hp = maxHp;
  }

  public void attack(Monster enemy){
    // 인스턴스 메서드를 완성하시오
    System.out.printf("[%s]의 공격 -> ", name);
    enemy.hp -= 10;
    System.out.printf("%s의 체력 : %d/%d\n", enemy.name, enemy.hp, Monster.maxHp);
  }

  

  public static void battle(Monster a, Monster b){
    while (a.hp > 0 && b.hp > 0) {
      // 클래스 메소드를 완성하시오


      // a = 오크, b = 스켈레톤
      Monster attacker = (Math.random() < 0.5) ? a : b ;
      // Math.random : 0 ~ 1 사이의 랜덤한 수
      // Math.random 이 0.5 보다 작으면 attacker = a, 0.5보다 크면 attaker = b
      Monster defender = (attacker == a) ? b : a ;
      // attacker 가 a와 같으면 defender = b, 같지 않으면 defender = a
      // 공격자와 수비자를 랜덤 설정
      
      attacker.attack(defender);
      
    }
  }
}
