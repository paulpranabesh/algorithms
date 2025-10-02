package com.example.algorithm.graph;

/**
 * You are given a square 2-D matrix of distinct integers grid where each integer grid[i][j] represents the elevation at position (i, j).
 *
 * Rain starts to fall at time = 0, which causes the water level to rise. At time t, the water level across the entire grid is t.
 *
 * You may swim either horizontally or vertically in the grid between two adjacent squares if the original elevation of both squares is less than or equal to the water level at time t.
 *
 * Starting from the top left square (0, 0), return the minimum amount of time it will take until it is possible to reach the bottom right square (n - 1, n - 1).
 */

public class WaterLevelBlock {

    public int swimInWater(int[][] grid) {
        int n = grid.length;
        boolean[][] visited = new boolean[n][n];
        return dfs(grid, 0, 0, visited, grid[0][0]);
    }

    private int dfs(int[][] grid, int row, int col, boolean[][] visited, int maxVal) {
        int n = grid.length;

        // Out of bounds or already visited
        if (row < 0 || col < 0 || row >= n || col >= n || visited[row][col]) {
            return Integer.MAX_VALUE;
        }

        maxVal = Math.max(maxVal, grid[row][col]);


        if (row == n - 1 && col == n - 1) {
            return maxVal;
        }

        visited[row][col] = true;

        int minPath = Integer.MAX_VALUE;

        // Explore all 4 directions
        minPath = Math.min(minPath, dfs(grid, row + 1, col, visited, maxVal));
        minPath = Math.min(minPath, dfs(grid, row - 1, col, visited, maxVal));
        minPath = Math.min(minPath, dfs(grid, row, col + 1, visited, maxVal));
        minPath = Math.min(minPath, dfs(grid, row, col - 1, visited, maxVal));

        visited[row][col] = false; // backtrack

        return minPath;
    }
}
