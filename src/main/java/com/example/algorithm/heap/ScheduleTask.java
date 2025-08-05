package com.example.algorithm.heap;

import java.util.*;

public class ScheduleTask {


    /**
     * You are given an array of CPU tasks tasks, where tasks[i] is an uppercase english character from A to Z. You are also given an integer n.
     *
     * Each CPU cycle allows the completion of a single task, and tasks may be completed in any order.
     *
     * The only constraint is that identical tasks must be separated by at least n CPU cycles, to cooldown the CPU.
     *
     * Return the minimum number of CPU cycles required to complete all tasks.
     * @param tasks
     * @param n
     * @return
     */
    public int leastInterval(char[] tasks, int n) {
        if(tasks == null || tasks.length == 0){
            return 0;
        }
        Map<String, Integer> map = new HashMap<>();
        for(Character c : tasks){
            map.putIfAbsent(c+"", 0);
            map.computeIfPresent(c+"", (now, prev)->prev+1);
        }

        //Highest remaining work pick that
        Queue<Pair> coolingTasks = new LinkedList<>();
        Queue<TaskDetail> queue = new PriorityQueue<>(Comparator.comparing(TaskDetail::getCount, Comparator.reverseOrder()));
        map.entrySet().stream().map(e->new TaskDetail(e.getKey(), e.getValue())).forEach(queue::offer);
        int time = 0;
        while(!queue.isEmpty() || !coolingTasks.isEmpty()){
            time++;
            if(!coolingTasks.isEmpty() && coolingTasks.peek().availableAt == time){
                queue.offer(coolingTasks.poll().task);
            }
            if(!queue.isEmpty()){
                TaskDetail task = queue.poll();
                task.count -=1;
                if(task.count>0){
                    coolingTasks.offer(new Pair(task, time+n+1));
                }
            }

        }
        return time;
    }

    private class TaskDetail{
        String name;
        int count;
        int lastExecTime = 0;

        public TaskDetail(String name, int count) {
            this.name = name;
            this.count = count;
        }

        public int getCount() {
            return count;
        }
    }

    private class Pair{
        TaskDetail task;
        int availableAt = 0;

        public Pair(TaskDetail task, int availableAt) {
            this.task = task;
            this.availableAt = availableAt;
        }
    }
}
