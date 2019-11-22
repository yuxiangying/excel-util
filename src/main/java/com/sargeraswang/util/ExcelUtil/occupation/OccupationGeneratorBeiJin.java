package com.sargeraswang.util.ExcelUtil.occupation;

import com.sargeraswang.util.ExcelUtil.occupation.dto.SortMap;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class OccupationGeneratorBeiJin extends FourLevelOccupationGenerator {

    public OccupationGeneratorBeiJin(int start, int end) {
        super(start, end);
    }

    @Override
    protected SortMap<String, SortMap<String, SortMap<String, List<String>>>> occupationOfFourLevel(Sheet sheet) {
        SortMap<String, SortMap<String, SortMap<String, List<String>>>> bigMap = new SortMap<>();

        String oneValueTemp = null;
        String oneCodeTemp = null;
        String twoValueTemp = null;
        String twoCodeTemp = null;
        String threeValueTemp = null;
        String threeCodeTemp = null;

        for (int i = startRowNum; i <= endRowNum; i++) {

            Cell cell1 = sheet.getRow(i - 1).getCell(0);
            Cell cell2 = sheet.getRow(i - 1).getCell(1);
            Cell cell3 = sheet.getRow(i - 1).getCell(2);
            Cell cell4 = sheet.getRow(i - 1).getCell(3);
            Cell cell5 = sheet.getRow(i - 1).getCell(4);


            String oneValue = cell1.getStringCellValue();
            String twoValue = cell2.getStringCellValue();
            String threeValue = cell3.getStringCellValue();
            String fourCode = stringValue(cell4);
            String fourValue = cell5.getStringCellValue();

            logger.info("当前行为:{},解析的值分别为:{}|{}|{}|{}|{}", i, oneValue, twoValue, threeValue, fourCode, fourValue);

            if (notNull(oneValue)) {
                oneCodeTemp = oneValue.trim().substring(0, 1);
                oneValueTemp = oneValue.trim().substring(1);
                SortMap<String, SortMap<String, List<String>>> secondMap = bigMap.get(oneCodeTemp + "#" + oneValueTemp);
                if (secondMap == null) {
                    secondMap = new SortMap<>();
                    bigMap.put(oneCodeTemp + "#" + oneValueTemp, secondMap);
                }
            }

            if (notNull(twoValue)) {
                twoCodeTemp = twoValue.trim().substring(0, 3);
                twoValueTemp = twoValue.trim().substring(3);

                SortMap<String, SortMap<String, List<String>>> secondMap = bigMap.get(oneCodeTemp + "#" + oneValueTemp);
                SortMap<String, List<String>> thirdMap = secondMap.get(twoCodeTemp + "#" + twoValueTemp);
                if (thirdMap == null) {
                    thirdMap = new SortMap<>();
                    secondMap.put(twoCodeTemp + "#" + twoValueTemp, thirdMap);
                }
            }

            if (notNull(threeValue)) {
                threeCodeTemp = threeValue.trim().substring(0, 5);
                threeValueTemp = threeValue.trim().substring(5);

                SortMap<String, SortMap<String, List<String>>> secondMap = bigMap.get(oneCodeTemp + "#" + oneValueTemp);
                Map<String, List<String>> thirdMap = secondMap.get(twoCodeTemp + "#" + twoValueTemp);
                List<String> smallList = thirdMap.get(threeCodeTemp + "#" + threeValueTemp);
                if (smallList == null) {
                    smallList = new ArrayList<>();
                    thirdMap.put(threeCodeTemp + "#" + threeValueTemp, smallList);
                }
            }

            SortMap<String, SortMap<String, List<String>>> secondMap = bigMap.get(oneCodeTemp + "#" + oneValueTemp);
            Map<String, List<String>> thirdMap = secondMap.get(twoCodeTemp + "#" + twoValueTemp);
            List<String> smallList = thirdMap.get(threeCodeTemp + "#" + threeValueTemp);

            String fourValueTemp = fourValue;

            smallList.add(fourCode.trim() + "#" + fourValueTemp);

        }
        return bigMap;
    }
}
