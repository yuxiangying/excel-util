/**
 * @author SargerasWang
 */
package com.sargeraswang.util.ExcelUtil;

import com.sargeraswang.util.ExcelUtil.oragin.ExcelLogs;
import com.sargeraswang.util.ExcelUtil.oragin.ExcelUtil;
import net.sf.json.JSONArray;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

/**
 * 测试导入Excel 97/2003
 */
public class TestImportExcel {

    @Test
    public void importXls() throws FileNotFoundException {
        File f = new File("src/test/resources/上海人寿 LI-SH-SD-合作机构对接通用接口说明书V1.1.xlsx");
        InputStream inputStream = new FileInputStream(f);

        ExcelLogs logs = new ExcelLogs();
        Collection<Map> importExcel = ExcelUtil.importExcel(Map.class, inputStream, "yyyy/MM/dd HH:mm:ss", logs, 0);
        JSONArray json = new JSONArray().fromObject(importExcel);
        System.out.println(json);
        for (Map m : importExcel) {
            System.out.println(m);
        }
    }

    @Test
    public void importXlsx() throws FileNotFoundException {
        File f = new File("src/test/resources/test.xlsx");
        InputStream inputStream = new FileInputStream(f);

        ExcelLogs logs = new ExcelLogs();
        Collection<Map> importExcel = ExcelUtil.importExcel(Map.class, inputStream, "yyyy/MM/dd HH:mm:ss", logs, 0);

        for (Map m : importExcel) {
            System.out.println(m);
        }
    }

    @Test
    public void praseExcel() throws FileNotFoundException {
        /*File f = new File("/Users/wangpeng/Desktop/职业/爱心人寿.xls");*/
        File f = new File("src/test/resources/test.xlsx");
        InputStream inputStream = new FileInputStream(f);
        ExcelLogs logs = new ExcelLogs();
        Collection<Map> importExcel = ExcelUtil.importExcel(Map.class, inputStream, "yyyy/MM/dd HH:mm:ss", logs, 0);

        for (Map m : importExcel) {
            System.out.println(m);
            Set set = m.keySet();
            for (Object key : set) {
                System.out.println("key:" + key + "====" + m.get(key));
            }


        }
    }

}
