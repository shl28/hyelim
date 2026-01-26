package a0126.filewrite1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileWrite1 {
    public static void main(String[] args) {
        String fileName = "C:/Users/TJ/out1.txt";
       
        // 저장할 데이터 생성
        ArrayList<String> list = new ArrayList<>();
        list.add("1번째 학생입니다.");
        list.add("2번째 학생입니다.");
        list.add("3번째 학생입니다.");
        list.add("자바파일 출력 연습");

        // 파일로 저장
        writeLines(fileName, list);
        System.out.println("파일 저장 완료");
    }

    private static void writeLines(String fileName, ArrayList<String> lines) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))){
            for(String line : lines){
                bw.write(line);
                bw.newLine(); //  줄바꿈
            }
        } catch (IOException e) {
            System.out.println("파일 쓰기 오류 : " + e.getMessage());
        }
    }
}
