package a0108.student;

public class Student {
  
  String name;
  int kor;
  int eng;
  int math;

  public Student(String name, int kor, int eng, int math) {
    this.name = name;
    this.kor = kor;
    this.eng = eng;
    this.math = math;
  }

  int getTotal(){
    return kor + eng + math;
  }

  double getAvg(){
    return getTotal() / 3.0;
  }

  // int getTotal(int kor, int eng, int math) {
  //   return kor + eng + math;
  // }

  // double getAvg(int getTotal) {
  //   return (double) getTotal / 3;
  // }

  public void showInfo(){

    System.out.println("이름: " + name);
    System.out.println("국어: " + kor + ", 영어: " + eng + ", 수학: " + math);
    System.out.println("총점: " + getTotal());
    System.out.println("평균: " + getAvg());
    System.out.println("===============================");

  }


}
