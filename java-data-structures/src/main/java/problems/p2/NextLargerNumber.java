package problems.p2;

public class NextLargerNumber {
    public static void main(String[] args) {
        System.out.println(nextNum(12));
        System.out.println(nextNum(21));
        System.out.println(nextNum(5231));
        System.out.println(nextNum(25663));
        System.out.println(nextNum(25665));
    }

    public static int nextNum(int n) {
        if (n <= 0) {
            return -1;
        }
        String num = String.valueOf(n);
        char[] arr = num.toCharArray();
        int i = arr.length - 2;
        while (i >= 0 && arr[i+1] <= arr[i]) {
            i--;
        }
        if (i < 0) {
            return -1;
        }
        int j = arr.length - 1;
        while (j > i && arr[j] <= arr[i]) {
            j--;
        }
        swap(arr, i, j);
        for (int k = i+1, l = arr.length-1; k < l; k++, l--) {
            swap(arr, k, l);
        }
        try {
            return Integer.parseInt(new String(arr));
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static void swap(char[] arr, int i, int j) {
        char temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }
}
