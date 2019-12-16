package com.sargeraswang.util.ExcelUtil.cvrate;

import com.sargeraswang.util.ExcelUtil.yxyrate.PickFactorOfRate;
import com.sargeraswang.util.ExcelUtil.yxyrate.RateDTO;
import com.sargeraswang.util.ExcelUtil.yxyrate.RateFactory;

import java.util.List;

/**
 * @author yuxy_cyd
 * @version v-1.1.0
 * @Description:TODO(根据模板导出标准费率表，模板请参考《现金价值表模板》，标准费率表，请参考《现金价值表》)
 * @date: 2019年12月12日 11:36
 **/
public class CashValueBootstrap {

    public static void main(String[] args) {
        int cashValueCode = CashValueFactory.CASHVALUE_BNAXJ;//修改此处，得到不同的模板
        String fileName = CashValueFactory.getRateFile(cashValueCode);
        String fileTemplatePath = "D:/java/guohua/ExcelUtil/excel/cvrate/template/"+fileName+"模板.xlsx";
        String outFilePath = "D:/java/guohua/ExcelUtil/excel/cvrate/"+fileName+".xlsx";
        String columnTitle = "PayPeriod:缴费期间,Age:年龄,Sex:性别,PolicyYear:保单年度,InsPeriod:保险期间,CVRate:现金价值,Sa0:基本保额金额";
        try {
            PickFactorOfCashValue pickFactorOfCashValue = CashValueFactory.getInstance(cashValueCode,columnTitle);
            List<CashValueDTO> cashValueDTOS =  pickFactorOfCashValue.parseSheet(fileTemplatePath);

            pickFactorOfCashValue.exportExcel(cashValueDTOS,outFilePath);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
