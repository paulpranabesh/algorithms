package com.example.algorithm.string;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * Given an encoded string, return its decoded string.
 *
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.
 *
 * You may assume that the input string is always valid; there are no extra white spaces, square brackets are well-formed, etc. Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there will not be input like 3a or 2[4].
 *
 * The test cases are generated so that the length of the output will never exceed 105.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "3[a]2[bc]"
 * Output: "aaabcbc"
 * Example 2:
 *
 * Input: s = "3[a2[c]]"
 * Output: "accaccacc"
 * Example 3:
 *
 * Input: s = "2[abc]3[cd]ef"
 * Output: "abcabccdcdcdef"
 */
public class DecodeString {

    public static void main(String[] args) {
        String result = new DecodeString().decodeString("3[z]2[2[y]pq4[2[jk]e1[f]]]ef");
        assert result.equals("zzzyypqjkjkefjkjkefjkjkefjkjkefyypqjkjkefjkjkefjkjkefjkjkefef");
        System.out.println(result);
    }

    public String decodeString(String s) {
        Stack<String> stack = new Stack<>();
        for (Character currChar : s.toCharArray()) {
            if (currChar == ']') {
                String stringForStack = multiplyStringsUntilClosingBracket(stack);
                stack.push(stringForStack.toString());
            } else {
                stack.push(currChar + "");
            }
        }
        return reverseTheStackAndAddTheStrings(stack);
    }

    private String multiplyStringsUntilClosingBracket(Stack<String> stack){
        List<String> string = new LinkedList<>();
        StringBuffer numberString = new StringBuffer();
        boolean addNumber = false;
        while (!stack.isEmpty()) {
            if("[".equals(stack.peek())){
                if(addNumber){
                    break;
                }
                stack.pop();
                addNumber = true;
            } else if (!addNumber) {
                string.add(stack.pop());
            } else if(addNumber){
                if(!isNumber(stack.peek()+"")){
                    break;
                }
                numberString.append(stack.pop());
            }
        }
        Integer occurance = reverseAndConvertToInteger(numberString);
        String strings = reverseTheListAndAddTheStrings(string);
        String stringForStack = joinAsManyAs(occurance, strings);
        return stringForStack;
    }
    private String joinAsManyAs(Integer noOfTimes, String value){
        StringBuffer joinedString = new StringBuffer();
        for (int k = 1; k <= noOfTimes; k++) {
            joinedString.append(value);
        }
        return joinedString.toString();
    }

    private Integer reverseAndConvertToInteger(StringBuffer aString){
        String reversedString = aString.reverse().toString();
        return isNumber(reversedString) ? Integer.valueOf(reversedString) : 0;
    }
    private String reverseTheListAndAddTheStrings(List<String> strings){
        Collections.reverse(strings);
        String value = strings.stream().collect(Collectors.joining());
        return value;
    }
    private String reverseTheStackAndAddTheStrings(Stack<String> stack){
        List<String> stringsInStack = new LinkedList<>();
        while (!stack.isEmpty()) {
            stringsInStack.add(stack.pop());
        }
        return reverseTheListAndAddTheStrings(stringsInStack);
    }

    private boolean isNumber(String string) {
        try {
            Integer.valueOf(string);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
