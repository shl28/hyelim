#include <stdio.h>

int main() {
    int arr[8], even[8], odd[8];
    int i;
    int evenCount = 0, oddCount = 0;

    printf("8개의 정수를 입력하세요 > ");
    
    for(i = 0; i < 8; i++){
        scanf("%d", &arr[i]);
    }

    for(i = 0; i < 8; i++){
        if(arr[i] % 2 == 0){
            even[evenCount] = arr[i];
            evenCount++;
        } else {
            odd[oddCount] = arr[i];
            oddCount++;
        }
    }

    printf("짝수: ");
    if(evenCount == 0){
        printf("없음\n");
    } else {
        for(i = 0; i < evenCount; i++){
            printf("%d", even[i]);
            if(i < evenCount-1){
                printf(" ");
            }
        }
        printf("\n");
    }

    printf("홀수: ");
    if(oddCount == 0){
        printf("없음\n");
    } else {
        for(i = 0; i < oddCount; i++){
            printf("%d", odd[i]);
            if(i < oddCount-1){
                printf(" ");
            }
        }
        printf("\n");
    }

    return 0;
}