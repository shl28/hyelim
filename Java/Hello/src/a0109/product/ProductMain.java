package a0109.product;

public class ProductMain {
  public static void main(String[] args) {
    System.out.println("=== 상품 총 금액 계산 ===");
    
    // 상품 객체 생성
    Product product1 = new Product("노트북", 1000000, 2);
    Product product2 = new Product("마우스", 25000, 3);
    Product product3 = new Product("키보드", 50000, 1);

    product1.setDiscountRate(0.1); // 10% 할인
    product2.setDiscountRate(0.15); // 15% 할인
    // product3.setDiscountRate(1.1); // 15% 할인
    // product3.setDiscountRate(0.1); // 10% 할인

    System.out.println();
    // 상품1 정보 출력
    product1.printProductInfo();

    System.out.println();
    product2.printProductInfo();

    System.out.println();
    product3.printProductInfo();


    System.out.println();
    // 상품1 정보 출력
    product1.printReceipt();

    System.out.println();
    product2.printReceipt();

    System.out.println();
    product3.printReceipt();
  }
}
