package com.example.algorithm.queue;

import java.util.*;
import java.util.concurrent.DelayQueue;
import java.util.stream.Collectors;

public class MaxElementInSlidingWindow {
    public static void main(String[] args) {
         new MaxElementInSlidingWindow().printMaxInWindow(new int[]{1, 4, 2, 3, 7, 8,9, 5, 3, 2, 1, 8, 1,2,7,5, 4}, 3);
    }

    private void printMaxInWindow(int[] numbers, int windowSize){
        if(numbers.length>=1 && numbers.length <= windowSize){
            System.out.println("Window "+new ArrayList<>(Arrays.asList(numbers)) +" Max: "+Arrays.stream(numbers).max());
            return;
        }
        Queue<Integer> queue = new LinkedList<>();
        Deque<Integer> dQueue = new LinkedList<>();
        int index = 0;
        for(int i=1; i<= windowSize;i++, index++){
            queue.add(numbers[index]);
            while(!dQueue.isEmpty() && dQueue.getLast()<numbers[index]){
                dQueue.removeLast();
            }
            dQueue.addLast(numbers[index]);
        }
        while(index<numbers.length){
            String array = Arrays.stream(numbers).mapToObj(String::valueOf).collect(Collectors.joining(", "));
            System.out.println("Array "+array +" Window "+queue +"  Max: "+dQueue.getFirst());
            Integer value = queue.remove();
            if(value == dQueue.getFirst()){
                dQueue.removeFirst();
            }
            queue.add(numbers[index]);
            while(!dQueue.isEmpty() && dQueue.getLast()<numbers[index]){
                dQueue.removeLast();
            }
            dQueue.addLast(numbers[index]);
            index++;
        }
    }
}
