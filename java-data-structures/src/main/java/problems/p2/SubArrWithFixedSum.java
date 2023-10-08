package problems.p2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SubArrWithFixedSum {
    public static void main(String[] args) {
        System.out.println(minSumOfLengths(new int[] {7,3,4,7}, 7));
        System.out.println(minSumOfLengths(new int[] {5,5,4,4,5}, 3));
        System.out.println(minSumOfLengths(new int[] {5,1,3,2,4,3}, 6));
    }

    public static int minSumOfLengths(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        List<Integer> res = new ArrayList<>();
        int l = 0;
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (sum >= k) {
                while (sum >= k) {
                    if (sum > k) {
                        sum -= arr[l];
                        l++;
                    } else {
                        res.add(i-l+1);
                        l = i + 1;
                        sum = 0;
                    }
                }
            }
        }
        if (res.size() < 2) {
            return -1;
        }
        Collections.sort(res);
        return res.get(0) + res.get(1);
    }
}
