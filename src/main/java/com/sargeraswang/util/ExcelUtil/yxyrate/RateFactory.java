package com.sargeraswang.util.ExcelUtil.yxyrate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @author yuxy_cyd
 * @version v-1.1.0
 * @Description:TODO(费率表静态数据)
 * @date: 2019年12月12日 17:58
 **/
public class RateFactory {
    public final static int RATE_TEMPLATE = 0;

    /*public final static int RATE_XTZJ = 1;

    public final static int RATE_XTLQ = 2;

    public final static int RATE_XTBZJ = 3;

    public final static int RATE_XTBLQ = 4;

    public final static int RATE_XTRY = 5;

    public final static int RATE_HKADBB = 6;

    public final static int RATE_HKAEXZL = 7;*/
    public final static int RATE_XTRY = 33;
    //=====长城
    public final static int RATE_CCADHS = 1;
    public final static int RATE_CCADJFRS = 2;
    public final static int RATE_CCADJKRS = 3;
    public final static int RATE_CCAPPNT = 4;
    public final static int RATE_CCHS = 5;
    public final static int RATE_CCJFRS = 6;
    public final static int RATE_CCJK = 7;
    public final static int RATE_CCJXB = 8;
    public final static int RATE_CCJFT = 9;
    public final static int RATE_CCYXWL = 10;
    public final static int RATE_CCADAPPNT = 11;
    public final static int RATE_CCJL = 12;
    public final static int RATE_CCJCYS = 13;
    public final static int RATE_CCYSS = 14;
    //年龄比较特殊  是范围
    public final static int RATE_CCADYCYL = 15;
    public final static int RATE_CCYXWY = 16;
    public final static int RATE_CCADAXZY = 17;
    public final static int RATE_CCADZYGF = 18;
    public final static int RATE_CCADZY = 19;

    //=====中英
    public final static int RATE_ZYADBJK = 25;
    public final static int RATE_ZYADBWSX = 26;
    public final static int RATE_ZYLXB = 27;
    public final static int RATE_ZYYXSJ = 28;

    //=====长生
    public final static int RATE_CSFZX = 29;

    //=====百年
    public final static int RATE_BNADAPPNT = 30;

    //=====信泰
    public final static int RATE_XTQWRS = 31;

    //=====百年
    public final static int RATE_BNADAKB = 32;

    //=====阳光人寿
    public final static int RATE_YGXSB = 34;

    //=====临时现价表 中英
    public final static int CASHVALUE_ZYBJK= 35;
    public final static int CASHVALUE_ZYADBWSX= 37;
    public final static int CASHVALUE_ZYLXB= 38;
    public final static int CASHVALUE_ZYXXSJ= 39;

    public final static int CASHVALUE_ZYADAPPNT= 36;

