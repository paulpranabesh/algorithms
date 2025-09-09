package com.example.algorithm.backtracking;

import java.util.LinkedList;
import java.util.List;

/**
 * he n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.
 *
 * Given an integer n, return all distinct solutions to the n-queens puzzle. You may return the answer in any order.
 *
 * Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space, respectively.
 */
public class NQueenSolution {

        public List<List<String>> solveNQueens(int n) {
            List<List<String>> result = new LinkedList<>();


            place(0,  new boolean[n][n], result);

            return result;
        }

        private List<String> convert(boolean[][] box){
            int n = box.length;
            List<String> subResult = new LinkedList<>();
            for (boolean[] booleans : box) {
                StringBuffer sb = new StringBuffer();
                for (int col = 0; col < n; col++) {
                    sb.append(booleans[col] ? "Q" : ".");
                }
                subResult.add(sb.toString());
            }
            return subResult;
        }

        private void place(int start, boolean[][] box,  List<List<String>> result){
            if(start == box.length){
                result.add( convert( box));
                return;
            }

            int row = start;
            for (int col = 0; col< box.length; col++){
                box[row][col] = true;
                if(isValid( row,  col , box)){
                    place(row+1, box, result);
                }
                box[row][col] = false;
            }
        }


        private boolean isValid(int row, int col , boolean[][] box){
            int n = box.length;
            for(int i = 0; i<n;i++){
                if(i==row){
                    continue;
                }
                if(box[i][col]){
                    return false;
                }
            }
            for(int i = 0; i<n;i++){
                if(i==col){
                    continue;
                }
                if(box[row][i]){
                    return false;
                }
            }

            int i = row-1;
            int j = col-1;
            while(i>=0 && j >=0){
                if(box[i][j]){
                    return false;
                }
                i--;
                j--;
            }
            i = row+1;
            j = col+1;
            while(i< n && j <n){
                if(box[i][j]){
                    return false;
                }
                i++;
                j++;
            }
            i = row+1;
            j = col-1;
            while(i< n && j >=0){
                if(box[i][j]){
                    return false;
                }
                i++;
                j--;
            }
            i = row-1;
            j = col+1;
            while(i>=0 && j <n){
                if(box[i][j]){
                    return false;
                }
                i--;
                j++;
            }
            return true;
        }
}
