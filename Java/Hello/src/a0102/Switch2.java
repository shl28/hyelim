package a0102;

public class Switch2 {
  public static void main(String[] args) {
    
    int day = 3;
    switch (day) {
      case 1:
        System.out.println("월요일");
        break;
    
      case 2:
        System.out.println("화요일");
        break;

      case 3:
        System.out.println("수요일");
        break;

      case 4:
        System.out.println("목요일");
        break;

      case 5:
        System.out.println("금요일");
        break;

      default:
        System.out.println("주말");
        break;
    }
  }
}
