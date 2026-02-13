package a0213.hotel;

import java.util.ArrayList;

public class Room {
    private ArrayList<String> rooms;

    public Room(int roomCount) {
        rooms = new ArrayList<>();

        for(int i = 0; i < roomCount; i++){
            rooms.add((roomCount + 1) + "");
        }
    }

    public int getAvailableRooms() {
        int count = 0;

        for(String room: rooms){
            if (!room.equalsIgnoreCase("X")) {
                count++;
            }
        }

        return count;
    }

    public void displayRooms() {
        System.out.println("\n==== 방 목록 ====");

        for(int i = 0; i < rooms.size(); i++) {
            System.out.printf("%2s ", rooms.get(i));

            if ((i + 1) % 10 == 0) {
                System.out.println();
            }
        }

    }

    public boolean reserveRoom(int roomNumber) {
        if (roomNumber > 0 && roomNumber <= rooms.size() && !rooms.get(roomNumber - 1).equalsIgnoreCase("X")) {
            rooms.set(roomNumber - 1, "X");
            return true;
        }
        return false;
    }

        // TODO: cancelRoom(int roomNumber) 구현
    // 방 예약 취소 (해당 방 번호를 원래 번호로 복원)}

    public void cancleRoom(int roomMumber) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cancleRoom'");
    }

}
