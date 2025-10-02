package com.example.algorithm.graph;

import java.util.*;

/**
 * You are given a square 2-D matrix of distinct integers grid where each integer grid[i][j] represents the elevation at position (i, j).
 *
 * Rain starts to fall at time = 0, which causes the water level to rise. At time t, the water level across the entire grid is t.
 *
 * You may swim either horizontally or vertically in the grid between two adjacent squares if the original elevation of both squares is less than or equal to the water level at time t.
 *
 * Starting from the top left square (0, 0), return the minimum amount of time it will take until it is possible to reach the bottom right square (n - 1, n - 1).
 */
public class AlienDictionary {
    public String foreignDictionary(String[] words) {
        Graph graph = new Graph();

        // Step 1: Initialize graph nodes for all characters
        for (String word : words) {
            for (char c : word.toCharArray()) {
                graph.getOrCreateNode(c);
            }
        }

        // Step 2: Build edges from adjacent word comparisons
        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];

            if (word1.length() > word2.length() && word1.startsWith(word2)) {
                return ""; // Invalid order: e.g., "abc" before "ab"
            }

            for (int j = 0; j < Math.min(word1.length(), word2.length()); j++) {
                char c1 = word1.charAt(j);
                char c2 = word2.charAt(j);
                if (c1 != c2) {
                    Node from = graph.getOrCreateNode(c1);
                    Node to = graph.getOrCreateNode(c2);
                    from.addNeighbour(to);
                    break; // Only first difference matters
                }
            }
        }

        // Step 3: Topological Sort
        Stack<Node> stack = new Stack<>();
        for (Node node : graph.nodes.values()) {
            if (node.visitStatus == VisitStatus.UNVISITED) {
                if (!dfs(node, stack)) {
                    return ""; // cycle detected
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop().c);
        }

        return sb.toString();
    }

    // DFS returns false if a cycle is found
    private boolean dfs(Node node, Stack<Node> stack) {
        if (node.visitStatus == VisitStatus.VISITING) {
            return false; // cycle detected
        }
        if (node.visitStatus == VisitStatus.VISITED) {
            return true;
        }

        node.visitStatus = VisitStatus.VISITING;

        for (Node neighbor : node.neighbours) {
            if (!dfs(neighbor, stack)) {
                return false;
            }
        }

        node.visitStatus = VisitStatus.VISITED;
        stack.push(node);
        return true;
    }

    private class Graph {
        Map<Character, Node> nodes = new HashMap<>();

        public Node getOrCreateNode(char c) {
            nodes.putIfAbsent(c, new Node(c));
            return nodes.get(c);
        }
    }

    private class Node {
        char c;
        VisitStatus visitStatus = VisitStatus.UNVISITED;
        List<Node> neighbours = new ArrayList<>();

        public Node(char c) {
            this.c = c;
        }

        public void addNeighbour(Node node) {
            neighbours.add(node);
        }
    }

    private enum VisitStatus {
        UNVISITED, VISITING, VISITED
    }
}
