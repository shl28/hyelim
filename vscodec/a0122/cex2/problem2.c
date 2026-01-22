#include <stdio.h>

int main() {
    int arr[8];
    int i;
    int count = 0, sum = 0;
    float average;

    printf("8개의 정수를 입력하세요 > ");

    for(i = 0; i < 8; i++){
        scanf("%d", &arr[i]);
    }

    
    return 0;
}