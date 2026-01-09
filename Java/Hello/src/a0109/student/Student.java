package a0109.student;

public class Student {
  // 접근 제어자 privatr : 보안
  private String name;
  private int korean;
  private int english;
  private int math;
  // private int sum = 0;

  public Student(String name, int korean, int english, int math) {
    this.name = name;
    this.korean = korean;
    this.english = english;
    this.math = math;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getKorean() {
    return korean;
  }

  public void setKorean(int korean) {
    this.korean = korean;
  }

  public int getEnglish() {
    return english;
  }

  public void setEnglish(int english) {
    this.english = english;
  }

  public int getMath() {
    return math;
  }

  public void setMath(int math) {
    this.math = math;
  }

  public void printInfo(){
    System.out.println("=== " + name + " 학생 정보 ===");
    System.out.println("국어: " + korean + "점");
    System.out.println("영어: " + english + "점");
    System.out.println("수학: " + math + "점");
    System.out.println("총점: " + calcTotal() + "점");
    System.out.println("평균: " + String.format("%.2f",calcAvg()) + "점");
    System.out.println("등급: " + calcGrade());
  }

  // private String calcGrade() {
  //   double avg = calcAvg();
  //   if (avg >= 90) { // 90이상이면 "A" 값을 리턴
  //     return "A";
  //   } else if (avg >= 80) {
  //     return "B";
  //   }else if (avg >= 70) {
  //     return "C";
  //   }else if (avg >= 60) {
  //     return "D";
  //   }else {
  //     return "F";
  //   }
  // }

  private String calcGrade() {
    double avg = calcAvg();
    if (avg >= 90) return "A";
    else if (avg >= 80) return "B";
    else if (avg >= 70) return "C";
    else if (avg >= 60) return "D";
    else return "F";
    //  결과값이 한 줄일 때 if {} 생략 가능
  }

  public int calcTotal() {
    return korean + english + math;
  }

  public double calcAvg() {
    return calcTotal() / 3.0;
  }

  // private int calcTotal() {
  //   sum = korean + english + math;
  //   return sum;
  // }
  

}
