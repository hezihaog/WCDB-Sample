package com.github.wally.wcdbsample.common.model.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * Package: com.github.wally.wcdbsample.common.model.vo
 * FileName: BaseVO
 * Date: on 2018/8/4  下午1:25
 * Auther: zihe
 * Descirbe:
 * Email: hezihao@linghit.com
 */
public class BaseVO implements Serializable {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}