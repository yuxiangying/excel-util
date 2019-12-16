package com.sargeraswang.util.ExcelUtil.oragin;

import com.sargeraswang.util.ExcelUtil.occupation.GeneratorFactory;
import com.sargeraswang.util.ExcelUtil.occupation.OccupationGenerator;
import com.sargeraswang.util.ExcelUtil.util.JsonUtil;
import com.sargeraswang.util.ExcelUtil.vo.area.AreaCode;
import com.sargeraswang.util.ExcelUtil.vo.area.AreaExcel;
import com.sargeraswang.util.ExcelUtil.vo.occupation.OccupationExcel;
import com.sargeraswang.util.ExcelUtil.vo.occupation.OccupationGradeCode;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Collection;

/**
 * @author yuxy_cyd
 * @version v-1.1.0
 * @Description:TODO(Excel转换模板)
 * @date: 2019年11月22日 14:00
 **/
public class ExcelTemplate {
    /*
     * @Description: TODO(二级职业编码转换为json格式,
     * 模板参考src/test/resources/上海人寿 LI-SH-SD-合作机构对接通用接口说明书V1.1.xlsx)
     * @param: [pathUrl：导出excel路径]
     * @return: void
     * @Author: yuxy_cyd
     * @Date: 2019/11/22 14:29   
     */
    public static void importOccupationExcel(String pathUrl) throws FileNotFoundException {
        File f = new File(pathUrl);
        InputStream inputStream = new FileInputStream(f);

        ExcelLogs logs = new ExcelLogs();
        Collection<OccupationExcel> importExcel = ExcelUtil.importExcel(OccupationExcel.class, inputStream, "yyyy/MM/dd HH:mm:ss", logs, 0);

        OccupationGradeCode occupationGradeCode = new OccupationGradeCode();

        for (OccupationExcel occupationExcel : importExcel) {
            OccupationGradeCode.OccupationCode occupationCode = new OccupationGradeCode.OccupationCode();
            occupationCode.setCode(occupationExcel.getCode().trim());
            occupationCode.setLabel(occupationExcel.getName().trim());
            occupationCode.setGrade(occupationExcel.getGrade().trim());

            boolean primaryClassifyFlag = false;
            boolean secondaryClassifyFlag = false;

            for (OccupationGradeCode.PrimaryClassifyCode primaryClassifyCode: occupationGradeCode.getContent()){
                if (primaryClassifyCode.getLabel().equals(occupationExcel.getPrimaryClassify().trim())){
                    primaryClassifyFlag = true;
                    for (OccupationGradeCode.SecondaryClassifyCode secondaryClassifyCode: primaryClassifyCode.getChildren()){
                        if(secondaryClassifyCode.getLabel().equals(occupationExcel.getSecondaryClassify().trim())){
                            secondaryClassifyFlag = true;
                            secondaryClassifyCode.getChildren().add(occupationCode);
                            break;
                        }
                    }
                    if (!secondaryClassifyFlag){
                        OccupationGradeCode.SecondaryClassifyCode secondaryClassifyCode = new OccupationGradeCode.SecondaryClassifyCode();
                        secondaryClassifyCode.setLabel(occupationExcel.getSecondaryClassify().trim());
                        secondaryClassifyCode.getChildren().add(occupationCode);

                        primaryClassifyCode.getChildren().add(secondaryClassifyCode);
                    }
                    break;
                }
            }
            if (!primaryClassifyFlag){
                OccupationGradeCode.PrimaryClassifyCode primaryClassifyCode = new OccupationGradeCode.PrimaryClassifyCode();
                primaryClassifyCode.setLabel(occupationExcel.getPrimaryClassify().trim());

                OccupationGradeCode.SecondaryClassifyCode secondaryClassifyCode = new OccupationGradeCode.SecondaryClassifyCode();
                secondaryClassifyCode.setLabel(occupationExcel.getSecondaryClassify().trim());
                secondaryClassifyCode.getChildren().add(occupationCode);

                primaryClassifyCode.getChildren().add(secondaryClassifyCode);

                occupationGradeCode.getContent().add(primaryClassifyCode);
            }
        }

        JSONObject jsonObject = new JSONObject().fromObject(occupationGradeCode);
        System.out.println(jsonObject.toString());
    }
}
