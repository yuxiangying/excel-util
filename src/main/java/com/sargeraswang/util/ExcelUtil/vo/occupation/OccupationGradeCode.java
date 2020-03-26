package com.sargeraswang.util.ExcelUtil.vo.occupation;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuxy_cyd
 * @version v-1.1.0
 * @Description:TODO(职业等级编码转换实体)
 * @date: 2019年11月22日 13:47
 **/
public class OccupationGradeCode {
    List<PrimaryClassifyCode> content = new ArrayList<>();

    public List<PrimaryClassifyCode> getContent() {
        return content;
    }

    public void setContent(List<PrimaryClassifyCode> content) {
        this.content = content;
    }

    //大分类编码
    public static class PrimaryClassifyCode {
        private String label;//职业名称
        List<SecondaryClassifyCode> children = new ArrayList<>();

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public List<SecondaryClassifyCode> getChildren() {
            return children;
        }

        public void setChildren(List<SecondaryClassifyCode> children) {
            this.children = children;
        }
    }

    //中分类编码
    public static class SecondaryClassifyCode {
        private String label;//职业名称
        List<OccupationCode> children = new ArrayList<>();

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public List<OccupationCode> getChildren() {
            return children;
        }

        public void setChildren(List<OccupationCode> children) {
            this.children = children;
        }
    }

    //职业编码
    public static class OccupationCode {
        private String code;//职业代码
        private String label;//职业名称
        private String grade;//职业等级

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }
    }


}
