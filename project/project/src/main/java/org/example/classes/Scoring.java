package org.example.classes;

public class Scoring {

    private int levelScore ;
    private int specialitiesScore;
    private int filedScore;
    private int uniScore;
    private int workstationScore;

    public Scoring(int levelScore, int specialitiesScore, int filedScore, int uniScore, int workstationScore) {
        this.levelScore = levelScore;
        this.specialitiesScore = specialitiesScore;
        this.filedScore = filedScore;
        this.uniScore = uniScore;
        this.workstationScore = workstationScore;
    }


    public int getLevelScore() {
        return levelScore;
    }

    public void setLevelScore(int levelScore) {
        this.levelScore = levelScore;
    }

    public int getSpecialitiesScore() {
        return specialitiesScore;
    }

    public void setSpecialitiesScore(int specialitiesScore) {
        this.specialitiesScore = specialitiesScore;
    }

    public int getFiledScore() {
        return filedScore;
    }

    public void setFiledScore(int filedScore) {
        this.filedScore = filedScore;
    }

    public int getUniScore() {
        return uniScore;
    }

    public void setUniScore(int uniScore) {
        this.uniScore = uniScore;
    }

    public int getWorkstationScore() {
        return workstationScore;
    }

    public void setWorkstationScore(int workstationScore) {
        this.workstationScore = workstationScore;
    }
}
