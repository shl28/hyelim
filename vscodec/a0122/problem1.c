#include <stdio.h>

int main(){
    int arr[6];
    int i;
    int max, min;

    for(i = 0; i < 6; i++) {
        scanf("%d", &arr[i]);
    }

    max = arr[0];
    min = arr[0];

    for(i = 1; i < 6; i++) {
        if(arr[i] > max){
            max = arr[i];
        }
    }

    for(i = 1; i < 6; i++) {
        if(arr[i] < min){
            min = arr[i];
        }
    }

    printf("최댓값: %d\n", max);
    printf("최솟값: %d\n", min);

    return 0;

}