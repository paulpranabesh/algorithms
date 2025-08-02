package com.example.algorithm.heap;

import java.util.*;

public class KthLargest {

    private int k;
    Queue<Integer> queue = new PriorityQueue<>(Comparator.naturalOrder());
    public KthLargest(int k, int[] nums) {
        this.k = k;
        for(int i : nums){
            if(queue.size()<k){
                queue.offer(i);
            }else if(queue.peek()<i){
                queue.poll();
                queue.offer(i);
            }
        }

    }

    public int add(int val) {
        if(queue.size()<k){
            queue.offer(val);
        }else if(queue.peek()<val){
            queue.poll();
            queue.offer(val);
        }
        return queue.stream().max(Comparator.naturalOrder()).get();
    }
}
