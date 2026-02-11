package a0211.sort.exam;

import java.util.Arrays;

public class SelectionSort1 {
    public static void main(String[] args) {
        int[] array = {63,34,25,17,22,11,90};
        selectionSort(array);
        System.out.println("Sort Array: "+Arrays.toString(array));
    }

    private static void selectionSort(int[] array) {
        int n = array.length;

        for(int i = 0; i < n - 1; i++){
            int minIndex = i;

            for(int j = i + 1; j < n; j++){
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
    }
    
}
