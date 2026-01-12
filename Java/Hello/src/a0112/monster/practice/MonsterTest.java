package a0112.monster.practice;

public class MonsterTest {
  public static void main(String[] args) {
    Monster orc = new Monster("오크");
    Monster skeleton = new Monster("해골");
    Monster.battle(orc, skeleton);

    System.out.printf("[%s]의 공격 -> [%s]의 체력: %d/%d", orc.getName(), skeleton.getName(), skeleton.getHp(), Monster.getMaxHp());
  }
}

// [오크]의 공격 -> 해골의 체력: 20/30