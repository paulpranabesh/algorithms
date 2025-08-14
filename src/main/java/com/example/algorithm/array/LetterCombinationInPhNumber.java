package com.example.algorithm.array;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.
 *
 * A mapping of digits to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
 */
public class LetterCombinationInPhNumber {


    Map<Integer, List<Character>> charMap = new HashMap<>();

    public List<String> letterCombinations(String digits) {
        if(digits == null || digits.length() ==0){
            return new LinkedList<>();

        }
        charMap.put(2, Arrays.stream(new Character[]{'a','b','c'}).collect(Collectors.toUnmodifiableList()));
        charMap.put(3, Arrays.stream(new Character[]{'d','e','f'}).collect(Collectors.toUnmodifiableList()));
        charMap.put(4, Arrays.stream(new Character[]{'g','h','i'}).collect(Collectors.toUnmodifiableList()));
        charMap.put(5, Arrays.stream(new Character[]{'j','k','l'}).collect(Collectors.toUnmodifiableList()));
        charMap.put(6, Arrays.stream(new Character[]{'m','n','o'}).collect(Collectors.toUnmodifiableList()));
        charMap.put(7, Arrays.stream(new Character[]{'p','q','r','s'}).collect(Collectors.toUnmodifiableList()));
        charMap.put(8, Arrays.stream(new Character[]{'t','u','v'}).collect(Collectors.toUnmodifiableList()));
        charMap.put(9, Arrays.stream(new Character[]{'w','x','y','z'}).collect(Collectors.toUnmodifiableList()));
        List<String> result = new LinkedList<>();
        populate(digits, 0, result, new Stack<Character>());
        return result;

    }

    private void populate(String digits, int digiIndex, List<String> result, Stack<Character> holder){
        if(digits.length() == digiIndex){
            result.add(holder.stream().map(c->c+"").collect(Collectors.joining()) );
            return;

        }

        int key = Integer.parseInt(digits.charAt(digiIndex) + "");
        if(charMap.containsKey(key)){
            for(Character achar : charMap.get(key)){
                holder.push(achar);
                populate( digits, 1+ digiIndex,  result, holder);
                holder.pop();
            }
        }
    }

}
