package a0115;

import java.util.ArrayList;

public class ArrayListEx1 {
    public static void main(String[] args) {
        ArrayList <String> students = new ArrayList<>();

        students.add("홍길동");
        students.add("김철수");
        students.add("이영희");

        System.out.println();
        
        System.out.println("등록된 학생: " + students);

        System.out.println();
        
        System.out.println("=== 학생 목록 ===");

        for(int i = 0; i < students.size(); i++){
            System.out.println((i+1) + "번: " + students.get(i));
        }
        
        System.out.println();

        System.out.println("=== 학생 정보 수정 ===");
        System.out.println("수정 전: " + students);
        students.set(1, "김민수");
        System.out.println("수정 후: " + students);

        System.out.println();

        System.out.println("=== 학생 삭제 ===");
        System.out.println("삭제 전: " + students);
        students.remove("이영희");
        System.out.println("삭제 후: " + students);

        System.out.println();

        System.out.println("최종 학생 목록: " + students);
        System.out.println("총 학생 수: " + students.size());
        
    }
}
