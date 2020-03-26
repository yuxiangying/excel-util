package com.sargeraswang.util.ExcelUtil.area;

import com.sargeraswang.util.ExcelUtil.oragin.ExcelLogs;
import com.sargeraswang.util.ExcelUtil.oragin.ExcelUtil;

import java.io.*;
import java.util.*;

/**
 * @author yuxy_cyd
 * @version v-1.1.0
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @date: 2019年12月12日 11:37
 **/
public class PickFactorOfArea {

    public static AreaCode parseSheet(String fileTemplatePath) throws FileNotFoundException {
        File f = new File(fileTemplatePath);
        InputStream inputStream = new FileInputStream(f);

        ExcelLogs logs = new ExcelLogs();
        Collection<com.sargeraswang.util.ExcelUtil.area.AreaExcel> importExcel = ExcelUtil.importExcel(com.sargeraswang.util.ExcelUtil.area.AreaExcel.class, inputStream, "yyyy/MM/dd HH:mm:ss", logs, 0);

        AreaCode areaCode = new AreaCode();
        areaCode.setCode(0);

        for (AreaExcel areaExcel : importExcel) {
            AreaCode.CountyCode countyCode = new AreaCode.CountyCode();
            countyCode.setValue(areaExcel.getCountyCode().trim());
            countyCode.setText(areaExcel.getCountyName().trim());

            boolean provinceFlag = false;
            boolean cityFlag = false;

            for (AreaCode.ProvinceCode provinceCode : areaCode.getContent().get(0)) {
                if (provinceCode.getValue().equals(areaExcel.getProvinceCode().trim())) {
                    provinceFlag = true;
                    for (AreaCode.CityCode cityCode : provinceCode.getChildren()) {
                        if (cityCode.getValue().equals(areaExcel.getCityCode().trim())) {
                            cityFlag = true;
                            cityCode.getChildren().add(countyCode);
                            break;
                        }
                    }
                    if (!cityFlag) {
                        AreaCode.CityCode cityCode = new AreaCode.CityCode();
                        cityCode.setValue(areaExcel.getCityCode().trim());
                        cityCode.setText(areaExcel.getCityName().trim());
                        cityCode.getChildren().add(countyCode);

                        provinceCode.getChildren().add(cityCode);

                    }
                    break;
                }
            }
            if (!provinceFlag) {
                AreaCode.ProvinceCode provinceCode = new AreaCode.ProvinceCode();
                provinceCode.setValue(areaExcel.getProvinceCode().trim());
                provinceCode.setText(areaExcel.getProvinceName().trim());

                AreaCode.CityCode cityCode = new AreaCode.CityCode();
                cityCode.setValue(areaExcel.getCityCode().trim());
                cityCode.setText(areaExcel.getCityName().trim());
                cityCode.getChildren().add(countyCode);

                provinceCode.getChildren().add(cityCode);

                areaCode.getContent().get(0).add(provinceCode);
            }
        }
        return areaCode;
    }

}
