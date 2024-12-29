package com.example.algorithm.array;

import java.util.*;

public class SmallestSubArrayContainsAllWords {
    public static void main(String[] args) {
        Set<String> words = new HashSet<>(Arrays.asList("and", "of", "one"));
        new SmallestSubArrayContainsAllWords().findSmallestSubString("one of the car and bike and one of those",
                words);
    }

    private void findSmallestSubString(String doc, Set<String> words) {
        LinkedList<Node> list = new LinkedList<>();
        Set<String> wordsSet = new HashSet<>();
        wordsSet.addAll(words);
        Result finalResult = null;
        StringTokenizer st = new StringTokenizer(doc, " ");
        int index = 0;
        while (st.hasMoreTokens()) {
            String w = st.nextToken();
            Node node = new Node(index, w);
            if (words.contains(w)) {
                wordsSet.remove(w);

                if (list.contains(node)) {
                    list.remove(node);
                }
                list.addFirst(node);
                Result currResult = new Result();
                currResult.last = list.getFirst().position;
                currResult.first = list.getLast().position;
                if (list.size() == words.size() && finalResult == null) {
                    finalResult = currResult;
                }else if ((list.size() == words.size()) && (finalResult.last - finalResult.first > currResult.last - currResult.first)) {
                    finalResult = currResult;
                }
            }
            index+=w.length()+1;
        }

        if(wordsSet.isEmpty()){
            System.out.println("First word pos: "+finalResult.first+" last word pos: "+finalResult.last);
        }else{
            System.out.println("Not all words covered");
        }


    }

    private class Result {
        int first;
        int last;
    }

    private class Node {
        private int position;
        private String word;

        public Node(int position, String word) {
            this.position = position;
            this.word = word;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Node node = (Node) o;

            return Objects.equals(word, node.word);
        }

        @Override
        public int hashCode() {
            return word != null ? word.hashCode() : 0;
        }
    }

}
