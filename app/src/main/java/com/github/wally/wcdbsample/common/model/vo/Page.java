package com.github.wally.wcdbsample.common.model.vo;

import java.util.List;

/**
 * Package: com.github.wally.wcdbsample.common.model.vo
 * FileName: Page
 * Date: on 2018/8/5  上午12:33
 * Auther: zihe
 * Descirbe:
 * Email: hezihao@linghit.com
 */
public class Page<T> {
    private int size = 10;
    private long pages;
    private long total;
    private int current;
    private List<T> records;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long getPages() {
        return pages;
    }

    public void setPages(long pages) {
        this.pages = pages;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }
}