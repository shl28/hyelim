package a0126.file;

import java.io.IOException;
import java.io.PrintWriter;

public class File3 {
    public static void main(String[] args) throws IOException {
        PrintWriter pw = new PrintWriter("C:/Users/TJ/out.txt");
        // PrintWriter pw = new PrintWriter("C:/Users/TJ/out.txt", "UTF-8");  : 메모장 글씨 깨질때
        for(int i = 1; i < 11; i++){
            String data = i + "번째 줄 입니다.";
            pw.println(data);
        }
        pw.close();
    }
}
