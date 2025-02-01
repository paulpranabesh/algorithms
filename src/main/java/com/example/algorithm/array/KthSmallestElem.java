package com.example.algorithm.array;

import java.util.Arrays;

public class KthSmallestElem {

    public static void main(String[] args) {
        int[] array = new int[]{6,4,1,8,4,0,2,34,5,78,2,5,9};
        int[] array1 = new int[]{6,4,1,8,4,0,2,34,5,78,2,5,9};
        new KthSmallestElem().quickSort(array, 0, array.length-1);
        Arrays.stream(array).forEach(System.out::println);
        new KthSmallestElem().findSmallestElem(array1, 0, array.length-1, 9);
    }

    private void findSmallestElem(int[] array, int start, int end, int target){
        if(start<=end){
            int mid = partition(array, start, end);
            System.out.println("mid is: "+mid);
            if(mid == target){
                System.out.println(" the "+target+"th smallest elem is "+array[target]);
            }else if(mid<target){
                findSmallestElem(array, mid+1, end, target);
            }else{
                findSmallestElem(array,start, mid-1, target);
            }
        }
    }




    private void quickSort(int[] array, int start, int end){
        if(start<=end){
            int mid = partition(array, start, end);
            quickSort(array,start, mid-1);
            quickSort(array, mid+1, end);
        }
    }


    private int partition(int[] array, int start, int end){
        int pivotItem = array[end];
        int i = start;
        int j = end-1;

        while(i<j){
            if(array[i]<=pivotItem){
                i++;
            }else if(array[j]>pivotItem){
                j--;
            }else if(array[i]>pivotItem){
                swap(array, i, j);
                j--;
            }else  if(array[j]<=pivotItem){
                swap(array, i, j);
                i++;
            }
        }

        if(array[i]<pivotItem){
            swap(array, ++i, end);
        }else{
            swap(array, i, end);
        }
        return i;
    }

    private void swap(int[] array, int i, int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
