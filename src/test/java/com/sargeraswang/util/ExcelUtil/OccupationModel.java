package com.sargeraswang.util.ExcelUtil;

import com.sargeraswang.util.ExcelUtil.oragin.ExcelCell;

import java.util.List;

/**
 * @author yuxy_cyd
 * @version v-1.1.0
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @date: 2019年11月21日 12:05
 **/
public class OccupationModel {
    @ExcelCell(index = 0)
    private String label;
    @ExcelCell(index = 999)
    List<OccupationSeModel> options;

    public OccupationModel() {
    }

    public OccupationModel( String label, List<OccupationSeModel> options) {
        this.label = label;
        this.options = options;
    }


    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<OccupationSeModel> getOptions() {
        return options;
    }

    public void setOptions(List<OccupationSeModel> options) {
        this.options = options;
    }
}
