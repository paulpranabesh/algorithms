package com.example.algorithm.point;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Collectors;

public class Draw2DSkyLine {
    public static void main(String[] args) {
        List<Building> buildings = new ArrayList<>();
        int [][] details = new int[][] {
                {1, 11, 5},
                {2, 6, 7},
                {3, 13, 9},
                {12, 7, 16},
                {14, 3, 25},
                {19, 18, 22},
                {23, 13, 29},
                {24, 4, 28}
        };
        for(int[] b : details){
            buildings.add(new Building(b[0], b[2], b[1]));
        }
        new Draw2DSkyLine().drawSkyLine(buildings);
    }

    private void drawSkyLine(List<Building> buildings){
        Queue<Building> maxHeap = new PriorityQueue<>();
        List<BuildingPoint> points = new ArrayList<>();

        for(Building building : buildings){
            points.add(new BuildingPoint(building.getStart(), building, true));
            points.add(new BuildingPoint(building.getEnd(), building, false));
        }

        points = points.stream().sorted((a, b)->{
            if(a.getValue() == b.getValue()){
                if(a.isStart() && !b.isStart()){
                    return 1;
                }else  if(!a.isStart() && b.isStart()){
                    return -1;
                }else{
                    return 0;
                }

            }else{
                return a.getValue().compareTo(b.getValue());
            }
        }).collect(Collectors.toList());

        for(BuildingPoint point: points){
            if(point.isStart()){
                maxHeap.add(point.getBuilding());
                Building b = maxHeap.peek();
                System.out.println(point.getValue()+": "+b.getHeight());
            }

            if(!point.isStart()){
                maxHeap.remove(point.getBuilding());
                if(maxHeap.isEmpty()){
                    System.out.println(point.getValue()+": "+0);
                }else{
                    Building b = maxHeap.peek();
                    System.out.println(point.getValue()+": "+b.getHeight());
                }
            }
        }
    }
}






















