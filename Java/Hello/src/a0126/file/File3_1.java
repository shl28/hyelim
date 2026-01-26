package a0126.file;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class File3_1 {
    public static void main(String[] args) throws IOException {
        PrintWriter pw = new PrintWriter("C:/Users/TJ/out.txt");
        // PrintWriter pw = new PrintWriter("C:/Users/TJ/out.txt", "UTF-8");  : 메모장 글씨 깨질때
        for(int i = 1; i < 11; i++){
            String data = i + "번째 줄 입니다.";
            pw.println(data);
        }
        pw.close();

        PrintWriter pw2 = new PrintWriter(new FileWriter("C:/Users/TJ/out.txt", true));
        // append는 FileWriter에서만 설정 가능
        // 이어쓰기 하려면 FileWriter 넣어야 함 
        // PrintWriter 는 이어쓰기 안함
        for(int i = 11; i < 21; i++){
            String data = i + "번째 줄 입니다.";
            pw2.println(data);
        }
        pw2.close();
    }
}
