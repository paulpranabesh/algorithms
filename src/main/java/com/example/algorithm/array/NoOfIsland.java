package com.example.algorithm.array;


/**
 * Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.
 *
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
 */
public class NoOfIsland {

    public int numIslands(char[][] grid) {
        int count=0;

        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == '1'){
                    visitNearbyLands(grid, i, j);
                    count++;
                }
            }

        }
        return count;
    }

    private void visitNearbyLands(char[][] grid, int x, int y) {
        if (grid[x][y] == 'X') {
            return;
        }
        grid[x][y] = 'X';
        Coordinate curr = new Coordinate(grid, x, y);
        try {
            Coordinate coordinate = curr.getLeft();
            if(grid[coordinate.x][ coordinate.y] == '1'){
                visitNearbyLands(coordinate.grid, coordinate.x, coordinate.y);
            }
        } catch (RuntimeException ex) {
        }
        try {
            Coordinate coordinate = curr.getRight();
            if(grid[coordinate.x][ coordinate.y] == '1'){
                visitNearbyLands(coordinate.grid, coordinate.x, coordinate.y);
            }
        } catch (RuntimeException ex) {
        }
        try {
            Coordinate coordinate = curr.getUp();
            if(grid[coordinate.x][ coordinate.y] == '1'){
                visitNearbyLands(coordinate.grid, coordinate.x, coordinate.y);
            }
        } catch (RuntimeException ex) {
        }
        try {
            Coordinate coordinate = curr.getDown();
            if(grid[coordinate.x][ coordinate.y] == '1'){
                visitNearbyLands(coordinate.grid, coordinate.x, coordinate.y);
            }
        } catch (RuntimeException ex) {
        }

    }

    private class Coordinate{
        int x;
        int y;
        char[][] grid;


        public Coordinate(char[][] grid, int x, int y) {
            this.x = x;
            this.y = y;
            this.grid = grid;
        }

        public Coordinate getLeft(){
            if(y == 0){
                throw new RuntimeException("Left Coordinate is not present");
            }
            return new Coordinate(grid, x, y-1);
        }
        public Coordinate getRight(){
            if(y == grid[0].length-1){
                throw new RuntimeException("Right Coordinate is not present");
            }
            return new Coordinate(grid, x, y+1);
        }
        public Coordinate getUp(){
            if(x == 0){
                throw new RuntimeException("Up Coordinate is not present");
            }
            return new Coordinate(grid, x-1, y);
        }
        public Coordinate getDown(){
            if(x == grid.length-1){
                throw new RuntimeException("Down Coordinate is not present");
            }
            return new Coordinate(grid, x+1, y);
        }
    }
}
