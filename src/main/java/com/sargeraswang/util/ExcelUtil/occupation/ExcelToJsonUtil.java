package com.sargeraswang.util.ExcelUtil.occupation;

import com.alibaba.fastjson.JSONObject;
import com.sargeraswang.util.ExcelUtil.occupation.dto.OccupationDTO;
import com.sargeraswang.util.ExcelUtil.occupation.dto.Option;
import com.sargeraswang.util.ExcelUtil.occupation.dto.SortMap;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

public class ExcelToJsonUtil {

    static int startRowNum = 1;
    static int endRowNum = 510;


    public static SortMap<String, List<String>> parseExcelOfHK(InputStream inputStream) {
        SortMap<String, List<String>> bigMap = new SortMap<>();

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

        String oneValueTemp = null;
        int oneCodeTemp = 0;


        for (int i = startRowNum; i < endRowNum; i++) {
            Row row = sheet.getRow(i);
            Cell cell1 = row.getCell(1);
            Cell cell2 = row.getCell(2);
            Cell cell3 = row.getCell(3);

            System.out.println("行数为+" + i);

            String oneValue = cell1.getStringCellValue();
            String twoValue = cell2.getStringCellValue();
            String twoCode = cell3.getStringCellValue();

            if (notNull(oneValue)) {
                oneValueTemp = oneValue;
                oneCodeTemp = ++oneCodeTemp;

                List<String> listTemp = bigMap.get(oneCodeTemp + "#" + oneValueTemp);

                if (listTemp == null) {
                    listTemp = new ArrayList<>();
                    bigMap.put(oneCodeTemp + "#" + oneValueTemp, listTemp);
                }

            }

            List<String> listTemp = bigMap.get(oneCodeTemp + "#" + oneValueTemp);
            listTemp.add(twoCode + "#" + twoValue);
        }

        return bigMap;


    }

    public static Map<String, Map<String, List<String>>> parseExcelOfHKRS(InputStream inputStream) {

        Map<String, Map<String, List<String>>> bigMap = new HashMap<>();

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

        String oneValueTemp = null;
        String oneCodeTemp = "";
        String twoValueTemp = null;
        String twoCodeTemp = "";
        String threeValueTemp = null;
        String threeCodeTemp = "";

        for (int i = startRowNum; i < endRowNum; i++) {
            Row row = sheet.getRow(i);
            Cell cell1 = row.getCell(0);
            Cell cell2 = row.getCell(1);
            Cell cell3 = row.getCell(2);
            Cell cell4 = row.getCell(3);

            System.out.println("行数为+" + i);

            String oneValue = cell1.getStringCellValue();
            String twoValue = cell2.getStringCellValue();
            String threeValue = cell3.getStringCellValue();
            cell4.setCellType(CellType.STRING);
            String threeCode = cell4.getStringCellValue();

            if (notNull(oneValue)) {
                oneValueTemp = oneValue;
                oneCodeTemp = threeCode.trim().substring(0, 2);
                Map<String, List<String>> secondMap = bigMap.get(oneCodeTemp + "#" + oneValueTemp);
                if (secondMap == null) {
                    secondMap = new HashMap<>();
                    bigMap.put(oneCodeTemp + "#" + oneValueTemp, secondMap);
                }

            }

            if (notNull(twoValue)) {
                twoValueTemp = twoValue;
                twoCodeTemp = threeCode.trim().substring(0, 4);

                Map<String, List<String>> midMapTemp = bigMap.get(oneCodeTemp + "#" + oneValueTemp);

                List<String> list = new ArrayList<>();
                midMapTemp.put(twoCodeTemp + "#" + twoValueTemp, list);

            }

            Map<String, List<String>> midMapTemp = bigMap.get(oneCodeTemp + "#" + oneValueTemp);

            List<String> listTemp = midMapTemp.get(twoCodeTemp + "#" + twoValueTemp);
            listTemp.add(threeCode + "#" + threeValue);

        }
        return bigMap;
    }

