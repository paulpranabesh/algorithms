package com.example.algorithm.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class TraverseGraph {
    private Set<Node> visitedNodesDfs = new HashSet<>();
    private Set<Node> visitedNodesBfs = new HashSet<>();

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {0,1,0,0,0},
                {1,0,1,0,1},
                {0,1,0,0,1},
                {0,0,0,0,1},
                {0,1,1,1,0},
        };
        Graph graph = new Graph();
        for(int i = 0; i < matrix.length; i++){
            Node currNode = graph.contains(i) ? graph.find(i).get() : new Node(i);
            if(!graph.contains(i)){
                graph.addNode(currNode);
            }
            for(int j = 0; j < matrix[0].length; j++){
                if(matrix[i][j] == 1){
                    Node neighbour =  graph.contains(j) ? graph.find(j).get() : new Node(j);
                    if(!graph.contains(j)){
                        graph.addNode(neighbour);
                    }
                    currNode.addNeighbour(neighbour);
                }
            }
        }

        new TraverseGraph().dfs(graph.find(0).get());
        System.out.println();
        new TraverseGraph().bfs(graph.find(0).get());
    }

    private void dfs(Node node){
        if(visitedNodesDfs.contains(node)){
            return;
        }
        visitedNodesDfs.add(node);
        System.out.print(node.value()+" ");
        for(Node nextNode : node.neighbours()){
            if(!visitedNodesDfs.contains(nextNode)){
                dfs(nextNode);
            }
        }
    }

    private void bfs(Node node){
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        while(!queue.isEmpty()){
            Node currNode = queue.poll();
            if(!visitedNodesBfs.contains(currNode)){
                System.out.print(currNode.value()+" ");
                visitedNodesBfs.add(currNode);
            }
            for(Node nextNode : currNode.neighbours()){
                if(!visitedNodesBfs.contains(nextNode)){
                    queue.add(nextNode);
                }
            }
        }
    }
}






















