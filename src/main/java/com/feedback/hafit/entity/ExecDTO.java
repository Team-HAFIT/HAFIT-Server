package com.feedback.hafit.entity;

public class ExecDTO {
    public int repsPerSet;
    public int totalSets;
    public int weight;
    public int restTime;

    public ExecDTO() {
        super();
    }

    public ExecDTO(int repsPerSet, int totalSets, int restTime, int weight) {
        this.repsPerSet = repsPerSet;
        this.totalSets = totalSets;
        this.restTime = restTime;
        this.weight = weight;
    }

    public int getRepsPerSet() {
        return repsPerSet;
    }

    public void setRepsPerSet(int repsPerSet) {
        this.repsPerSet = repsPerSet;
    }

    public int getTotalSets() {
        return totalSets;
    }

    public void setTotalSets(int totalSets) {
        this.totalSets = totalSets;
    }

    public int getRestTime() {
        return restTime;
    }

    public void setRestTime(int restTime) {
        this.restTime = restTime;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}