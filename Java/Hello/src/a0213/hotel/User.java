package a0213.hotel;

import java.util.ArrayList;

public class User {
    private String name;
    private ArrayList<Integer> reservationNumbers;  
    private ArrayList<String> reservedHotels;       
    private ArrayList<Integer> reservedRooms;        
    private static int reservationCounter = 1;      
    private int totalPaid = 0;              

    public User(String name) {
        this.name = name;
        reservationNumbers = new ArrayList<>();
        reservedHotels = new ArrayList<>();
        reservedRooms = new ArrayList<>();
    }  

    public String getName() {
        return name;
    }

    public ArrayList<Integer> getReservationNumbers() {
        return reservationNumbers;
    }

    public ArrayList<String> getReservedHotels() {
        return reservedHotels;
    }

    public ArrayList<Integer> getReservedRooms() {
        return reservedRooms;
    }

    public static int getReservationCounter() {
        return reservationCounter;
    }

    public int getTotalPaid() {
        return totalPaid;
    }

    public void addReservation(String hotelName, int roomNumber) {
        reservedHotels.add(hotelName);
        reservedRooms.add(roomNumber);
        reservationNumbers.add(reservationCounter++);
    }
    
    public void addTotalPaid(int amount) {
        totalPaid += amount;
        
    }

    @Override
    public String toString() {
        return "예약번호: " + reservationNumbers + " | 호텔 : " + reservedHotels
                + " | 방 번호 : " + reservedRooms;
    }

    public void showReservations() {
        System.out.println("\n==== 예약 내역 ====" );
        for(int i = 0; i < reservedHotels.size(); i++){
            System.out.println("예약번호 : " + reservationNumbers.get(i) + " | 호텔 : " + reservedHotels.get(i) + " | 방번호 : " + reservedRooms);
        }
    }
    

    // TODO: cancelReservation(String hotelName, int roomNumber) 구현
    // 특정 호텔의 특정 방 예약 취소

    // TODO: clearReservations() 구현
    // 모든 예약 내역 초기화

    // TODO: removeReservationsByHotel(String hotelName) 구현
    // 특정 호텔의 모든 예약 취소
    // 주의: 뒤에서부터 삭제해야 인덱스 오류 방지}

}
