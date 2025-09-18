package com.example.algorithm.graph;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class NoOfConnectedComponent {

    public int countComponents(int n, int[][] edges) {

        Graph graph = new Graph();
        for (int[] edge : edges) {
            graph.getNode(edge[0]).addNeighbour(graph.getNode(edge[1]));
            graph.getNode(edge[1]).addNeighbour(graph.getNode(edge[0]));
        }
        AtomicInteger count = new AtomicInteger(0);
        Set<Node> visited = new HashSet<>();
        IntStream.range(0, n).forEach(graph::addNode);
        IntStream.range(0, n).forEach(i -> {
            if(!visited.contains(graph.nodes.get(i))){
                dfs(graph.nodes.get(i), visited);
                count.incrementAndGet();
            }
        });
        return count.get();
    }

    private void dfs(Node node, Set<Node> visited) {
        if(visited.contains(node)){
            return;
        }
        visited.add(node);
        for (Node neighbour : node.neighbours) {
            if (!visited.contains(neighbour)) {
                dfs(neighbour, visited);
            }
        }
    }

    private class Graph {
        private Map<Integer, Node> nodes = new HashMap<>();

        public Node getNode(int id) {
            nodes.putIfAbsent(id, new Node(id));
            return nodes.get(id);
        }

        public void addNode(int id) {
            nodes.putIfAbsent(id, new Node(id));
        }
    }

    private class Node {
        private int id;
        private List<Node> neighbours = new ArrayList<>();

        public Node(int id) {
            this.id = id;
        }

        public void addNeighbour(Node node) {
            if (!neighbours.contains(node)) {
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
