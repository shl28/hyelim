package a1231;

public class Ifex01 {
    public static void main(String[] args) {
        
    // ## 문제 8: 로그인 체크
    // 아이디와 비밀번호를 확인하여 로그인 성공 여부를 판별하는 프로그램을 작성하세요.

    // **조건:**
    // - 아이디가 "admin"이고 비밀번호가 "1234"인 경우에만 로그인 성공
    // - 그 외의 경우는 로그인 실패

    // **요구사항:**
    // - if-else 문 사용
    // - 논리 연산자(&&) 활용
    // - "로그인 성공" 또는 "로그인 실패" 출력

 String id = "admin";
 int pw = 1254;

 if (id=="admin" && pw == 1234) {
    System.out.println("로그인 성공");
 } else {
    System.out.println("로그인 실패");
 }

 }
}
