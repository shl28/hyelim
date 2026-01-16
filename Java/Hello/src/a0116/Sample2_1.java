package a0116;

public class Sample2_1 {
    public static void main(String[] args) {
        Sample2_1 sample = new Sample2_1();        
        int c ;
        try {
            c = 4 / 0;
            // sample.shouldRun(); // 예외 상황 시 이 코드는 실행되지 않음
        } catch (ArithmeticException e) { // 수학적 에러가 발생하면
            c = -1;
            // 예외가 발생하면 수행
        } finally{
            // 예외와 상관없이 무조건 실행
            sample.shouldRun();
        }
        System.out.println(c);
    }

    private void shouldRun() {
        System.out.println("Ok. Thanks.");
    }
}
