package com.example.algorithm.array;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an array nums of unique integers, return all possible subsets of nums.
 *
 * The solution set must not contain duplicate subsets. You may return the solution in any order.
 *
 * Example 1:
 *
 * Input: nums = [1,2,3]
 *
 * Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 */
public class FindAllSubSet {

    public static void main(String[] args) {
        System.out.println(new FindAllSubSet().subsets(new int[]{1,2,3}));
    }
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        for(int i = 0; i < Math.pow(2,nums.length) ; i++){
            String binary = getBinary(i, nums.length);
            List<Integer> subResult = new ArrayList<>();
            for(int j=0 ; j<binary.length();j++){
                int takeIt = Integer.valueOf(binary.charAt(j)+"");
                if(takeIt != 0){
                    subResult.add(nums[j]);
                }

            }
            result.add(subResult);
        }
        return result;
    }

    private String getBinary(int val, int length){
        String binString = Integer.toBinaryString(val);
        StringBuffer sb = new StringBuffer();
        for(int i = 1; i<=length-binString.length();i++){
            sb.append("0");
        }
        return sb.toString()+binString;
    }
}
