package a0123.Map;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MovieRecommandationSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // 영화 목록 초기화
        HashMap<String, Double> movies = new HashMap<>();
        movies.put("인터스텔라", 9.2);
        movies.put("어벤져스", 8.5);
        movies.put("기생충", 9.0);
        movies.put("겨울왕국", 8.7);
        movies.put("토이스토리", 9.1);
        movies.put("라라랜드", 8.8);
        movies.put("매트릭스", 8.9);

        HashMap<String, Double> input = new HashMap<>();

        double totalRate = 0.0;
        int totalCount = 0;

        System.out.println();

        System.out.println("=== 영화 평점 입력 ===");
        System.out.println("영화를 보고 평점을 입력하세요 (종료: '종료')");

        while (true) {

            //  영화 목록 출력
            System.out.println("\n=== 영화 목록 ===");
            for(Map.Entry<String,Double> entry : movies.entrySet()){
                System.out.println(entry.getKey() + " : " + entry.getValue() + "점");
            }

            System.out.print("\n평점을 입력할 영화명을 입력하세요 (종료: '종료'): ");
            String title = scanner.nextLine();
            if (title.equals("종료")) {
                break;
            }
            if (!movies.containsKey(title)) {
                System.out.println("해당 영화가 없습니다. 다시 입력해주세요");
                continue;
            }

            System.out.print("평점을 입력하세요 (0.0 ~ 10.0): ");
            double rate = scanner.nextDouble();
            scanner.nextLine();
            if (rate < 0.0 || rate > 10.0) {
                System.out.println("평점은 0.0부터 10.0 사이여야 합니다.");
                continue;
            }

            if (movies.containsKey(title)) {
                input.put(title, rate); 
                System.out.printf("%s에 %.1f점을 주셨습니다.\n", title, rate);
            }

            totalCount++;
            totalRate += rate;

        }

        scanner.close();

        System.out.println();

        // 사용자 목록 출력
        System.out.println("=== 입력한 평점 ===");
        for(Map.Entry<String,Double> entry : input.entrySet()){
            System.out.println(entry.getKey() + " : " + entry.getValue() + "점");
        }
        System.out.println();

        // 평균 평점 계산
        double averageScore = totalRate / totalCount;
        System.out.printf("평균 평점: %.2f점\n", averageScore);
        System.out.println();

        System.out.println("=== 추천 영화 (9.0점 이상) ===");
        boolean found = false; 

        for (Map.Entry<String, Double> entry : movies.entrySet()) {
            String movieName = entry.getKey();
            double score = entry.getValue();

            if (score >= 9.0 && !input.containsKey(movieName)) {
                System.out.println(movieName + ": " + score + "점");
                found = true; 
            }
        }

        if (!found) {
            System.out.println("추천할 영화가 없습니다.");
        }
        
    
    }
}
