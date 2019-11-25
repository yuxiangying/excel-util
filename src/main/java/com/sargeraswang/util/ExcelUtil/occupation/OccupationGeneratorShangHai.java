package com.sargeraswang.util.ExcelUtil.occupation;

import com.sargeraswang.util.ExcelUtil.occupation.dto.SortMap;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class OccupationGeneratorShangHai extends ThreeLevelOccupationGenerator {

    public OccupationGeneratorShangHai(int start, int end) {
        super(start, end);
    }

    @Override
    protected SortMap<String, SortMap<String, List<String>>> occupationOfThreeLevel(Sheet sheet) {
        SortMap<String, SortMap<String, List<String>>> bigMap = new SortMap<>();

        String oneValueTemp = "";
        String twoValueTemp = "";

        int oneNumber = 0;
        int twoNumber = 0;
        String oneCode = "";
        String twoCode = "";
        for (int i = startRowNum - 1; i < endRowNum; i++) {
            Cell cell1 = sheet.getRow(i).getCell(0);
            Cell cell2 = sheet.getRow(i).getCell(1);
            Cell cell3 = sheet.getRow(i).getCell(2);
            Cell cell4 = sheet.getRow(i).getCell(3);

            String oneValue = cell1.getStringCellValue();
            String twoValue = cell2.getStringCellValue();
            String threeCode = cell3.getStringCellValue();
            String threeValue = cell4.getStringCellValue();

            logger.info("当前行为:{},解析的值分别为:{}|{}|{}|{}", i, oneValue, twoValue, threeCode, threeValue);

            if (notNull(oneValue)) {
                oneCode = String.format("%02d", oneNumber);
                oneValueTemp = oneValue;
                SortMap<String, List<String>> midMap = new SortMap<>();
                bigMap.put(oneCode + "#" + oneValueTemp, midMap);


                oneNumber++;
            }

            if (notNull(twoValue)) {
                twoCode = String.format("%02d", twoNumber);
                twoValueTemp = twoValue;
                Map<String, List<String>> midMapTemp = bigMap.get(oneCode + "#" + oneValueTemp);

                List<String> list = new ArrayList<>();
                midMapTemp.put(twoCode + "#" + twoValueTemp, list);


                twoNumber++;
            }

            SortMap<String, List<String>> midMapTemp = bigMap.get(oneCode + "#" + oneValueTemp);

            List<String> listTemp = midMapTemp.get(twoCode + "#" + twoValueTemp);
            listTemp.add(threeCode + "#" + threeValue);
        }
        return bigMap;
    }
}
