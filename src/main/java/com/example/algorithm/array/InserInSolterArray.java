package com.example.algorithm.array;

/**
 * Insert into sorted array nu log(n)
 */
public class InserInSolterArray {
    public static void main(String[] args) {
        System.out.println("######## -> "+findTheIndex(new int[]{1,2,4,5,7,7,7,7,7,7,7,9,9,9,9}, 8));

    }

    private static int findTheIndex(int[] array, int target){
        if(array == null || array.length<=0){
            return 0;
        }
        if(array[0] > target){
            return 0;
        }
        if(array[array.length-1] < target){
            return array.length;
        }
        int start = 0;
        int end = array.length-1;
        while(start<=end){
            int mid = start + (end-start)/2;
            if(target > array[mid]){
                start = mid+1;
            }else if(target<array[mid]){
                if(mid>0 && target<array[mid-1] ){
                    end = mid-1;
                }else{
                    return mid == 0 ? 0 : mid;
                }
            }else {
                if(mid>0 && target==array[mid-1] ){
                    end = mid-1;
                }else {
                    return mid == 0 ? 0 : mid;
                }
            }

        }
        return -1;
    }
}
