package org.example.classes;

public class SpecifiedInfo {

    private String university;
    private String field;
    private String workStation;

    public SpecifiedInfo(String university, String field, String workStation) {
        this.university = university;
        this.field = field;
        this.workStation = workStation;
    }

    @Override
    public String toString() {
        return "SpecifiedInfo{" +
                "university='" + university + '\'' +
                ", field='" + field + '\'' +
                ", workStation='" + workStation + '\'' +
                '}';
    }


    //getter setter ======================================================


    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getWorkStation() {
        return workStation;
    }

    public void setWorkStation(String workStation) {
        this.workStation = workStation;
    }
}
