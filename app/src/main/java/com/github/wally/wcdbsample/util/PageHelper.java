package com.github.wally.wcdbsample.util;


import com.github.wally.wcdbsample.common.model.vo.Page;

/**
 * Package: com.sayeeit.ms.hpc.service.util
 * FileName: PageUtil
 * Date: on 2018/7/26  上午10:59
 * Auther: hezihao
 * Descirbe:
 */
public class PageHelper<T> {
    /**
     * 计算分页起始位置
     *
     * @param pageNum  当前页码
     * @param pageSize 要查询的条数
     */
    public static int calculatePageStartIndex(int pageNum, int pageSize) {
        return (pageNum - 1) * pageSize;
    }

    /**
     * 组装分页数量VO
     *
     * @param pageSize 要查询的条数
     * @param total    表的总条数
     * @param pageNum  当前页码
     */
    public Page<T> handlePage(int pageSize, long total, int pageNum) {
        Page<T> vo = new Page<>();
        //计算总页数
        long pages = calculatePages(total, pageSize);
        vo.setSize(pageSize);
        vo.setPages(pages);
        vo.setTotal(total);
        vo.setCurrent(pageNum);
        return vo;
    }

    /**
     * 计算总页数
     *
     * @param totalCount 数据总条数
     * @param pageSize   一页的条数
     * @return 分页后的总页数
     */
    private static long calculatePages(long totalCount, long pageSize) {
        //如果刚好能够整除平分，就直接返回整除
        if (totalCount % pageSize == 0) {
            return totalCount / pageSize;
        } else {
            //除了能够填满的那几页，剩下的不能填满一页，就加上一页来显示
            return (totalCount / pageSize) + 1;
        }
    }
}