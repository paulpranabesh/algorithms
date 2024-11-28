package com.example.algorithm.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Print all combinations/permutation of length X.
 */

public class PrintAllCombinationsAndPermutations {
    public static void main(String[] args) {
        combination(new int[]{1, 2, 3, 4, 6, 9}, 3);
        System.out.println("Permutations #############");
        permutation(new int[]{1, 2, 3, 4}, 3);

    }

    private static void permutation(int[] a, int x) {
        int[] buffer = new int[x];
        permutationHelper(a, buffer, 0, new HashSet<>());
    }

    private static void permutationHelper(int[] a, int[] buffer, int startB, Set<Integer> indexes) {
        if (buffer.length == startB) {
            Arrays.stream(buffer).forEach(i -> {
                System.out.print(i + " ");
            });
            System.out.println();
            return;
        }
        for (int i = 0; i < a.length; i++) {
            if (indexes.contains(i)) {
                continue;
            }
            buffer[startB] = a[i];
            indexes.add(i);
            permutationHelper(a, buffer, startB + 1, indexes);
            indexes.remove(i);
        }
    }

    private static void combination(int[] a, int x) {
        int[] buffer = new int[x];
        combinationHelper(a, buffer, 0, 0);
    }

    private static void combinationHelper(int[] a, int[] buffer, int start, int bufIndex) {
        if (buffer.length == bufIndex) {
            Arrays.stream(buffer).forEach(i -> {
                System.out.print(i + " ");
            });
            System.out.println();
            return;
        }
        if (start >= a.length) {
            return;
        }
        for (int i = start; i < a.length; i++) {
            buffer[bufIndex] = a[i];
            combinationHelper(a, buffer, i + 1, 1 + bufIndex);
        }
    }
}
