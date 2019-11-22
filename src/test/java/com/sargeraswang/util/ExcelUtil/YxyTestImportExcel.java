/**
 * @author SargerasWang
 */
package com.sargeraswang.util.ExcelUtil;

import com.sargeraswang.util.ExcelUtil.oragin.ExcelTemplate;
import org.junit.Test;

import java.io.FileNotFoundException;

/**
 * 测试导入Excel 97/2003
 */
public class YxyTestImportExcel {

    @Test
    public void test() throws FileNotFoundException {
        String pathUrl = "src/test/resources/上海人寿地区码表.xlsx";
        ExcelTemplate.importAreaExcel(pathUrl);

        /*String pathUrl = "src/test/resources/上海人寿 LI-SH-SD-合作机构对接通用接口说明书V1.1.xlsx";
        ExcelTemplate.importOccupationExcel(pathUrl);*/

        System.out.println("生成成功！！！");
    }

}
