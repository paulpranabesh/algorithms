package com.example.algorithm.array;

public class FindMaxStockProfitWith2TimesTransaction {

    public static void main(String[] args) {
        new FindMaxStockProfitWith2TimesTransaction().printMaxProfit(new int[]{9,3,2,1,5,7,2,8,3,4});

    }

    private void printMaxProfit(int[] stocks){
        Result[] bestUntil = new Result[stocks.length];
        Result[] bestFrom = new Result[stocks.length];
        int minSoFar = Integer.MAX_VALUE;
        int maxProfit = 0;
        int start = 0;
        int end = 0;
        for(int i = 0; i< stocks.length; i++){
            if(stocks[i] < minSoFar){
                minSoFar = stocks[i];
                start = i;
            }

            if(maxProfit < stocks[i] - minSoFar){
                maxProfit = stocks[i] - minSoFar;
                end = i;
            }
            bestUntil[i] = new Result(start, end, maxProfit);
        }
        start = stocks.length-1;
        end = stocks.length-1;
        maxProfit = 0;
        int maxSoFar = Integer.MIN_VALUE;
        for(int i = stocks.length-1; i>=0; i--){
           if(maxSoFar < stocks[i]){
               end = i;
               maxSoFar = stocks[i];
           }

           if(maxSoFar - stocks[i] > maxProfit){
               start = i;
               maxProfit = maxSoFar - stocks[i];
           }
            bestFrom[i] = new Result(start, end, maxProfit);
        }
        maxProfit = 0;
        for(int i = 0; i< stocks.length; i++){
            if(maxProfit < bestUntil[i].profit + ( (i+1>= stocks.length) ? 0 : bestFrom[i+1].profit)){
                maxProfit  = bestUntil[i].profit + ( (i+1>= stocks.length) ? 0 : bestFrom[i+1].profit);
                System.out.println("profit "+bestUntil[i].profit+"+"+ ((i+1>= stocks.length) ? 0 : bestFrom[i+1].profit)+"= "+maxProfit);
                System.out.println("First Transaction StartDay : "+bestUntil[i].start+ " End day: "+bestUntil[i].end);
                System.out.println("second Transaction  StartDay : "+bestFrom[i+1].start+ " End day: "+bestFrom[i+1].end);
            }
        }


    }
    private class Result{
        int start;
        int end;
        int profit;

        public Result(int start, int end, int profit) {
            this.start = start;
            this.end = end;
            this.profit = profit;
        }
    }
}
