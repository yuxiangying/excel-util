package com.sargeraswang.util.ExcelUtil.vo.area;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuxy_cyd
 * @version v-1.1.0
 * @Description:TODO(地区编码转换实体)
 * @date: 2019年11月22日 9:52
 **/
public class AreaCode {

    private String code;
    private List<ProvinceCode> content = new ArrayList<>();

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<ProvinceCode> getContent() {
        return content;
    }

    public void setContent(List<ProvinceCode> content) {
        this.content = content;
    }

    //省编码
    public static class ProvinceCode{
        private String value;//编码
        private String text;//名称
        List<CityCode> children = new ArrayList<>();

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public List<CityCode> getChildren() {
            return children;
        }

        public void setChildren(List<CityCode> children) {
            this.children = children;
        }
    }

    //市编码
    public static class CityCode{
        private String value;//编码
        private String text;//名称
        List<CountyCode> children = new ArrayList<>();

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public List<CountyCode> getChildren() {
            return children;
        }

        public void setChildren(List<CountyCode> children) {
            this.children = children;
        }
    }

    //区县编码
    public static class CountyCode{
        private String value;//编码
        private String text;//名称

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}
