package com.feedback.hafit.entity;

public class ScoreDTO {
    private int NO;
    private int COUNT;
    private int SCORE;
    private int BAD;
    public ScoreDTO() {
        super();
    }

    public ScoreDTO(int NO, int COUNT, int SCORE, int BAD) {
        this.NO = NO;
        this.COUNT = COUNT;
        this.SCORE = SCORE;
        this.BAD = BAD;
    }

    public int getNO() {
        return NO;
    }

    public void setNO(int NO) {
        this.NO = NO;
    }

    public int getCOUNT() {
        return COUNT;
    }

    public void setCOUNT(int COUNT) {
        this.COUNT = COUNT;
    }

    public int getSCORE() {
        return SCORE;
    }

    public void setSCORE(int SCORE) {
        this.SCORE = SCORE;
    }

    public int getBAD() {
        return BAD;
    }

    public void setBAD(int BAD) {
        this.BAD = BAD;
    }
}