    public static Map<String, Map<String, Map<String, List<String>>>> parseExcelOfBJRS(InputStream inputStream) {

        Map<String, Map<String, Map<String, List<String>>>> bigMap = new HashMap<>();

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

        String oneValueTemp = null;
        String oneCodeTemp = "";
        String twoValueTemp = null;
        String twoCodeTemp = "";
        String threeValueTemp = null;
        String threeCodeTemp = "";

        for (int i = startRowNum; i < endRowNum; i++) {

            if (i == 1994) {
                System.out.println(1111);
            }
            Row row = sheet.getRow(i);
            Cell cell1 = row.getCell(0);
            Cell cell2 = row.getCell(1);
            Cell cell3 = row.getCell(2);
            Cell cell4 = row.getCell(3);
            Cell cell5 = row.getCell(4);

            System.out.println("行数为+" + i);

            String oneValue = cell1.getStringCellValue();
            String twoValue = cell2.getStringCellValue();
            String threeValue = cell3.getStringCellValue();
            cell4.setCellType(CellType.STRING);
            String fourCode = cell4.getStringCellValue();
            String fourValue = cell5.getStringCellValue();

            if (notNull(oneValue)) {
                oneCodeTemp = oneValue.trim().substring(0, 1);
                oneValueTemp = oneValue.trim().substring(1);
                Map<String, Map<String, List<String>>> secondMap = bigMap.get(oneCodeTemp + "#" + oneValueTemp);
                if (secondMap == null) {
                    secondMap = new HashMap<>();
                    bigMap.put(oneCodeTemp + "#" + oneValueTemp, secondMap);
                }

            }

            if (notNull(twoValue)) {
                twoCodeTemp = twoValue.trim().substring(0, 3);
                twoValueTemp = twoValue.trim().substring(3);

                Map<String, Map<String, List<String>>> secondMap = bigMap.get(oneCodeTemp + "#" + oneValueTemp);
                Map<String, List<String>> thirdMap = secondMap.get(twoCodeTemp + "#" + twoValueTemp);
                if (thirdMap == null) {
                    thirdMap = new HashMap<>();
                    secondMap.put(twoCodeTemp + "#" + twoValueTemp, thirdMap);
                }

            }

            if (notNull(threeValue)) {
                System.out.println("threeValue==>" + threeValue);
                threeCodeTemp = threeValue.trim().substring(0, 5);
                threeValueTemp = threeValue.trim().substring(5);

                Map<String, Map<String, List<String>>> secondMap = bigMap.get(oneCodeTemp + "#" + oneValueTemp);
                Map<String, List<String>> thirdMap = secondMap.get(twoCodeTemp + "#" + twoValueTemp);
                List<String> smallList = thirdMap.get(threeCodeTemp + "#" + threeValueTemp);
                if (smallList == null) {
                    smallList = new ArrayList<>();
                    thirdMap.put(threeCodeTemp + "#" + threeValueTemp, smallList);
                }
            }

            Map<String, Map<String, List<String>>> secondMap = bigMap.get(oneCodeTemp + "#" + oneValueTemp);
            Map<String, List<String>> thirdMap = secondMap.get(twoCodeTemp + "#" + twoValueTemp);
            List<String> smallList = thirdMap.get(threeCodeTemp + "#" + threeValueTemp);

            String fourValueTemp = fourValue;
            System.out.println("===>" + fourValueTemp);
        /*    if (fourValueTemp.indexOf("(旧)") != -1) {
                fourValueTemp = fourValueTemp.substring(0, fourValueTemp.indexOf("旧") - 2);
            }*/

            smallList.add(fourCode.trim() + "#" + fourValueTemp);

        }
        return bigMap;
    }

