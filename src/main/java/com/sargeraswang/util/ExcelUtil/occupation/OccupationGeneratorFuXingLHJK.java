package com.sargeraswang.util.ExcelUtil.occupation;

import com.sargeraswang.util.ExcelUtil.occupation.dto.SortMap;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.ArrayList;
import java.util.List;

public class OccupationGeneratorFuXingLHJK extends ThreeLevelOccupationGenerator {

    private final static int JOB_TYPE_FILTER_ZJ = 7;

    private final static int JOB_TYPE_FILTER_YW = 7;


    public OccupationGeneratorFuXingLHJK(int start, int end) {
        super(start, end);
    }


    @Override
    protected SortMap<String, SortMap<String, List<String>>> occupationOfThreeLevel(Sheet sheet) {
        SortMap<String, SortMap<String, List<String>>> bigMap = new SortMap<>();

        List<String> escapeList = new ArrayList<>();


        String oneValueTemp = null;
        String oneCodeTemp = null;
        String twoValueTemp = null;
        String twoCodeTemp = null;

        for (int i = startRowNum; i <= endRowNum; i++) {

            Cell cell1 = sheet.getRow(i - 1).getCell(0);
            Cell cell2 = sheet.getRow(i - 1).getCell(1);
            Cell cell3 = sheet.getRow(i - 1).getCell(2);
            Cell cell4 = sheet.getRow(i - 1).getCell(3);
            Cell cell5 = sheet.getRow(i - 1).getCell(4);
            Cell cell6 = sheet.getRow(i - 1).getCell(5);

            String oneValue = cell1.getStringCellValue();
            String twoValue = cell2.getStringCellValue();
            String threeCode = cell3.getStringCellValue();
            String threeValue = cell4.getStringCellValue();

            String jobTypeOfZJ = stringValue(cell5);
            int jobTypeOfZJInt = -1;
            if (notNull(jobTypeOfZJ))
                jobTypeOfZJInt = Double.valueOf(jobTypeOfZJ).intValue();

            String jobTypeOfYW = stringValue(cell6);
            int jobTypeOfYWInt = -1;
            if (notNull(jobTypeOfYW))
                jobTypeOfYWInt = Double.valueOf(jobTypeOfYW).intValue();


            logger.info("当前行为:{},解析的值分别为:{}|{}|{}|{}|{}|{}", i, oneValue, twoValue, threeCode, threeValue, jobTypeOfZJ, jobTypeOfYW);


            if (notNull(oneValue)) {
                oneValueTemp = oneValue.substring(2);
                oneCodeTemp = oneValue.substring(0, 2);
                SortMap<String, List<String>> midMap = new SortMap<>();
                bigMap.put(oneCodeTemp + "#" + oneValueTemp, midMap);
            }

            if (notNull(twoValue)) {

                twoValueTemp = twoValue.substring(4);
                twoCodeTemp = twoValue.substring(0, 4);
                SortMap<String, List<String>> midMapTemp = bigMap.get(oneCodeTemp + "#" + oneValueTemp);
                List<String> list = new ArrayList<>();
                midMapTemp.put(twoCodeTemp + "#" + twoValueTemp, list);
            }

            SortMap<String, List<String>> midMapTemp = bigMap.get(oneCodeTemp + "#" + oneValueTemp);

            List<String> listTemp = midMapTemp.get(twoCodeTemp + "#" + twoValueTemp);
            listTemp.add(threeCode + "#" + threeValue);


            if (jobTypeOfZJInt > JOB_TYPE_FILTER_ZJ || jobTypeOfYWInt > JOB_TYPE_FILTER_YW) { //四类以上的职业不提取
                escapeList.add(threeCode + "#" + threeValue);
                continue;
            }

        }

        return bigMap;
    }
}
