package a0108;

public class AccountTest {
  public static void main(String[] args) {

    Account acc = new Account();

    acc.deposit(1000);
    acc.withdraw(300);
    
    System.out.println("잔액 : " + acc.balance);

  }

}

class Account {

  int balance; // 잔액

  void deposit(int money){ // 입금
    balance += money;
  }

  void withdraw(int money){ // 출금
    balance -= money;
  }

}
