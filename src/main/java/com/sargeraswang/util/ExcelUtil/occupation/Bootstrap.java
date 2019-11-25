package com.sargeraswang.util.ExcelUtil.occupation;

import java.io.InputStream;

public class Bootstrap {

    public static void main(String[] args) {

        int insurerCode = GeneratorFactory.INSURER_SHANGHAI;

        OccupationGenerator generator = GeneratorFactory.getInstance(insurerCode);
        InputStream inputStream = GeneratorFactory.getOccupationFile(insurerCode);
        String occupationJson = generator.toJson(inputStream);

        String fileName = GeneratorFactory.getOccupationFileName(insurerCode);
        boolean flag = generator.createJsonFile(occupationJson, "D:/java/guohua/ExcelUtil/excel/json", fileName);

        System.out.println(occupationJson);


    }
}
