package com.example.algorithm.array;

public class RotateMatrix {

    public static void main(String[] args) {
        int[][]square = new int[][]{
                {1,2,3,4},
                {5,6,7,8},
                {9,10,11,12},
                {13,14,15,16}
        };
        new RotateMatrix().rotate90Degree(square);
        for(int row = 0; row < square.length; row++){
            for(int col = 0; col < square[0].length; col++){
                System.out.print(square[row][col]+" ");
            }
            System.out.println();
        }

    }

    private void rotate90Degree(int[][]square){
        for(int col =0; col<square[0].length; col++){
            int rowMax = square.length-1;
            int row = 0;
            while(row<=rowMax){
                int temp = square[row][col];
                square[row][col] = square[rowMax][col];
                square[rowMax][col]= temp;
                row++;
                rowMax--;
            }
        }
        for(int row = 0; row < square.length; row++){
            for(int col = 0; col < square[0].length; col++){
                if(row>=col){
                    continue;
                }
                int temp = square[row][col];
                square[row][col] = square[col][row];
                square[col][row]= temp;
            }
        }
    }

}
