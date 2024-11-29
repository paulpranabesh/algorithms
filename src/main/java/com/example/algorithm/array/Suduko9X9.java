package com.example.algorithm.array;

import java.util.HashSet;
import java.util.Set;

/**
 * Sudoku Solver: Given a 9x9 partially filled array, find a way to fill it such that each row, column and 3x3 sub-grid contain exactly one instance of digits 1-9. For example,
 */
public class Suduko9X9 {
    public static void main(String[] args) {
        sudoko();
    }

    private static void sudoko() {
        Set<Integer>[][] blocks = new Set[3][3];
        Set<Integer>[] rowValues = new Set[9];
        Set<Integer>[] colValues = new Set[9];
        int[][] matrix = new int[][]{
                {5, 3, 0, 0, 7, 0, 0, 0, 0},
                {6, 0, 0, 1, 9, 5, 0, 0, 0},
                {0, 9, 8, 0, 0, 0, 0, 6, 0},
                {8, 0, 0, 0, 6, 0, 0, 0, 3},
                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                {7, 0, 0, 0, 2, 0, 0, 0, 6},
                {0, 6, 0, 0, 0, 0, 2, 8, 0},
                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };
        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (blocks[i / 3][j / 3] == null) {
                    blocks[i / 3][j / 3] = new HashSet<>();
                }
                if (rowValues[i] == null) {
                    rowValues[i] = new HashSet<>();
                }
                if (colValues[j] == null) {
                    colValues[j] = new HashSet<>();
                }
                blocks[i / 3][j / 3].add(matrix[i][j]);
                rowValues[i].add(matrix[i][j]);
                colValues[j].add(matrix[i][j]);
            }

        }
        boolean result = populate(matrix , blocks,  rowValues, colValues);
        if(result){
            for (int i = 0; i < matrix[0].length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    System.out.print(matrix[i][j] + " ");
                }
                System.out.println();
            }
        }
    }

    private static boolean populate( int[][] matrix , Set<Integer>[][] blocks,  Set<Integer>[] rowValues, Set<Integer>[] colValues){
        boolean remainning = false;
        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if(matrix[i][j] == 0){
                    remainning = true;
                    for(int num = 1; num <=9; num++){
                        if(canPlace(num, i, j, blocks,  rowValues, colValues)){
                            matrix[i][j] = num;
                            blocks[i / 3][j / 3].add(matrix[i][j]);
                            rowValues[i].add(matrix[i][j]);
                            colValues[j].add(matrix[i][j]);
                            boolean placed = populate(matrix , blocks,  rowValues, colValues);
                            remainning = !placed;
                            if(!placed){
                                blocks[i / 3][j / 3].remove(matrix[i][j]);
                                rowValues[i].remove(matrix[i][j]);
                                colValues[j].remove(matrix[i][j]);
                                matrix[i][j] = 0;
                            }
                            if(placed && i == matrix[0].length && j == matrix[0].length){
                                return true;
                            }
                        }
                    }
                    if(remainning){
                        return false;
                    }
                }
            }
        }
        return !remainning;
    }

    private static boolean canPlace(int n, int i, int j, Set<Integer>[][] blocks,  Set<Integer>[] rowValues, Set<Integer>[] colValues){

        return !( blocks[i / 3][j / 3].contains(n) ||  rowValues[i].contains(n) ||
                colValues[j].contains(n)  );
    }
}
