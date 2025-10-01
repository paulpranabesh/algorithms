package com.example.algorithm.graph;

import java.util.*;

public class MinCostConnectPoint {

    public int minCostConnectPoints(int[][] points) {
        List<Node> nodes = new ArrayList<>();
        Set<Node> visited = new HashSet<>();

        for (int[] point : points) {
            nodes.add(new Node(point[0], point[1]));
        }

        Node firstNode = nodes.get(0);
        firstNode.weight = 0;

        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(n -> n.weight));
        queue.offer(firstNode);

        int result = 0;

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (visited.contains(node)) continue;

            visited.add(node);
            result += node.weight;

            for (Node neighbor : nodes) {
                if (!visited.contains(neighbor)) {
                    int newDist = manhatamDistance(node, neighbor);
                    if (newDist < neighbor.weight) {
                        neighbor.weight = newDist;
                        queue.offer(new Node(neighbor.x, neighbor.y, neighbor.weight)); // add again with new weight
                    }
                }
            }
        }

        return result;
    }

    private int manhatamDistance(Node a, Node b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }

    private class Node {
        int x, y, weight;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
            this.weight = Integer.MAX_VALUE;
        }

        public Node(int x, int y, int weight) {
            this.x = x;
            this.y = y;
            this.weight = weight;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Node)) return false;
            Node node = (Node) o;
            return x == node.x && y == node.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "Node{" + "x=" + x + ", y=" + y + ", weight=" + weight + '}';
        }
    }
}
