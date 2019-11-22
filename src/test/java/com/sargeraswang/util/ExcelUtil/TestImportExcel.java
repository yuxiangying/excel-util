/**
 * @author SargerasWang
 */
package com.sargeraswang.util.ExcelUtil;

import com.sargeraswang.util.ExcelUtil.oragin.ExcelLogs;
import com.sargeraswang.util.ExcelUtil.oragin.ExcelUtil;
import com.sargeraswang.util.ExcelUtil.oragin.ExcelUtilTest;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
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


    @Test
    public void importXls2() throws FileNotFoundException {
        File f = new File("src/test/resources/上海人寿 LI-SH-SD-合作机构对接通用接口说明书V1.1.xlsx");
        InputStream inputStream = new FileInputStream(f);

        ExcelLogs logs = new ExcelLogs();
        /*Collection<Map> importExcel = ExcelUtil.importExcel(Map.class, inputStream, "yyyy/MM/dd HH:mm:ss", logs, 0);*/
        Collection<Occupation> importExcel = ExcelUtilTest.importExcel(Occupation.class, inputStream, "yyyy/MM/dd HH:mm:ss", logs,
                0);

        List<OccupationModel> occupationModelList = new ArrayList<>();
        for(Occupation occupation : importExcel){
            if(Integer.valueOf(occupation.getGrade().trim())>=1 && Integer.valueOf(occupation.getGrade().trim())<=4) {
                boolean primaryClassifyFlag = false;
                boolean secondaryClassifyFlag = false;

                List<OccupationThModel> occupationThModelList = new ArrayList<>();
                OccupationThModel occupationThModel = new OccupationThModel();
                occupationThModel.setCode(occupation.getCode());
                occupationThModel.setLabel(occupation.getName());
                occupationThModel.setGrade(occupation.getGrade());
                occupationThModelList.add(occupationThModel);
                for (OccupationModel occupationModel : occupationModelList) {
                    if (occupationModel.getLabel().equals(occupation.getPrimaryClassify().trim())) {
                        primaryClassifyFlag = true;
                        for (OccupationSeModel occupationSeModel : occupationModel.getOptions()) {
                            if (occupationSeModel.getLabel().equals(occupation.getSecondaryClassify().trim())) {
                                secondaryClassifyFlag = true;
                                occupationSeModel.getOccupation().add(occupationThModel);
                                break;
                            }
                        }
                        if (!secondaryClassifyFlag) {
                            OccupationSeModel occupationSeModel = new OccupationSeModel();
                            occupationSeModel.setLabel(occupation.getSecondaryClassify().trim());
                            occupationSeModel.setOccupation(occupationThModelList);
                            occupationModel.getOptions().add(occupationSeModel);
                        }
                    }
                }
                if (!primaryClassifyFlag) {
                    List<OccupationSeModel> occupationSeModelList = new ArrayList<>();
                    OccupationSeModel occupationSeModel = new OccupationSeModel();
                    occupationSeModel.setLabel(occupation.getSecondaryClassify().trim());
                    occupationSeModel.setOccupation(occupationThModelList);
                    occupationSeModelList.add(occupationSeModel);

                    OccupationModel occupationModel = new OccupationModel();
                    occupationModel.setLabel(occupation.getPrimaryClassify().trim());
                    occupationModel.setOptions(occupationSeModelList);
                    occupationModelList.add(occupationModel);
                }
            }
        }




        /*Map<String,Map<String,Map<String,String>>> occupationMaps = new HashMap<>();

        for(Occupation occupation : importExcel){
            if(Integer.valueOf(occupation.getGrade().trim())>=1 && Integer.valueOf(occupation.getGrade().trim())<=4){
                Map<String,String> occupations = new HashMap<>();
                occupations.put("code",occupation.getCode());
                occupations.put("name",occupation.getName());
                occupations.put("grade",occupation.getGrade());
                if(!occupationMaps.containsKey(occupation.getPrimaryClassify().trim())){//如果大类无
                    //创建中等类
                    Map<String,Map<String,String>> occupationMap = new HashMap<>();

                    //将职业Map放入中等类
                    occupationMap.put(occupation.getSecondaryClassify().trim(),occupations);

                    //将中等类放入大类
                    occupationMaps.put(occupation.getPrimaryClassify().trim(),occupationMap);

                }else {//如果大的分类有
                    //查询大类中的中等类
                    Map<String, Map<String, String>> getOccupationMap = occupationMaps.get(occupation.getPrimaryClassify().trim());
                    //判断是否存在中等类
                    if(!getOccupationMap.containsKey(occupation.getSecondaryClassify())){//无
                        //创建中等类
                        Map<String,Map<String,String>> occupationMap = new HashMap<>();
                        //将职业Map放入中等类
                        occupationMap.put(occupation.getSecondaryClassify().trim(),occupations);
                        //将中等类放入大类
                        occupationMaps.put(occupation.getPrimaryClassify().trim(),occupationMap);

                    }else {//有
                        getOccupationMap.put(occupation.getSecondaryClassify().trim(),occupations);
                    }
                }


            }
        }*/

        JSONArray json = new JSONArray().fromObject(occupationModelList);
        System.out.println(json);
        for (Occupation m : importExcel) {
            System.out.println(m);
        }
    }

}
