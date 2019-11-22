package com.sargeraswang.util.ExcelUtil.occupation;

import java.io.InputStream;

public class Bootstrap {

    public static void main(String[] args) {

        int insurerCode = GeneratorFactory.INSURER_FUXINGLHJK;

        OccupationGenerator generator = GeneratorFactory.getInstance(insurerCode);
        InputStream inputStream = GeneratorFactory.getOccupationFile(insurerCode);
        String occupationJson = generator.toJson(inputStream);
        System.out.println(occupationJson);


    }
}
