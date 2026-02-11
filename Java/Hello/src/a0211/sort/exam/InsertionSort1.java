package a0211.sort.exam;

import java.util.Arrays;

public class InsertionSort1 {
    public static void main(String[] args) {
        int[] array = {63,34,25,17,22,11,90};
        insertionSort(array);
        System.out.println("Sort Array: "+ Arrays.toString(array));
    }

    private static void insertionSort(int[] array) {
        int n = array.length;

        for(int i = 1; i < n; i++){
            int key = array[i];
            int j = i - 1;

            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            
            array[j + 1] = key;
           
        }

    }
    
}
