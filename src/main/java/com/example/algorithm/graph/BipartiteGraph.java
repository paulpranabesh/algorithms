package com.example.algorithm.graph;

import java.util.LinkedList;
import java.util.Queue;

public class BipartiteGraph {

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {0, 1, 1, 0},
                {1, 0, 0, 1},
                {1, 0, 0, 1},
                {0, 1, 1, 0}
        };

       /* int[][] matrix = new int[][]{
                {0, 1, 1, 0},
                {1, 0, 1, 1},
                {1, 1, 0, 1},
                {0, 1, 1, 0}
        };*/

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
        new BipartiteGraph().checkAndPrintBipartite(graph.find(0).get());
    }

    private void checkAndPrintBipartite(Node start){
        if(NodeVisitStatus.VISITED.equals(start.getVisitStatus())){
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        Queue<Node> oddQueue = new LinkedList<>();
        Queue<Node> evenQueue = new LinkedList<>();
        start.setVisitStatus(NodeVisitStatus.VISITING);
        start.setLevel(0);
        queue.add(start);
        while(!queue.isEmpty()){
            Node currNode = queue.poll();
            currNode.setVisitStatus(NodeVisitStatus.VISITED);
            if(currNode.getLevel()%2 == 0){
                evenQueue.add(currNode);
            }else{
                oddQueue.add(currNode);
            }
            for(Node neighbour : currNode.neighbours()){
                if(neighbour.getVisitStatus() == NodeVisitStatus.VISITED && currNode.getLevel() == neighbour.getLevel()){
                    System.out.println("Not a bipartite graph");
                    return ;
                }else if(neighbour.getVisitStatus() == NodeVisitStatus.UNVISITED){
                        neighbour.setVisitStatus(NodeVisitStatus.VISITING);
                        neighbour.setLevel(currNode.getLevel()+1);
                        queue.add(neighbour);
                    }
                }
            }


        System.out.println("Bipartite graph"+evenQueue+"   "+oddQueue);
    }
}
