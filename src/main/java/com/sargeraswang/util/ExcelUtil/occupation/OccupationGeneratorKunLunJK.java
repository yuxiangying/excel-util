package com.sargeraswang.util.ExcelUtil.occupation;

import com.sargeraswang.util.ExcelUtil.occupation.dto.SortMap;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OccupationGeneratorKunLunJK extends ThreeLevelOccupationGenerator {

    public OccupationGeneratorKunLunJK(int start, int end) {
        super(start, end);
    }

    @Override
    protected SortMap<String, SortMap<String, List<String>>> occupationOfThreeLevel(Sheet sheet) {
        SortMap<String, SortMap<String, List<String>>> bigMap = new SortMap<>();

        String oneValueTemp = null;
        String oneCodeTemp = "";
        String twoValueTemp = null;
        String twoCodeTemp = "";

        for (int i = startRowNum; i <= endRowNum; i++) {

            Cell cell1 = sheet.getRow(i - 1).getCell(0);
            Cell cell2 = sheet.getRow(i - 1).getCell(1);
            Cell cell3 = sheet.getRow(i - 1).getCell(5);
            Cell cell4 = sheet.getRow(i - 1).getCell(2);

            String oneValue = cell1.getStringCellValue();
            String twoValue = cell2.getStringCellValue();
            String threeCode = cell3.getStringCellValue();
            String threeValue = cell4.getStringCellValue();

            logger.info("当前行为:{},解析的值分别为:{}|{}|{}|{}", i, oneValue, twoValue, threeCode, threeValue);


            if (notNull(oneValue)) {
                oneValueTemp = oneValue.substring(0, oneValue.length() - 2);
                oneCodeTemp = oneValue.substring(oneValue.length() - 2);
                SortMap<String, List<String>> midMap = new SortMap<>();
                bigMap.put(oneCodeTemp + "#" + oneValueTemp, midMap);
            }

            if (notNull(twoValue)) {

                twoValueTemp = twoValue.substring(0, twoValue.length() - 2);
                twoCodeTemp = twoValue.substring(twoValue.length() - 2);
                SortMap<String, List<String>> midMapTemp = bigMap.get(oneCodeTemp + "#" + oneValueTemp);

                List<String> list = new ArrayList<>();
                midMapTemp.put(twoCodeTemp + "#" + twoValueTemp, list);
            }

            Map<String, List<String>> midMapTemp = bigMap.get(oneCodeTemp + "#" + oneValueTemp);

            List<String> listTemp = midMapTemp.get(twoCodeTemp + "#" + twoValueTemp);
            listTemp.add(threeCode + "#" + threeValue);


        }
        return bigMap;
    }
}
