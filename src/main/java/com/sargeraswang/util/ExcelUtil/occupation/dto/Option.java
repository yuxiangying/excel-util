package com.sargeraswang.util.ExcelUtil.occupation.dto;

import java.io.Serializable;

public class Option implements Serializable {

    private String value;
    private String label;
    private String grade;
    private OccupationDTO children;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public OccupationDTO getChildren() {
        return children;
    }

    public void setChildren(OccupationDTO children) {
        this.children = children;
    }
}
