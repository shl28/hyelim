package a0212.movie;

public class Ticket {
    private ReservationManager reservationManager;

    // 기존 예약 관리자를 객체 변수로 받아서 사용하도록 변경
    public Ticket(ReservationManager reservationManager) {
        this.reservationManager = reservationManager;
    }
}
