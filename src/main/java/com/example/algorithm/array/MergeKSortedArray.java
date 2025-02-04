package com.example.algorithm.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class MergeKSortedArray {

    public static void main(String[] args) {
        List<Integer>[] arrays =  new List[]{
                Arrays.asList(11,34,55,66,77,88,90),
                Arrays.asList(1,3,4,6,7,8,9),
                Arrays.asList(10,20,30,40,50,60),
                Arrays.asList(12,23,34,45,56,67,78),
                Arrays.asList(102,309,406,507),
                Arrays.asList(-11,-2,-1,0,12,44,57,89),
        };
        new MergeKSortedArray().mergeKArrays(arrays);
    }


    private void mergeKArrays(List<Integer>[] arrays){
        int [] indexes = new int[arrays.length];
        List<Integer> resultList = new ArrayList<>();
        Arrays.fill(indexes, 0);
        int minIndex = 0;
        while(minIndex != -1){
            int min = Integer.MAX_VALUE;
            minIndex = -1;
            for(int i =0; i< arrays.length; i++){
                List<Integer>list = arrays[i];
                if(indexes[i] < list.size()){
                    if(min > list.get(indexes[i])){
                        min = list.get(indexes[i]);
                        minIndex = i;
                    }
                }
            }
            if(minIndex != -1){
                List<Integer> listWithMinItem = arrays[minIndex];
                resultList.add(listWithMinItem.get(indexes[minIndex]));
                indexes[minIndex] = indexes[minIndex] +1;
            }
        }
        resultList.stream().forEach(System.out::println);
    }
}
