package a0213.hotel;

public class ReservationTicket {
    private ReservationManager reservationManager;
    
    public ReservationTicket(ReservationManager reservationManager) {
        this.reservationManager = reservationManager;
    }

    // TODO: printTicket(int reservationNum) 구현
    // 1. d:\\hotelReservation 폴더 생성 (없으면)
    // 2. ticket_{reservationNum}.txt 파일 생성
    // 3. 예약 정보를 파일에 저장
    // 4. 예외 처리 포함

    // TODO: updateHotelList() 구현
    // 1. d:\\hotelReservation\\hotelList.txt 파일 읽기
    // 2. 각 줄을 "/"로 분리하여 파싱
    // 3. 형식: 호텔이름/위치/가격/방개수
    // 4. 중복 호텔 확인 후 추가
    // 5. 예외 처리 포함
}
