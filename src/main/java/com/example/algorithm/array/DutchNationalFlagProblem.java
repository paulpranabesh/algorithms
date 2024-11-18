package com.example.algorithm.array;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.stream.IntStream;

/**
 *) Dutch National Flag Problem: Given an array of integers A and a pivot, rearrange A in the following order:
 * [Elements less than pivot, elements equal to pivot, elements greater than pivot]
 *
 * For example, if A = [5,2,4,4,6,4,4,3] and pivot = 4 -> result = [3,2,4,4,4,4,6,5]
 */
public class DutchNationalFlagProblem {

    public static void main(String[] args) {
        Arrays.stream(arrangeArray(new int[]{5,2,4,4,6,4,4,3}, 3)).forEach(System.out::println);
    }

    private static int[] arrangeArray(int []array, int pivot){
        if(array == null || array.length<=1){
            return array;
        }
        int start = 0;
        int end = array.length-1;
        int index = 0;
        while(index < end){
            if(array[index] < pivot){
                swap(array, start, index);
                start++;
                index++;
            }else if(array[index] > pivot){
                swap(array, end, index);
                end--;
            }else{
                index++;
            }

        }

        return array;
    }
    private static void swap(int[] array, int i, int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
