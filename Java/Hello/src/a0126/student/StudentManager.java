package a0126.student;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class StudentManager {
    private static final String OUTPUT_FILE = "c:/Users/TJ/student/data.txt";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("=== 학생 관리 프로그램 ===");
            System.out.println("1. 학생 정보 등록(새로 저장)");
            System.out.println("2. 학생 목록 읽기");
            System.out.println("3. 학생 정보 추가");
            System.out.println("4. 종료");
            System.out.print("선택 > ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    writeNewMemo(sc);
                    break;

                case 2:
                    readMemo();
                    break;
            
                case 3:
                    appendMemo(sc);
                    break;
                    
                case 4:
                    System.out.println("프로그램을 종료합니다.");
                    sc.close();
                    return;

                default:
                    System.out.println("1~4 중에 선택하세요.");
                    break;
            }

        }
    }


    private static void writeNewMemo(Scanner sc) {
        System.out.println("학생 정보 입력(종료: 빈 줄 입력)");
        StringBuilder content= new StringBuilder();
        String line;
        while (true) {
            System.out.print("이름: ");
            String name = sc.nextLine();
            
            // 1. 이름을 입력받자마자 빈 줄인지 체크해서 즉시 탈출! (가장 중요)
            if (name.isEmpty()) {
                break;
            }

            // 2. 나머지도 nextLine()으로 받고 숫자로 변환 (버퍼 꼬임 방지)
            try {
                System.out.print("국어: ");
                int kor = Integer.parseInt(sc.nextLine());
                System.out.print("영어: ");
                int eng = Integer.parseInt(sc.nextLine());
                System.out.print("수학: ");
                int math = Integer.parseInt(sc.nextLine());

                content.append(name).append(",").append(kor).append(",").append(eng).append(",").append(math).append("\n");
            } catch (NumberFormatException e) {
                System.out.println("숫자 형식이 잘못되었습니다. 다시 입력해주세요.");
                continue;
            }
        }

        File outputFile = new File(OUTPUT_FILE);
        File parentDir = outputFile.getParentFile();  
        if(parentDir != null && !parentDir.exists()){  
            parentDir.mkdirs(); 
            System.out.println("디렉토리가 생성되었습니다." + parentDir.getPath());
        }

        boolean fileExists = outputFile.exists(); 

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_FILE))){
            bw.write(content.toString());  
            if (fileExists) {
                outputFile.delete();
                System.out.println("기존 파일 삭제하고 " + OUTPUT_FILE + "에 저장되었습니다.");
            } else {
                System.out.println("새 파일을 생성하여 " + OUTPUT_FILE + "에 저장되었습니다.");
            }
        } catch (IOException e) {
            System.out.println("파일 쓰기 오류: " + e.getMessage());
        }

    }

    private static void readMemo() {
        File file = new File(OUTPUT_FILE);
        if (!file.exists()) {
            System.out.println("저장된 학생 정보가 없습니다.");
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(OUTPUT_FILE))){
            System.out.println("=== 학생 정보 ===");
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("파일 읽기 오류: " + e.getMessage());
        }
    }

    private static void appendMemo(Scanner sc) {
        System.out.println("추가 학생 정보 입력(종료: 빈 줄 입력)");
        StringBuilder content= new StringBuilder();
        String line;
        while (true) {
            System.out.print("이름: ");
            String name = sc.nextLine();
            
            // 1. 이름을 입력받자마자 빈 줄인지 체크해서 즉시 탈출! (가장 중요)
            if (name.isEmpty()) {
                break;
            }

            // 2. 나머지도 nextLine()으로 받고 숫자로 변환 (버퍼 꼬임 방지)
            try {
                System.out.print("국어: ");
                int kor = Integer.parseInt(sc.nextLine());
                System.out.print("영어: ");
                int eng = Integer.parseInt(sc.nextLine());
                System.out.print("수학: ");
                int math = Integer.parseInt(sc.nextLine());

                content.append(name).append(",").append(kor).append(",").append(eng).append(",").append(math).append("\n");
            } catch (NumberFormatException e) {
                System.out.println("숫자 형식이 잘못되었습니다. 다시 입력해주세요.");
                continue;
            }
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_FILE, true))){
            bw.write(content.toString());  
            System.out.println("학생 정보 추가가 완료되었습니다.");
        } catch (IOException e) {
            System.out.println("파일 쓰기 오류: " + e.getMessage());
        }

    }

}
