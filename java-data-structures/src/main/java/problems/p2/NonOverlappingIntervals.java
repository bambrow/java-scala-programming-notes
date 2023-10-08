package problems.p2;

import java.util.ArrayList;
import java.util.Arrays;

public class NonOverlappingIntervals {
    public static void main(String[] args) {
        printArray(nonOverlap(new int[][] {
                new int[] {1,2},
                new int[] {2,3},
                new int[] {3,4},
                new int[] {1,3}
        }));
        printArray(nonOverlap(new int[][] {
                new int[] {1,2},
                new int[] {1,2},
                new int[] {1,3}
        }));
        printArray(nonOverlap(new int[][] {
                new int[] {1,2},
                new int[] {2,3},
                new int[] {4,10}
        }));
    }

    // marker[0]: interval 0-1
    // marker[1]: interval 1-2
    // ...
    // marker[length-1]: interval (length-1)-length
    public static int[][] nonOverlap(int[][] intervals) {
        int[] marker = new int[10];
        for (int[] interval : intervals) {
            int start = interval[0];
            int end = interval[1];
            for (int i = start; i < end; i++) {
                marker[i]++;
            }
        }
        System.out.println(Arrays.toString(marker));
        ArrayList<int[]> res = new ArrayList<>();
        boolean continuous = false;
        int start = 0;
        for (int i = 0; i < marker.length; i++) {
            if (marker[i] == 1) {
                if (!continuous) {
                    start = i;
                    continuous = true;
                }
            } else {
                if (continuous) {
                    res.add(new int[] {start, i});
                    continuous = false;
                }
            }
        }
        if (continuous) {
            res.add(new int[] {start, marker.length});
        }
        int[][] arr = new int[res.size()][];
        return res.toArray(arr);
    }

    public static void printArray(int[][] arr) {
        System.out.println(Arrays.deepToString(arr));
    }
}
