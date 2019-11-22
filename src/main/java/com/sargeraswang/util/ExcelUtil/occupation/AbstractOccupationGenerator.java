package com.sargeraswang.util.ExcelUtil.occupation;

import com.alibaba.fastjson.JSONObject;
import com.sargeraswang.util.ExcelUtil.occupation.dto.OccupationDTO;
import com.sargeraswang.util.ExcelUtil.occupation.dto.SortMap;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
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
