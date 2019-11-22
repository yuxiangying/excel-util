package com.sargeraswang.util.ExcelUtil.vo.area;

import com.sargeraswang.util.ExcelUtil.oragin.ExcelCell;

/**
 * @author yuxy_cyd
 * @version v-1.1.0
 * @Description:TODO(地区编码转换Excel中间实体)
 * @date: 2019年11月22日 10:05
 **/
public class AreaExcel {
    @ExcelCell(index = 0)
    private String index;
    @ExcelCell(index = 1)
    private String provinceCode;//省编码
    @ExcelCell(index = 2)
    private String provinceName;//省名称
    @ExcelCell(index = 3)
    private String cityCode;//市编码
    @ExcelCell(index = 4)
    private String cityName;//市名称
    @ExcelCell(index = 5)
    private String countyCode;//区县编码
    @ExcelCell(index = 6)
    private String countyName;//区县名称

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(String countyCode) {
        this.countyCode = countyCode;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }
}
