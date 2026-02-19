package a0219.lottery;

public class Main {
    public static void main(String[] args) {
        // 환영인사 및 회원 가입 진행 여부
        Membership m = Membership.getInstance();

        m.membershipGuide();

        // 로또 구매
        Purchase p = Purchase.getInstance();

        // 로또 구매 여부를 묻고 로또를 구매함
        p.purchaseQuestion();

        Loading l = new Loading();
        l.loading();

        // 로또 번호를 추첨한다
        Lottery lt = new Lottery();

        // 랜덤으로 로또번호를 생성
        lt.randomLotteryNum();

        // 당첨된 로또 번호를 출력
        lt.lotteryNum();

        // 당첨 결과 출력
        lt.result();
    }
}