    public static Map<String, Map<String, Map<String, List<String>>>> parseExcelOfBDX(InputStream inputStream) {

        Map<String, Map<String, Map<String, List<String>>>> bigMap = new HashMap<>();

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

        String oneValueTemp = null;
        String oneCodeTemp = "";
        String twoValueTemp = null;
        String twoCodeTemp = "";
        String threeValueTemp = null;
        String threeCodeTemp = "";

        for (int i = startRowNum; i < endRowNum; i++) {
            Row row = sheet.getRow(i);

            Cell cell = row.getCell(1);
            Cell cell1 = row.getCell(2);
            Cell cell2 = row.getCell(3);
            Cell cell3 = row.getCell(4);
            Cell cell4 = row.getCell(5);
            Cell cell5 = row.getCell(6);

            System.out.println("行数为+" + i);

            cell.setCellType(CellType.STRING);
            String fourCode = cell.getStringCellValue();
            String oneValue = cell1.getStringCellValue();
            String twoValue = cell2.getStringCellValue();
            String threeValue = cell3.getStringCellValue();
            String fourValue = cell4.getStringCellValue();
            String fiveValue = cell5.getStringCellValue();

            if (notNull(oneValue)) {
                oneValueTemp = twoValue;
                oneCodeTemp = oneValue.trim().substring(0, 1);
                Map<String, Map<String, List<String>>> secondMap = bigMap.get(oneCodeTemp + "#" + oneValueTemp);
                if (secondMap == null) {
                    secondMap = new HashMap<>();
                    bigMap.put(oneCodeTemp + "#" + oneValueTemp, secondMap);
                }

            }

            if (notNull(twoValue)) {
                twoValueTemp = threeValue;
                twoCodeTemp = oneValue.trim().substring(0, 3);

                Map<String, Map<String, List<String>>> secondMap = bigMap.get(oneCodeTemp + "#" + oneValueTemp);
                Map<String, List<String>> thirdMap = secondMap.get(twoCodeTemp + "#" + twoValueTemp);
                if (thirdMap == null) {
                    thirdMap = new HashMap<>();
                    secondMap.put(twoCodeTemp + "#" + twoValueTemp, thirdMap);
                }

            }

            if (notNull(threeValue)) {
                System.out.println("threeValue==>" + threeValue);
                threeValueTemp = fourValue;
                threeCodeTemp = oneValue.trim().substring(0, 5);

                Map<String, Map<String, List<String>>> secondMap = bigMap.get(oneCodeTemp + "#" + oneValueTemp);
                Map<String, List<String>> thirdMap = secondMap.get(twoCodeTemp + "#" + twoValueTemp);
                List<String> smallList = thirdMap.get(threeCodeTemp + "#" + threeValueTemp);
                if (smallList == null) {
                    smallList = new ArrayList<>();
                    thirdMap.put(threeCodeTemp + "#" + threeValueTemp, smallList);
                }
            }

            Map<String, Map<String, List<String>>> secondMap = bigMap.get(oneCodeTemp + "#" + oneValueTemp);
            Map<String, List<String>> thirdMap = secondMap.get(twoCodeTemp + "#" + twoValueTemp);
            List<String> smallList = thirdMap.get(threeCodeTemp + "#" + threeValueTemp);

            String fourValueTemp = fiveValue;
            System.out.println("===>" + fourValueTemp);
        /*    if (fourValueTemp.indexOf("(旧)") != -1) {
                fourValueTemp = fourValueTemp.substring(0, fourValueTemp.indexOf("旧") - 2);
            }*/
            smallList.add(fourCode.trim() + "#" + fourValueTemp);

        }
        return bigMap;
    }


