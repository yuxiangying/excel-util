package com.sargeraswang.util.ExcelUtil.occupation.dto;

import java.io.Serializable;
import java.util.List;

public class OccupationDTO implements Serializable {

    //private Integer code;//以后要交code=0,说是表示请求成功
    private String name;
    private String label;
    private List<Option> options;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }
}


