package com.example.algorithm.graph;

import java.util.*;
import java.util.stream.Collectors;

/**
 * You are given an integer n, which indicates that there are n courses labeled from 1 to n. You are also given an array relations where relations[i] = [prevCoursei, nextCoursei], representing a prerequisite relationship between course prevCoursei and course nextCoursei: course prevCoursei has to be taken before course nextCoursei. Also, you are given the integer k.
 *
 * In one semester, you can take at most k courses as long as you have taken all the prerequisites in the previous semesters for the courses you are taking.
 *
 * Return the minimum number of semesters needed to take all courses. The testcases will be generated such that it is possible to take every course.
 */
public class MinSemForCourses {

    public int minNumberOfSemesters(int n, int[][] relations, int maxCoursePerSemester) {
        Graph graph = new Graph();
        for(int i=1; i<=n; i++){
            graph.getNode(i);
        }
        for(int[] relation : relations){
            graph.getNode(relation[0]).addNeighbour(graph.getNode(relation[1]));
        }
        Stack<Node> stack = new Stack<>();
        for(int i=1; i<=n; i++){
            dfs(graph.getNode(i), stack);
        }
        int [] prerequisite = new int[n+1];
        Arrays.fill(prerequisite, 1);
        while(!stack.isEmpty()){
            Node node = stack.pop();
            for(Node neighbour : node.neighbours){
                prerequisite[neighbour.id] = Math.max(prerequisite[node.id] + 1, prerequisite[neighbour.id]);
            }
        }
        int maxSem = 0;
        for(int i=1; i<=n; i++){
            if(prerequisite[i] <= maxCoursePerSemester){
                maxSem++;
            }
        }
        return maxSem;



    }

    private void dfs(Node node, Stack<Node> stack){
        if(node.visitStatus == NodeVisitStatus.VISITING){
            return;
        }
        if(node.visitStatus == NodeVisitStatus.VISITED){
            return;
        }
        node.visitStatus = NodeVisitStatus.VISITING;
        for(Node neighbour : node.neighbours){
            if(neighbour.visitStatus == NodeVisitStatus.UNVISITED){
                dfs(neighbour, stack);
            }
        }
        node.visitStatus = NodeVisitStatus.VISITED;
        stack.push(node);
    }




    private class Graph{
        Map<Integer, Node> nodes;

        public Graph(){
            this.nodes = new HashMap<>();
        }

        public Node getNode(int id){
            nodes.putIfAbsent(id, new Node(id));
            return nodes.get(id);
        }

    }

    private enum NodeVisitStatus {
        UNVISITED, VISITING, VISITED;
    }

    private class Node{
        int id;
        List<Node> neighbours = new LinkedList<>();
        private NodeVisitStatus visitStatus = NodeVisitStatus.UNVISITED;

        public Node(int id) {
            this.id = id;
        }
        public void addNeighbour(Node node){
            if(!neighbours.contains(node)){
                neighbours.add(node);
            }
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return id == node.id;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(id);
        }
    }


}
