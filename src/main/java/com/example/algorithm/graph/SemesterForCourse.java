package com.example.algorithm.graph;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class SemesterForCourse {
    private Set<Integer> visited = new HashSet<>();
    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {0,1,0,1,0},
                {0,0,1,1,1},
                {0,0,0,0,1},
                {0,0,0,0,0},
                {0,0,0,0,0},
        };
        Graph graph = new Graph();
        for(int row = 0; row < matrix.length; row++) {
            Node currNode = graph.contains(row) ? graph.find(row).get() : new Node(row);
            if (!graph.contains(currNode.value())) {
                graph.addNode(currNode);
            }
            for (int col = 0; col < matrix[0].length; col++) {
                if (matrix[row][col] == 1) {
                    Node neighbour = graph.contains(col) ? graph.find(col).get() : new Node(col);
                    if (!graph.contains(neighbour.value())) {
                        graph.addNode(neighbour);
                    }
                    currNode.addNeighbour(neighbour);
                }
            }
        }
            Stack<Integer> stack = new Stack<>();
            new SemesterForCourse().topologicalSort(graph.find(0).get(), stack);
            int[] semester = new int[matrix.length];

            for(int row = 0; row < matrix.length; row++){
                semester[row] = -1;
            }
            boolean first = true;
            while(!stack.isEmpty()){
                Integer course = stack.pop();
                if(first){
                    semester[course] = 1;
                    first = false;
                }
                for(int col = 0; col < matrix[0].length; col++){
                    if(matrix[course][col] == 1){
                        semester[col] =  Math.max(semester[col], semester[course]+1);
                    }
                }
            }
            int maxSem = Integer.MIN_VALUE;
            for(int row = 0; row < matrix.length; row++){
                maxSem = maxSem < semester[row] ? semester[row] : maxSem;
            }
            System.out.println("Max required semester : "+maxSem);
    }

    private  void topologicalSort(Node node, Stack<Integer> stack){
        if(visited.contains(node.value())){
            return;
        }
        visited.add(node.value());
        for(Node neigbour: node.neighbours()){
            if(!visited.contains(neigbour.value())){
                topologicalSort(neigbour, stack);
            }
        }
        stack.push(node.value());
    }
}
