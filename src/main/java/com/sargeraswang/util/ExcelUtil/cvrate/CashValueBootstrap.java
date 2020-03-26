package com.sargeraswang.util.ExcelUtil.cvrate;

import com.sargeraswang.util.ExcelUtil.yxyrate.PickFactorOfRate;
import com.sargeraswang.util.ExcelUtil.yxyrate.RateDTO;
import com.sargeraswang.util.ExcelUtil.yxyrate.RateFactory;

import java.util.Arrays;
import java.util.List;

/**
 * @author yuxy_cyd
 * @version v-1.1.0
 * @Description:TODO(根据模板导出标准费率表，模板请参考《现金价值表模板》，标准费率表，请参考《现金价值表》)
 * @date: 2019年12月12日 11:36
 **/
public class CashValueBootstrap {

    public static void main(String[] args) {
//        int cashValueCode = CashValueFactory.CASHVALUE_TEMPLATE;//修改此处，得到不同的模板
        List<Integer> cashValueCodes = Arrays.asList(0);
        for (int cashValueCode : cashValueCodes) {
            String fileName = CashValueFactory.getRateFile(cashValueCode);
            String columnTitle = CashValueFactory.getColumnTitle(cashValueCode);
            String fileTemplatePath = "C:/ideaProject/guohua/excel-util/excel/cvrate/template/" + fileName + "模板.xlsx";
            String outFilePath = "C:/ideaProject/guohua/excel-util/excel/cvrate/" + fileName + ".xlsx";
            try {
                PickFactorOfCashValue pickFactorOfCashValue = CashValueFactory.getInstance(cashValueCode, columnTitle);
                List<CashValueDTO> cashValueDTOS = pickFactorOfCashValue.parseSheet(fileTemplatePath);

                pickFactorOfCashValue.exportExcel(cashValueDTOS, outFilePath);

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }
}