    public static Map<String, Map<String, Map<String, List<String>>>> parseExcelOfBaiNian(InputStream inputStream) {

        Map<String, Map<String, Map<String, List<String>>>> bigMap = new HashMap<>();

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

        String oneValueTemp = null;
        String oneCodeTemp = "";
        String twoValueTemp = null;
        String twoCodeTemp = "";
        String threeValueTemp = null;
        String threeCodeTemp = "";


        for (int i = startRowNum; i < endRowNum; i++) {
            Row row = sheet.getRow(i);
            Cell cell1 = row.getCell(0);
            Cell cell2 = row.getCell(1);
            Cell cell3 = row.getCell(2);
            Cell cell4 = row.getCell(3);

            System.out.println("行数为+" + i);


            String oneValue = cell1.getStringCellValue();
            String twoValue = cell2.getStringCellValue();
            String threeValue = cell3.getStringCellValue();
            String fourValue = cell4.getStringCellValue();


            if (notNull(oneValue)) {
                oneValueTemp = oneValue.trim().substring(1);
                oneCodeTemp = oneValue.trim().substring(0, 1);
                Map<String, Map<String, List<String>>> secondMap = bigMap.get(oneCodeTemp + "#" + oneValueTemp);
                if (secondMap == null) {
                    secondMap = new HashMap<>();
                    bigMap.put(oneCodeTemp + "#" + oneValueTemp, secondMap);
                }


            }

            if (notNull(twoValue)) {
                twoValueTemp = twoValue.trim().substring(3);
                twoCodeTemp = twoValue.trim().substring(0, 3);

                Map<String, Map<String, List<String>>> secondMap = bigMap.get(oneCodeTemp + "#" + oneValueTemp);
                Map<String, List<String>> thirdMap = secondMap.get(twoCodeTemp + "#" + twoValueTemp);
                if (thirdMap == null) {
                    thirdMap = new HashMap<>();
                    secondMap.put(twoCodeTemp + "#" + twoValueTemp, thirdMap);
                }


            }

            if (notNull(threeValue)) {
                System.out.println("threeValue==>" + threeValue);
                threeValueTemp = threeValue.trim().substring(5);
                threeCodeTemp = threeValue.trim().substring(0, 5);

                Map<String, Map<String, List<String>>> secondMap = bigMap.get(oneCodeTemp + "#" + oneValueTemp);
                Map<String, List<String>> thirdMap = secondMap.get(twoCodeTemp + "#" + twoValueTemp);
                List<String> smallList = thirdMap.get(threeCodeTemp + "#" + threeValueTemp);
                if (smallList == null) {
                    smallList = new ArrayList<>();
                    thirdMap.put(threeCodeTemp + "#" + threeValueTemp, smallList);
                }
            }


            Map<String, Map<String, List<String>>> secondMap = bigMap.get(oneCodeTemp + "#" + oneValueTemp);
            Map<String, List<String>> thirdMap = secondMap.get(twoCodeTemp + "#" + twoValueTemp);
            List<String> smallList = thirdMap.get(threeCodeTemp + "#" + threeValueTemp);

            String fourValueTemp = fourValue.trim().substring(7);
            System.out.println("===>" + fourValueTemp);
            if (fourValueTemp.indexOf("(旧)") != -1) {
                fourValueTemp = fourValueTemp.substring(0, fourValueTemp.indexOf("旧") - 2);
            }
            smallList.add(fourValue.trim().substring(0, 7) + "#" + fourValueTemp);

        }
        return bigMap;

    }


    public static Map<String, Map<String, List<String>>> parseExcelOfWangJin(InputStream inputStream) {

        Map<String, Map<String, List<String>>> bigMap = new HashMap<>();

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


        Map<String, String> originMap = new HashMap<>();

        for (int i = startRowNum; i < endRowNum; i++) {
            Row row = sheet.getRow(i);
            Cell cell1 = row.getCell(0);
            Cell cell2 = row.getCell(1);


            String code = cell1.getStringCellValue();
            String value = cell2.getStringCellValue();
            originMap.put(code, value);

            System.out.println("行数为+" + i + "key&Value==>" + code + "#" + value);

            if (code != null && code.length() == 6) {
                Map<String, List<String>> midMap = new HashMap<>();
                bigMap.put(code + "#" + value, midMap);
            }

            if (code != null && code.length() == 8) {
                String pCode = code.substring(0, 6);
                String pValue = originMap.get(pCode);
                Map<String, List<String>> midMap = bigMap.get(pCode + "#" + pValue);
                List<String> threeList = midMap.get(code + "#" + value);
                if (threeList == null) {
                    threeList = new ArrayList<>();
                    midMap.put(code + "#" + value, threeList);
                }
            }

            if (code != null && code.length() == 10) {
                String ppCode = code.substring(0, 6);
                String ppValue = originMap.get(ppCode);
                Map<String, List<String>> midMap = bigMap.get(ppCode + "#" + ppValue);

                String pCode = code.substring(0, 8);
                String pValue = originMap.get(pCode);
                List<String> threeList = midMap.get(pCode + "#" + pValue);
                threeList.add(code + "#" + value);
            }

        }
        return bigMap;

    }


