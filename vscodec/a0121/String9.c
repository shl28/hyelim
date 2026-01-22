#include <stdio.h>
#include <string.h>

int main(){
    char str[] = "Hello  World";
    char search = 'o';
    int i, found = 0;

    for(i = 0; i < strlen(str); i++){
        if(str[i] == search){
            printf("%c를 %d의 위치에서 찾았습니다. \n", search, i);
            found = 1;
        }
    }
    if(!found){
        printf("%c를 찾을수 없습니다. \n", search);
    }

    return 0;
}