package com.example.algorithm.array;

/**
 * Given a sorted array that can contain duplicates, find the first occurrence of the
 * target element. For example:
 * A = [1,3,4,6,6,6,7] and Target = 6, return index 3.
 */
public class BinarySearchWithDuplicate {

    public static void main(String[] args) {
        System.out.println("Result  :"+binSearchWithDup(new int[]{2,3,3,3,3,3,3,3,3,4,4,5,6}, 3));
    }

    private static int binSearchWithDup(int[] array, int target){
        int start = 0;
        int end = array.length-1;
        while(start<=end){
            int mid = start + (end-start)/2;
            if(array[mid] == target ){
                if(mid>0 && array[mid-1] == target){
                    end = mid-1;
                    continue;
                }
                return mid;
            }else if(array[mid]>target){
                if(mid == 0){
                  return -1;
                }
                end = mid-1;
            }else{
                start = mid+1;
            }
        }
        return -1;
    }
}
