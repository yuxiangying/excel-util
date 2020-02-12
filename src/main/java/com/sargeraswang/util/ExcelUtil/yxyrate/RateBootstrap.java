package com.sargeraswang.util.ExcelUtil.yxyrate;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * @author yuxy_cyd
 * @version v-1.1.0
 * @Description:TODO(根据模板导出标准费率表，模板请参考《费率表模板》，标准费率表，请参考《费率表》)
 * @date: 2019年12月12日 11:36
 **/
public class RateBootstrap {

    public static void main(String[] args) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        List<Integer> rateCodes = Arrays.asList(41);

        for(int rateCode: rateCodes){
            //int rateCode = RateFactory.RATE_CCADHS;//修改此处，得到不同的模板
            String fileName = RateFactory.getRateFile(rateCode);
            String columnTitle = RateFactory.getColumnTitle(rateCode);
        String fileTemplatePath = "D:/java/guohua/ExcelUtil/excel/rate/template/"+fileName+"模板.xlsx";
        String outFilePath = "D:/java/guohua/ExcelUtil/excel/rate/"+fileName+".xlsx";
            //主险
            //String columnTitle = "Sex:性别,PayPeriod:缴费期间,InsPeriod:保险期间,Age:年龄,Rate:费率";
            //附加险
//        String columnTitle = "Sex:性别,PayPeriod:缴费期间,Age:年龄,Rate:费率";
            /*//==信泰
            String fileTemplatePath = "D:/资料/国华人寿_企业管理平台/险种配置/信泰/信泰费率表生成模板/"+fileName+"模板.xlsx";
            String outFilePath = "D:/资料/国华人寿_企业管理平台/险种配置/信泰/信泰费率表/"+fileName+".xlsx";*/
            //==长城
            /*String fileTemplatePath = "D:/资料/国华人寿_企业管理平台/险种配置/长城产品资料/华瑞所需资料/Z长城产品费率表/模板/"+fileName+"模板.xlsx";
            String outFilePath = "D:/资料/国华人寿_企业管理平台/险种配置/长城产品资料/华瑞所需资料/Z长城产品费率表/rate/"+fileName+".xlsx";*/
            //==中英
            /*String fileTemplatePath = "D:/资料/国华人寿_企业管理平台/险种配置/中英/中英费率表/模板/"+fileName+"模板.xlsx";
            String outFilePath = "D:/资料/国华人寿_企业管理平台/险种配置/中英/中英费率表/zy_rate/"+fileName+".xlsx";*/
            /*//==百年
            String fileTemplatePath = "D:/资料/国华人寿_企业管理平台/险种配置/百年/经代产品资料/5909百年附加投保人豁免保险费重大疾病保险（2018版)201906012/"+fileName+"模板.xlsx";
            String outFilePath = "D:/资料/国华人寿_企业管理平台/险种配置/百年/经代产品资料/5909百年附加投保人豁免保险费重大疾病保险（2018版)201906012/"+fileName+".xlsx";*/
            try {
                PickFactorOfRate pickFactorOfRate = RateFactory.getInstance(rateCode,columnTitle);
                List<RateDTO> rateDTOS =  pickFactorOfRate.parseSheet(fileTemplatePath);

                pickFactorOfRate.exportExcel(rateDTOS,outFilePath);

            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

    }
}
