package com.sargeraswang.util.ExcelUtil.area;

import com.sargeraswang.util.ExcelUtil.occupation.OccupationGenerator;
import com.sargeraswang.util.ExcelUtil.yxyrate.PickFactorOfRate;

/**
 * @author yuxy_cyd
 * @version v-1.1.0
 * @Description:TODO(地区码表静态数据)
 * @date: 2019年12月12日 17:58
 **/
public class AreaFactory {
    public final static int AREA_TEMPLATE = 0;

    public static String getRateFile(int rateCode) {
        String filePath = null;
        switch (rateCode) {
            case AREA_TEMPLATE:
                filePath = "地区码表";
                break;
        }
        return filePath;
    }


}
