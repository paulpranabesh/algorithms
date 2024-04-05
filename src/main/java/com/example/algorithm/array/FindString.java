package com.example.algorithm.array;


import java.util.HashSet;
import java.util.Set;

/**
 * Given an m x n grid of characters board and a string word, return true if word exists in the grid.
 * <p>
 * The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.
 */
public class FindString {

    public boolean exist(char[][] board, String word) {
        if (word.length() < 1) {
            return true;
        }
        if(!charMatch(board, word)){
            return false;
        }
        boolean[][] visit = new boolean[board.length][board[0].length];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (checkCharAtPosition(board, i, j, visit, word)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    boolean charMatch(char[][] board, String word){
        Set<Character> chars = new HashSet<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                chars.add(board[i][j]);
            }
        }
        for(char aChar : word.toCharArray()){

            if(!chars.contains(aChar)){
                return false;
            }
        }
        return true;
    }

    private boolean checkCharAtPosition(char[][] board, int x, int y, boolean[][] visit, String word) {
        if (visit[x][y]) {
            return false;
        }
        if (word.length() < 1) {
            return true;
        }
        if (word.length() == 1 && board[x][y] == word.charAt(0)) {
            return true;
        }
        if (visit[x][y]) {
            return false;
        }
        if (word.charAt(0) != board[x][y]) {
            return false;
        }
        visit[x][y] = true;
        Coordinate curr = new Coordinate(board, x, y);
        try {
            Coordinate coordinate = curr.getLeft();
            boolean result = checkCharAtPosition(coordinate.grid, coordinate.x, coordinate.y, visit, word.substring(1, word.length()));
            if (result) {
                return true;
            }
        } catch (RuntimeException ex) {
        }
        try {
            Coordinate coordinate = curr.getRight();
            boolean result = checkCharAtPosition(coordinate.grid, coordinate.x, coordinate.y, visit, word.substring(1, word.length()));
            if (result) {
                return true;
            }
        } catch (RuntimeException ex) {
        }
        try {
            Coordinate coordinate = curr.getUp();
            boolean result = checkCharAtPosition(coordinate.grid, coordinate.x, coordinate.y, visit, word.substring(1, word.length()));
            if (result) {
                return true;
            }
        } catch (RuntimeException ex) {
        }
        try {
            Coordinate coordinate = curr.getDown();
            boolean result = checkCharAtPosition(coordinate.grid, coordinate.x, coordinate.y, visit, word.substring(1, word.length()));
            if (result) {
                return true;
            }
        } catch (RuntimeException ex) {
        }
        visit[x][y] = false;
        return false;
    }

    private class Coordinate {
        int x;
        int y;
        char[][] grid;


        public Coordinate(char[][] grid, int x, int y) {
            this.x = x;
            this.y = y;
            this.grid = grid;
        }

        public Coordinate getLeft() {
            if (y == 0) {
                throw new RuntimeException("Left Coordinate is not present");
            }
            return new Coordinate(grid, x, y - 1);
        }

        public Coordinate getRight() {
            if (y == grid[0].length - 1) {
                throw new RuntimeException("Right Coordinate is not present");
            }
            return new Coordinate(grid, x, y + 1);
        }

        public Coordinate getUp() {
            if (x == 0) {
                throw new RuntimeException("Up Coordinate is not present");
            }
            return new Coordinate(grid, x - 1, y);
        }

        public Coordinate getDown() {
            if (x == grid.length - 1) {
                throw new RuntimeException("Down Coordinate is not present");
            }
            return new Coordinate(grid, x + 1, y);
        }
    }
}
