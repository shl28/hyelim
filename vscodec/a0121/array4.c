#include <stdio.h>

int main(){
    int arr[5] = {10, 5, 30, 20, 8};
    int i;
    int max = arr[0];

    for(i = 1; i < 5; i++) {
        if(arr[i] > max){
            max = arr[i];
        }
    }
    printf("최대값 : %d\n", max);

    return 0;
}