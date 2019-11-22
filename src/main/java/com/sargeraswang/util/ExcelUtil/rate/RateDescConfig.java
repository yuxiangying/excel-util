package com.sargeraswang.util.ExcelUtil.rate;

import java.util.HashMap;
import java.util.Map;

public class RateDescConfig {

    private int startRowNum = 0;
    private int endRowNum = 0;
    private int ageColumnNum = 0;
    private Map<Integer, String> colDescMap;


    public RateDescConfig(int ageColumnNum, int startRowNum, int endRowNum, String colDesc) {
        this.startRowNum = startRowNum;
        this.endRowNum = endRowNum;
        this.ageColumnNum = ageColumnNum;
        colDescMap = new HashMap<>();
        System.out.println("==>" + colDesc);
        if (colDesc != null && !"".equals(colDesc)) {
            String[] columnArray = colDesc.split("[,]");
            for (String column : columnArray) {
                String[] colNumAndName = column.split(":");
                colDescMap.put(Integer.valueOf(colNumAndName[1]), colNumAndName[0]);
            }
        }


    }

    public int getStartRowNum() {
        return startRowNum;
    }

    public void setStartRowNum(int startRowNum) {
        this.startRowNum = startRowNum;
    }

    public int getEndRowNum() {
        return endRowNum;
    }

    public void setEndRowNum(int endRowNum) {
        this.endRowNum = endRowNum;
    }

    public int getAgeColumnNum() {
        return ageColumnNum;
    }

    public void setAgeColumnNum(int ageColumnNum) {
        this.ageColumnNum = ageColumnNum;
    }

    public Map<Integer, String> getColDescMap() {
        return colDescMap;
    }

    public void setColDescMap(Map<Integer, String> colDescMap) {
        this.colDescMap = colDescMap;
    }
}
