package com.github.wally.wcdbsample.model.dto;

import java.io.Serializable;

/**
 * Package: com.github.wally.wcdbsample.model.dto
 * FileName: PersonDTO
 * Date: on 2018/8/4  下午12:51
 * Auther: zihe
 * Descirbe:
 * Email: hezihao@linghit.com
 */
public class PersonDTO implements Serializable {
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
}