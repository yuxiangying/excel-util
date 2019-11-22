package com.sargeraswang.util.ExcelUtil;

import com.sargeraswang.util.ExcelUtil.oragin.ExcelCell;

/**
 * @author yuxy_cyd
 * @version v-1.1.0
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @date: 2019年11月21日 15:48
 **/
public class OccupationThModel {
    @ExcelCell(index = 2)
    private String label;//职业名称
    @ExcelCell(index = 3)
    private String code;//职业代码
    @ExcelCell(index = 4)
    private String grade;//职业等级

    public OccupationThModel() {
    }

    public OccupationThModel(String label, String code, String grade) {
        this.label = label;
        this.code = code;
        this.grade = grade;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
