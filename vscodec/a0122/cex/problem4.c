#include <stdio.h>

int main(){
    int arr[6];
    int i;
    int even[6], odd[6];
    int evenCount = 0, oddCount = 0;

    printf("6개의 정수를 입력하세요 > ");
    for(i = 0; i < 6; i++){
        scanf("%d", &arr[i]);
    }

    for(i = 0; i < 6; i++){
        if (arr[i] % 2 == 0){
            even[evenCount] = arr[i] ;
            evenCount++;
        } else {
            odd[oddCount] = arr[i] ;
            oddCount++;
        }
    }

    printf("짝수: ");
    if(evenCount == 0) {
        printf("없음\n");
    } else {
        for(i = 0; i <evenCount; i++){
            printf("%d", even[i]);
            if(i < evenCount - 1){
                printf(" ");
            }
        }
        printf("\n");
    }

    printf("홀수: ");
    if(oddCount == 0) {
        printf("없음\n");
    } else {
        for(i = 0; i <oddCount; i++){
            printf("%d", odd[i]);
            if(i < oddCount - 1){
                printf(" ");
            }
        }
        printf("\n");
    }

    return 0;

}