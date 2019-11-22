package com.sargeraswang.util.ExcelUtil.occupation;

import com.sargeraswang.util.ExcelUtil.occupation.dto.SortMap;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.ArrayList;
import java.util.List;


public class OccupationGeneratorWangJin extends ThreeLevelOccupationGenerator {

    public OccupationGeneratorWangJin(int start, int end) {
        super(start, end);
    }

    @Override
    protected SortMap<String, SortMap<String, List<String>>> occupationOfThreeLevel(Sheet sheet) {
        SortMap<String, SortMap<String, List<String>>> bigMap = new SortMap<>();

        SortMap<String, String> originMap = new SortMap<>();

        for (int i = startRowNum; i <= endRowNum; i++) {

            Cell cell1 = sheet.getRow(i - 1).getCell(0);
            Cell cell2 = sheet.getRow(i - 1).getCell(1);
            String code = cell1.getStringCellValue();
            String value = cell2.getStringCellValue();

            logger.info("当前行为:{},解析的值分别为:{}|{}", i, code, value);

            originMap.put(code, value);


            if (code != null && code.length() == 6) {
                SortMap<String, List<String>> midMap = new SortMap<>();
                bigMap.put(code + "#" + value, midMap);
            }

            if (code != null && code.length() == 8) {
                String pCode = code.substring(0, 6);
                String pValue = originMap.get(pCode);
                SortMap<String, List<String>> midMap = bigMap.get(pCode + "#" + pValue);
                List<String> threeList = midMap.get(code + "#" + value);
                if (threeList == null) {
                    threeList = new ArrayList<>();
                    midMap.put(code + "#" + value, threeList);
                }
            }

            if (code != null && code.length() == 10) {
                String ppCode = code.substring(0, 6);
                String ppValue = originMap.get(ppCode);
                SortMap<String, List<String>> midMap = bigMap.get(ppCode + "#" + ppValue);

                String pCode = code.substring(0, 8);
                String pValue = originMap.get(pCode);
                List<String> threeList = midMap.get(pCode + "#" + pValue);
                threeList.add(code + "#" + value);
            }

        }
        return bigMap;
    }
}
