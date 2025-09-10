package com.example.algorithm.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * You are given a 2-D matrix grid. Each cell can have one of three possible values:
 *
 * 0 representing an empty cell
 * 1 representing a fresh fruit
 * 2 representing a rotten fruit
 * Every minute, if a fresh fruit is horizontally or vertically adjacent to a rotten fruit, then the fresh fruit also becomes rotten.
 *
 * Return the minimum number of minutes that must elapse until there are zero fresh fruits remaining. If this state is impossible within the grid, return -1.
 */
public class RottenFruit {

    public int orangesRotting(int[][] grid) {
        Queue<Point> queue = new LinkedList<>();
        int freshCount = 0;
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == 2) {
                    queue.add(new Point(row, col));
                }
                if (grid[row][col] == 1) {
                    freshCount++;
                }
            }
        }
        if(freshCount == 0){
            return 0;
        }

        int count = 0;
        while (!queue.isEmpty()) {
            Queue<Point> children = new LinkedList<>();
            boolean changed= false;

            for (Point p : queue) {
                grid[p.row][p.col] = 0;
                if (p.row > 0 && grid[p.row - 1][p.col] == 1) {
                    children.add(new Point(p.row - 1, p.col));
                    grid[p.row - 1][p.col] = 2;
                    freshCount--;
                    changed = true;
                }
                if (p.row < grid.length - 1 && grid[p.row + 1][p.col] == 1) {
                    children.add(new Point(p.row + 1, p.col));
                    grid[p.row + 1][p.col] = 2;
                    freshCount--;
                    changed = true;
                }
                if (p.col > 0 && grid[p.row][p.col - 1] == 1) {
                    children.add(new Point(p.row, p.col - 1));
                    grid[p.row][p.col - 1] = 2;
                    freshCount--;
                    changed = true;
                }
                if (p.col < grid[0].length - 1 && grid[p.row][p.col + 1] == 1) {
                    children.add(new Point(p.row, p.col + 1));
                    grid[p.row][p.col + 1] = 2;
                    freshCount--;
                    changed = true;
                }
            }
            if(changed){
                count++;
            }
            queue.clear();
            queue.addAll(children);
        }
        return freshCount == 0 ? count : -1;

    }

    private class Point{
        int row;
        int col;
        public Point(int row, int col){
            this.row = row;
            this.col = col;
        }
    }
}
