package com.sargeraswang.util.ExcelUtil.yxyoccupation;

import com.sargeraswang.util.ExcelUtil.yxyrate.RateDTO;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yuxy_cyd
 * @version v-1.1.0
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @date: 2019年12月12日 11:37
 **/
public class PickFactorOfOccupation {
    private int startRowNum;//模板开始行数
    private int endRowNum;//模板结束行数
    private int startClnNum;//模板开始行数
    private int endClnNum;//模板结束行数
    private Map<Integer, Map<String, String>> columnMap;//导出标准费率表的title

    public PickFactorOfOccupation(int startRowNum, int endRowNum, int startClnNum, int endClnNum, String columnTitle) {
        this.startRowNum = startRowNum;
        this.endRowNum = endRowNum;
        this.startClnNum = startClnNum;
        this.endClnNum = endClnNum;
        this.columnMap = new HashMap<>();
        Integer num = new Integer(0);
        if (StringUtils.isNotBlank(columnTitle)) {
            String[] columnArry = columnTitle.split(",");
            for (String column : columnArry) {
                String[] celArry = column.split(":");
                Map<String, String> celMap = new HashMap<>();
                celMap.put(celArry[0].trim(), celArry[1].trim());
                this.columnMap.put(num, celMap);
                num++;
            }
        }
    }

    public List<RateDTO> parseSheet(String fileTemplatePath) {
        List<RateDTO> rateDTOS = new ArrayList<>();
        List<String> payPeriodTiles = new ArrayList<>();
        Map<String, List<RateDTO>> rateDTOMap = new HashMap<>();
        try {
            InputStream stream = new FileInputStream(fileTemplatePath);
            Workbook xssfWorkbook = WorkbookFactory.create(stream);
            Sheet sheet = xssfWorkbook.getSheet("费率表");
            Row xssfStartRow = sheet.getRow(startRowNum);
            Row xssfSecondRow = sheet.getRow(startRowNum + 1);
            for (int i = xssfStartRow.getRowNum() + 2; i <= endRowNum; i++) {
                Map<String, String> clmMap = new HashMap<>();
                Row xssfRow = sheet.getRow(i);
                // 判断行是否为空
                if (xssfRow == null || xssfRow.getCell(startClnNum) == null
                        || StringUtils.isBlank(xssfRow.getCell(startClnNum).getStringCellValue())) {
                    break;
                }
                for (int j = startClnNum; j <= endClnNum; j++) {
                    // 取出第一行的值+第二行
                    Cell titleCell = xssfStartRow.getCell(j);
                    Cell secondCell = xssfSecondRow.getCell(j);
                    if (i == xssfStartRow.getRowNum() + 2) {
                        if (secondCell != null && StringUtils.isNotBlank(secondCell.getStringCellValue().trim())) {
                            payPeriodTiles.add(secondCell.getStringCellValue().trim());
                        }
                    }
                    // 循环取出所有行的值
                    Cell contentCell = xssfRow.getCell(j);
                    String fieldName = "";
                    String fieldValue = "";
                    if (contentCell != null && StringUtils.isNotBlank(contentCell.getStringCellValue().trim())) {
                        if (secondCell != null && StringUtils.isNotBlank(secondCell.getStringCellValue())) {
                            fieldName = secondCell.getStringCellValue().trim();
                        } else {
                            fieldName = titleCell.getStringCellValue().trim();
                        }
                        fieldValue = contentCell.getStringCellValue().trim();
                        clmMap.put(fieldName, fieldValue);
                    }
                }
                String age = clmMap.get("投保年龄");
                String sex = clmMap.get("性别");
                String insPeriod = clmMap.get("保险期间");
                for (String payPeriodTile : payPeriodTiles) {
                    if (clmMap.containsKey(payPeriodTile)) {
                        RateDTO rateDTO = new RateDTO();
                        rateDTO.setAge(age);
                        rateDTO.setSex(sex);
                        rateDTO.setInsPeriod(insPeriod);
                        rateDTO.setPayPeriod(payPeriodTile);
                        rateDTO.setRate(clmMap.get(payPeriodTile));
                        if (rateDTOMap.containsKey(payPeriodTile)) {
                            rateDTOMap.get(payPeriodTile).add(rateDTO);
                        } else {
                            List<RateDTO> rateDTOS1 = new ArrayList<>();
                            rateDTOS1.add(rateDTO);
                            rateDTOMap.put(payPeriodTile, rateDTOS1);
                        }
                    }
                }
            }
            for (String payPeriodTile : payPeriodTiles) {
                if (rateDTOMap.containsKey(payPeriodTile)) {
                    for (RateDTO rateDTO : rateDTOMap.get(payPeriodTile)) {
                        rateDTOS.add(rateDTO);
                    }
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return rateDTOS;
    }


    public void exportExcel(List<RateDTO> rateDTOS, String outFilePath) throws IOException {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet workbookSheet = workbook.createSheet("Sheet1");
            XSSFRow cnrow = workbookSheet.createRow(0);
            XSSFRow engRow = workbookSheet.createRow(1);
            for (int i = 0; i < columnMap.size(); i++) {
                XSSFCell cel = cnrow.createCell(i);
                XSSFCell engCel = engRow.createCell(i);
                Map<String, String> celMap = columnMap.get(i);
                for (Map.Entry<String, String> entry : celMap.entrySet()) {
                    cel.setCellValue(entry.getValue());
                    engCel.setCellValue(entry.getKey());
                }
            }
            for (int i = 0; i < rateDTOS.size(); i++) {
                RateDTO rateDTO = rateDTOS.get(i);
                XSSFRow row = workbookSheet.createRow(i + 2);
                for (int j = 0; j < columnMap.size(); j++) {
                    XSSFCell row1Cel = row.createCell(j);
                    Map<String, String> celMap = columnMap.get(j);
                    for (Map.Entry<String, String> entry : celMap.entrySet()) {
                        Method method = rateDTO.getClass().getMethod("get" + entry.getKey());
                        row1Cel.setCellValue((String) method.invoke(rateDTO));
                    }
                }
            }
            File file = new File(outFilePath);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            workbook.write(fileOutputStream);
            fileOutputStream.close();
            System.out.println(outFilePath + "成功！");
        } catch (Exception e) {
            System.err.println(outFilePath + e.getMessage());
        }
    }


}
