package a0108.student;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    
    Scanner sc = new Scanner(System.in);

    Student[] students = new Student[2];

    for(int i = 0; i < students.length; i++){
      System.out.println("==== 학생 " + (i+1) + " 정보입력 ====");

      System.out.print("이름 : ");
      String name = sc.nextLine();
      // String name = sc.next(); 로 받으면 for 문 마지막에 sc.nextLine(); 안해도 가능

      System.out.print("국어 : ");
      int kor = sc.nextInt();
      
      System.out.print("영어 : ");
      int eng = sc.nextInt();

      System.out.print("수학 : ");
      int math = sc.nextInt();

      // nextInt() 후에 버퍼를 비우기 위한 nextLine() // 숫자입력 끝나고 엔터가 삽입될 경우가 있기에 그것을 제거하는 작업
      sc.nextLine();
      // 또는 int eng1 = Integer.parseInt(sc.nextLine()); //  문자로 입력된 것을 숫자로 변형 
      // 입력은 sc.nextLine() 로 통일 후 Integer.parseInt 사용하여 숫자로 변경

      students[i] = new Student(name, kor, eng, math);

    }

    System.out.println("\n=== 학생정보 출력 ===\n");
    
    for(int i = 0; i < students.length; i++){
      students[i].showInfo();
    }

    sc.close();

  }
}

// 이름: 홍길동
// 국어: 90, 영어: 85, 수학: 95
// --------------------
// 이름: 김철수
// 국어: 80, 영어: 75, 수학: 70
// --------------------

