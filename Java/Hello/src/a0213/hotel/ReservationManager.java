package a0213.hotel;

import java.util.ArrayList;
import java.util.Scanner;

public class ReservationManager {
    private ArrayList<Hotel> hotels;
    private ArrayList<User> users;
    private Scanner sc;
    private static ReservationTicket ticket;
    private int discountRate = 0;
    
    public ReservationManager() {
        hotels = new ArrayList<>();
        users = new ArrayList<>();
        sc = new Scanner(System.in);
    }

    // 호텔 목록에 추가
    public void addHotel(Hotel hotel) {
        hotels.add(hotel);
    }

    public void showHotels() {
        System.out.println("\n==== 호텔 목록 ====");
        for(Hotel hotel : hotels) {
            System.out.println(hotel);
        }
    }

    public void hotelReservation() {
        System.out.println("\n==== 호텔 예약하기 ====");
        System.out.print("호텔 이름 입력 : ");
        String hotelName = sc.nextLine();
        System.out.print("사용자 이름 입력 : ");
        String userName = sc.nextLine();
        Hotel hotel = getHotel(hotelName);
        
        if (hotel == null) {
            System.out.println("해당 호텔이 없습니다.");
            return;
        }

        hotel.getRoom().displayRooms();

        System.out.print("방 번호 입력 : ");
        int roomNumber = sc.nextInt();
        sc.nextLine();

        if (bookRoom(userName, hotelName, roomNumber)) {
            int price = hotel.getPrice();
            int discount = price * discountRate / 100;
            int finalPrice = price - discount;
            System.out.println("예약이 완료 되었습니다.");
            System.out.println("원가 : " + price + "원");
            System.out.println("할인율 : " + discountRate + "%");
            System.out.println("할인된 금액 : " + discount + "원");
            System.out.println("결제 금액 : " + finalPrice + "원");

            User user = getUser(userName);
            if (user != null) {
                user.addTotalPaid(finalPrice);
            }
        } else {
            System.out.println("이미 예약된 좌석입니다.");
        }
    }

    private Hotel getHotel(String hotelName) {
        for(Hotel hotel : hotels){
            if (hotel.getName().equalsIgnoreCase(hotelName)) {
                return hotel;
            }
        }
        return null;
    }

    private boolean bookRoom(String userName, String hotelName, int roomNumber) {
        Hotel hotel = getHotel(hotelName);

        if (hotel == null) {
            System.out.println("해당 호텔이 없습니다.");
            return false;
        }

        if (!hotel.getRoom().reserveRoom(roomNumber)) {
            System.out.println("이미 예약된 호실이거나 잘못된 호실번호입니다.");
            return false;
        }

        User user = getUser(userName);

        if (user == null) {
            user = new User(hotelName);
            users.add(user);
        }
        user.addReservation(hotelName, roomNumber);
        return true;
    }

    private User getUser(String userName) {
        for(User user : users){
            if (user.getName().equalsIgnoreCase(userName)) {
                return user;
            }
        }
        return null;
    }

    public void checkReservation() {
        System.out.print("사용자 이름 입력 : ");
        String userName = sc.nextLine();
        User user = getUser(userName);

        if (user != null && !user.getReservedHotels().isEmpty()) {
            for(User userReserve : users) {
                System.out.println(userReserve);
            }
            System.out.println("총 결제 금액 : " + user.getTotalPaid() + "원");
        } else {
            System.out.println("예약된 내역이 없습니다.");
        }
    }

    // TODO: cancelReservation() 구현
    // 예약 번호로 특정 예약 취소
    public void cancelReservation() {
        System.out.print("사용자 이름 입력 : ");
        String userName = sc.nextLine();
        User user = getUser(userName);

        if (user == null || user.getReservedHotels().isEmpty()) {
            System.out.println(userName + "님은 예약된 내역이 없습니다.");
            return;
        }

        user.showReservations();

        System.out.println("취소할 예약 번호를 입력하세요 : ");
        int reservationNumber = sc.nextInt();
        sc.nextLine();

        int index = user.getReservationNumbers().indexOf(reservationNumber);
        if (index == -1) {
            System.out.println("해당 예약번호의 예약이 없습니다.");
            return;
        }

        String hotelName = user.getReservedHotels().get(index);
        int roomMumber = user.getReservedRooms().get(index);

        Hotel hotel = getHotel(hotelName);
        if (hotel != null) {
            hotel.getRoom().cancleRoom(roomMumber);

            System.out.println("호텔 [" + hotelName + "] 좌석 [" + roomMumber + "] 예약이 취소되었습니다.");
        }
    }

    // TODO: cancelAllReservation() 구현
    // 사용자의 모든 예약 취소

    // TODO: printTicket() 구현
    // 예약 번호 입력받아 티켓 출력

    // TODO: getReservationDetails(int reservationNum) 구현
    // 예약 번호로 예약 정보 문자열 반환

    // TODO: deleteHotel(Scanner sc) 구현
    // 호텔 삭제 (해당 호텔의 모든 예약도 함께 취소)

    // TODO: modifyHotelInfo(Scanner sc) 구현
    // 호텔 정보 수정 (모든 예약 취소 후 수정)

    // TODO: setDiscountRate(Scanner sc) 구현
    // 할인율 설정

    // TODO: getDiscountRate() 구현
    // 할인율 반환
}
