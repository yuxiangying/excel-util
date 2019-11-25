package com.sargeraswang.util.ExcelUtil.occupation;

import com.alibaba.fastjson.JSONObject;
import com.sargeraswang.util.ExcelUtil.occupation.dto.OccupationDTO;
import com.sargeraswang.util.ExcelUtil.occupation.dto.SortMap;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.List;

public abstract class AbstractOccupationGenerator implements OccupationGenerator {


    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected int startRowNum = 0;
    protected int endRowNum = 0;
    private SortMap<String, SortMap<String, List<String>>> bigMap;


    public AbstractOccupationGenerator(int start, int end) {
        startRowNum = start;
        endRowNum = end;
    }

    @Override
    public String toJson(InputStream inputStream) {
        Workbook workBook = null;
        try {
            workBook = WorkbookFactory.create(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("解析 EXCEL 文件失败！");
        }
        if (workBook == null)
            throw new RuntimeException("解析 EXCEL 文件失败！");

        Sheet sheet = workBook.getSheetAt(0);

        OccupationDTO occupationDTO = getOccupationDTO(sheet);

        return JSONObject.toJSONString(occupationDTO);

    }

    @Override
    public boolean createJsonFile(String jsonString, String filePath, String fileName) {
        // 标记文件生成是否成功
        boolean flag = true;

        // 拼接文件完整路径
        String fullPath = filePath + File.separator + fileName + ".json";

        // 生成json格式文件
        try {
            // 保证创建一个新文件
            File file = new File(fullPath);
            if (!file.getParentFile().exists()) { // 如果父目录不存在，创建父目录
                file.getParentFile().mkdirs();
            }
            if (file.exists()) { // 如果已存在,删除旧文件
                file.delete();
            }
            file.createNewFile();

            if(jsonString.indexOf("'")!=-1){
                //将单引号转义一下，因为JSON串中的字符串类型可以单引号引起来的
                jsonString = jsonString.replaceAll("'", "\\'");
            }
            if(jsonString.indexOf("\"")!=-1){
                //将双引号转义一下，因为JSON串中的字符串类型可以单引号引起来的
                jsonString = jsonString.replaceAll("\"", "\\\"");
            }

            if(jsonString.indexOf("\r\n")!=-1){
                //将回车换行转换一下，因为JSON串中字符串不能出现显式的回车换行
                jsonString = jsonString.replaceAll("\r\n", "\\u000d\\u000a");
            }
            if(jsonString.indexOf("\n")!=-1){
                //将换行转换一下，因为JSON串中字符串不能出现显式的换行
                jsonString = jsonString.replaceAll("\n", "\\u000a");
            }

            // 格式化json字符串
            //jsonString = JsonFormatTool.formatJson(jsonString);

            // 将格式化后的字符串写入文件
            Writer write = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
            write.write(jsonString);
            write.flush();
            write.close();
        } catch (Exception e) {
            flag = false;
            e.printStackTrace();
        }

        // 返回是否成功的标记
        return flag;
    }

    protected static boolean notNull(String value) {
        return value != null && !"".equals(value);
    }

    protected static String stringValue(Cell cell) {
        String value = "";
        Object cellValue = cellValue(cell);
        if (cellValue instanceof String) {
            value = (String) cellValue;

        } else if (cellValue instanceof Double) {
            value = Double.toString((Double) cellValue);

            if (value != null && value.endsWith(".0")) {
                value = ((Double) cellValue).intValue() + "";
            }
        } else if (cellValue instanceof Integer) {
            value = Integer.toString((Integer) cellValue);
        } else {
            value = "NA";
        }

        return value;

    }

    protected static Object cellValue(Cell cell) {
        if (cell == null
                || (cell.getCellTypeEnum() == CellType.STRING && !notNull(cell
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

    protected abstract OccupationDTO getOccupationDTO(Sheet sheet);


}
