package com.sargeraswang.util.ExcelUtil.cvrate;

/**
 * @author yuxy_cyd
 * @version v-1.1.0
 * @Description:TODO(现金价值实体)
 * @date: 2019年12月03日 10:28
 **/
public class CashValueDTO implements Cloneable{
    private String age;
    private String sex ;
    private String payPeriod;
    private String policyYear;
    private String insPeriod;
    private String cVRate;
    private String sa0;

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
