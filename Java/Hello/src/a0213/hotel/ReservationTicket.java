package a0213.hotel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReservationTicket {
    private ReservationManager reservationManager;
    
    public ReservationTicket(ReservationManager reservationManager) {
        this.reservationManager = reservationManager;
    }

    public void printTicket(int reservationNum) {
        try {
            File dir = new File("d:\\hotelReservation");
            if (!dir.exists()) {
                dir.mkdir();
            }
            File file = new File(dir, "ticket_" + reservationNum + ".txt");
            boolean isNewFile = file.createNewFile();

            try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
                if (file.canWrite()) {
                    if (!isNewFile) {
                        bufferedWriter.newLine();
                    }
                    String ticketInfo = reservationManager.getReservationDetails(reservationNum);
                    if (ticketInfo == null) {
                        System.out.println("예약 정보를 찾을 수 없습니다.");
                        return;
                    }
                    bufferedWriter.write(ticketInfo);
                    bufferedWriter.flush();
                    System.out.println("티켓 출력 완료");
                } else {
                    System.out.println("티켓 출력 실패");
                }
            }
        } catch (Exception e) {
            System.out.println("티켓 출력 실패 : " + e.getMessage());
        }
    }

    public void updateHotelList() {
        File file = new File("d:\\hotelReservation\\hotelList.txt");

        if (!file.exists()) {
            System.out.println("hotelList.txt 파일이 존재하지 않습니다.");
            return;
        }

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"))){
            String line;
            System.out.println("================================");

            while ((line = bufferedReader.readLine()) != null) {
                line = line.trim();

                if (line.isEmpty()) {
                    continue;
                }

                String[] hotels = line.split("/");
                if (hotels.length != 4) {
                    System.out.println("잘못된 형식의 정보 : " + line);
                }

                try {
                    String hotelName = hotels[0];
                    String location = hotels[1];
                    int price = Integer.parseInt(hotels[2]);
                    int roomCount = Integer.parseInt(hotels[3]);

                    if (reservationManager.getHotel(hotelName) != null) {
                        System.out.println("중복된 호텔 [" + hotelName + "] 은(는) 추가되지 않습니다.");
                        continue;
                    }

                    Hotel hotel = new Hotel(hotelName, location, price, roomCount);
                    reservationManager.addHotel(hotel);

                    System.out.println("호텔 추가 완료 : " + hotel.getName());
                } catch (NumberFormatException e) {
                    System.out.println("가격 또는 방개수가 잘못된 형식입니다. " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("파일 읽기 오류 : " + e.getMessage());
        }
    }

}
