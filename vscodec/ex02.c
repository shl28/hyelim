#include <stdio.h>
int main(){
    int myNum = 15;
    float myFloatNum = 5.99;
    char myLetter = 'D';
    printf("%d\n", myNum);
    printf("%.2f\n", myFloatNum);
    printf("%c\n", myLetter);
    // %d : int 의 형식 지정자 (십진수)    
    // %f : float 의 형식 지정자 (실수)    // %.2f : 소수점 2째자리까지 출력
    // %c : char 의 형식 지정자 (문자)    

    printf("내가 좋아하는 숫자 : %d", myNum);

    return 0;
}