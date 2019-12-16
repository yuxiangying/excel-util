package com.sargeraswang.util.ExcelUtil.yxyoccupation;

import com.sargeraswang.util.ExcelUtil.yxyrate.PickFactorOfRate;
import com.sargeraswang.util.ExcelUtil.yxyrate.RateDTO;
import com.sargeraswang.util.ExcelUtil.yxyrate.RateFactory;

import java.util.List;

/**
 * @author yuxy_cyd
 * @version v-1.1.0
 * @Description:TODO(根据模板导出标准费率表，模板请参考《费率表模板》，标准费率表，请参考《费率表》)
 * @date: 2019年12月12日 11:36
 **/
public class OccupationBootstrap {

    public static void main(String[] args) {
        int rateCode = RateFactory.RATE_HKAEXZL;//修改此处，得到不同的模板
        String fileName = RateFactory.getRateFile(rateCode);
        String fileTemplatePath = "D:/java/guohua/ExcelUtil/excel/rate/template/"+fileName+"模板.xlsx";
        String outFilePath = "D:/java/guohua/ExcelUtil/excel/rate/"+fileName+".xlsx";
        //主险
        //String columnTitle = "Sex:性别,PayPeriod:缴费期间,InsPeriod:保险期间,Age:年龄,Rate:费率";
        //附加险
        String columnTitle = "Sex:性别,PayPeriod:缴费期间,Age:年龄,Rate:费率";
        try {
            PickFactorOfRate pickFactorOfRate = RateFactory.getInstance(rateCode,columnTitle);
            List<RateDTO> rateDTOS =  pickFactorOfRate.parseSheet(fileTemplatePath);

            pickFactorOfRate.exportExcel(rateDTOS,outFilePath);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
