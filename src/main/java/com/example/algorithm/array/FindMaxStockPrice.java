package com.example.algorithm.array;

public class FindMaxStockPrice {
    public static void main(String[] args) {
        new FindMaxStockPrice().findMaxStockProfit(new int[]{9,3,2,1,5,7,2,8,3,4});

    }

    private void findMaxStockProfit(int[] values){
        int minSoFar = Integer.MAX_VALUE;
        int maxProfit = 0;
        int startDay = 0;
        int endDay = 0;
        for(int i = 0; i< values.length; i++){
            if(values[i] < minSoFar){
                minSoFar = values[i];
                startDay = i ;
            }
            if(maxProfit < values[i] - minSoFar){
                maxProfit = values[i] - minSoFar;
                endDay = i;
            }
        }
        System.out.println("Maxprofit : "+maxProfit);
        System.out.println("StartDay : "+startDay+ " End day: "+endDay);

    }

}
