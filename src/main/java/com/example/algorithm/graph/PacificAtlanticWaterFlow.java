package com.example.algorithm.graph;

import java.util.List;

public class PacificAtlanticWaterFlow {

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        if(heights.length == 0 || heights[0].length == 0){
            return List.of();
        }
        Cell[][] cells = new Cell[heights.length][heights[0].length];

        for(int row = 0; row < heights.length; row++){
            for(int col = 0; col< heights[0].length; col++){
                cells[row][col] = cells[row][col] != null ? cells[row][col] : new Cell(heights[row][col]);
                if(row ==0 || col ==0){
                    cells[row][col].pacific = true;
                }
                if(row == heights.length-1 || col == heights[0].length-1){
                    cells[row][col].atlantic = true;
                }
            }
        }
        for(int row = 0; row < heights.length; row++){
            for(int col = 0; col< heights[0].length; col++){
               if(row>0 && cells[row-1][col].height <= cells[row][col].height){
                   cells[row][col].pacific = cells[row][col].pacific? cells[row][col].pacific:cells[row-1][col].pacific;
                   cells[row][col].atlantic = cells[row][col].atlantic? cells[row][col].atlantic: cells[row-1][col].atlantic;

               }
               if(col>0 && cells[row][col-1].height <= cells[row][col].height){
                   cells[row][col].pacific = cells[row][col].pacific ?  cells[row][col].pacific : cells[row][col-1].pacific;
                   cells[row][col].atlantic = cells[row][col].atlantic ? cells[row][col].atlantic :cells[row][col-1].atlantic;
               }
               if(row<heights.length-1 && cells[row+1][col].height <= cells[row][col].height){
                   cells[row][col].atlantic = cells[row][col].atlantic? cells[row][col].atlantic:cells[row+1][col].atlantic;
                   cells[row][col].pacific = cells[row][col].pacific? cells[row][col].pacific: cells[row+1][col].pacific;
               }
               if(col<heights[0].length-1 && cells[row][col+1].height <= cells[row][col].height){
                   cells[row][col].atlantic = cells[row][col].atlantic ? cells[row][col].atlantic : cells[row][col+1].atlantic;
                   cells[row][col].pacific = cells[row][col].pacific ? cells[row][col].pacific : cells[row][col+1].pacific;
               }
            }
        }
        List<List<Integer>> results = List.of();
        for(int row = 0; row < heights.length; row++) {
            List<Integer> result = List.of();
            for (int col = 0; col < heights[0].length; col++) {
                if(cells[row][col].pacific && cells[row][col].atlantic){
                    result.add(row);
                    result.add(col);
                }
            }
            if(!result.isEmpty()){
                results.add(result);
            }
        }
        return results;

    }

    private class Cell{
        boolean pacific;
        boolean atlantic;
        int height;
        public Cell(int height) {
            this.height = height;
        }

    }
}
