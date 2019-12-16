package com.sargeraswang.util.ExcelUtil.area;

import com.sargeraswang.util.ExcelUtil.util.JsonUtil;
import com.sargeraswang.util.ExcelUtil.area.AreaCode;
import net.sf.json.JSONObject;

import java.io.File;
import java.util.List;

/**
 * @Description: TODO(二级地址编码转换为json格式,模板为固定格式
 * @author yuxy_cyd
 * @version v-1.1.0
 * @Description:TODO(根据模板导出标准地区码表，模板请参考《地区码表模板》，标准地区码表，请参考《地区码表》)
 * @date: 2019年12月12日 11:36
 **/
public class AreaBootstrap {

    public static void main(String[] args) {
        int areaCode = AreaFactory.AREA_TEMPLATE;//修改此处，得到不同的模板
        String fileName = AreaFactory.getRateFile(areaCode);
        String fileTemplatePath = "D:/java/guohua/ExcelUtil/excel/area/template/"+fileName+"模板.xlsx";
        String outFilePath = "D:/java/guohua/ExcelUtil/excel/area/" + File.separator + fileName + ".json";
        try {
            AreaCode areaCodeDTO =  PickFactorOfArea.parseSheet(fileTemplatePath);

            JSONObject jsonObject = new JSONObject().fromObject(areaCodeDTO);

            boolean flag = JsonUtil.createJsonFile(jsonObject.toString(), outFilePath);

            System.out.println(jsonObject.toString());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
