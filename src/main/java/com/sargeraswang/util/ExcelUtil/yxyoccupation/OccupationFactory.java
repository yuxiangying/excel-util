package com.sargeraswang.util.ExcelUtil.yxyoccupation;

import com.sargeraswang.util.ExcelUtil.yxyrate.PickFactorOfRate;

/**
 * @author yuxy_cyd
 * @version v-1.1.0
 * @Description:TODO(费率表静态数据)
 * @date: 2019年12月12日 17:58
 **/
public class OccupationFactory {
    public final static int RATE_TEMPLATE = 0;

    public final static int RATE_XTZJ = 1;

    public final static int RATE_XTLQ = 2;

    public final static int RATE_XTBZJ = 3;

    public final static int RATE_XTBLQ = 4;

    public final static int RATE_XTRY = 5;

    public final static int RATE_HKADBB = 6;

    public final static int RATE_HKAEXZL = 7;

    public static String getRateFile(int rateCode) {
        String filePath = null;
        switch (rateCode) {
            case RATE_TEMPLATE:
                filePath = "费率表";
                break;
            case RATE_XTZJ:
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
    public static PickFactorOfRate getInstance(int rateCode, String columnTitle){
        switch (rateCode) {
            case RATE_TEMPLATE:
                return new PickFactorOfRate(3,126,0,9,columnTitle);
            case RATE_XTZJ:
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
                return new PickFactorOfRate(3,136,0,22,columnTitle);
        }
        return null;
    }
}
