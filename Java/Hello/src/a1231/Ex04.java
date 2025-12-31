package a1231;

public class Ex04 {
    public static void main(String[] args) {
        
        // age 나이
        // height 키
        // parent 보호자 동반 여부
        // heartDease 심장 질환 여부
        // take 탑승 가능 여부

        int age = 6;
        int height = 130;
        boolean parent = false;
        boolean heartDease = false;
        boolean take = ((age>=6 && height >= 120) || (height >= 120 && parent == true)) && heartDease == false ;
        System.out.println(take);
        // 신장이 120 이상이면서 나이가 6세이거나 보호자가 동반되고 심장질환이 없을 때만 탑승 가능


    }
}
