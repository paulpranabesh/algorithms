package com.example.algorithm.graph;

import java.util.Arrays;

public class NetworkDelay {

    /**
     * You are given a network of n directed nodes, labeled from 1 to n. You are also given times, a list of directed edges where times[i] = (ui, vi, ti).
     *
     * ui is the source node (an integer from 1 to n)
     * vi is the target node (an integer from 1 to n)
     * ti is the time it takes for a signal to travel from the source to the target node (an integer greater than or equal to 0).
     * You are also given an integer k, representing the node that we will send a signal from.
     *
     * Return the minimum time it takes for all of the n nodes to receive the signal. If it is impossible for all the nodes to receive the signal, return -1 instead.
     * @param times
     * @param n
     * @param k
     * @return
     */
    public int networkDelayTime(int[][] times, int n, int k) {
        k = k-1;
        int[] distance = new int[n];
        for(int i = 0; i < n; i++){
            distance[i] = Integer.MAX_VALUE;
        }
        distance[k] = 0;
        for(int i=0; i<n-1;i++){
            for(int[] time : times){
                int u = time[0]-1;
                int v = time[1]-1;
                int weight = time[2];
                if(distance[u] != Integer.MAX_VALUE && distance[v] > distance[u]+weight){
                    distance[v] = distance[u]+weight;
                }
            }
        }
        Integer max = Arrays.stream(distance).max().getAsInt();
        return max == Integer.MAX_VALUE ? -1 : max;
    }
}
