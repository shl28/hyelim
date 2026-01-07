package a0107;

public class Method4 {
  public static void main(String[] args) {
    // 원기둥 부피 = v
    // 원기둥 반지름 = r
    // 원기둥 높이 = h
    // 원주율 파이 = 3.141592...  Math.PI
    // [원기둥 부피 공식] 
    // v = π*r²*h     (r² = r^2)
    
    double r = 7;
    double h = 5;

    double v = volume(r , h);
    System.out.printf("반지름이 %.1f, 높이가 %.1f인 원기둥의 부피: %.1f", r, h, v);

  }

  static double volume(double r, double h) {

    return Math.PI * r * r * h;

  }
}
