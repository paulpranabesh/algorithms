package com.example.algorithm.array;

public class CyclicSortedArray {
    public static void main(String[] args) {
        int start = cyclicallySortedMin(new int[]{3,4,5,6,1,2});
        System.out.println("Min item is at : " + start);

    }

    public static int cyclicallySortedMin(int[] a) {
        if (a == null) {
            return -1;
        }
        int low = 0, high = a.length - 1, right = a[a.length - 1];
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (a[mid] <= right && (mid == 0 || a[mid - 1] > a[mid])) {
                return mid;
            } else if (a[mid] > right) {
                low = mid + 1;  ///very imp good
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }
}
