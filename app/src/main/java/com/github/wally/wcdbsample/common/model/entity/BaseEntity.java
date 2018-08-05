package com.github.wally.wcdbsample.common.model.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Package: com.github.wally.wcdb_sample.entity
 * FileName: BaseEntity
 * Date: on 2018/8/4  下午12:24
 * Auther: zihe
 * Descirbe:
 * Email: hezihao@linghit.com
 */
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键，UUID
     */
    protected String id;
    /**
     * 创建时间
     */
    protected Date createTime = new Date();
    /**
     * 修改时间
     */
    protected Date updateTime = new Date();
    /**
     * 删除标识，0：未删除，1：删除
     */
    protected Integer deleteFlag = 0;
    /**
     * 版本号，默认1
     */
    protected Integer version = 1;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}