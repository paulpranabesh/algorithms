package com.example.algorithm.point;

public class Building implements Comparable<Building>{

    private Integer start;
    private Integer end;

    private Integer height;

    public Building(Integer start, Integer end, Integer height) {
        this.start = start;
        this.end = end;
        this.height = height;
    }

    public Integer getStart() {
        return start;
    }

    public Integer getEnd() {
        return end;
    }

    public Integer getHeight() {
        return height;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Building building = (Building) o;

        if (!start.equals(building.start)) return false;
        if (!end.equals(building.end)) return false;
        return height.equals(building.height);
    }

    @Override
    public int hashCode() {
        int result = start.hashCode();
        result = 31 * result + end.hashCode();
        result = 31 * result + height.hashCode();
        return result;
    }

    @Override
    public int compareTo(Building o) {
        return -1 * this.getHeight().compareTo(o.getHeight());
    }
}
