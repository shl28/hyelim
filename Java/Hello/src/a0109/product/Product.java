package a0109.product;

public class Product {
  // 필드
  private String name;
  private int price;
  private int quantity;
  private double discountRate; // 할인율

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public double getDiscountRate() {
    return discountRate;
  }

  // 할인율 설정(유효성 검사: )
  public void setDiscountRate(double discountRate) {
    if(discountRate < 0 || discountRate > 1){
      System.out.println("할인율은 0.0 ~ 1.0 사이여야 합니다.");
      return; // 빠져나감 
    }
    this.discountRate = discountRate;
  }

  public Product(String name, int price, int quantity) {
    this.name = name;
    this.price = price;
    this.quantity = quantity;
    this.discountRate = 0.0; // 기본 할인율 0%로 설정
  }

  public void printProductInfo() {
    // System.out.println("========== 영수증 ==========");
    System.out.println("=== 상품 정보 ===");
    System.out.println("상품명: " + name);
    System.out.println("단가: " + String.format("%,d", price) + "원");
    // String.format("%,d", price) : 세자리 마다 ,
    System.out.println("수량: " + quantity + "개");
    System.out.println("총금액: " + String.format("%,d", calcTotalPrice()) + "원");
    if (discountRate > 0) {
      System.out.println("할인율: " + (discountRate * 100) + "%");
      System.out.println("할인 금액: " + String.format("%,d" , calcDiscountAmount()) + "원");
      System.out.println("할인 후 금액: " + String.format("%,d" , calcFinalPrice()) + "원");
    } else{
      System.out.println("할인 후 금액: " + String.format("%,d" , calcFinalPrice()) + "원");
    }
    // System.out.println("부가세: " + String.format("%,d", calcVAT()) + "원");
    // System.out.println("최종 결제 금액: " + String.format("%,d", calcRealPrice()) + "원");
    // System.out.println("============================");
  }
  
    public void printReceipt() {
    System.out.println("========== 영수증 ==========");
    // System.out.println("=== 상품 정보 ===");
    // System.out.println("상품명: " + name);
    // System.out.println("단가: " + String.format("%,d", price) + "원");
    // // String.format("%,d", price) : 세자리 마다 ,
    // System.out.println("수량: " + quantity + "개");
    // System.out.println("총금액: " + String.format("%,d", calcTotalPrice()) + "원");
    // if (discountRate > 0) {
    //   System.out.println("할인율: " + (discountRate * 100) + "%");
    //   System.out.println("할인 금액: " + String.format("%,d" , calcDiscountAmount()) + "원");
    //   System.out.println("할인 후 금액: " + String.format("%,d" , calcFinalPrice()) + "원");
    // } else{
    //   System.out.println("할인 후 금액: " + String.format("%,d" , calcFinalPrice()) + "원");
    // }
    printProductInfo();
    System.out.println("부가세: " + String.format("%,d", calcVAT()) + "원");
    System.out.println("최종 결제 금액: " + String.format("%,d", calcRealPrice()) + "원");
    System.out.println("============================");
  }
  
  private int calcRealPrice() {
    return calcFinalPrice() + calcVAT();
  }

  private int calcVAT() {
    return (int)(calcFinalPrice() * 0.1);
  }

  // 최종 금액(할인 후 금액)
  private int calcFinalPrice() {
    return calcTotalPrice() - calcDiscountAmount();
    // 총금액- 할인금액 = 최종금액
  }

  // 할인 금액
  private int calcDiscountAmount() {
    return (int)(calcTotalPrice() * discountRate);
  }

  private int calcTotalPrice() {
    return price * quantity;
  }

}
