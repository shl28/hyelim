package a0205.jinair;

import java.time.LocalDate;
import java.time.Period;

public class Passenger {
    private String name;
    private int birthDate;
    private String pw;
    private String seat;
    
    public Passenger(String name, int birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    public Passenger(String name, int birthDate, String pw) {
        this.name = name;
        this.birthDate = birthDate;
        this.pw = pw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(int birthDate) {
        this.birthDate = birthDate;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    // 15세 이상 국제선 예약 가능 제한
    public boolean man15(Passenger p){ // birthDate 예시 : 831016
        int y = p.birthDate / 10000;  // 831016 -> 83만 남음
        int m = (p.birthDate % 10000) / 100; // 10000으로 나눈 나머지 : 1016 -> 100 나눔: 10
        int d = p.birthDate % 100; // 16

        if (y > 0 && y <= 26) {
            y = y + 2000; // 2000년 이후
        } else {
            y += 1900;
        }
        // y = (y > 0 && y <= 26) ? y + 2000 : y + 1900;

        LocalDate birthDate2 = LocalDate.of(y,m,d);  // 생년월일 1983 10 16
        LocalDate currentDate = LocalDate.now();  // 현재 날짜: 2026.02.05.

        int age = Period.between(birthDate2, currentDate).getYears();
        // 생년월일과 오늘의 년월일을 비교해서 년도만 추출해서 연령을 구함

        return age > 15;
    }

}
