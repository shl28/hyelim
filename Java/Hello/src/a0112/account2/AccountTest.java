package a0112.account2;

public class AccountTest {
  public static void main(String[] args) {
    Account a = new Account("철수", 10000);
    Account b = new Account("영희", 2000);

    System.out.println("==== 입금 ====");
    a.deposit(5000);

    System.out.println("==== 출금 ====");
    a.withdraw(3000);
    a.withdraw(20000);

    System.out.println("\n==== 송금 ====");
    a.transfer(b, 3000);

    System.out.println("\n=== 반복 송금 ===");
    while (a.transfer(b, 3000)) {
      ;
    }
  }
}
