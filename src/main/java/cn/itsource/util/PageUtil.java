package cn.itsource.util;

import java.util.List;

public class PageUtil<T> {
    /**
     * 首页
     */
    private Integer firstPage;
    /**
     * 最后一页
     */
    private Integer lastPage;
    /**
     * 当前第几页
     */
    private Integer currentPage;
    /**
     * 一页多少条数据
     */
    private Integer pageSize;
    /**
     * 总记录数
     */
    private Integer totalRows;
    /**
     * 总页数
     */
    private Integer totalPage;
    /**
     * 下一页
     */
    private Integer nextPage;
    /**
     * 上一页
     */
    private Integer prePage;

    /**
     * 传递值，总记录数，等计算
     */
    private List<T> list;

    public PageUtil() {
    }

    public PageUtil(Integer currentPage, Integer pageSize, Integer totalRows) {
        this.firstPage = 1;
        this.currentPage = currentPage < 1 ? 1 : currentPage;/*处理用户手动输入的情况*/
        this.pageSize = pageSize < 1 ? 10 : pageSize;/*可以不判断*/
        this.totalRows = totalRows;
        /*计算总页数（总记录数+页面大小-1）/页面大小,总记录数不能为0*/
        this.totalPage = (this.totalRows + this.pageSize - 1) / this.pageSize;
        this.currentPage = this.currentPage > this.totalPage ? this.totalPage : this.currentPage;/*处理用户手动输入的情况*/
        /*下一页如果是最后一页，就是当前页*/
        this.nextPage = (this.currentPage + 1) > totalPage ? totalPage : this.currentPage + 1;
        /*上一页是第一页那么上一页就是第一页*/
        this.prePage = (this.currentPage - 1) < 1 ? 1 : this.currentPage - 1;
        this.lastPage = totalPage;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Integer getFirstPage() {
        return firstPage;
    }

    public Integer getLastPage() {
        return lastPage;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Integer getTotalRows() {
        return totalRows;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public Integer getNextPage() {
        return nextPage;
    }

    public Integer getPrePage() {
        return prePage;
    }

    public List<T> getList() {
        return list;
    }

    public void setFirstPage(Integer firstPage) {
        this.firstPage = firstPage;
    }

    public void setLastPage(Integer lastPage) {
        this.lastPage = lastPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setTotalRows(Integer totalRows) {
        this.totalRows = totalRows;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public void setNextPage(Integer nextPage) {
        this.nextPage = nextPage;
    }

    public void setPrePage(Integer prePage) {
        this.prePage = prePage;
    }

    @Override
    public String toString() {
        return "PageUtil{" +
                "firstPage=" + firstPage +
                ", lastPage=" + lastPage +
                ", currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                ", totalRows=" + totalRows +
                ", totalPage=" + totalPage +
                ", nextPage=" + nextPage +
                ", prePage=" + prePage +
                ", list=" + list.size() +
                '}';
    }
}
