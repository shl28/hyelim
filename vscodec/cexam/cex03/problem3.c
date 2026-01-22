#include <stdio.h>
#include <string.h>

int main() {
    char str[101];
    int i;
    int count = 0;
    char ch;

    printf("전체 문자열을 입력하세요 > ");
    fgets(str, 101, stdin);
    int len = strlen(str);
    if(len > 0 && str[len - 1] == '\n'){
        str[len - 1] = '\0';
        len--;
    }

    printf("찾을 문자를 입력하세요 > ");
        scanf(" %c", &ch);

    for(i = 0; i < len; i++){
        if(str[i] == ch){
            count++;
        }
    }

    printf("%c가 나타나는 횟수: %d", ch, count);

    return 0;
}