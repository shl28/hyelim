package a0212.movie;

import java.util.ArrayList;

public class Theater {
    private ArrayList<String> seats;

    public Theater(int seatCount) {
        seats = new ArrayList<>();

        for(int i = 0; i < seatCount; i++) {
            seats.add((i + 1) + "");
            // 초기 좌석 번호 저장  // +"" : 문자(String)로 저장
        }
    }

    public int getAvailableSeats() {
        int count = 0;
        for(String seat : seats) {
            if (!seat.equalsIgnoreCase("X")) count++;
            // 예약되지 않은 좌석 수 카운트
        }
        return count;
    }

    public void displaySeats() {
        System.out.println("\n좌석 배치 (예약된 좌석 : X)");
        for(int i = 0; i < seats.size(); i++) {
            System.out.printf("%2s ", seats.get(i));
            // 예약 되지 않은 좌석: 좌석 번호, 예약된 좌석: "X" 출력

            if ((i + 1) % 10 == 0) System.out.println();  // 10의 배수 좌석 : 줄바꿈
        }
    }

    public boolean reserveSeat(int seatNumber) {
        if (seatNumber > 0 && seatNumber <= seats.size() && !seats.get(seatNumber - 1).equalsIgnoreCase("X")) {
            seats.set(seatNumber - 1, "X");
            // 좌석 예약 시 인덱스번호 해당하는 값을 "X"로 변경
            return true;
        }
        return false;
    }

    public void cancleSeat(int seatNumber) {
        if (seatNumber > 0 && seatNumber <= seats.size() && seats.get(seatNumber - 1).equalsIgnoreCase("X")) {
            seats.set(seatNumber - 1, String.valueOf(seatNumber));
            // String.valueOf(seatNumber) : int 인 seatNumber 를 문자(String)으로 저장
            // 예약 취소 시 "X" => 원래 좌석 번호로 변경
        }
    }
}
