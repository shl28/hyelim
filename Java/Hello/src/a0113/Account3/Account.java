package a0113.Account3;

public class Account {
  private String ano;  // 계좌번호
  private String owver;  // 계좌주
  private int balance;  // 잔액
  
  public Account(String ano, String owver, int balance) {
    this.ano = ano;
    this.owver = owver;
    this.balance = balance;
  }

  public String getAno() {
    return ano;
  }

  public void setAno(String ano) {
    this.ano = ano;
  }

  public String getOwver() {
    return owver;
  }

  public void setOwver(String owver) {
    this.owver = owver;
  }

  public int getBalance() {
    return balance;
  }

  public void setBalance(int balance) {
    this.balance = balance;
  }

  
}
