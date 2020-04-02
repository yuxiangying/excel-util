package com.sargeraswang.util.ExcelUtil.yxyrate;

/**
 * @author yuxy_cyd
 * @version v-1.1.0
 * @Description:TODO(费率表实体)
 * @date: 2019年12月12日 11:31
 **/
public class RateDTO {
    private String sex;//性别 0-1
    private String payPeriod;//缴费期间 1Y-2Y-10Y
    private String insPeriod;//保险期间 1Y-2Y-10Y
    private String mainRiskInsPeriod;//主险保险期间 1Y-2Y-10Y
    private String mainRiskAmount;//主险保额
    private String age;//年龄 0-1-2
    private String getPeriod;//领取年龄
    private String sIFlag;//有无社保 Y-N
    private String amount;//保险金额
    private String mult;//份数
    private String rate;//费率

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPayPeriod() {
        return payPeriod;
    }

    public void setPayPeriod(String payPeriod) {
        this.payPeriod = payPeriod;
    }

    public String getInsPeriod() {
        return insPeriod;
    }

    public void setInsPeriod(String insPeriod) {
        this.insPeriod = insPeriod;
    }

    public String getMainRiskInsPeriod() {
        return mainRiskInsPeriod;
    }

    public void setMainRiskInsPeriod(String mainRiskInsPeriod) {
        this.mainRiskInsPeriod = mainRiskInsPeriod;
    }

    public String getMainRiskAmount() {
        return mainRiskAmount;
    }

    public void setMainRiskAmount(String mainRiskAmount) {
        this.mainRiskAmount = mainRiskAmount;
    }

    public String getGetPeriod() {
        return getPeriod;
    }

    public void setGetPeriod(String getPeriod) {
        this.getPeriod = getPeriod;
    }

    public String getSocialSecurityFlag() {
        return sIFlag;
    }

    public void setSocialSecurityFlag(String sIFlag) {
        this.sIFlag = sIFlag;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getSIFlag() {
        return sIFlag;
    }

    public void setSIFlag(String sIFlag) {
        this.sIFlag = sIFlag;
    }

    public String getMult() {
        return mult;
    }

    public void setMult(String mult) {
        this.mult = mult;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
