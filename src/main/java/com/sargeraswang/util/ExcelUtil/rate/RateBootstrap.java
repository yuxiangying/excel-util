package com.sargeraswang.util.ExcelUtil.rate;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class RateBootstrap {

    public static void main(String[] args) {
        String excelPath = "/Users/wangpeng/Desktop/费率/信泰费率/百万守护B重大疾病rate.xlsx";
        String columnDesc = "sex:2,InsPeriod:3,PayPeriod:4";
        RateDescConfig config = new RateDescConfig(1, 3, 796, columnDesc);


        try {
            PickFactorOfRate rate = new PickFactorOfRate(config);
            File f = new File(excelPath);

            InputStream inputStream = new FileInputStream(f);

            Map<String, List<String>> rateMap = rate.parseRate(inputStream);
            String factorOfRage = rate.getFactorOfRage(rateMap);

            System.out.println(factorOfRage);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
