package com.sargeraswang.util.ExcelUtil.occupation;

import com.sargeraswang.util.ExcelUtil.occupation.dto.SortMap;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.ArrayList;
import java.util.List;

public class OccupationGeneratorFuXingBDX extends FourLevelOccupationGenerator {

    public OccupationGeneratorFuXingBDX(int start, int end) {
        super(start, end);
    }

    @Override
    protected SortMap<String, SortMap<String, SortMap<String, List<String>>>> occupationOfFourLevel(Sheet sheet) {
        SortMap<String, SortMap<String, SortMap<String, List<String>>>> bigMap = new SortMap<>();

        String oneCodeTemp = "";
        String twoCodeTemp = "";
        String threeCodeTemp = "";

        for (int i = startRowNum; i <= endRowNum; i++) {

            Cell cell0 = sheet.getRow(i - 1).getCell(0);
            Cell cell1 = sheet.getRow(i - 1).getCell(1);
            Cell cell2 = sheet.getRow(i - 1).getCell(2);
            Cell cell3 = sheet.getRow(i - 1).getCell(3);
            Cell cell4 = sheet.getRow(i - 1).getCell(4);
            Cell cell5 = sheet.getRow(i - 1).getCell(5);


            String actualCode = stringValue(cell0);
            String jobCode = cell1.getStringCellValue();
            String oneValue = cell2.getStringCellValue();
            String twoValue = cell3.getStringCellValue();
            String threeValue = cell4.getStringCellValue();
            String fourValue = cell5.getStringCellValue();

            logger.info("当前行为:{},解析的值分别为:{}|{}|{}|{}|{}|{}", i, actualCode, jobCode, oneValue, twoValue, threeValue, fourValue);

            if (notNull(oneValue)) {
                oneCodeTemp = jobCode.trim().substring(0, 1);
                SortMap<String, SortMap<String, List<String>>> secondMap = bigMap.get(oneCodeTemp + "#" + oneValue);
                if (secondMap == null) {
                    secondMap = new SortMap<>();
                    bigMap.put(oneCodeTemp + "#" + oneValue, secondMap);
                }
            }

            if (notNull(twoValue)) {
                twoCodeTemp = jobCode.trim().substring(0, 3);

                SortMap<String, SortMap<String, List<String>>> secondMap = bigMap.get(oneCodeTemp + "#" + oneValue);
                SortMap<String, List<String>> thirdMap = secondMap.get(twoCodeTemp + "#" + twoValue);
                if (thirdMap == null) {
                    thirdMap = new SortMap<>();
                    secondMap.put(twoCodeTemp + "#" + twoValue, thirdMap);
                }

            }

            if (notNull(threeValue)) {
                threeCodeTemp = jobCode.trim().substring(0, 5);

                SortMap<String, SortMap<String, List<String>>> secondMap = bigMap.get(oneCodeTemp + "#" + oneValue);
                SortMap<String, List<String>> thirdMap = secondMap.get(twoCodeTemp + "#" + twoValue);
                List<String> smallList = thirdMap.get(threeCodeTemp + "#" + threeValue);
                if (smallList == null) {
                    smallList = new ArrayList<>();
                    thirdMap.put(threeCodeTemp + "#" + threeValue, smallList);
                }
            }

            SortMap<String, SortMap<String, List<String>>> secondMap = bigMap.get(oneCodeTemp + "#" + oneValue);
            SortMap<String, List<String>> thirdMap = secondMap.get(twoCodeTemp + "#" + twoValue);
            List<String> smallList = thirdMap.get(threeCodeTemp + "#" + threeValue);

            if (notNull(actualCode))
                actualCode = Double.valueOf(actualCode.trim()).intValue() + "";

            smallList.add(actualCode + "#" + fourValue);

        }
        return bigMap;

    }


}
