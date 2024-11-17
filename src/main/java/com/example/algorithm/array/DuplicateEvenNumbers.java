package com.example.algorithm.array;


import java.util.Arrays;

/**
 * Given an array of numbers, replace each even number with two
 * of the same number. e.g, [1,2,5,6,8, , , ,] -> [1,2,2,5,6,6,8,8].
 * Assume that the array has the exact amount of space to accommodate the result.
 */
public class DuplicateEvenNumbers {
    public static void main(String[] args) {
        int[] result = new DuplicateEvenNumbers().cloneEvenNumbers(new int[]{1,2,5,6,8,-1 ,-1 ,-1 ,});
        Arrays.stream(result).forEach(System.out::print);
    }

    private  int[] cloneEvenNumbers(int[] a){
       if(a == null || a.length<1){
           return a;
       }
       int last = a.length-1;
       int lastNumPos = findLastPositionOfNumbers(a);
       while(lastNumPos>=0){
           if(a[lastNumPos]%2 == 0){
               a[last--] = a[lastNumPos];
               a[last--] = a[lastNumPos];
           }else{
               a[last--] = a[lastNumPos];
           }
           lastNumPos--;
       }
       return a;
    }

    private int findLastPositionOfNumbers(int[] a){
        int i =0;
        for(; i< a.length; i++){
            if(a[i] == -1){
                return i-1;
            }
        }
        return i-1;
    }
}
