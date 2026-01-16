package a0116;

public class Sample2 {
    public static void main(String[] args) {
        int c ;
        try {
            c = 4 / 0;
        } catch (ArithmeticException e) { // 수학적 에러가 발생하면
            c = -1;
            // 예외가 발생하면 수행
        }
        System.out.println(c);
    }
}
