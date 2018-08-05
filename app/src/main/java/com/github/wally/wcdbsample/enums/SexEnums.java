package com.github.wally.wcdbsample.enums;

/**
 * Package: com.github.wally.wcdbsample.enums
 * FileName: SexEnums
 * Date: on 2018/8/4  下午1:55
 * Auther: zihe
 * Descirbe:
 * Email: hezihao@linghit.com
 */
public enum SexEnums implements IEnums {
    male("1", "男"),
    femal("2", "女");

    private String mCode;
    private String mDesc;

    SexEnums(String code, String desc) {
        mCode = code;
        mDesc = desc;
    }

    @Override
    public String getCode() {
        return this.mCode;
    }

    @Override
    public String getDesc() {
        return this.mDesc;
    }
}
