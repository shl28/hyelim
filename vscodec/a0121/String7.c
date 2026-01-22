#include <stdio.h>
#include <string.h>

int main(){
    char str1[] = "Hello";
    char str2[] = "Hello";
    char str3[] = " World";

    if(strcmp(str1, str2) == 0){
        printf("str1과 str2는 같습니다.\n");
    }

    if(strcmp(str1, str3) != 0){
        printf("str1과 str3는 다릅니다.\n");
    }

    return 0;
}