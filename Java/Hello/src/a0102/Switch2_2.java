package a0102;

public class Switch2_2 {
  public static void main(String[] args) {
    
    // 자바 14 이상
    
    int day = 3;
    String dayName = switch (day) {
      case 1 -> "월요일";
      case 2 -> "화요일";
      case 3 -> "수요일";
      case 4 -> "목요일";
      case 5 -> "금요일";
      case 6 -> "토요일";
      case 7 -> "일요일";
      default -> "잘못된 요일";
    }; // 값을 반환하는 표현식이여서 ;가 반드시 필요
    
    System.out.println(dayName);

  }
}
