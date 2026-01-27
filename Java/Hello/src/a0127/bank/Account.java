package a0127.bank;

public class Account {
    private String accountNumber;
    private String ownerName;
    private int balance;

    public Account(String accountNumber, String ownerName, int balance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = balance;
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

    public void deposit(int amount) {
        this.balance += amount;
    }

    public boolean withdraw(int amount) {
        if (this.balance < amount) return false;
        this.balance -= amount;
        return true;
    }

    @Override
    public String toString() {
        return "Account [accountNumber=" + accountNumber + ", ownerName=" + ownerName + ", balance=" + balance + "]";
    }

    public String toFileString() {
        return accountNumber + "|" + ownerName + "|" + balance;
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
