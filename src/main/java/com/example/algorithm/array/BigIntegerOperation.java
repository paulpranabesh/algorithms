package com.example.algorithm.array;

import java.util.Arrays;
import java.util.stream.Stream;

public class BigIntegerOperation {
    public static void main(String[] args) {
        int[] result = new BigIntegerOperation().add(new int[]{1,6,4,3,5,6,7,0}, new int[]{1,3,1});
        Arrays.stream(result).forEach(System.out::print);
    }

    private  int[] add(int[] a, int[] b){
        int[] result = new int[((a.length>b.length ? a.length : b.length)+1)];
        int rIndex = result.length-1;
        int aIndex = a.length-1;
        int bIndex = b.length-1;
        int inHand = 0;
        while(aIndex>=0 && bIndex >=0){
            int val = a[aIndex--]+b[bIndex--];
            result[rIndex--] = val % 10 + inHand;
            inHand = val / 10;

        }

        while(aIndex>=0){
            result[rIndex--] = a[aIndex] % 10 + inHand;
            inHand = a[aIndex--]/ 10;
        }

        while(bIndex >=0){
            result[rIndex--] = b[bIndex] % 10 + inHand;
            inHand = b[bIndex--] / 10;
        }
        result[rIndex--] = inHand;
        return result;
    }
}
