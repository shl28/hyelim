package a0126.file;

import java.io.IOException;
import java.io.FileWriter;

public class File4 {
    public static void main(String[] args) throws IOException {
        FileWriter fw = new FileWriter("C:/Users/TJ/out.txt");
        for(int i = 1; i < 11; i++){
            String data = i + "번째 줄 입니다.\r\n";
            fw.write(data);
        }
        fw.close();

        FileWriter fw2 = new FileWriter("C:/Users/TJ/out.txt", true);
        for(int i = 11; i < 21; i++){
            String data = i + "번째 줄 입니다.\r\n";
            fw2.write(data);
        }
        fw2.close();
    }
}
