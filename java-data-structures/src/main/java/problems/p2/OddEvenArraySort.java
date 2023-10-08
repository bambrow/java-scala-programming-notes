package problems.p2;

import java.util.Arrays;

public class OddEvenArraySort {
    public static void main(String[] args) {
        int[] arr1 = new int[] {3,1,2,4};
        arraySort(arr1);
        System.out.println(Arrays.toString(arr1));
        int[] arr2 = new int[] {0};
        arraySort(arr2);
        System.out.println(Arrays.toString(arr2));
        int[] arr3 = new int[] {1,3,5,7,9,2,4,6,8};
        arraySort(arr3);
        System.out.println(Arrays.toString(arr3));
    }

    public static void arraySort(int[] arr) {
        int even = 0, odd = arr.length - 1;
        while (even < odd) {
            while (even < odd && arr[even] % 2 == 0) {
                even++;
            }
            while (even < odd && arr[odd] % 2 == 1) {
                odd--;
            }
            if (even < odd) {
                swap(arr, even, odd);
                even++;
                odd--;
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }
}
