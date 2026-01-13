package a0113.Account3;

import java.util.Scanner;

public class BankApp {
  
  // 객체를 하나만 만든다
  // Account acc = new Account("123-123", "이순신", 10000);
  // Accoount 객체를 100개 관리하고 싶다.

  // Accont 객체 100개를 관리하는 배열을 만든다.
  private static Account[] accountsArray = new Account[100];

  private static Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    boolean run = true;
    while (run) {
      System.out.println("-------------------------------------------------------------------------");
      System.out.println("1. 계좌생성 | 2. 계좌목록 | 3. 예금 | 4. 출금 | 5. 계좌이체 | 6. 종료");
      System.out.println("-------------------------------------------------------------------------");
      System.out.print("선택> ");
      int selNum = 0;
     
      // 문자로 받아서 : nextLine() , 숫자로 형변환 : Integer.parseInt
      // selNum = Integer.parseInt(scanner.nextLine());
      
      // 예외 처리: 에러 발생해도 프로그램 계속 실행 - while문 유지
      // a라고 문자열을 입력해도(에러) 프로그램 중지 X
      try{
        selNum = Integer.parseInt(scanner.nextLine());
      } catch(Exception e){
      }

      switch (selNum) {
        case 1 : createAccount();
        break;

        case 2 : accountList();
        break;

        case 3 : deposit();
        break;

        case 4 : withdraw();
        break;

        case 5 : transfer();
        break;

        case 6 : 
          System.out.println("프로그램 종료");
          run = false;
          break;
      }
    }
  }

  private static void createAccount() {
    System.out.println("-----------------------------");
    System.out.println("계좌생성");
    System.out.println("-----------------------------");
    System.out.print("계좌입력 : ");
    String ano = scanner.nextLine();
    if (findAccount(ano) != null) {
      System.out.println("이미 존재하는 계좌번호 입니다.");
      return;
    }
    System.out.print("계좌주 : ");
    String owner = scanner.nextLine();
    System.out.print("초기 입금액 : ");
    int balance = 0;  // 잔액 0으로 초기화
    try {
      balance = Integer.parseInt(scanner.nextLine());
      if (balance < 0) {
        System.out.println("초기입금액은 0원 이상이여야 합니다.");
        return;
      }
      Account newAccount = new Account(ano, owner, balance);
      for(int i = 0; i < accountsArray.length; i++){
        if (accountsArray[i] == null) {
          accountsArray[i] = newAccount;
          System.out.println("결과 : 계좌가 생성되었습니다.");
          break;
        }
      }
    } catch (Exception e) {
        System.out.println("잘못된 입력입니다. 숫자를 입력하세요.");
    }
  }

  private static void accountList() {
    System.out.println("-------------------------------");
    System.out.println("계좌목록");
    System.out.println("-------------------------------");
    for(int j = 0; j < accountsArray.length; j++){
      if (accountsArray[j] != null) {
        System.out.printf("%s %4s %d\n", accountsArray[j].getAno(), accountsArray[j].getOwver(), accountsArray[j].getBalance());
        // %4s : 4글자
      }
    }
  }

  private static void deposit() {
    System.out.println("-----------");
    System.out.println("예금");
    System.out.println("-----------");
    System.out.print("계좌번호: ");
    String ano = scanner.nextLine();
    Account account = findAccount(ano);
    if (account == null) {
      System.out.println("결과 : 계좌가 없습니다.");
      return;
    }
    System.out.print("예금액: ");
    try {
      int money = Integer.parseInt(scanner.nextLine());
      if (money <= 0) {
        System.out.println("예금액은 1원 이상이여야 합니다.");
        return;
      }
      account.setBalance(account.getBalance() + money);
    } catch (NumberFormatException e) { // 숫자 에러(문자가 들어왔을 때)
      System.out.println("잘못된 입력 입니다. 숫자를 입력해 주세요.");
    }
  }

  private static void withdraw() {
    System.out.println("-----------");
    System.out.println("출금");
    System.out.println("-----------");
    System.out.print("계좌번호: ");
    String ano = scanner.nextLine();
    Account account = findAccount(ano);
    if (account == null) {
      System.out.println("결과 : 계좌가 없습니다.");
      return;
    }
    System.out.print("출금액: ");
    try {
      int money = Integer.parseInt(scanner.nextLine());
      if (money <= 0) {
        System.out.println("출금액은 1원 이상이여야 합니다.");
        return;
      }
      if (account.getBalance() <= money) {
        // 잔액보다 찾는 금액이 많으면 잔액부족
        System.out.println("잔액 부족입니다.");
        return;
      }
      account.setBalance(account.getBalance() - money);
      System.out.println("결과: 출금완료\n현재 잔액: " + account.getBalance() + "원");
    } catch (NumberFormatException e) { // 숫자 에러(문자가 들어왔을 때)
      System.out.println("잘못된 입력 입니다. 숫자를 입력해 주세요.");
    }
  }

  private static void transfer() {
    System.out.println("-----------");
    System.out.println("계좌이체");
    System.out.println("-----------");
    System.out.print("츌금 계좌번호: ");
    String ano = scanner.nextLine();
    System.out.print("입금 계좌번호: ");
    String send = scanner.nextLine();
    Account account = findAccount(ano);
    Account sendAccount = findAccount(send);
    if (account == null || sendAccount == null) {
      System.out.println("결과 : 계좌가 없습니다.");
      return;
    }
    System.out.print("이체금액: ");
    int money = 0;
    try {
      money = Integer.parseInt(scanner.nextLine());
      if (money <= 0) {
        System.out.println("이체금액은 1원 이상이여야 합니다.");
        return;
      }
      if (account.getBalance() <= money) {
        // 잔액보다 찾는 금액이 많으면 잔액부족
        System.out.println("잔액 부족입니다.");
        return;
      }
      account.setBalance(account.getBalance() - money);
      sendAccount.setBalance(sendAccount.getBalance() + money);
      System.out.println("결과: 이체 성공");
      System.out.println("출금 계좌 잔액: " + account.getBalance() + "원");
      System.out.println("입금 계좌 잔액: " + sendAccount.getBalance() + "원");
    } catch (NumberFormatException e) { // 숫자 에러(문자가 들어왔을 때)
      System.out.println("잘못된 입력 입니다. 숫자를 입력해 주세요.");
    }
  }

  // 계좌번호 찾는 메서드
  private static Account findAccount(String ano) {
    Account account = null;
    // 반환될 값 초기화
    for(int k = 0; k < accountsArray.length; k++){
      if(accountsArray[k] != null){
        String dbAno = accountsArray[k].getAno();
        if (dbAno.equals(ano)) {
          account = accountsArray[k];
          break;
        }
      }
    }
    return account;
  }
 
}
