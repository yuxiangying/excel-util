package com.sargeraswang.util.ExcelUtil.yxyrate;

/**
 * @author yuxy_cyd
 * @version v-1.1.0
 * @Description:TODO(费率表实体)
 * @date: 2019年12月12日 11:31
 **/
public class RateDTO {
    private String sex;//性别
    private String payPeriod;//交费期间
    private String insPeriod;//保险期间
    private String age;//年龄
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
