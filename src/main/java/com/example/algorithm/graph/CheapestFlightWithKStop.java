package com.example.algorithm.graph;

import java.util.Arrays;

public class CheapestFlightWithKStop {

    /**
     * There are n cities connected by some number of flights. You are given an array flights where flights[i] = [fromi, toi, pricei] indicates that there is a flight from city fromi to city toi with cost pricei.
     *
     * You are also given three integers src, dst, and k, return the cheapest price from src to dst with at most k stops. If there is no such route, return -1.
     * @param n
     * @param flights
     * @param src
     * @param dst
     * @param k
     * @return
     */
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int[] prevDist = new int[n];
        Arrays.fill(prevDist, Integer.MAX_VALUE);
        prevDist[src] = 0;

        for (int i = 0; i <= k; i++) {
            int[] currDist = Arrays.copyOf(prevDist, n); // copy previous round distances

            for (int[] flight : flights) {
                int u = flight[0], v = flight[1], w = flight[2];
                if (prevDist[u] != Integer.MAX_VALUE) {
                    currDist[v] = Math.min(currDist[v], prevDist[u] + w);
                }
            }

            prevDist = currDist; // use current distances in next iteration
        }

        return prevDist[dst] == Integer.MAX_VALUE ? -1 : prevDist[dst];
    }
}