    public static Map<String, Map<String, List<String>>> parseExcelOfKunLun(InputStream inputStream) {

        Map<String, Map<String, List<String>>> bigMap = new HashMap<>();

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

        String oneValueTemp = null;
        String oneCodeTemp = "";
        int oneCodeInt = 1;
        String twoValueTemp = null;
        String twoCodeTemp = "";
        int twoCodeInt = 1;

        for (int i = startRowNum; i < endRowNum; i++) {
            Row row = sheet.getRow(i);
            Cell cell1 = row.getCell(0);
            Cell cell2 = row.getCell(1);
            Cell cell3 = row.getCell(5);
            Cell cell4 = row.getCell(2);

            System.out.println("行数为+" + i);
            String oneValue = cell1.getStringCellValue();
            System.out.println("oneValue==>" + oneValue);

            String oneCode = "";

            String twoValue = cell2.getStringCellValue();
            String twoCode = "";

            String threeValue = cell4.getStringCellValue();
            String threeCode = cell3.getStringCellValue();


            if (notNull(oneValue)) {
                oneValueTemp = oneValue.substring(0, oneValue.length() - 2);
                oneCodeTemp = oneValue.substring(oneValue.length() - 2);
                Map<String, List<String>> midMap = new HashMap<>();
                bigMap.put(oneCodeTemp + "#" + oneValueTemp, midMap);
            }

            if (notNull(twoValue)) {

                System.out.println("twoValue==>" + twoValue);
                twoValueTemp = twoValue.substring(0, twoValue.length() - 2);
                twoCodeTemp = twoValue.substring(twoValue.length() - 2);
                Map<String, List<String>> midMapTemp = bigMap.get(oneCodeTemp + "#" + oneValueTemp);

                List<String> list = new ArrayList<>();
                midMapTemp.put(twoCodeTemp + "#" + twoValueTemp, list);
            }


            Map<String, List<String>> midMapTemp = bigMap.get(oneCodeTemp + "#" + oneValueTemp);

            System.out.println("====>" + twoCodeTemp + "#" + twoValueTemp);
            List<String> listTemp = midMapTemp.get(twoCodeTemp + "#" + twoValueTemp);
            listTemp.add(threeCode + "#" + threeValue);


        }
        return bigMap;

    }

    public static Map<String, Map<String, List<String>>> parseExcelOfYiAn(InputStream inputStream) {

        Map<String, Map<String, List<String>>> bigMap = new HashMap<>();

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

        String oneValueTemp = null;
        String oneCodeTemp = "";
        int oneCodeInt = 1;
        String twoValueTemp = null;
        String twoCodeTemp = "";
        int twoCodeInt = 1;

        for (int i = startRowNum; i < endRowNum; i++) {
            Row row = sheet.getRow(i);
            Cell cell1 = row.getCell(0);
            Cell cell2 = row.getCell(1);
            Cell cell3 = row.getCell(2);
            Cell cell4 = row.getCell(3);
            Cell cell5 = row.getCell(4);
            Cell cell6 = row.getCell(5);

            System.out.println("行数为+" + i);
            String oneValue = cell2.getStringCellValue();
            String oneCode = cell1.getStringCellValue();
            System.out.println("oneValue==>" + oneValue);


            String twoValue = cell4.getStringCellValue();
            String twoCode = cell3.getStringCellValue();

            String threeValue = cell6.getStringCellValue();
            String threeCode = cell5.getStringCellValue();


            if (notNull(oneValue)) {
                oneValueTemp = oneValue;
                oneCodeTemp = oneCode;
                Map<String, List<String>> midMap = bigMap.get(oneCodeTemp + "#" + oneValueTemp);
                if (midMap == null) {
                    midMap = new HashMap<>();
                    bigMap.put(oneCodeTemp + "#" + oneValueTemp, midMap);
                }


            }

            if (notNull(twoValue)) {
                System.out.println("twoValue==>" + twoValue);
                twoValueTemp = twoValue;
                twoCodeTemp = twoCode;
                Map<String, List<String>> midMapTemp = bigMap.get(oneCodeTemp + "#" + oneValueTemp);
                List<String> smallList = midMapTemp.get(twoCodeTemp + "#" + twoValueTemp);
                if (smallList == null) {
                    smallList = new ArrayList<>();
                    midMapTemp.put(twoCodeTemp + "#" + twoValueTemp, smallList);
                }
            }


            Map<String, List<String>> midMapTemp = bigMap.get(oneCodeTemp + "#" + oneValueTemp);

            System.out.println("====>" + twoCodeTemp + "#" + twoValueTemp);
            List<String> listTemp = midMapTemp.get(twoCodeTemp + "#" + twoValueTemp);
            listTemp.add(threeCode + "#" + threeValue);


        }
        return bigMap;

    }

