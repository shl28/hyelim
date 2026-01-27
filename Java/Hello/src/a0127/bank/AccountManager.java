package a0127.bank;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AccountManager {
    ArrayList<Account> accounts = new ArrayList<>();
    private String fileName;
    
    public AccountManager() {
        this.accounts = new ArrayList<>();
        this.fileName = "c:/Users/TJ/memo/account.txt";
    }

    private void ensureDirectory(){
        File file = new File(fileName);
        File parentDir = file.getParentFile();  
        if(parentDir != null && !parentDir.exists()){  
            parentDir.mkdirs(); 
            System.out.println("디렉토리가 생성되었습니다." + parentDir.getPath());
        }
    }

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        AccountManager manager = new AccountManager();

        while (true) {
            System.out.println("=== 은행 계좌 관리 시스템 ===");
            System.out.println("1. 계좌 생성");
            System.out.println("2. 입금");
            System.out.println("3. 출금");
            System.out.println("4. 계좌 조회");
            System.out.println("5. 전체 계좌 조회");
            System.out.println("6. 계좌 삭제");
            System.out.println("7. 파일 저장");
            System.out.println("8. 파일 불러오기");
            System.out.println("9. 종료");
            System.out.print("선택 > ");

            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    System.out.print("계좌번호 입력: ");
                    String accountNumber = scanner.nextLine();
                    System.out.print("예금주 입력: ");
                    String ownerName = scanner.nextLine();
                    System.out.print("초기 잔액 입력: ");
                    int balance;
                    try {
                        balance = Integer.parseInt(scanner.nextLine());
                    } catch (Exception e) {
                        System.out.println("숫자를 입력하세요.");
                        continue;
                    }
                    manager.addAccount(accountNumber,ownerName, balance);

                    break;
            
                case 2:
                    System.out.print("입금할 계좌번호 입력: ");
                    accountNumber = scanner.nextLine();
                    System.out.print("입금할 금액 입력: ");
                    int depositMoney;
                    try {
                        depositMoney = Integer.parseInt(scanner.nextLine());
                    } catch (Exception e) {
                        System.out.println("숫자를 입력하세요.");
                        continue;
                    }
                    manager.deposit(accountNumber, depositMoney);
                    break;

                case 3:
                    System.out.print("출금할 계좌번호 입력: ");
                    accountNumber = scanner.nextLine();
                    System.out.print("출금할 금액 입력: ");
                    int withdrawMoney;
                    try {
                        withdrawMoney = Integer.parseInt(scanner.nextLine());
                    } catch (Exception e) {
                        System.out.println("숫자를 입력하세요.");
                        continue;
                    }
                    manager.withdraw(accountNumber, withdrawMoney);
                    break;

                case 4:
                    System.out.print("조회할 계좌번호 입력: ");
                    accountNumber = scanner.nextLine();
                    manager.printAccount(accountNumber);
                    
                    break;

                case 5:
                    manager.printAll();
                    break;

                case 6:
                    System.out.print("삭제할 계좌번호 입력: ");
                    accountNumber = scanner.nextLine();
                    manager.deleteAccount(accountNumber);
                    break;

                case 7:
                    manager.saveToFile();
                    break;

                case 8:
                    manager.loadFromFile();
                    break;

                case 9:
                    System.out.println("프로그램을 종료합니다.");
                    scanner.close();
                    return;

                default:
                    System.out.println("잘못된 선택입니다.");
                    break;
            }
        }

        
    }

    private void addAccount(String accountNumber, String ownerName, int balance) {
        if (findAccount(accountNumber) != null) {
            System.out.println("이미 존재하는 계좌번호입니다.");
            return;
        }
        accounts.add(new Account(accountNumber, ownerName, balance));
        System.out.println("계좌가 생성되었습니다.");
    }

    private void deposit(String accountNumber, int depositMoney) {
        Account account = findAccount(accountNumber);
        if (account != null) {
            account.deposit(depositMoney);
            System.out.println("입금 완료! 현재 잔액 " + account.getBalance() + "원");
            saveToFile();
        } else {
            System.out.println("계좌를 찾을 수 없습니다.");
        }
    }

    private void withdraw(String accountNumber, int withdrawMoney) { 
        Account account = findAccount(accountNumber);
        if (account != null) {
            if (account.withdraw(withdrawMoney)) {
                System.out.println("출금 완료! 현재 잔액: " + account.getBalance() + "원");
                saveToFile();
            } else {
                System.out.println("잔액이 부족합니다. 현재 잔액: " + account.getBalance() + "원");
            }
        } else {
            System.out.println("계좌를 찾을 수 없습니다.");
        }
    }

    private void printAccount(String accountNumber) {
       Account account = findAccount(accountNumber);
        if (account != null) {
            System.out.println("=== 계좌 정보 ===");
            System.out.println("계좌번호: " + account.getAccountNumber());
            System.out.println("예금주명: " + account.getOwnerName());
            System.out.println("잔액: " + account.getBalance());
        } else {
            System.out.println("해당 계좌가 없습니다.");
        } 
    }

    private void printAll() {
        if (accounts.isEmpty()) {
            System.out.println("등록된 계좌가 없습니다.");
            return;
        }
        System.out.println("=== 계좌 목록 ===");
        for(int i = 0; i < accounts.size(); i++){
            System.out.println((i + 1) + ". " + accounts.get(i));
        }
    }

    private void deleteAccount(String accountNumber) {
        Account account = findAccount(accountNumber);
        if (account != null) {
            accounts.remove(account);
            System.out.println("계좌가 삭제되었습니다.");
            saveToFile();
        } else {
            System.out.println("계좌를 찾을 수 없습니다.");
        } 
    }

    private void saveToFile() {
        ensureDirectory();
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))){
            for(Account account : accounts){
                bw.write(account.toFileString());
                bw.newLine();
            }
            System.out.println("파일 저장 완료: " + fileName);
        } catch (IOException e) {
            System.out.println("파일 저장 오류: " + e.getMessage());
        }
    }

    private void loadFromFile() {
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("파일이 없습니다. 새로 시작합니다.");
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))){
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    Account account = Account.fromFileString(line);
                    if (account != null) {
                        accounts.add(account);
                    }
                }
            }
            System.out.println("파일 불러오기 완료: " + accounts.size() + "개");
        } catch (IOException e) {
            System.out.println("파일 읽기 오류: " + e.getMessage());
        }
    }

    private Account findAccount(String accountNumber) {
        for(Account account : accounts){
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }
}
