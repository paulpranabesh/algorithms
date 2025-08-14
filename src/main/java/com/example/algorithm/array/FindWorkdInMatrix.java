package com.example.algorithm.array;


/**
 * Given a 2-D grid of characters board and a string word, return true if the word is present in the grid, otherwise return false.
 *
 * For the word to be present it must be possible to form it with a path in the board with horizontally or vertically neighboring cells. The same cell may not be used more than once in a word.
 */
public class FindWorkdInMatrix {

    public boolean exist(char[][] board, String word) {

        for(int i = 0; i < board.length;i++){
            for(int j = 0; j < board[0].length;j++){
                if(board[i][j] == word.charAt(0)){
                    boolean[][] visited = new boolean[board.length][board[0].length];
                    boolean found = find( board, visited,  word,  0, i, j);
                    if(found){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean find(char[][] board, boolean[][] visited, String word, int wordIndex, int row, int col) {

        if(word.length() == wordIndex){
            return true;
        }
        if(board[row][col] == word.charAt(wordIndex)){
            visited[row][col] = true;
            if(word.length()-1 == wordIndex){
                visited[row][col] = false;
                return true;
            }
        }else{
            visited[row][col] = false;
            return false;
        }
        if(canGoLeft( board,  row,  col) && !visited[row][col-1]){
            boolean found = find( board,  visited,  word,  wordIndex+1,  row,  col-1);
            if(found){
                visited[row][col] = false;
                return true;
            }
        }

        if(canGoRight( board,  row,  col) && !visited[row][col+1]){
            boolean found = find( board,  visited,  word,  wordIndex+1,  row,  col+1);
            if(found){
                visited[row][col] = false;
                return true;
            }
        }

        if(canGoUp( board,  row,  col) && !visited[row-1][col]){
            boolean found = find( board,  visited,  word,  wordIndex+1,  row-1,  col);
            if(found){
                visited[row][col] = false;
                return true;
            }
        }

        if(canGoDown( board,  row,  col) && !visited[row+1][col]){
            boolean found = find( board,  visited,  word,  wordIndex+1,  row+1,  col);
            if(found){
                visited[row][col] = false;
                return true;
            }
        }
        visited[row][col] = false;
        return false;

    }

    private boolean canGoLeft(char[][] board, int row, int col){
        return col-1 >=0;
    }
    private boolean canGoRight(char[][] board, int row, int col){
        return col+1 < board[0].length;
    }
    private boolean canGoUp(char[][] board, int row, int col){
        return row-1 >=0;
    }
    private boolean canGoDown(char[][] board, int row, int col){
        return row+1 < board.length;
    }
}
