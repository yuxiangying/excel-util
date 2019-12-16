package com.sargeraswang.util.ExcelUtil.cvrate;

import com.sargeraswang.util.ExcelUtil.yxyrate.PickFactorOfRate;

/**
 * @author yuxy_cyd
 * @version v-1.1.0
 * @Description:TODO(费率表静态数据)
 * @date: 2019年12月12日 17:58
 **/
public class CashValueFactory {
    public final static int CASHVALUE_TEMPLATE = 0;

    public final static int CASHVALUE_BNCX = 1;

    public final static int CASHVALUE_BNKBB = 2;

    public final static int CASHVALUE_BNKDB = 3;

    public final static int CASHVALUE_BNKSB = 4;

    public final static int CASHVALUE_BNTHB = 5;

    public final static int CASHVALUE_BNAXJ = 6;

    public static String getRateFile(int cashValueCode) {
        String filePath = null;
        switch (cashValueCode) {
            case CASHVALUE_TEMPLATE:
                filePath = "现金价值表";
                break;
            case CASHVALUE_BNCX:
                filePath = "百年传禧终身寿险现金价值表";
                break;
            case CASHVALUE_BNKBB:
                filePath = "百年康倍保重大疾病保险现金价值表";
                break;
            case CASHVALUE_BNKDB:
                filePath = "百年康多保终身重大疾病保险现金价值表";
                break;
            case CASHVALUE_BNKSB:
                filePath = "百年康盛保终身重大疾病保险现金价值表";
                break;
            case CASHVALUE_BNTHB:
                filePath = "百年糖惠保终身疾病保险现金价值表";
                break;
            case CASHVALUE_BNAXJ:
                filePath = "百年附加欣逸两全保险现金价值表";
                break;

        }
        return filePath;
    }
    /*
     * @Description: TODO(初始化实体)
     * @param: [rateCode, columnTitle]
     * @return: com.sargeraswang.util.ExcelUtil.yxyrate.PickFactorOfRate
     * @Author: yuxy_cyd
     * @Date: 2019/12/13 9:57
     */
    public static PickFactorOfCashValue getInstance(int cashValueCode, String columnTitle){
        switch (cashValueCode) {
            case CASHVALUE_TEMPLATE:
                return new PickFactorOfCashValue(4,18664,0,5,columnTitle);
            case CASHVALUE_BNCX:
                return new PickFactorOfCashValue(4,18664,0,5,columnTitle);
            case CASHVALUE_BNKBB:
                return new PickFactorOfCashValue(4,16694,0,5,columnTitle);
            case CASHVALUE_BNKDB:
                return new PickFactorOfCashValue(4,25966,0,5,columnTitle);
            case CASHVALUE_BNKSB:
                return new PickFactorOfCashValue(4,24856,0,5,columnTitle);
            case CASHVALUE_BNTHB:
                return new PickFactorOfCashValue(4,10324,0,5,columnTitle);
            case CASHVALUE_BNAXJ:
                return new PickFactorOfCashValue(5,46521,0,5,columnTitle);
        }
        return null;
    }
}
