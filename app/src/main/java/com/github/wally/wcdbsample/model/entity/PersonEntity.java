package com.github.wally.wcdbsample.model.entity;

import com.github.wally.wcdbsample.common.model.entity.BaseEntity;

/**
 * Package: com.github.wally.wcdb_sample.entity
 * FileName: PersonEntity
 * Date: on 2018/8/4  下午12:13
 * Auther: zihe
 * Descirbe:
 * Email: hezihao@linghit.com
 */
public class PersonEntity extends BaseEntity {
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