package com.example.algorithm.array;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of positive and negative integers, find a subarray whose sum equals X.
 *
 * For example: Input = [2,4,-2,1,-3,5,-3], X = 5 --> Result = [2,4,-2,1]
 */
public class SubArraySunToTarget {
    public static void main(String[] args) {
       new SubArraySunToTarget().findThePosition(new int[]{2,4,-2,1,-3,5,-3}, 0);
    }

    private void findThePosition(int[] array, int target){
        if(array == null || array.length==0){
            System.out.println("Data not found");
            return;
        }
        if(array.length==1 && target == array[0]){
            System.out.println("subarray is 0...0");
            return;
        }
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        int index = 0;
        while(index<array.length){
            sum+=array[index];
            if(sum == target){
                System.out.println("subarray is "+0+"..."+index);
                //return;
            }
            if(map.containsKey(sum - target)){
                System.out.println("subarray is "+(map.get(sum - target)+1) +"..."+index);
                //start = index+1;
            }

            map.put(sum, index++);
        }
    }
}
