#include <stdio.h>

int main () {
    int arr[8];
    int i;
    int sum = 0;
    float average;
    int count = 0;

    printf("정수를 8개 입력해주세요 > ");

    for(i = 0; i < 8; i++) {
        scanf("%d", &arr[i]);
    }

    for(i = 0; i < 8; i++) {
        sum += arr[i];
    }

    average = (float) sum / 8;

    for(i = 0; i < 8; i++) {
        if(arr[i] >= average) {
            count++;
        }
    }

    printf("평균: %.2f\n", average);
    printf("평균 이상인 값의 개수: %d\n", count);

    return 0;
}