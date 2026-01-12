package a0112.account2;

public class Account {
  String owner; // 예금주
  int balance;

  public Account(String owner, int balance) {
    this.owner = owner;
    this.balance = balance;
  }

  public void deposit(int money) {
    balance += money;
    System.out.printf("[%s] %,d원 입금 → 잔액: %,d원\n", owner, money, balance);
  }

  public boolean withdraw(int money) {
    if (balance < money) {
      System.out.printf("[%s] 출금 실패(잔액 부족)\n", owner);
      return false;
    }
    balance -= money;
    System.out.printf("[%s] %,d원 출금 → 잔액: %,d원\n", owner, money, balance);
    return true;
  }

  public boolean transfer(Account target, int money) {
    if (balance < money) {
      System.out.printf("(%s => %s) 송금 실패(잔액부족)\n", owner, target.owner);
      return false;
    }
    this.balance -= money; // this 빼도 됨
    target.balance += money;
    System.out.printf("(%s => %s) %,d원 송금 완료\n", owner, target.owner, money);
    System.out.printf("[%s 잔액 : %,d원]\n", owner, balance);
    System.out.printf("[%s 잔액 : %,d원]\n", target.owner, target.balance);
    return true;
  }

  
}

// 3️⃣ 실행 흐름 설명
// 💰 입금
// 철수 계좌에 5000원 추가

// 💸 출금

// 잔액 충분 → 출금 성공

// 잔액 부족 → 출금 실패 (false 반환)

// 🔁 송금

// 보내는 계좌(this)에서 차감

// 받는 계좌(target)에 증가

// 성공 시 true, 실패 시 false
