package com.example.algorithm.point;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MergeOverlapPoints {

    public static void main(String[] args) {
        List<Point> list = new MergeOverlapPoints().mergeOverlap(
                new int[][]{
                        {1,3},
                        {3,5},
                        {6,8},
                        {7,9}
                }
        );
        list.forEach(System.out::println);


    }

    List<Point> mergeOverlap(int[][] pointsRaw) {

        List<Point> list = new ArrayList<>();
        for (int[] val : pointsRaw) {
            list.add(new Point(val[0], true));
            list.add(new Point(val[1], false));
        }
        list = list.stream().sorted((k, p) -> {
            if(k.getValue() == p.getValue()){
                if(k.isStart() == p.isStart()){
                     return 0;
                }else{
                    return k.isStart() ? -1 : 1;
                }
            }else {
                return Integer.valueOf(k.getValue()).compareTo( Integer.valueOf(p.getValue()));
            }
        }).collect(Collectors.toList());
        List<Point> result = new ArrayList<>();
        int start = 0;
        Point startPoint=null;
        for (int i = 0; i < list.size(); i++) {
            Point pointCurr = list.get(i);
            if (pointCurr.isStart()) {
                start++;
                if(start == 1){
                    startPoint = pointCurr;
                }
            } else {
                start --;
                if(start == 0){
                    result.add(startPoint);
                    result.add(pointCurr);
                }
            }
        }
        return result;
    }


}










































