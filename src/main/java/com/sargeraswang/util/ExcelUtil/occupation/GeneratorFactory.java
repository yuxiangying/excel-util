package com.sargeraswang.util.ExcelUtil.occupation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public abstract class GeneratorFactory {

    public final static int INSURER_AIXIN = 0;

    public final static int INSURER_FUXINGBDX = 1;

    public final static int INSURER_FUXINGLHJK = 2;

    public final static int INSURER_KUNLUNJK = 3;

    public final static int INSURER_WANGJIN = 4;

    public final static int INSURER_YIAN = 5;

    public final static int INSURER_BEIJING = 6;

    public final static int INSURER_BAINIAN = 7;

    public final static int INSURER_SHANGHAI = 8;

    public final static int INSURER_HZ = 9;


    private GeneratorFactory() {
    }


    public static OccupationGenerator getInstance(int insurerCode) {

        switch (insurerCode) {
            case INSURER_AIXIN:
                return new OccupationGeneratorAiXin(4, 1657);
            case INSURER_FUXINGBDX:
                return new OccupationGeneratorFuXingBDX(2, 2146);
            case INSURER_FUXINGLHJK:
                return new OccupationGeneratorFuXingLHJK(3, 842);
            case INSURER_KUNLUNJK:
                return new OccupationGeneratorKunLunJK(2, 633);
            case INSURER_WANGJIN:
                return new OccupationGeneratorWangJin(1, 2548);
            case INSURER_YIAN:
                return new OccupationGeneratorYiAn(2, 1172);
            case INSURER_BEIJING:
                return new OccupationGeneratorBeiJin(3, 2145);
            case INSURER_BAINIAN:
                return new OccupationGeneratorBaiNian(3, 2161);
            case INSURER_SHANGHAI:
                return new OccupationGeneratorShangHai(2, 2045);
            case INSURER_HZ:
                return new OccupationGeneratorShangHai(2, 1213);
        }
        return null;
    }


    public static InputStream getOccupationFile(int insurerCode) {

        InputStream in = null;
        String filePath = null;

        switch (insurerCode) {
            case INSURER_AIXIN:
                filePath = "D:/java/guohua/ExcelUtil/excel/爱心人寿.xlsx";
                break;
            case INSURER_FUXINGLHJK:
                filePath = "D:/java/guohua/ExcelUtil/excel/复星联合.xlsx";
                break;
            case INSURER_KUNLUNJK:
                filePath = "D:/java/guohua/ExcelUtil/excel/昆仑健康.xlsx";
                break;
            case INSURER_WANGJIN:
                filePath = "/Users/wangpeng/git/GitHub/ExcelUtil/excel/网金保险.xlsx";
                break;
            case INSURER_YIAN:
                filePath = "/Users/wangpeng/git/GitHub/ExcelUtil/excel/易安保险.xlsx";
                break;
            case INSURER_FUXINGBDX:
                filePath = "/Users/wangpeng/git/GitHub/ExcelUtil/excel/复星保德信.xlsx";
                break;
            case INSURER_BEIJING:
                filePath = "/Users/wangpeng/git/GitHub/ExcelUtil/excel/北京人寿.xlsx";
                break;
            case INSURER_BAINIAN:
                filePath = "/Users/wangpeng/git/GitHub/ExcelUtil/excel/百年人寿.xlsx";
                break;
            case INSURER_SHANGHAI:
                filePath = "C:/ideaProject/guohua/excel-util/excel/上海人寿.xlsx";
                break;
            case INSURER_HZ:
                filePath = "C:/ideaProject/guohua/excel-util/excel/众惠职业编码new.xlsx";
                break;

        }
        if (filePath == null || "".equals(filePath)) {
            throw new RuntimeException("文件路径为空！");
        }
        try {
            in = new FileInputStream(new File(filePath));
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }
        return in;
    }


    public static String getOccupationFileName(int insurerCode) {

        String fileName = null;

        switch (insurerCode) {
            case INSURER_AIXIN:
                fileName = "爱心人寿";
                break;
            case INSURER_FUXINGLHJK:
                fileName = "复星联合";
                break;
            case INSURER_KUNLUNJK:
                fileName = "昆仑健康";
                break;
            case INSURER_WANGJIN:
                fileName = "网金保险";
                break;
            case INSURER_YIAN:
                fileName = "易安保险.";
                break;
            case INSURER_FUXINGBDX:
                fileName = "复星保德信";
                break;
            case INSURER_BEIJING:
                fileName = "北京人寿";
                break;
            case INSURER_BAINIAN:
                fileName = "百年人寿";
                break;
            case INSURER_SHANGHAI:
                fileName = "上海人寿职业编码";
                break;
            case INSURER_HZ:
                fileName = "众惠职业编码new";
                break;

        }
        if (fileName == null || "".equals(fileName)) {
            throw new RuntimeException("文件路径为空！");
        }

        return fileName;
    }

}
