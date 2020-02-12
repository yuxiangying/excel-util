package com.sargeraswang.util.ExcelUtil.cvrate;

/**
 * @author yuxy_cyd
 * @version v-1.1.0
 * @Description:TODO(现金价值实体)
 * @date: 2019年12月03日 10:28
 **/
public class CashValueDTO implements Cloneable{
    private String age;//年龄
    private String sex ;//性别
    private String payPeriod;//交费期间
    private String policyYear;//保单年度
    private String insPeriod;//保险期间
    private String cVRate;//现金价值
    private String sa0;//基本保额金额

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

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

    public String getPolicyYear() {
        return policyYear;
    }

    public void setPolicyYear(String policyYear) {
        this.policyYear = policyYear;
    }

    public String getInsPeriod() {
        return insPeriod;
    }

    public void setInsPeriod(String insPeriod) {
        this.insPeriod = insPeriod;
    }

    public String getCVRate() {
        return cVRate;
    }

    public void setCVRate(String cVRate) {
        this.cVRate = cVRate;
    }

    public String getSa0() {
        return sa0;
    }

    public void setSa0(String sa0) {
        this.sa0 = sa0;
    }
}
