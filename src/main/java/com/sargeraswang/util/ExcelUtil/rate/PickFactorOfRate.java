package com.sargeraswang.util.ExcelUtil.rate;

import com.alibaba.fastjson.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.*;

import java.io.InputStream;
import java.util.*;

public class PickFactorOfRate {


    private static int startRowNum;
    private static int endRowNum;
    private static int ageColumnNum;
    private Map<Integer, String> columnMap;

    public PickFactorOfRate(RateDescConfig config) {
        startRowNum = config.getStartRowNum();
        endRowNum = config.getEndRowNum();
        ageColumnNum = config.getAgeColumnNum();
        this.columnMap = config.getColDescMap();
        System.out.println("==>" + JSONObject.toJSONString(columnMap));

    }

    private static Object getCellValue(Cell cell) {
        if (cell == null
                || (cell.getCellTypeEnum() == CellType.STRING && isBlank(cell
                .getStringCellValue()))) {
            return null;
        }
        CellType cellType = cell.getCellTypeEnum();
        if (cellType == CellType.BLANK)
            return null;
        else if (cellType == CellType.BOOLEAN)
            return cell.getBooleanCellValue();
        else if (cellType == CellType.ERROR)
            return cell.getErrorCellValue();
        else if (cellType == CellType.FORMULA) {
            try {
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue();
                } else {
                    double numericCellValue = cell.getNumericCellValue();
                    return cell.getNumericCellValue();
                }
            } catch (IllegalStateException e) {
                return cell.getRichStringCellValue();
            }
        } else if (cellType == CellType.NUMERIC) {
            if (DateUtil.isCellDateFormatted(cell)) {
                return cell.getDateCellValue();
            } else {
                return cell.getNumericCellValue();
            }
        } else if (cellType == CellType.STRING)
            return cell.getStringCellValue();
        else
            return null;
    }

    private static boolean isBlank(String str) {
        if (str == null) {
            return true;
        }
        return str.length() == 0;
    }

    public Map<String, List<String>> parseRate(InputStream inputStream) {
        Workbook workBook = null;
        try {
            workBook = WorkbookFactory.create(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (workBook == null) {
            return null;
        }
        Sheet sheet = workBook.getSheetAt(0);

        Map<String, List<String>> rateMap = new HashMap<>();

        for (int i = startRowNum - 1; i < endRowNum; i++) {
            Set<Integer> colNumSet = columnMap.keySet();

            Row row = sheet.getRow(i);
            System.out.println("当前行为：" + i);

            Cell ageCell = row.getCell(ageColumnNum - 1);
            String age = getStringValue(ageCell);

            Map<String, String> rowMap = new HashMap<>();
            for (Integer colNum : colNumSet) {
                String value = getStringValue(row.getCell(colNum - 1));
                if ("NA".equals(value)) {
                    System.err.println("不支持的数据类型,行号和列号分别为:[" + (startRowNum + 1) + "," + colNum + "]");
                }
                rowMap.put(columnMap.get(colNum), value);
            }
            String factorJson = JSONObject.toJSONString(rowMap);

            List<String> ageList = rateMap.get(factorJson);
            if (ageList == null) {
                ageList = new ArrayList<>();
            }
            ageList.add(age);
            rateMap.put(factorJson, ageList);

        }
        return rateMap;


    }

    public String getFactorOfRage(Map<String, List<String>> rateMap) {

        Set<String> keySet = rateMap.keySet();
        List<String> list = new ArrayList<>();
        Map<String, List<String>> list2 = new HashMap<>();

        for (String key : keySet) {
            List<String> ageList = rateMap.get(key);
            String agePeriod = getAgePeriod(ageList);
            Map<String, String> map = (Map<String, String>) JSONObject.parse(key);


            map.put("age", agePeriod);
            list.add(JSONObject.toJSONString(map));
        }

        return JSONObject.toJSONString(list);
    }

    private String getStringValue(Cell cell) {
        String value = null;
        Object cellValue = getCellValue(cell);
        if (cellValue instanceof String) {
            value = (String) cellValue;

        } else if (cellValue instanceof Double) {
            value = Double.toString((Double) cellValue);
        } else {

            value = "NA";
        }

        return value;

    }

    private String getAgePeriod(List<String> ageList) {
        Double minAge = 0.0;
        Double maxAge = 0.0;
        if (ageList == null && ageList.size() == 0) {
            return "NA";
        }
        for (String ageStr : ageList) {

            try {
                Double age = Double.valueOf(ageStr);
                maxAge = age > maxAge ? age : minAge;
                minAge = age < minAge ? age : minAge;

            } catch (Exception e) {
                System.out.println("age 非数字类型，待处理！"); //TODO 可能是 1-5 这种类型
            }
        }
        return minAge + "~" + maxAge;
    }


}
