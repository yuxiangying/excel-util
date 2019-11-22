package com.sargeraswang.util.ExcelUtil;

import com.sargeraswang.util.ExcelUtil.oragin.ExcelCell;

import java.util.List;

/**
 * @author yuxy_cyd
 * @version v-1.1.0
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @date: 2019年11月21日 15:45
 **/
public class OccupationSeModel {
    @ExcelCell(index = 1)
    private String label;
    @ExcelCell(index = 999)
    List<OccupationThModel> occupation;

    public OccupationSeModel() {
    }

    public OccupationSeModel(String label, List<OccupationThModel> occupation) {
        this.label = label;
        this.occupation = occupation;
    }


    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<OccupationThModel> getOccupation() {
        return occupation;
    }

    public void setOccupation(List<OccupationThModel> occupation) {
        this.occupation = occupation;
    }
}
