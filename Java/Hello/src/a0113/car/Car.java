package a0113.car;

public class Car {
  String model;
  String color;

  public Car(){
    this("기본모델","흰색");
  }

  public Car(String model, String color) {
    this.model = model;
    this.color = color;
  }
  
}