    public static Map<String, Map<String, List<String>>> parseExcelOfFuxing(InputStream inputStream) {

        Map<String, Map<String, List<String>>> bigMap = new HashMap<>();

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

        String oneValueTemp = null;
        String oneCodeTemp = "";
        int oneCodeInt = 1;
        String twoValueTemp = null;
        String twoCodeTemp = "";
        int twoCodeInt = 1;

        for (int i = startRowNum; i < endRowNum; i++) {
            Row row = sheet.getRow(i);
            Cell cell1 = row.getCell(0);
            Cell cell2 = row.getCell(1);
            Cell cell3 = row.getCell(2);
            Cell cell4 = row.getCell(3);

            System.out.println("行数为+" + i);
            String oneValue = cell1.getStringCellValue();
            System.out.println("oneValue==>" + oneValue);

            String oneCode = "";

            String twoValue = cell2.getStringCellValue();
            String twoCode = "";

            String threeValue = cell4.getStringCellValue();
            String threeCode = cell3.getStringCellValue();


            if (notNull(oneValue)) {
                oneValueTemp = oneValue.substring(2);
                oneCodeTemp = oneValue.substring(0, 2);
                Map<String, List<String>> midMap = new HashMap<>();
                bigMap.put(oneCodeTemp + "#" + oneValueTemp, midMap);
            }

            if (notNull(twoValue)) {

                System.out.println("twoValue==>" + twoValue);
                twoValueTemp = twoValue.substring(4);
                twoCodeTemp = twoValue.substring(0, 4);
                Map<String, List<String>> midMapTemp = bigMap.get(oneCodeTemp + "#" + oneValueTemp);

                List<String> list = new ArrayList<>();
                midMapTemp.put(twoCodeTemp + "#" + twoValueTemp, list);
            }


            Map<String, List<String>> midMapTemp = bigMap.get(oneCodeTemp + "#" + oneValueTemp);

            System.out.println("====>" + twoCodeTemp + "#" + twoValueTemp);
            List<String> listTemp = midMapTemp.get(twoCodeTemp + "#" + twoValueTemp);
            listTemp.add(threeCode + "#" + threeValue);


        }
        return bigMap;

    }


