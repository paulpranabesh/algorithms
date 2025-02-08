package com.example.algorithm.bit;

public class BitManupulation {
    public static void main(String[] args) {
        int val = 77;
        int mask = 1;
        Integer.toBinaryString(val);
        System.out.println(Integer.toBinaryString(val));
        int msbWithOne = (int) ((int) Math.log(77)/Math.log(2));
        while(msbWithOne>=0){
            mask = mask<<1 | 1;

            msbWithOne--;
        }
        System.out.println(Integer.toBinaryString(val^mask));

    }


}
