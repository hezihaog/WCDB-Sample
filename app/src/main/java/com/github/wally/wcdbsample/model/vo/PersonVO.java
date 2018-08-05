package com.github.wally.wcdbsample.model.vo;

import com.github.wally.wcdbsample.common.model.vo.BaseVO;

/**
 * Package: com.github.wally.wcdbsample.model.vo
 * FileName: PersonVO
 * Date: on 2018/8/4  下午12:52
 * Auther: zihe
 * Descirbe:
 * Email: hezihao@linghit.com
 */
public class PersonVO extends BaseVO {
    private String personName;

    private String sex;

    private Integer age;

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "PersonVO{" +
                "personName='" + personName + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                '}';
    }
}