    public static Map<String, Map<String, List<String>>> parseExcel(InputStream inputStream) {

        Map<String, Map<String, List<String>>> bigMap = new HashMap<>();

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

        String oneValueTemp = null;
        String oneCodeTemp = "";
        int oneCodeInt = 1;
        String twoValueTemp = null;
        String twoCodeTemp = "";
        int twoCodeInt = 1;

        for (int i = startRowNum; i < endRowNum; i++) {
            Row row = sheet.getRow(i);
            Cell cell1 = row.getCell(0);
            Cell cell2 = row.getCell(1);
            Cell cell3 = row.getCell(2);
            Cell cell4 = row.getCell(3);

            String oneValue = cell1.getStringCellValue();
            String oneCode = "";

            String twoValue = cell2.getStringCellValue();
            String twoCode = "";

            String threeValue = cell4.getStringCellValue();
            String threeCode = cell3.getStringCellValue();


            if (notNull(oneValue)) {
                oneValueTemp = oneValue;
                oneCode = "" + oneCodeInt++;
                oneCodeTemp = oneCode;
                Map<String, List<String>> midMap = new HashMap<>();
                bigMap.put(oneCodeTemp + "#" + oneValueTemp, midMap);
            }

            if (notNull(twoValue)) {

                twoValueTemp = twoValue;
                twoCode = "" + twoCodeInt++;
                twoCodeTemp = twoCode;
                Map<String, List<String>> midMapTemp = bigMap.get(oneCodeTemp + "#" + oneValueTemp);

                List<String> list = new ArrayList<>();
                midMapTemp.put(twoCodeTemp + "#" + twoValueTemp, list);
            }


            Map<String, List<String>> midMapTemp = bigMap.get(oneCodeTemp + "#" + oneValueTemp);

            List<String> listTemp = midMapTemp.get(twoCodeTemp + "#" + twoValueTemp);
            listTemp.add(threeCode + "#" + threeValue);


        }
        return bigMap;

    }

    public static OccupationDTO toJson4Leval(Map<String, Map<String, Map<String, List<String>>>> bigMap) {
        Set<String> bigMapKey = bigMap.keySet();
        OccupationDTO first = new OccupationDTO();
        first.setLabel("大分类");
        first.setName("1");
        List<Option> options = new ArrayList<>();
        first.setOptions(options);
        for (String firstKey : bigMapKey) {
            Option firstOption = new Option();

            String[] firstValueAndLabel = firstKey.split("#");
            firstOption.setValue(firstValueAndLabel[0]);
            firstOption.setLabel(firstValueAndLabel[1]);
            OccupationDTO second = new OccupationDTO();
            firstOption.setChildren(second);

            second.setLabel("中分类");
            second.setName("2");
            List<Option> secondOptions = new ArrayList<>();
            second.setOptions(secondOptions);


            options.add(firstOption);
            Map<String, Map<String, List<String>>> secondMap = bigMap.get(firstKey);
            Set<String> secondKeySet = secondMap.keySet();
            for (String secondKey : secondKeySet) {

                Option secondOption = new Option();
                String[] secondValueAndLabel = secondKey.split("#");
                secondOption.setValue(secondValueAndLabel[0]);
                secondOption.setLabel(secondValueAndLabel[1]);

                OccupationDTO third = new OccupationDTO();
                secondOption.setChildren(third);

                third.setLabel("小分类");
                third.setName("3");
                List<Option> thirdOptions = new ArrayList<>();
                third.setOptions(thirdOptions);

                secondOptions.add(secondOption);


                Map<String, List<String>> thirdMap = secondMap.get(secondKey);
                Set<String> thirdKeySet = thirdMap.keySet();
                for (String thirdKey : thirdKeySet) {
                    Option thirdOption = new Option();

                    String[] thirdValueAndLabel = thirdKey.split("#");
                    thirdOption.setValue(thirdValueAndLabel[0]);
                    thirdOption.setLabel(thirdValueAndLabel[1]);

                    OccupationDTO four = new OccupationDTO();
                    thirdOption.setChildren(four);

                    four.setLabel("细类（职业）");
                    four.setName("4");
                    List<Option> fourOptions = new ArrayList<>();
                    four.setOptions(fourOptions);

                    thirdOptions.add(thirdOption);

                    List<String> fourList = thirdMap.get(thirdKey);
                    for (String fourKey : fourList) {
                        Option fourOption = new Option();
                        String[] fourValueAndLabel = fourKey.split("#");
                        fourOption.setValue(fourValueAndLabel[0]);
                        fourOption.setLabel(fourValueAndLabel[1]);
                        fourOptions.add(fourOption);
                    }

                }


            }
        }
        return first;
    }


