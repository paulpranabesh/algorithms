package com.example.algorithm.backtracking.sudoku;

import java.util.*;

public class SudokuSolver {
    private int [][] box;
    private Set<Integer>[] rowSets = new Set[9];
    private Set<Integer>[] colSets = new Set[9];
    private Map<Pair, Set<Integer>> subMatrixStore = new HashMap<>();
    public SudokuSolver(int [][] box) {
        this.box = box;
        for(int i = 0; i<=8; i++) {
            rowSets[i] = new HashSet<>();
            colSets[i] = new HashSet<>();
        }
        for(int i = 0; i<=8; i++) {
            for (int j = 0; j <= 8; j++) {
                if(box[i][j] != 0){
                    rowSets[i].add(box[i][j]);
                    colSets[j].add(box[i][j]);
                    assignValue(i, j, box[i][j]);
                }
            }
        }
    }

    /**
     * Sudoku Solver: Given a 9x9 partially filled array,
     * find a way to fill it such that each row, column and
     * 3x3 sub-grid contain exactly one instance of digits 1-9. For example:
     * {5,3,0,0,7,0,0,0,0},
     *                 {6,0,0,1,9,5,0,0,0},
     *                 {0,9,8,0,0,0,0,6,0},
     *                 {8,0,0,0,6,0,0,0,3},
     *                 {4,0,0,8,0,3,0,0,1},
     *                 {7,0,0,0,2,0,0,0,6},
     *                 {0,6,0,0,0,0,2,8,0},
     *                 {0,0,0,4,1,9,0,0,5},
     *                 {0,0,0,0,8,0,0,7,9}
     *
     *Solves to
     *                  5 3 4 6 7 8 9 1 2
     *                  6 7 2 1 9 5 3 4 8
     *                  1 9 8 3 4 2 5 6 7
     *                  8 5 9 7 6 1 4 2 3
     *                  4 2 6 8 5 3 7 9 1
     *                  7 1 3 9 2 4 8 5 6
     *                  9 6 1 5 3 7 2 8 4
     *                  2 8 7 4 1 9 6 3 5
     *                  3 4 5 2 8 6 1 7 9
     *
     */
    public void solve(){
        if(doSolve(0,0)){
            for(int i = 0; i<=8; i++) {
                for (int j = 0; j <= 8; j++) {
                    System.out.print(box[i][j]+" ");
                }
                System.out.println();
            }
            return;
        }
        System.out.println("Can not be solved!!");
    }

    private boolean doSolve(int i, int j) {
        if(i == 9){
            return true;
        }
        if (box[i][j] != 0) {
            int nextRow = j < 8 ? i : i + 1;
            int nextCol = (j + 1) % 9;
            return doSolve(nextRow, nextCol);
        }
        for (int candidate = 1; candidate <= 9; candidate++) {
            if (!rowSets[i].contains(candidate) && !colSets[j].contains(candidate)
                    && isUniqueIn3X3SubMatrix(i, j, candidate)) {
                assignValue(i, j,candidate);
                int nextRow = j < 8 ? i : i + 1;
                int nextCol = (j + 1) % 9;
                if (doSolve(nextRow, nextCol)) {
                    return true;
                } else {
                    removeValue(i, j,candidate);
                }
            }
        }
        return false;
    }

    private void assignValue(int row, int col, int val){
        box[row][col] = val;
        rowSets[row].add(val);
        colSets[col].add(val);
        Pair pair = startPosition(row, col);
        subMatrixStore.putIfAbsent(pair, new HashSet<>(Arrays.asList(val)));
        subMatrixStore.get(pair).add(val);
    }

    private void removeValue(int row, int col, int val){
        box[row][col] = 0;
        rowSets[row].remove(val);
        colSets[col].remove(val);
        Pair pair = startPosition(row, col);
        subMatrixStore.get(pair).remove(val);
    }

    private boolean isUniqueIn3X3SubMatrix(int row, int col, int value){

        Pair pair = startPosition(row, col);
        if(subMatrixStore.containsKey(pair) &&
                subMatrixStore.get(pair).contains(value)){
            return false;
        }
        return true;
    }
    private Pair startPosition(int row, int col){
        Pair pair = new Pair();
        pair.x = (row/3)*3;
        pair.y = (col/3)*3;
        return pair;
    }


    private class Pair{
        int x;
        int y;
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()){
                return false;
            }
            Pair pair = (Pair) o;
            return x == pair.x && y == pair.y;
        }
        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
