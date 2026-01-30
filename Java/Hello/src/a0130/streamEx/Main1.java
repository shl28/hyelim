package a0130.streamEx;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main1 {
    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 700),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
        );

        // 문제1. 2011년 일어난 모든 트랜잭션을 찾아 오름차순으로 정렬
        practice1(transactions);

        // 문제2. 거래자가 근무하는 모든 도시를 중복없이 나열
        practice2(transactions);

        // 문제3. Combridge에서 근무하는 모든 거래자를 찾아 이름순으로 정렬
        practice3(transactions);

        // 문제4. 밀라노엔 거래자가 있는지 유무
        practice4(transactions);

        // 문제5. 케임브리지 거주하는 모든 트랜잭션값 출력
        practice5(transactions);

        // 문제6. 모든 거래자의 이름을 알파벳 순으로 정렬
        practice6(transactions);

        // 문제7. 최대값 구하기
        practice7(transactions);

        // 문제8. 최소값 구하기
        practice8(transactions);
    }

    private static void practice1(List<Transaction> transactions) {
        List<Transaction> result = transactions.stream().filter(transaction -> 2011 == transaction.getYear())
            .sorted(Comparator.comparing(Transaction::getValue)).collect(Collectors.toList());
        // .sorted(Comparator.comparing(transaction -> transaction.getValue())).collect(Collectors.toList());
        System.out.println(result);
        // Comparator.comparing(기준이 될 값 반환 함수)
        // Transaction::getValue : transaction -> transaction.getValue()   ==  Transaction 객체에서 value 값을 꺼냄
        // .sorted(Comparator.comparing(Transaction::getValue).reversed()) 내림차순
        // .sorted(Comparator.comparing(transaction -> transaction.getTrader().getName())) 이름별
    }

    private static void practice2(List<Transaction> transactions) {
        List<String> result = transactions.stream().map(tran -> tran.getTrader().getCity())
            .distinct().collect(Collectors.toList());

        result.forEach(System.out::println);
        //tran(transactions) 객체를 입력받아서 
        //.map(Transaction::getTrader) //각 거래에서 거래자 객체를 추출
        //.map(Trader::getCity) //각 거래자에서 도시정보추출
    }

    private static void practice3(List<Transaction> transactions) {
        List<Trader> result = transactions.stream().map(Transaction::getTrader)
            .filter(trader -> "Cambridge".equals(trader.getCity())).distinct()
            .sorted(Comparator.comparing(Trader::getName))
            .collect(Collectors.toList());

        System.out.println(result);
    }

    private static void practice4(List<Transaction> transactions) {
        boolean result = transactions.stream().anyMatch(tran -> "Milan".equals(tran.getTrader().getCity()));

        System.out.println(result);
    }

    private static void practice5(List<Transaction> transactions) {
        List<Transaction> result = transactions.stream().filter(tran -> "Cambridge".equals(tran.getTrader().getCity())).collect(Collectors.toList());

        System.out.println(result);
    }

    private static void practice6(List<Transaction> transactions) {
        List<String> result = transactions.stream().map(Transaction::getTrader).map(Trader::getName)
        .distinct().sorted().collect(Collectors.toList());
        // .map((tran) -> tran.getTrader().getCity())

        System.out.println(result);
    }

    private static void practice7(List<Transaction> transactions) {
        Transaction result = transactions.stream().max(Comparator.comparing(Transaction::getValue))
            .orElse(null); // 값이 없으면 null, 있으면 객체 반환 (min, max : null 값 처리해줘야 함)

        System.out.println(result);
    }

    private static void practice8(List<Transaction> transactions) {
        Optional<Transaction> result = transactions.stream().min(Comparator.comparing(Transaction::getValue));

        System.out.println(result);
        System.out.println(result.get().getValue());
        // Optional 의 경우 get() 쓰고 원하는 getter(getValue) 사용
    }

}
