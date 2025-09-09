package com.example.algorithm.array;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * You are given a matrix grid where grid[i] is either a 0 (representing water) or 1 (representing land).
 *
 * An island is defined as a group of 1's connected horizontally or vertically. You may assume all four edges of the grid are surrounded by water.
 *
 * The area of an island is defined as the number of cells within the island.
 *
 * Return the maximum area of an island in grid. If no island exists, return 0.
 */
public class MaxAreaInIsland {
    public int maxAreaOfIsland(int[][] grid) {
        int max = Integer.MIN_VALUE;
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == 1) {
                    AtomicInteger count = new AtomicInteger(0);
                    findNumberOfCells( row,  col,  grid, count );
                    max = Math.max(max, count.get());
                }

            }
        }
        return max !=  Integer.MIN_VALUE ? max: 0;
    }

    private void findNumberOfCells(int row, int col, int[][] grid, AtomicInteger count){
        if(row<0 || row>=grid.length || col<0 || col>=grid[0].length || grid[row][col]!=1){
            return;
        }
        if(grid[row][col]==1){
            count.incrementAndGet();
            grid[row][col]=0;
        }
        findNumberOfCells( row,  col-1,  grid,  count);
        findNumberOfCells( row,  col+1,  grid,  count);
        findNumberOfCells( row-1,  col,  grid,  count);
        findNumberOfCells( row+1,  col,  grid,  count);
    }
}
