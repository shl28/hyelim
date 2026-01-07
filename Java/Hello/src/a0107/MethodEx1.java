package a0107;

public class MethodEx1 {
  public static void main(String[] args) {
    // printf 사용 
    // 반환값 있음
    // 부피 = v
    // 한변의길이 = n
    // 출력: 한 변의 길이가 3인 정육면체의 부피: 27

    int n = 3;
    int v = volume(n);
    System.out.printf("한 변의 길이가 %d인 정육면체의 부피: %d", n, v);

  }

  static int volume(int n) {
    return n * n * n;
  }
  
}
