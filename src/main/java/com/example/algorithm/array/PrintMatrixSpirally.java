package com.example.algorithm.array;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given an m x n matrix, return all elements of the matrix in spiral order.
 *
 * Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [1,2,3,6,9,8,7,4,5]
 */
public class PrintMatrixSpirally {

    public static void main(String[] args) {
        int[][] array = new int[][]{{1,2,3},{4,5,6},{7,8,9}} ;
        System.out.println(new PrintMatrixSpirally().spiralOrder(array));
    }
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> answer = new ArrayList<>();
        Direction[] directions = {Direction.RIGHT, Direction.DOWN, Direction.LEFT, Direction.UP};
        int row = 0;
        int col = 0;
        int index = 0;
        int turn = matrix.length > matrix[0].length ? matrix[0].length : matrix.length;
        Set<Integer> rowSet = new HashSet<>();
        Set<Integer> colSet = new HashSet<>();
        while(true){

            switch(directions[index]){
                case RIGHT:
                    rowSet.add(row);
                    int i;
                    for(i = col; !colSet.contains(i) && i<matrix[0].length ; i++){
                        answer.add(matrix[row][i]);
                    }
                    col = i == col ? i : i-1;
                    row++;

                    break;
                case DOWN:
                    colSet.add(col);
                    int j;
                    for(j = row; !rowSet.contains(j) && j<matrix.length ; j++){
                        answer.add(matrix[j][col]);
                    }
                    col--;
                    row = j == row ? j : j-1;

                    break;
                case LEFT:
                    rowSet.add(row);
                    int k;
                    for(k = col; !colSet.contains(k) && k>=0 ; k--){
                        answer.add(matrix[row][k]);
                    }
                    col = k == col ? k : k+1;
                    row--;
                    break;
                case UP:
                    colSet.add(col);
                    int p;
                    for(p = row; !rowSet.contains(p) && p>=0 ;p--){
                        answer.add(matrix[p][col]);
                    }
                    row = p == row ? p: p+1;
                    col++;
                    break;
            }
            if(rowSet.size() == turn && colSet.size() == turn){
                break;
            }
            index = (index+1)%4;
        }
        return answer;
    }
    private enum Direction{
        RIGHT, DOWN,LEFT,UP;
    }
}
