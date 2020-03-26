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

    public final static int CASHVALUE_BNADAPPNT = 7;

    public final static int CASHVALUE_BNKXB = 8;

    public static String getRateFile(int cashValueCode) {
        String filePath = null;
        switch (cashValueCode) {
            case CASHVALUE_TEMPLATE:
                filePath = "现金价值表";
//                filePath = "2-4.国华附加年金保险（2020版）现金价值表";
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
            case CASHVALUE_BNADAPPNT:
                filePath = "10 百年附加投保人豁免保险费重大疾病保险（2018版） 现金价值表（全表）";
                break;
            case CASHVALUE_BNKXB:
                filePath = "5918百年康欣保 终身重大疾病保险 现金价值表";
                break;

        }
        return filePath;
    }

    public static String getColumnTitle(int rateCode) {
        String columnTitle = null;
        switch (rateCode) {
            case CASHVALUE_TEMPLATE:
                /*columnTitle = "PayPeriod:缴费期间,Age:年龄,PolicyYear:保单年度,InsPeriod:保险期间,CVRate:现金价值,Sa0:基本保额金额,Sex:性别";*/
                columnTitle = "PayPeriod:交费期间,InsPeriod:保险期间,Age:投保年龄,PolicyYear:保单年度末,Sex:性别,CVRate:现金价值,Sa0:基本保额金额";
                break;
            case CASHVALUE_BNKXB:
                /*columnTitle = "PayPeriod:缴费期间,Age:年龄,PolicyYear:保单年度,InsPeriod:保险期间,CVRate:现金价值,Sa0:基本保额金额,Sex:性别";*/
                columnTitle = "PayPeriod:交费期间,InsPeriod:保险期间,Age:投保年龄,PolicyYear:保单年度末,Sex:性别,CVRate:现金价值,Sa0:基本保额金额";
                break;
        }
        return columnTitle;
    }

    /*
     * @Description: TODO(初始化实体)
     * @param: [rateCode, columnTitle]
     * @return: com.sargeraswang.util.ExcelUtil.yxyrate.PickFactorOfRate
     * @Author: yuxy_cyd
     * @Date: 2019/12/13 9:57
     */
    public static PickFactorOfCashValue getInstance(int cashValueCode, String columnTitle) {
        switch (cashValueCode) {
            case CASHVALUE_TEMPLATE:
                return new PickFactorOfCashValue(4, 18664, 0, 5, columnTitle);
//                return new PickFactorOfCashValue(4, 13867, 0, 5, columnTitle);
            case CASHVALUE_BNCX:
                return new PickFactorOfCashValue(4, 18664, 0, 5, columnTitle);
            case CASHVALUE_BNKBB:
                return new PickFactorOfCashValue(4, 16694, 0, 5, columnTitle);
            case CASHVALUE_BNKDB:
                return new PickFactorOfCashValue(4, 25966, 0, 5, columnTitle);
            case CASHVALUE_BNKSB:
                return new PickFactorOfCashValue(4, 24856, 0, 5, columnTitle);
            case CASHVALUE_BNTHB:
                return new PickFactorOfCashValue(4, 10324, 0, 5, columnTitle);
            case CASHVALUE_BNAXJ:
                return new PickFactorOfCashValue(5, 46521, 0, 5, columnTitle);
            case CASHVALUE_BNADAPPNT:
                return new PickFactorOfCashValue(4, 15536, 0, 5, columnTitle);
            case CASHVALUE_BNKXB:
                return new PickFactorOfCashValue(4, 24856, 0, 5, columnTitle);
        }
        return null;
    }
}
