#include <stdio.h>
#include <string.h>

int main(){
    char name[20];
    printf("이름을 입력하세요: ");
    fgets(name, 20, stdin);  // scanf 처럼 문자 입력
    // printf("입력한 이름: %s\n", name);

    // name[strlen(name) - 1] = '\0';
    // 입력: abc + 엔터
    // index : 0  1  2  3  4
    // value : a  b  c \n \0
    // name[strlen(name) - 1] = '\0';  → enter \n 지운다는 뜻
    // scanf: 공백 전까지만 입력가능
    // fget : 공백 입력 가능, 문장 단위로 입력 가능
    // printf("입력한 이름: %s\n", name);

    // 실무용
    // 문자열끝에 \n 있으면 \0 으로 대체
    if (name[strlen(name) - 1] == '\n') {
            name[strlen(name) - 1] = '\0';
    }

    printf("입력한 이름: %s\n", name);
    return 0;
}