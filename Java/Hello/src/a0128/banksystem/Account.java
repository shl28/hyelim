package a0128.banksystem;

public class Account {
    private String accountNumber;
    private String ownerName;
    private int balance;

    public Account(String accountNumber, String ownerName, int balance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = balance;
    }

    // 파일 저장용 문자열
    public String toFileString(){
        return accountNumber + "|" + ownerName + "|" + balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "계좌번호 : " + accountNumber + ", 예금주 : " + ownerName + ", 잔액 : " + balance + "원";
    }

    public void deposit(int depositAmount) {
        this.balance += depositAmount;
    }

    public boolean withdraw(int withdrawAmount) {
        if (balance >= withdrawAmount) {
            this.balance -= withdrawAmount;
            return true;
        } else {
            return false;
        }
    }

    public static Account fromFileString(String line) {
        try {
            String[] parts = line.split("\\|"); 
            if (parts.length == 3) {
                String accountNumber = parts[0].trim();
                String ownerName = parts[1].trim();
                int balance = Integer.parseInt(parts[2].trim());
                return new Account(accountNumber, ownerName, balance);
            }
        } catch (NumberFormatException e) {
            return null; 
        }
        return null;
    }
    
}
