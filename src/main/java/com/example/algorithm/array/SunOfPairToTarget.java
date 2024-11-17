package com.example.algorithm.array;

import java.util.*;

/**
 * Given an array of integers, find a pair of integers that sums to a number X.
 */
public class SunOfPairToTarget {
    public static void main(String[] args) {
        System.out.println("result "+new SunOfPairToTarget().returnThePair(new int[]{6,3,5,2,1,3,1,7}, 4));
    }

    private List<Pair> returnThePair(int[] array, int target){
        if(array == null || array.length<=1){
            return new ArrayList<>();
        }
        Set<Integer> store = new HashSet<>();
        List<Pair> result = new ArrayList<>();
        Arrays.stream(array).forEach(a->{
              if(store.contains(target-a)){
                  result.add(new Pair(a, target-a));
              }else{
                  store.add(a);
              }

        });
        return result;
    }

    private class Pair{
        int a;
        int b;

        public Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public String toString() {
            return a+", "+b;
        }
    }
}
