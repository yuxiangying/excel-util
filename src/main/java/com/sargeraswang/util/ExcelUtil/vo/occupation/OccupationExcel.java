package com.sargeraswang.util.ExcelUtil.vo.occupation;

import com.sargeraswang.util.ExcelUtil.oragin.ExcelCell;

/**
 * @author yuxy_cyd
 * @version v-1.1.0
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @date: 2019年11月22日 13:45
 **/
public class OccupationExcel {
    @ExcelCell(index = 0)
    private String index;
    @ExcelCell(index = 1)
    private String primaryClassify;
    @ExcelCell(index = 2)
    private String secondaryClassify;
    @ExcelCell(index = 3)
    private String name;
    @ExcelCell(index = 4)
    private String code;
    @ExcelCell(index = 5)
    private String grade;

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getPrimaryClassify() {
        return primaryClassify;
    }

    public void setPrimaryClassify(String primaryClassify) {
        this.primaryClassify = primaryClassify;
    }

    public String getSecondaryClassify() {
        return secondaryClassify;
    }

    public void setSecondaryClassify(String secondaryClassify) {
        this.secondaryClassify = secondaryClassify;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