    //在家办公
    public final static int RATE_CS1 = 40;
    public final static int RATE_CS2 = 41;
    public final static int RATE_CS3 = 42;
    public static String getRateFile(int rateCode) {
        String filePath = null;
        switch (rateCode) {
            case RATE_TEMPLATE:
                filePath = "费率表";
                break;
            /*case RATE_XTZJ:
                filePath = "信泰百万守护（2019）重大疾病保险费率表";
                break;
            case RATE_XTLQ:
                filePath = "信泰附加百万守护（2019）两全保险费率表";
                break;
            case RATE_XTBZJ:
                filePath = "信泰百万守护（2019）B款重大疾病保险费率表";
                break;
            case RATE_XTBLQ:
                filePath = "信泰附加百万守护（2019）B款两全保险费率表";
                break;
            case RATE_XTRY:
                filePath = "信泰如意享（六福版）养老年金保险基本保险金额表";
                break;
            case RATE_HKADBB:
                filePath = "03_弘康附加多倍保疾病保险费率表";
                break;
            case RATE_HKAEXZL:
                filePath = "03_弘康附加恶性肿瘤疾病保险费率表";
                break;*/
            case RATE_XTRY:
                filePath = "信泰如意享（六福版）养老年金保险基本保险金额表";
                break;
            //=====长城
            case RATE_CCADHS:
                filePath = "1长城附加鸿盛两全保险（分红型，2017）费率表";
                break;
            case RATE_CCADJFRS:
                filePath = "2长城附加吉福人生两全保险费率表";
                break;
            case RATE_CCADJKRS:
                filePath = "3长城附加吉康人生两全保险费率表(含19年）";
                break;
            case RATE_CCAPPNT:
                filePath = "4长城附加投保人豁免保险费重大疾病保险B款费率表";
                break;
            case RATE_CCHS:
                filePath = "5长城鸿盛重大疾病保险（2017）费率表";
                break;
            case RATE_CCJFRS:
                filePath = "6长城吉福人生重大疾病保险费率表";
                break;
            case RATE_CCJK:
                filePath = "7长城吉康人生重大疾病保险费率表(含19年）";
                break;
            case RATE_CCJXB:
                filePath = "8长城吉祥保重大疾病保险费率表";
                break;
            case RATE_CCJFT:
                filePath = "9长城金福泰终身重大疾病保险费率表";
                break;
            case RATE_CCYXWL:
                filePath = "10长城赢向未来少儿年金保险费率表";
                break;
            case RATE_CCADAPPNT:
                filePath = "11长城附加投保人豁免保险费重大疾病保险费率表";
                break;
            case RATE_CCJL:
                filePath = "12长城金利年金保险费率表";
                break;
            case RATE_CCJCYS:
                filePath = "13长城金彩一生养老年金保险费率表";
                break;
            case RATE_CCYSS:
                filePath = "14长城盈相随年金保险C款费率表";
                break;

            case RATE_CCADYCYL:
                filePath = "15长城附加重大疾病远程医疗保险费率表";
                break;
            case RATE_CCYXWY:
                filePath = "16长城医享无忧百万医疗保险费率表";
                break;
            case RATE_CCADAXZY:
                filePath = "17长城附加安心住院医疗保险费率表";
                break;
            case RATE_CCADZYGF:
                filePath = "18长城附加住院定额给付医疗保险（2007）费率表";
                break;
            case RATE_CCADZY:
                filePath = "19长城附加住院医疗保险（2007）费率表";
                break;

                //=====中英
            case RATE_ZYADBJK:
                filePath = "1中英人寿附加贝健康重大疾病保险费率表";
                break;
            case RATE_ZYADBWSX:
                filePath = "2中英人寿附加百万随行意外伤害住院津贴医疗保险费率表";
                break;
            case RATE_ZYLXB:
                filePath = "3中英人寿乐相伴两全保险费率表";
                break;
            case RATE_ZYYXSJ:
                filePath = "4中英人寿鑫玺世家终身寿险费率表";
                break;
            //=====长生
            case RATE_CSFZX:
                filePath = "1长生长生福尊享重大疾病保险费率表";
                break;
            //=====百年
            case RATE_BNADAPPNT:
                filePath = "05 百年附加投保人豁免保险费重大疾病保险（2018版） 费率表";
                break;
            //=====信泰
            case RATE_XTQWRS:
                filePath = "信泰发〔2019〕765号_附件_附件2-信泰千万人生（2020）年金保险基本保险金额表";
                break;
            //=====百年
            case RATE_BNADAKB:
                filePath = "百年附加安康保住院医疗保险费率表";
                break;
            //=====阳光人寿
            case RATE_YGXSB:
                filePath = "阳光人寿孝顺保恶性肿瘤疾病保险费率表";
                break;
            //=====临时现价表 中英
            case CASHVALUE_ZYBJK:
                filePath = "1中英人寿附加贝健康重大疾病保险现金价值表";
                break;

            case CASHVALUE_ZYADBWSX:
                filePath = "2中英人寿附加百万随行意外伤害住院津贴医疗保险现金价值表";
                break;
            case CASHVALUE_ZYLXB:
                filePath = "3中英人寿乐相伴两全保险现金价值表";
                break;
            case CASHVALUE_ZYXXSJ:
                filePath = "4中英人寿鑫玺世家终身寿险现金价值表";
                break;
            case CASHVALUE_ZYADAPPNT:
                filePath = "5中英人寿附加投保人保费豁免重大疾病保险现金价值表";
                break;
            case RATE_CS1:
                filePath = "1长生附加鑫康泰两全保险保险费率表";
                break;
            case RATE_CS2:
                filePath = "2长生附加鑫康泰重大疾病保险保险费率表";
                break;
            case RATE_CS3:
                filePath = "3长生鑫康泰养老年金保险保险费率表";
                break;
        }
        return filePath;
    }

