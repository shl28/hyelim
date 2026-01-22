#include <stdio.h>
#include <string.h>

int main() {
    char str[101];
    char ch;
    int i;
    int count = 0;
    int len;

    printf("전체 문자열 입력하세요 > ");
    fgets(str, 101, stdin);
    len = strlen(str);
    if(len > 0 && str[len-1] == '\n'){
        str[len-1] = '\0';
        len--;
    }

    printf("\n");

    printf("찾을 문자를 입력하세요 > ");
    scanf(" %c", &ch);
    // 앞에 일력문이 있기에 공백문자 주의 필수로 입력!
    // %c 앞의 공백은 '공백문자들을 모두 건너 뛰어라
    // (스페이스, 엔터 \n , 탭 \t 전부 포함)

    for(i = 0; i < len; i++){
        if(str[i] == ch){
            count ++;
        }
    }

    printf("%c가 나타나는 횟수: %d\n", ch, count);

    return 0;
}