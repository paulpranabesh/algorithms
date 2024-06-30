package com.example.algorithm.graph;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class TotalImportanceRoad {

    public static void main(String[] args) {
        new TotalImportanceRoad().maximumImportance(5, new int[][]{{0,1},{1,2},{2,3},{0,2},{1,3},{2,4}});
    }
    public long maximumImportance(int n, int[][] roads) {
        Map<Integer, Long> cardinality = new HashMap<>();
        AtomicInteger weightage = new AtomicInteger(n);
        for(int[] values : roads){
            cardinality.putIfAbsent(values[0], 0l);
            cardinality.computeIfPresent(values[0], ( now, prv)->prv+1l);
            cardinality.putIfAbsent(values[1], 0l);
            cardinality.computeIfPresent(values[1], (now, prv)->prv+1l);
        }
        Map<Integer, Integer> weightageMap = cardinality.entrySet().stream().sorted((e1, e2)->-1 * e1.getValue().compareTo(e2.getValue()))
                .map(i->i.getKey()).collect(Collectors.toMap(i->i,  (a)->weightage.getAndDecrement()));

        long sum = 0;
        for(int[] values : roads){
            sum +=  weightageMap.get(values[0])+ weightageMap.get(values[1]);
        }
        return sum;
    }
}
