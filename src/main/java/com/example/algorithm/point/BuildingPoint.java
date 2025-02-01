package com.example.algorithm.point;

public class BuildingPoint {

    private Integer value;

    private Building building;

    private boolean start;

    public BuildingPoint(Integer value, Building building, boolean start) {
        this.value = value;
        this.building = building;
        this.start = start;
    }

    public Integer getValue() {
        return value;
    }

    public Building getBuilding() {
        return building;
    }

    public boolean isStart() {
        return start;
    }
}
