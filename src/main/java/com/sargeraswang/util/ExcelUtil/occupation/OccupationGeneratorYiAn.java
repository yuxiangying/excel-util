package com.sargeraswang.util.ExcelUtil.occupation;

import com.sargeraswang.util.ExcelUtil.occupation.dto.SortMap;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.ArrayList;
import java.util.List;


public class OccupationGeneratorYiAn extends ThreeLevelOccupationGenerator {

    public OccupationGeneratorYiAn(int start, int end) {
        super(start, end);
    }

    @Override
    protected SortMap<String, SortMap<String, List<String>>> occupationOfThreeLevel(Sheet sheet) {

        SortMap<String, SortMap<String, List<String>>> bigMap = new SortMap<>();

        for (int i = startRowNum; i <= endRowNum; i++) {

            Cell cell1 = sheet.getRow(i - 1).getCell(0);
            Cell cell2 = sheet.getRow(i - 1).getCell(1);
            Cell cell3 = sheet.getRow(i - 1).getCell(2);
            Cell cell4 = sheet.getRow(i - 1).getCell(3);
            Cell cell5 = sheet.getRow(i - 1).getCell(4);
            Cell cell6 = sheet.getRow(i - 1).getCell(5);

            String oneCode = cell1.getStringCellValue();
            String oneValue = cell2.getStringCellValue();
            String twoCode = cell3.getStringCellValue();
            String twoValue = cell4.getStringCellValue();
            String threeCode = cell5.getStringCellValue();
            String threeValue = cell6.getStringCellValue();

            logger.info("当前行为:{},解析的值分别为:{}|{}|{}|{}|{}", i, oneCode, oneValue, twoCode, twoValue, threeCode, threeValue);

            if (notNull(oneValue)) {
                SortMap<String, List<String>> midMap = bigMap.get(oneCode + "#" + oneValue);
                if (midMap == null) {
                    midMap = new SortMap<>();
                    bigMap.put(oneCode + "#" + oneValue, midMap);
                }
            }

            if (notNull(twoValue)) {
                SortMap<String, List<String>> midMapTemp = bigMap.get(oneCode + "#" + oneValue);
                List<String> thirdList = midMapTemp.get(twoCode + "#" + twoValue);

                if (thirdList == null) {
                    thirdList = new ArrayList<>();
                    midMapTemp.put(twoCode + "#" + twoValue, thirdList);
                }
            }

            SortMap<String, List<String>> midMapTemp = bigMap.get(oneCode + "#" + oneValue);

            List<String> listTemp = midMapTemp.get(twoCode + "#" + twoValue);
            listTemp.add(threeCode + "#" + threeValue);

        }
        return bigMap;
    }
}
