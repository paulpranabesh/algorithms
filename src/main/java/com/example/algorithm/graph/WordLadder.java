package com.example.algorithm.graph;

import java.util.*;

/**
 * You are given two words, beginWord and endWord, and also a list of words wordList. All of the given words are of the same length, consisting of lowercase English letters, and are all distinct.
 *
 * Your goal is to transform beginWord into endWord by following the rules:
 *
 * You may transform beginWord to any word within wordList, provided that at exactly one position the words have a different character, and the rest of the positions have the same characters.
 * You may repeat the previous step with the new word that you obtain, and you may do this as many times as needed.
 * Return the minimum number of words within the transformation sequence needed to obtain the endWord, or 0 if no such sequence exists.
 *
 * Example 1:
 *
 * Input: beginWord = "cat", endWord = "sag", wordList = ["bat","bag","sag","dag","dot"]
 *
 * Output: 4
 */
public class WordLadder {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> words = new HashSet<>(wordList);
        if (!words.contains(endWord)) {
            return 0;
        }
        int steps = 0;
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        queue.offer("#");

        while (!queue.isEmpty()) {

            while (!queue.isEmpty()) {
                String nextWord = queue.poll();
                if (nextWord.equals("#")) {
                    break;
                }
                if (endWord.equals(nextWord)) {
                    return steps+1;
                }
                List<String> oneDiffWords = words.stream().filter(word -> difference(word, nextWord) == 1).collect(Collectors.toList());
                oneDiffWords.stream().forEach(queue::offer);
                oneDiffWords.forEach(words::remove);
            }
            if (!queue.isEmpty()) {
                queue.offer("#");
            }
            steps++;
        }
        return 0;
    }

    private int difference(String wordA, String wordB) {
        if (Math.abs(wordA.length() - wordB.length()) > 1) {
            return 2;
        }
        int count = 0;
        for (int i = 0; i < wordA.length(); i++) {
            if (wordA.charAt(i) != wordB.charAt(i)) {
                count++;
            }
        }
        return count;
    }
}
