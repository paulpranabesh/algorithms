package com.example.algorithm.queue;

import java.util.LinkedList;
import java.util.Queue;

public class FindMaxInQueue {
    public static void main(String[] args) {
        new FindMaxInQueue().printMaxInQ(new int[]{1, 4, 2, 3, 7, 8,9, 5, 3, 2, 1, 8, 1,2,7,5, 4});
    }

    private void printMaxInQ(int[] nums) {
        Queue<Integer> queue = new LinkedList<>();
        LinkedList<Integer> maxList = new LinkedList<>();

        for (Integer num : nums) {
            queue.add(num);
            maxList.addLast(num);
            for (int i = maxList.size()-1; i >= 0 && maxList.get(i) <= num; i--) {
                maxList.set(i, num);
            }

        }
        while (!queue.isEmpty()) {
            System.out.println(queue + " max: " + maxList.getFirst());
            queue.remove();
            maxList.removeFirst();
        }

    }
}
