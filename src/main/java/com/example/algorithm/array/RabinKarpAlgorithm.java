package com.example.algorithm.array;

public class RabinKarpAlgorithm {

    public static void main(String[] args) {
        int val = new RabinKarpAlgorithm().search("rtdgbdghfhjhellowloorldowo", "owo");
        System.out.println("Location found: "+val);
    }


    private int search(String string, String key){
        if(string == null || key == null || string.length()<key.length()){
            return -1;
        }
        int hashKey = hashVal(key);
        int x = 31;
        int hash = hashVal(string.substring(0, key.length()));
        for(int i = key.length();i<=string.length();i++){
            int l = i - (key.length());
            if(hashKey == hash && key.equals(string.substring(l, i ))){
                return l;
            }
            hash = (int) ((hash - Math.pow(x, (key.length()-1))*string.charAt(l))*x + string.charAt(i == string.length() ? string.length()-1:i));
            int hh = (hashVal(string.substring(l, i )));
            System.out.println(hh);
        }
        return -1;
    }

    private int hashVal(String key){
        int hashKey = 0;
        int x = 31;
        int pow=0;
        for(int i =  key.length()-1; i>=0; i--){
            hashKey+= key.charAt(i)*Math.pow(x,pow++);
        }
        return hashKey;
    }
}
