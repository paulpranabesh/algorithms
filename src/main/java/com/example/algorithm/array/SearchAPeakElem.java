package com.example.algorithm.array;

/**
 * Search for a Peak: A peak element in array A is an A[i] where its adjacent elements are less than A[i]. So, A[i - 1] < A[i] and A[i + 1] < A[i].
 *
 * Assume there are no duplicates. Also, assume that A[-1] and A[length] are negative infinity (-∞). So A[0] can be a peak if A[1] < A[0].
 *
 * A = [1,3,4,5,2] => Peak = 5
 * A = [5,3,1] => Peak = 5
 * A = [1,3,5] => Peak = 5
 */
public class SearchAPeakElem {
    public static void main(String[] args) {
        int start = searchThePeak(new int[]{1,3,4,5,2});
        System.out.println("The peak is at : " + start);

    }

    public static int searchThePeak(int[] a) {
        if (a == null) {
            return -1;
        }
        int low = 0, high = a.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int left = mid <=0 ? Integer.MIN_VALUE : a[mid-1];
            int right = mid >= a.length - 1 ? Integer.MIN_VALUE : a[mid+1];
            if(left <a[mid] && a[mid] > right){
                return mid;
            }else if(left <a[mid] && a[mid] < right){
                low = mid+1;
            }else if(left >a[mid] && a[mid] > right){
                high = mid -1;
            }else if(left >a[mid] && a[mid] < right){
                low = mid+1;
            }
        }
        return -1;
    }
}
