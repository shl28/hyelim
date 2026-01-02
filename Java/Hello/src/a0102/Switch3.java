package a0102;

public class Switch3 {
  public static void main(String[] args) {
    // 여러 case 묶기
    int month = 7;

    switch (month) {
      case 12:
      case 1:
      case 2:
        System.out.println("겨울");
      break;
      
      case 3:
      case 4:
      case 5:
        System.out.println("봄");
      break;

      case 6:
      case 7:
      case 8:
        System.out.println("여름");
      break;

      case 9:
      case 10:
      case 11:
        System.out.println("가을");
      break;

      default:
        System.out.println("잘못 입력되었습니다.");
      break;
    }
    // switch (score>60) X : 범위나 비교 불가
    // switch : 값이 딱 떨어질 때 사용하는 조건문
  }
}
