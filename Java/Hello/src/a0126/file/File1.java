package a0126.file;

import java.io.FileOutputStream;
import java.io.IOException;

public class File1 {
    public static void main(String[] args) throws IOException {
        FileOutputStream output = new FileOutputStream("C:/Users/TJ/out.txt");
        output.close();
        // c:/ 드라이브 루트는 기본적으로 액세스거부
        // C:/Users/TJ/ : user(사용자) 폴더 밑 본인계정으로 하면 가능
        // C:/Users/본인계정/ 

    }
}
