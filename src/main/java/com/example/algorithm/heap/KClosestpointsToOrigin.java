package com.example.algorithm.heap;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * You are given an 2-D array points where points[i] = [xi, yi] represents the coordinates of a point on an X-Y axis plane. You are also given an integer k.
 *
 * Return the k closest points to the origin (0, 0).
 *
 * The distance between two points is defined as the Euclidean distance (sqrt((x1 - x2)^2 + (y1 - y2)^2)).
 *
 * You may return the answer in any order.
 */
public class KClosestpointsToOrigin {

    public int[][] kClosest(int[][] points, int k) {
        if(points.length == 0){
            return new int[0][0];
        }
        Queue<Point> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        for(int i =0; i< k && i< points.length ;i++){
            maxHeap.offer(new Point(points[i][0], points[i][1]));
        }
        for(int i =k;  i< points.length ;i++){
            Point currrPoint = new Point(points[i][0], points[i][1]);
            if(maxHeap.peek().compareTo(currrPoint)>0){
              maxHeap.poll();
              maxHeap.offer(currrPoint);
            }
        }

        int[][] result = new int[maxHeap.size()][2];
        AtomicInteger index = new AtomicInteger(0);
        maxHeap.forEach(p->{
            result[index.get()][0] = p.x;
            result[index.getAndIncrement()][1] = p.y;
        });
        return result;

    }

    private class Point implements Comparable<Point> {
        Integer x;
        Integer y;

        public Point(Integer x, Integer y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point o2) {
            return Double.compare(Math.sqrt(x*x + y*y), Math.sqrt(o2.x*o2.x + o2.y*o2.y));
        }
    }
}
