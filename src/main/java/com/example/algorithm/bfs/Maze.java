package com.example.algorithm.bfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Nearest Exit from Entrance in Maze
 *
 * You are given an m x n matrix maze (0-indexed) with empty cells (represented as '.') and walls (represented as '+'). You are also given the entrance of the maze, where entrance = [entrancerow, entrancecol] denotes the row and column of the cell you are initially standing at.
 *
 * In one step, you can move one cell up, down, left, or right. You cannot step into a cell with a wall, and you cannot step outside the maze. Your goal is to find the nearest exit from the entrance. An exit is defined as an empty cell that is at the border of the maze. The entrance does not count as an exit.
 *
 * Return the number of steps in the shortest path from the entrance to the nearest exit, or -1 if no such path exists.
 */
public class Maze {
    public int nearestExit(char[][] maze, int[] entrance) {
        List<int[]> coords = Arrays.asList(
                new int[]{0, 1},
                new int[]{0, -1},
                new int[]{1, 0},
                new int[]{-1, 0}
        );
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        int[] curr;
        int x, y, moves = 0;
        queue.offer(entrance);
        visited[entrance[0]][entrance[1]] = true;
        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            moves++;
            for (int i = 0; i < queueSize; i++) {
                curr = queue.poll();

                for (int[] coord : coords) {
                    x = coord[0] + curr[0];
                    y = coord[1] + curr[1];

                    if (x < 0 || x >= maze.length || y < 0 || y >= maze[0].length) {
                        continue;
                    }

                    if (visited[x][y] || maze[x][y] == '+') {
                        continue;
                    }

                    if (isBoundary(x, y, maze)) {
                        return moves;
                    }

                    queue.offer(new int[]{x, y});
                    visited[x][y] = true;
                }
            }
        }
        return -1;
    }

    private boolean isBoundary(int row, int col, char[][] maze) {
        return (row == 0 || col == 0 || row == maze.length - 1 || col == maze[0].length - 1);
    }
}
