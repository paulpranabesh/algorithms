package com.example.algorithm.array;

import javax.naming.PartialResultException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class CombinationSum {

    public List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> result = new LinkedList<>();
        getTheCombination( nums,  target,  0, 0,new Stack<>(), new LinkedList<>());
        return result;
    }

    private void getTheCombination(int[] nums, int target, int sum, int startIndex, Stack<Integer> stack, List<List<Integer>> result){
        if(sum>target){
            return;
        }
        if(sum == target){
            result.add(new ArrayList<>(stack));
        }
        for(int i = startIndex; i< nums.length; i++){
            stack.add(nums[i]);

            getTheCombination(nums, target,  sum+nums[i], i,stack,  result);
            stack.pop();

        }
    }
}