    public static String getColumnTitle(int rateCode) {
        String columnTitle = null;
        switch (rateCode) {
            case RATE_TEMPLATE:
                columnTitle = "Sex:性别,Age:年龄,InsPeriod:保险期间,PayPeriod:缴费期间,Rate:费率";
                break;
            case RATE_XTRY:
                columnTitle = "Sex:性别,PayPeriod:缴费期间,GetPeriod:领取年龄,Age:年龄,Rate:费率";
                break;
            /*case RATE_XTZJ:
                filePath = "信泰百万守护（2019）重大疾病保险费率表";
                break;
            case RATE_XTLQ:
                filePath = "信泰附加百万守护（2019）两全保险费率表";
                break;
            case RATE_XTBZJ:
                filePath = "信泰百万守护（2019）B款重大疾病保险费率表";
                break;
            case RATE_XTBLQ:
                filePath = "信泰附加百万守护（2019）B款两全保险费率表";
                break;
            case RATE_XTRY:
                filePath = "信泰如意享（六福版）养老年金保险基本保险金额表";
                break;
            case RATE_HKADBB:
                filePath = "03_弘康附加多倍保疾病保险费率表";
                break;
            case RATE_HKAEXZL:
                filePath = "03_弘康附加恶性肿瘤疾病保险费率表";
                break;*/
            //==长城
            case RATE_CCADHS:
                columnTitle = "Sex:性别,PayPeriod:缴费期间,InsPeriod:保险期间,Age:年龄,Rate:费率";
                break;
            case RATE_CCADJFRS:
                columnTitle = "Sex:性别,PayPeriod:缴费期间,InsPeriod:保险期间,Age:年龄,Rate:费率";
                break;
            case RATE_CCADJKRS:
                columnTitle = "Sex:性别,PayPeriod:缴费期间,InsPeriod:主险保险期间,GetPeriod:领取期间,Age:年龄,Rate:费率";
                break;
            case RATE_CCAPPNT:
                columnTitle = "Sex:性别,PayPeriod:缴费期间,Age:年龄,Rate:费率";
                break;
            case RATE_CCHS:
                columnTitle = "Sex:性别,PayPeriod:缴费期间,InsPeriod:保险期间,Age:年龄,Rate:费率";
                break;
            case RATE_CCJFRS:
                columnTitle = "Sex:性别,PayPeriod:缴费期间,InsPeriod:保险期间,Age:年龄,Rate:费率";
                break;
            case RATE_CCJK:
                columnTitle = "Sex:性别,PayPeriod:缴费期间,InsPeriod:保险期间,Age:年龄,Rate:费率";
                break;
            case RATE_CCJXB:
                columnTitle = "Sex:性别,PayPeriod:缴费期间,InsPeriod:保险期间,Age:年龄,Rate:费率";
                break;
            case RATE_CCJFT:
                columnTitle = "Sex:性别,PayPeriod:缴费期间,InsPeriod:保险期间,Age:年龄,Rate:费率";
                break;
            case RATE_CCYXWL:
                columnTitle = "Sex:性别,PayPeriod:缴费期间,InsPeriod:保险期间,Age:年龄,Rate:费率";
                break;
            case RATE_CCADAPPNT:
                columnTitle = "Sex:性别,PayPeriod:缴费期间,InsPeriod:保险期间,Age:年龄,Rate:费率";
                break;
            case RATE_CCJL:
                columnTitle = "Sex:性别,PayPeriod:缴费期间,Age:年龄,Rate:费率";
                break;
            case RATE_CCJCYS:
                columnTitle = "Sex:性别,PayPeriod:缴费期间,Age:年龄,Rate:费率";
                break;
            case RATE_CCYSS:
                columnTitle = "Sex:性别,PayPeriod:缴费期间,InsPeriod:保险期间,Age:年龄,Rate:费率";
                break;

            case RATE_CCADYCYL:
                columnTitle = "Sex:性别,PayPeriod:缴费期间,SIFlag:有无社保,Age:年龄,Rate:费率";
                break;
            case RATE_CCYXWY:
                columnTitle = "Age:年龄,Amount:保险金额,PayPeriod:缴费期间,SIFlag:有无社保,Rate:费率";
                break;
            case RATE_CCADAXZY:
                columnTitle = "Age:年龄,PayPeriod:保险金额,SIFlag:有无社保,Rate:费率";
                break;
            case RATE_CCADZYGF:
                columnTitle = "Age:年龄,PayPeriod:份数,Rate:费率";
                break;
            case RATE_CCADZY:
                columnTitle = "Age:年龄,PayPeriod:缴费期间,Rate:费率";
                break;
            //=====中英
            case RATE_ZYADBJK:
                columnTitle = "Sex:性别,PayPeriod:缴费期间,InsPeriod:保险期间,Age:年龄,Rate:费率";
                break;
            case RATE_ZYADBWSX:
                columnTitle = "Sex:性别,PayPeriod:缴费期间,InsPeriod:保险期间,Age:年龄,Rate:费率";
                break;
            case RATE_ZYLXB:
                columnTitle = "Sex:性别,PayPeriod:缴费期间,InsPeriod:保险期间,Age:年龄,Rate:费率";
                break;
            case RATE_ZYYXSJ:
                columnTitle = "Sex:性别,PayPeriod:缴费期间,InsPeriod:保险期间,Age:年龄,Rate:费率";
                break;
            //=====长生
            case RATE_CSFZX:
                columnTitle = "Sex:性别,PayPeriod:缴费期间,InsPeriod:保险期间,Age:年龄,Rate:费率";
                break;
            //=====百年
            case RATE_BNADAPPNT:
                columnTitle = "Sex:性别,PayPeriod:缴费期间,Age:年龄,Rate:费率";
                break;
            //=====信泰
            case RATE_XTQWRS:
                columnTitle = "Sex:性别,PayPeriod:缴费期间,Age:年龄,Rate:费率";
                break;
            //=====百年
            case RATE_BNADAKB:
                columnTitle = "Amount:保险金额,PayPeriod:有无社保,Age:年龄,Rate:费率";
                break;
            //=====阳光人寿
            case RATE_YGXSB:
                columnTitle = "Sex:性别,PayPeriod:缴费期间,Age:年龄,Rate:费率";
                break;
            //=====临时现价表 中英
            case CASHVALUE_ZYBJK:
                columnTitle = "PayPeriod:缴费期间,Sex:性别,Age:年龄,GetPeriod:保单年度,Rate:费率";
                break;
            case CASHVALUE_ZYADBWSX:
                columnTitle = "Amount:缴费期间,InsPeriod:保险期间,Sex:性别,Age:年龄,PayPeriod:保单年度,Rate:费率";
                break;
            case CASHVALUE_ZYLXB:
                columnTitle = "Amount:缴费期间,InsPeriod:保险期间,Sex:性别,Age:年龄,PayPeriod:保单年度,Rate:费率";
                break;
            case CASHVALUE_ZYXXSJ:
                columnTitle = "Amount:缴费期间,InsPeriod:保险期间,Sex:性别,Age:年龄,PayPeriod:保单年度,Rate:费率";
                break;
            case CASHVALUE_ZYADAPPNT:
                columnTitle = "InsPeriod:缴费期间,Sex:性别,Age:年龄,PayPeriod:保单年度,Rate:费率";
                break;
            case RATE_CS1:
                columnTitle = "Sex:性别,Age:年龄,InsPeriod:保险期间,PayPeriod:缴费期间,Rate:费率";
                break;
            case RATE_CS2:
                columnTitle = "Sex:性别,Age:年龄,InsPeriod:保险期间,PayPeriod:缴费期间,Rate:费率";
                break;
            case RATE_CS3:
                columnTitle = "Sex:性别,Age:年龄,InsPeriod:保险期间,PayPeriod:缴费期间,Rate:费率";
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
    public static PickFactorOfRate getInstance(int rateCode, String columnTitle){
        switch (rateCode) {
            case RATE_TEMPLATE:
                return new PickFactorOfRate(3,126,0,9,columnTitle);
            //==长城
            case RATE_CCADHS:
                return new PickFactorOfRate(2,523,0,7,columnTitle);
            case RATE_CCADJFRS:
                return new PickFactorOfRate(2,339,0,9,columnTitle);
            case RATE_CCADJKRS:
                return new PickFactorOfRate(2,767,0,11,columnTitle);
            case RATE_CCAPPNT:
                return new PickFactorOfRate(2,89,0,29,columnTitle);
            case RATE_CCHS:
                return new PickFactorOfRate(2,523,0,7,columnTitle);
            case RATE_CCJFRS:
                return new PickFactorOfRate(2,125,0,9,columnTitle);
            case RATE_CCJK:
                return new PickFactorOfRate(2,491,0,10,columnTitle);
            case RATE_CCJXB:
                return new PickFactorOfRate(2,349,0,8,columnTitle);
            case RATE_CCJFT:
                return new PickFactorOfRate(2,135,0,7,columnTitle);
            case RATE_CCYXWL:
                return new PickFactorOfRate(2,29,0,6,columnTitle);
            case RATE_CCADAPPNT:
                return new PickFactorOfRate(2,757,0,10,columnTitle);
            case RATE_CCJL:
                return new PickFactorOfRate(2,125,0,5,columnTitle);
            case RATE_CCJCYS:
                return new PickFactorOfRate(2,115,0,5,columnTitle);
            case RATE_CCYSS:
                return new PickFactorOfRate(2,379,0,6,columnTitle);

            case RATE_CCADYCYL:
                return new PickFactorOfRate(2,27,0,3,columnTitle);
            case RATE_CCYXWY:
                return new PickFactorOfRate(2,55,0,3,columnTitle);
            case RATE_CCADAXZY:
                return new PickFactorOfRate(2,27,0,7,columnTitle);
            case RATE_CCADZYGF:
                return new PickFactorOfRate(2,14,0,6,columnTitle);
            case RATE_CCADZY:
                return new PickFactorOfRate(2,15,0,1,columnTitle);

            //=====中英
            case RATE_ZYADBJK:
                return new PickFactorOfRate(2,83,0,6,columnTitle);
            case RATE_ZYADBWSX:
                return new PickFactorOfRate(2,175,0,5,columnTitle);
            case RATE_ZYLXB:
                return new PickFactorOfRate(2,155,0,5,columnTitle);
            case RATE_ZYYXSJ:
                return new PickFactorOfRate(2,145,0,6,columnTitle);

                //=====长生
            case RATE_CSFZX:
                return new PickFactorOfRate(2,135,0,10,columnTitle);
            //=====百年
            case RATE_BNADAPPNT:
                return new PickFactorOfRate(3,90,0,7,columnTitle);
            //=====信泰
            case RATE_XTQWRS:
                return new PickFactorOfRate(3,146,0,4,columnTitle);
            //=====百年
            case RATE_BNADAKB:
                return new PickFactorOfRate(3,36,0,3,columnTitle);
                /*case RATE_XTZJ:
                return new PickFactorOfRate(3,126,0,9,columnTitle);
            case RATE_XTLQ:
                return new PickFactorOfRate(3,310,0,9,columnTitle);
            case RATE_XTBZJ:
                return new PickFactorOfRate(3,126,0,9,columnTitle);
            case RATE_XTBLQ:
                return new PickFactorOfRate(3,310,0,9,columnTitle);
            case RATE_XTRY:
                return new PickFactorOfRate(3,449,0,7,columnTitle);
            case RATE_HKADBB:
                return new PickFactorOfRate(3,136,0,22,columnTitle);
            case RATE_HKAEXZL:
                return new PickFactorOfRate(3,136,0,22,columnTitle);*/
            case RATE_XTRY:
                return new PickFactorOfRate(3,449,0,7,columnTitle);
            //=====阳光人寿
            case RATE_YGXSB:
                return new PickFactorOfRate(3,56,0,5,columnTitle);
            //=====临时现价表 中英
            case CASHVALUE_ZYBJK:
                return new PickFactorOfRate(2,323,0,22,columnTitle);
            case CASHVALUE_ZYADBWSX:
                return new PickFactorOfRate(2,459,0,60,columnTitle);
            case CASHVALUE_ZYLXB:
                return new PickFactorOfRate(3,400,0,60,columnTitle);
            case CASHVALUE_ZYXXSJ:
                return new PickFactorOfRate(3,532,0,108,columnTitle);
            case CASHVALUE_ZYADAPPNT:
                return new PickFactorOfRate(3,2008,0,107,columnTitle);
            case RATE_CS1:
                return new PickFactorOfRate(3,116,0,7,columnTitle);
            case RATE_CS2:
                return new PickFactorOfRate(3,116,0,7,columnTitle);
            case RATE_CS3:
                return new PickFactorOfRate(3,116,0,7,columnTitle);
        }
        return null;
    }
}
