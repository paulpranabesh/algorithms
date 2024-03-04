package com.example.algorithm.array;

import java.util.*;

/**
 * You are given an array of variable pairs equations and an array of real numbers values, where equations[i] = [Ai, Bi] and values[i] represent the equation Ai / Bi = values[i]. Each Ai or Bi is a string that represents a single variable.
 *
 * You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query where you must find the answer for Cj / Dj = ?.
 *
 * Return the answers to all queries. If a single answer cannot be determined, return -1.0.
 *
 * Note: The input is always valid. You may assume that evaluating the queries will not result in division by zero and that there is no contradiction.
 *
 * Note: The variables that do not occur in the list of equations are undefined, so the answer cannot be determined for them.
 */
public class Division {

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Node> valuesMap = new HashMap<>();
        double[] result = new double[queries.size()];
        int i =0;
        for(List<String> xyValues : equations){
            valuesMap.putIfAbsent(xyValues.get(0), new Node());
            valuesMap.putIfAbsent(xyValues.get(1), new Node());
            double eqValue = values[i];
            valuesMap.computeIfPresent(xyValues.get(0), (key, prev)-> {
                prev.addYValue(xyValues.get(1), eqValue);
                return prev;
            });

            double eqValue2 = 1/values[i];
            valuesMap.computeIfPresent(xyValues.get(1), (key, prev)-> {
                prev.addYValue(xyValues.get(0), eqValue2);
                return prev;
            });
            i++;
        }

        i =0;
        for(List<String> query : queries){
            if(!valuesMap.containsKey(query.get(0)) || !valuesMap.containsKey(query.get(1))){
                result[i] = -1.0;
            }else if(query.get(0).equals(query.get(1))){
                result[i] = 1.0;
            }else{
                result[i] = findValue(valuesMap, query.get(0), query.get(1), new HashSet<>());
            }

            i++;
        }
        return result;
    }

    private double findValue(Map<String, Node> valuesMap, String X, String Y, Set<String> xValuesSofar) {
        if (xValuesSofar.contains(X)) {
            return -1;
        }
        Node nodeForX = valuesMap.get(X);
        if (nodeForX.containsYValue(Y)) {
            return nodeForX.equationValue(Y);
        }
        xValuesSofar.add(X);
        for(String yValue : nodeForX.yValues()){
            double currValue = findValue(valuesMap, yValue, Y,  xValuesSofar);
            if(currValue != -1.0){
                double valForCurrY= nodeForX.equationValue(yValue);
                xValuesSofar.remove(X);
                return valForCurrY * currValue;
            }
        }
        xValuesSofar.remove(X);
        return -1.0;
    }

    private class Node{
        private Map<String, Double> map = new HashMap<>();

        public void addYValue(String yValue, double equationValue){
            map.put(yValue, equationValue);
        }

        public boolean containsYValue(String yValue){
            return map.containsKey(yValue);
        }
        public double equationValue(String yVal){
            return map.get(yVal);
        }

        public Set<String> yValues(){
            return map.keySet();
}
    }
}
