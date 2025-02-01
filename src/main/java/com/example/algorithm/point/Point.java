package com.example.algorithm.point;

public class Point {
    private int value;
    private boolean isStart = false;

    public Point(int value, boolean isStart) {
        this.value = value;
        this.isStart = isStart;
    }

    public int getValue() {
        return value;
    }

    public boolean isStart() {
        return isStart;
    }

    @Override
    public String toString() {
        return "Point{" +
                "value=" + value +
                ", isStart=" + isStart +
                '}';
    }
}