    public static OccupationDTO toJson2Level(SortMap<String, List<String>> bigMap) {
        OccupationDTO first = new OccupationDTO();
        first.setName("1");
        List<Option> options = new ArrayList<>();
        first.setOptions(options);
        for (String firstKey : bigMap.keyList()) {
            Option firstOption = new Option();

            String[] firstValueAndLabel = firstKey.split("#");
            firstOption.setValue(firstValueAndLabel[0]);
            firstOption.setLabel(firstValueAndLabel[1]);
            OccupationDTO second = new OccupationDTO();
            firstOption.setChildren(second);

            second.setName("2");
            List<Option> secondOptions = new ArrayList<>();
            second.setOptions(secondOptions);

            options.add(firstOption);

            for (String secondKey : bigMap.get(firstKey)) {
                Option thirdOption = new Option();
                String[] thirdValueAndLabel = secondKey.split("#");
                thirdOption.setValue(thirdValueAndLabel[0]);
                thirdOption.setLabel(thirdValueAndLabel[1]);
                secondOptions.add(thirdOption);
            }
        }
        return first;
    }

    public static OccupationDTO toJson(Map<String, Map<String, List<String>>> bigMap) {
        Set<String> bigMapKey = bigMap.keySet();
        OccupationDTO first = new OccupationDTO();
//        first.setLabel("");
        first.setName("1");
        List<Option> options = new ArrayList<>();
        first.setOptions(options);
        for (String firstKey : bigMapKey) {
            Option firstOption = new Option();

            String[] firstValueAndLabel = firstKey.split("#");
            firstOption.setValue(firstValueAndLabel[0]);
            firstOption.setLabel(firstValueAndLabel[1]);
            OccupationDTO second = new OccupationDTO();
            firstOption.setChildren(second);

//            second.setLabel("");
            second.setName("2");
            List<Option> secondOptions = new ArrayList<>();
            second.setOptions(secondOptions);


            options.add(firstOption);
            Map<String, List<String>> midMap = bigMap.get(firstKey);
            Set<String> midMapKeySet = midMap.keySet();
            for (String midMapKey : midMapKeySet) {

                Option secondOption = new Option();
                String[] secondValueAndLabel = midMapKey.split("#");
                secondOption.setValue(secondValueAndLabel[0]);
                secondOption.setLabel(secondValueAndLabel[1]);

                OccupationDTO third = new OccupationDTO();
                secondOption.setChildren(third);

//                third.setLabel("");
                third.setName("3");
                List<Option> thirdOptions = new ArrayList<>();
                third.setOptions(thirdOptions);

                secondOptions.add(secondOption);

                List<String> thirdList = midMap.get(midMapKey);
                for (String thirdKey : thirdList) {
                    Option thirdOption = new Option();
                    String[] thirdValueAndLabel = thirdKey.split("#");
                    thirdOption.setValue(thirdValueAndLabel[0]);
                    thirdOption.setLabel(thirdValueAndLabel[1]);
                    thirdOptions.add(thirdOption);
                }
            }
        }
        return first;
    }


    public static void main(String[] args) {
        File f = new File("/Users/wangpeng/Desktop/弘康职业类别.xlsx");
        try {
            InputStream inputStream = new FileInputStream(f);

            // Map<String, Map<String, List<String>>> bigMap = ExcelToJsonUtil.parseExcelOfHKRS(inputStream);
            //   OccupationDTO occupationDTO = ExcelToJsonUtil.toJson(bigMap);

            //  Map<String, Map<String, Map<String, List<String>>>> bigMap = ExcelToJsonUtil.parseExcelOfBaiNian(inputStream);
            // OccupationDTO occupationDTO = ExcelToJsonUtil.toJson4Leval(bigMap);

            SortMap<String, List<String>> sortMap = ExcelToJsonUtil.parseExcelOfHK(inputStream);
            OccupationDTO occupationDTO = ExcelToJsonUtil.toJson2Level(sortMap);


            String s = JSONObject.toJSONString(occupationDTO);
            System.out.println(s);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static boolean notNull(String value) {
        return value != null && !"".equals(value);
    }

}
