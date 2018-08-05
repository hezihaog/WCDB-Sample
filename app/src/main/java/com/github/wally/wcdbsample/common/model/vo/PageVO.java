package com.github.wally.wcdbsample.common.model.vo;


import java.util.List;
import java.util.Map;

/**
 * Api返回分页结果的专用视图类
 * 泛型T传入结果数据集VO的类以便swagger生成注释
 */
public class PageVO<T> {
    private int pageSize = 10;
    private long pages;
    private long total;
    private int pageNum;
    private List<T> records;

    private List<Map<String, String>> chooseList;

    public PageVO() {
    }

    /**
     * 构造
     * @param page
     * @param dataList 查询结果集
     */
    public PageVO(Page<T> page, List<T> dataList){
        this.pageSize = page.getSize();
        this.pages = page.getPages();
        this.total = page.getTotal();
        this.pageNum = page.getCurrent();
        this.records = dataList;
    }

    /**
     * 构造
     * @param page
     */
    public PageVO(Page<T> page){
        this.pageSize = page.getSize();
        this.pages = page.getPages();
        this.total = page.getTotal();
        this.pageNum = page.getCurrent();
        this.records = page.getRecords();
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
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

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }
}