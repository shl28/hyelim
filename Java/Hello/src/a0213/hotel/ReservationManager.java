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
            System.out.println("이미 예약된 호실입니다.");
        }
    }

    public Hotel getHotel(String hotelName) {
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
            user = new User(userName);
            users.add(user);
        }
        user.addReservation(hotelName, roomNumber);
        return true;
    }

    public User getUser(String userName) {
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
            for (int i = 0; i < user.getReservationNumbers().size(); i++) {
                System.out.println("예약번호: " + user.getReservationNumbers().get(i) + " | 호텔 : " + user.getReservedHotels().get(i)
                + " | 방 번호 : " + user.getReservedRooms().get(i));
            }
            System.out.println("총 결제 금액 : " + user.getTotalPaid() + "원");
        } else {
            System.out.println("예약된 내역이 없습니다.");
        }
    }

    public void cancelReservation() {
        System.out.print("사용자 이름 입력 : ");
        String userName = sc.nextLine();
        User user = getUser(userName);

        if (user == null || user.getReservedHotels().isEmpty()) {
            System.out.println(userName + "님은 예약된 내역이 없습니다.");
            return;
        }

        user.showReservations();

        System.out.print("취소할 예약 번호를 입력하세요 : ");
        int reservationNumber = sc.nextInt();
        sc.nextLine();

        int index = user.getReservationNumbers().indexOf(reservationNumber);
        if (index == -1) {
            System.out.println("해당 예약번호의 예약이 없습니다.");
            return;
        }

        String hotelName = user.getReservedHotels().get(index);
        int roomNumber = user.getReservedRooms().get(index);

        Hotel hotel = getHotel(hotelName);
        if (hotel != null) {
            hotel.getRoom().cancleRoom(roomNumber);

            System.out.println("호텔 [" + hotelName + "] 좌석 [" + roomNumber + "] 예약이 취소되었습니다.");
        }

        int price = hotel.getPrice();
        if (user != null) {
            user.minusTotalPaid(price);
        }

        user.cancelReservation(hotelName, roomNumber);
    }

    public void cancelAllReservation() {
        System.out.print("사용자 이름 입력 : ");
        String userName = sc.nextLine();
        User user = getUser(userName);

        if (user == null || user.getReservedHotels().isEmpty()) {
            System.out.println(userName + "님은 예약된 내역이 없습니다.");
            return;
        }

        ArrayList<String> hotels = new ArrayList<>(user.getReservedHotels());
        ArrayList<Integer> rooms = new ArrayList<>(user.getReservedRooms());
        ArrayList<Integer> numbers = new ArrayList<>(user.getReservationNumbers());

        for(int i = 0; i < hotels.size(); i++) {
            String hotelName = hotels.get(i);
            int roomNumber = rooms.get(i);
            int reservationNumber = numbers.get(i);

            Hotel hotel = getHotel(hotelName);

            int price = hotel.getPrice();
            if (user != null) {
                user.minusTotalPaid(price);
            }

            if (hotel != null) {
                hotel.getRoom().cancleRoom(reservationNumber);
                System.out.println("[" + reservationNumber + "] 호텔 [" + hotelName + "] 방번호 [" + roomNumber + "] 취소 완료");
            }
        }

        user.clearReservations();

        System.out.println(userName + "님의 모든 예약이 취소되었습니다.");
    }

    public void printTicket() {
        System.out.println("\n==== 티켓 출력 ====");
        System.out.print("예약 번호를 입력하세요 : ");

        int reservationNum = -1;

        try {
            reservationNum = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력하세요.");
        }

        if (reservationNum != -1) {
            if (ticket == null) {
                ticket = new ReservationTicket(this);
            }
            ticket.printTicket(reservationNum);
        }
    }

    public String getReservationDetails(int reservationNum) {
        for(User user : users) {
            if (user.getReservationNumbers().contains(reservationNum)) {
                int index = user.getReservationNumbers().indexOf(reservationNum);
                return "예약번호 : " + user.getReservationNumbers().get(index) + " | 호텔 : " + user.getReservedHotels().get(index) + " | 방번호 : " + user.getReservedRooms().get(index);
            }
        }
        return null;
    }

    public void deleteHotel(Scanner sc) {
        System.out.print("삭제할 호텔명을 입력하세요 : ");
        String hotelName = sc.nextLine();
        Hotel hotel = getHotel(hotelName);

        if (hotel != null) {
            for(User user : users) {
                ArrayList<String> reservedHotels = user.getReservedHotels();

                if (reservedHotels.contains(hotelName)) {
                    ArrayList<Integer> reservationNums = new ArrayList<>(user.getReservationNumbers());

                    for(int i = 0; i < reservationNums.size(); i++) {
                        if (user.getReservedHotels().get(i).equalsIgnoreCase(hotelName)) {
                            int roomNumber = user.getReservedRooms().get(i);
                            hotel.getRoom().cancleRoom(roomNumber);
                            int price = hotel.getPrice();
                            if (user != null) {
                                user.minusTotalPaid(price);
                            }
                            System.out.println("[" + reservationNums.get(i) + "] 예약이 취소 되었습니다.");
                        }
                    }
                    user.removeReservationsByHotel(hotelName);
                }
            }
            hotels.remove(hotel);
            System.out.println("[" + hotelName + "] 호텔이 삭제되었습니다.");
        } else {
            System.out.println("해당 호텔이 존재하지 않습니다.");
        }
    }

    public void modifyHotelInfo(Scanner sc) {
        System.out.print("수정할 호텔명을 입력하세요 : ");
        String hotelName = sc.nextLine();
        Hotel hotel = getHotel(hotelName);

        if (hotel == null) {
            System.out.println("해당 호텔은 없습니다.");
            return;
        }

        System.out.println("현재 정보 : " + hotel.getName() + ", " + hotel.getLocation() + ", " + hotel.getPrice() + "원");
        System.out.println("수정할 정보를 입력하세요. 입력하지 않을 경우 기존 정보가 유지됩니다.");

        for(User user : users) {
            ArrayList<String> reservedHotels = user.getReservedHotels();

            if (reservedHotels.contains(hotelName)) {
                ArrayList<Integer> reservationNumbers = new ArrayList<>(user.getReservationNumbers());

                for(int i = 0; i <reservationNumbers.size(); i++) {
                    if (user.getReservedHotels().get(i).equalsIgnoreCase(hotelName)) {
                        int roomNumber = user.getReservedRooms().get(i);
                        hotel.getRoom().cancleRoom(roomNumber);
                        int price = hotel.getPrice();
                    if (user != null) {
                        user.minusTotalPaid(price);
                    }
                        System.out.println("[" + reservationNumbers.get(i) + "] 예약이 취소되었습니다.");
                    }
                }

                user.removeReservationsByHotel(hotelName);
            }
        }

        System.out.print("새 호텔명 : ");
        String newHotelName = sc.nextLine();

        if (newHotelName.isEmpty()) {
            newHotelName = hotel.getName();
        }

        System.out.print("새 위치 : ");
        String newLocation = sc.nextLine();

        if (newLocation.isEmpty()) {
            newLocation = hotel.getLocation();
        }

        System.out.print("새 가격 : ");
        String priceInput = sc.nextLine();
        int newPrice = 0;

        try {
            if (priceInput.trim().isEmpty()) {
                newPrice = hotel.getPrice();
            } else {
                newPrice = Integer.parseInt(priceInput);
            }
        } catch (Exception e) {
            System.out.println("잘못된 입력입니다. 가격은 숫자로 입력해주세요.");
        }

        System.out.print("새 방 수 : ");
        String roomInput = sc.nextLine();
        int newRooms = 0;

        try {
            if (roomInput.trim().isEmpty()) {
                newRooms = hotel.getRoom().getAvailableRooms();
            } else {
                newRooms = Integer.parseInt(roomInput);
            }

            if (newRooms == 0) {
                newRooms = hotel.getRoom().getAvailableRooms();
            }
        } catch (Exception e) {
            System.out.println("잘못된 입력입니다. 숫자로 입력해주세요.");
            return;
        }

        Hotel updateHotel = new Hotel(newHotelName, newLocation, newPrice, newRooms);
        hotels.remove(hotel);
        hotels.add(updateHotel);

        System.out.println("호텔 정보가 수정되었습니다.");
    }

    public void setDiscountRate(Scanner sc) {
        System.out.println("현재 할인율 " + discountRate + "%");
        System.out.print("설정할 할인율(%)을 입력하세요 : ");

        try {
            discountRate = Integer.parseInt(sc.nextLine());
            System.out.println("할인율이 " + discountRate + "%로 설정되었습니다.");
        } catch (Exception e) {
            System.out.println("잘못된 입력입니다.");
        }
    }

    public int getDiscountRate() {
        return discountRate;
    }

    public void addHotel() {
        System.out.print("추가할 호텔 명을 입력하세요 : ");
        String newHotelName = sc.nextLine();
        System.out.print("추가할 호텔 위치를 입력하세요 : ");
        String newLocation = sc.nextLine();
        System.out.print("추가할 호텔 가격을 입력하세요 : ");
        int newPrice = Integer.parseInt(sc.nextLine());
        System.out.print("추가할 호텔 방개수를 입력하세요 : ");
        int newRoomCounts = Integer.parseInt(sc.nextLine());

        Hotel hotel = new Hotel(newHotelName, newLocation, newPrice, newRoomCounts);
        hotels.add(hotel);

        System.out.println(newHotelName + " 호텔 추가가 완료되었습니다.");
    }

}
