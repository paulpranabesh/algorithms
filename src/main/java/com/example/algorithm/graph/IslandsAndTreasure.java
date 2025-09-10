package com.example.algorithm.graph;

/**
 * You are given a
 * m
 * ×
 * n
 * m×n 2D grid initialized with these three possible values:
 *
 * -1 - A water cell that can not be traversed.
 * 0 - A treasure chest.
 * INF - A land cell that can be traversed. We use the integer 2^31 - 1 = 2147483647 to represent INF.
 * Fill each land cell with the distance to its nearest treasure chest. If a land cell cannot reach a treasure chest then the value should remain INF.
 *
 * Assume the grid can only be traversed up, down, left, or right.
 *
 * Modify the grid in-place.
 */
public class IslandsAndTreasure {

    public void islandsAndTreasure(int[][] grid) {

        for(int row = 0; row < grid.length; row++){
            for(int col = 0; col < grid[0].length; col++){
                boolean[][] visited = new boolean[grid.length][grid[0].length];
                if(grid[row][col] == 0){
                    dfs( grid,  row,  col,  0,  visited);
                }
            }
        }
    }

    private void dfs(int[][] grid, int row, int col, int distanceFromTreasure, boolean[][] visited){
        if(grid[row][col] == -1 || visited[row][col]){
            return;
        }
        if(grid[row][col] == 0){
            distanceFromTreasure = 0;
        }else {
            if(grid[row][col] > distanceFromTreasure){
                grid[row][col]  = distanceFromTreasure;
            }else{
                return;
            }
        }
        visited[row][col] = true;
        if(row > 0 && grid[row-1][col] != -1){
            dfs(grid, row-1, col, distanceFromTreasure+1, visited);
        }
        if(row < grid.length-1 && grid[row+1][col] != -1){
            dfs(grid, row+1, col, distanceFromTreasure+1, visited);
        }
        if(col > 0 && grid[row][col-1] != -1){
            dfs(grid, row, col-1, distanceFromTreasure+1, visited);
        }
        if(col < grid[0].length-1 && grid[row][col+1] != -1){
            dfs(grid, row, col+1, distanceFromTreasure+1, visited);
        }
        visited[row][col] = false;
    }
}
