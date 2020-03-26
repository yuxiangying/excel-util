package com.sargeraswang.util.ExcelUtil.occupation;

import com.sargeraswang.util.ExcelUtil.util.JsonUtil;

import java.io.File;
import java.io.InputStream;

public class Bootstrap {

    public static void main(String[] args) {

        int insurerCode = GeneratorFactory.INSURER_HZ;

        OccupationGenerator generator = GeneratorFactory.getInstance(insurerCode);
        InputStream inputStream = GeneratorFactory.getOccupationFile(insurerCode);
        String occupationJson = generator.toJson(inputStream);
        System.out.println(occupationJson);
        String fileName = GeneratorFactory.getOccupationFileName(insurerCode);

        String outFilePath = "C:/ideaProject/guohua/excel-util/excel/json" + File.separator + fileName + ".json";
        boolean flag = JsonUtil.createJsonFile(occupationJson, outFilePath);
    }
}
