package com.sargeraswang.util.ExcelUtil.cvrate;

import com.sargeraswang.util.ExcelUtil.yxyrate.RateDTO;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.security.userdetails.ldap.Person;

import java.beans.PropertyDescriptor;
import java.io.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yuxy_cyd
 * @version v-1.1.0
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @date: 2019年12月12日 11:37
 **/
public class PickFactorOfCashValue {
    private int startRowNum;//模板开始行数
    private int endRowNum;//模板结束行数
    private int startClnNum;//模板开始行数
    private int endClnNum;//模板结束行数
    private Map<Integer, Map<String, String>> columnMap;//导出标准现金价值表的title

    public PickFactorOfCashValue(int startRowNum, int endRowNum, int startClnNum, int endClnNum, String columnTitle) {
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

    /*public List<CashValueDTO> parseSheet(String fileTemplatePath){
        List<CashValueDTO>  cashValueDTOS = new ArrayList<>();
        List<String> sexTiles = new ArrayList<>();
        Map<String,List<CashValueDTO>> cashValueDTOMap = new HashMap<>();
        try {
            InputStream stream = new FileInputStream(fileTemplatePath);
            Workbook xssfWorkbook = WorkbookFactory.create(stream);
            Sheet sheet = xssfWorkbook.getSheet("现金价值表");
            Row xssfStartRow = sheet.getRow(startRowNum);
            Row xssfSecondRow = sheet.getRow(startRowNum+1);
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
                    if(i == xssfStartRow.getRowNum() + 2){
                        if(secondCell != null && StringUtils.isNotBlank(secondCell.getStringCellValue().trim())){
                            sexTiles.add(secondCell.getStringCellValue().trim());
                        }
                    }
                    // 循环取出所有行的值
                    Cell contentCell = xssfRow.getCell(j);
                    String fieldName = "";
                    String fieldValue = "";
                    if (contentCell != null && StringUtils.isNotBlank(contentCell.getStringCellValue().trim())) {
                        if (secondCell != null && StringUtils.isNotBlank(secondCell.getStringCellValue())) {
                            fieldName = secondCell.getStringCellValue().trim();
                        }else {
                            fieldName = titleCell.getStringCellValue().trim();
                        }
                        fieldValue = contentCell.getStringCellValue().trim();
                        clmMap.put(fieldName, fieldValue);
                    }
                }
                String payPeriod = clmMap.get("交费期间")+"Y";
                String age = clmMap.get("投保年龄");
                String policyYear = clmMap.get("保单年度末");
                String insPeriod = clmMap.get("保险期间");
                //根据中文保险期间得到英文,若本身为英文 可省略
                if(insPeriod.trim().contains("终身")){
                    insPeriod = "999A";
                }else if(insPeriod.trim().contains("保至")){
                    insPeriod = getNumStr(insPeriod)+"A";
                }else {
                    insPeriod = getNumStr(insPeriod)+"Y";
                }
                for (String  sexTile: sexTiles){
                    if(clmMap.containsKey( sexTile)){
                        CashValueDTO cashValueDTO = new CashValueDTO();
                        cashValueDTO.setPayPeriod(payPeriod);
                        cashValueDTO.setAge(age);
                        cashValueDTO.setPolicyYear(policyYear);
                        cashValueDTO.setInsPeriod(insPeriod);
                        cashValueDTO.setCVRate(clmMap.get(sexTile));
                        cashValueDTO.setSa0("1000");
                        if("男".equals(sexTile)){
                            cashValueDTO.setSex("0");
                        }else {
                            cashValueDTO.setSex("1");
                        }
                        if (cashValueDTOMap.containsKey(sexTile)){
                            cashValueDTOMap.get(sexTile).add(cashValueDTO);
                        }else {
                            List<CashValueDTO> cashValueDTOS1 = new ArrayList<>();
                            cashValueDTOS1.add(cashValueDTO);
                            cashValueDTOMap.put(sexTile,cashValueDTOS1);
                        }
                    }
                }
            }
            for (String sexTile: sexTiles){
                if (cashValueDTOMap.containsKey(sexTile)){
                    for (CashValueDTO cashValueDTO:cashValueDTOMap.get(sexTile)){
                        cashValueDTOS.add(cashValueDTO);
                    }
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return cashValueDTOS;
    }*/
    public List<CashValueDTO> parseSheet(String fileTemplatePath) {
        List<CashValueDTO> cashValueDTOS = new ArrayList<>();
        List<String> secondTiles = new ArrayList<>();
        Map<String, List<CashValueDTO>> cashValueDTOMap = new HashMap<>();
        try {
            InputStream stream = new FileInputStream(fileTemplatePath);
            Workbook xssfWorkbook = WorkbookFactory.create(stream);
            Sheet sheet = xssfWorkbook.getSheet("现金价值表");
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
                            secondTiles.add(secondCell.getStringCellValue().trim());
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
                /*String payPeriod = clmMap.get("交费期间")+"Y";
                String age = clmMap.get("投保年龄");
                String policyYear = clmMap.get("保单年度末");
                String insPeriod = clmMap.get("保险期间");
                //根据中文保险期间得到英文,若本身为英文 可省略
                if(insPeriod.trim().contains("终身")){
                    insPeriod = "999A";
                }else if(insPeriod.trim().contains("保至")){
                    insPeriod = getNumStr(insPeriod)+"A";
                }else {
                    insPeriod = getNumStr(insPeriod)+"Y";
                }
                for (String  secondTile: secondTiles){
                    if(clmMap.containsKey( secondTile)){
                        CashValueDTO cashValueDTO = new CashValueDTO();
                        cashValueDTO.setPayPeriod(payPeriod);
                        cashValueDTO.setAge(age);
                        cashValueDTO.setPolicyYear(policyYear);
                        cashValueDTO.setInsPeriod(insPeriod);
                        cashValueDTO.setCVRate(clmMap.get(secondTile));
                        cashValueDTO.setSa0("1000");
                        if("男".equals(secondTile)){
                            cashValueDTO.setSex("0");
                        }else {
                            cashValueDTO.setSex("1");
                        }
                        if (cashValueDTOMap.containsKey(secondTile)){
                            cashValueDTOMap.get(secondTile).add(cashValueDTO);
                        }else {
                            List<CashValueDTO> cashValueDTOS1 = new ArrayList<>();
                            cashValueDTOS1.add(cashValueDTO);
                            cashValueDTOMap.put(secondTile,cashValueDTOS1);
                        }
                    }
                }*/
                for (String secondTile : secondTiles) {
                    if (clmMap.containsKey(secondTile)) {
                        CashValueDTO cashValueDTO = new CashValueDTO();
                        for (int k = 0; k < columnMap.size() - 2; k++) { //set除缴费期间和费率的值
                            Map<String, String> celMap = columnMap.get(k);
                            for (Map.Entry<String, String> entry : celMap.entrySet()) {
                                // 此处应该判断beanObj,property不为null
                                PropertyDescriptor pd = new PropertyDescriptor((new StringBuilder()).append(Character.toLowerCase(entry.getKey().charAt(0)))
                                        .append(entry.getKey().substring(1)).toString(), cashValueDTO.getClass());
                                Method setMethod = pd.getWriteMethod();
                                if (setMethod != null && k == columnMap.size() - 3) {
                                    setMethod.invoke(cashValueDTO, secondTile);
                                } else if (setMethod != null) {
                                    setMethod.invoke(cashValueDTO, clmMap.get(entry.getValue()));
                                }
                            }
                        }
                        cashValueDTO.setCVRate(clmMap.get(secondTile));
                        cashValueDTO.setSa0("1000");
                        if (cashValueDTOMap.containsKey(secondTile)) {
                            cashValueDTOMap.get(secondTile).add(cashValueDTO);
                        } else {
                            List<CashValueDTO> cashValueDTOS1 = new ArrayList<>();
                            cashValueDTOS1.add(cashValueDTO);
                            cashValueDTOMap.put(secondTile, cashValueDTOS1);
                        }
                    }
                }
            }
            for (String secondTile : secondTiles) {
                if (cashValueDTOMap.containsKey(secondTile)) {
                    for (CashValueDTO cashValueDTO : cashValueDTOMap.get(secondTile)) {
                        cashValueDTOS.add(cashValueDTO);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return cashValueDTOS;
    }


    public void exportExcel(List<CashValueDTO> cashValueDTOS, String outFilePath) throws IOException {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet workbookSheet = workbook.createSheet("现金价值表");
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
            for (int i = 0; i < cashValueDTOS.size(); i++) {
                CashValueDTO cashValueDTO = cashValueDTOS.get(i);
                XSSFRow row = workbookSheet.createRow(i + 2);
                for (int j = 0; j < columnMap.size(); j++) {
                    XSSFCell row1Cel = row.createCell(j);
                    Map<String, String> celMap = columnMap.get(j);
                    for (Map.Entry<String, String> entry : celMap.entrySet()) {
                        Method method = cashValueDTO.getClass().getMethod("get" + entry.getKey());
                        row1Cel.setCellValue((String) method.invoke(cashValueDTO));
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


    private String getNumStr(String str) {
        //String a="love23next234csdn3423javaeye";
        String regEx = "[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
//       System.out.println( m.replaceAll("").trim());
        return m.replaceAll("").trim();
    }


}
