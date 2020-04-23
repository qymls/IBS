package cn.itsource.util;

public class Paging {
    /**
     * 当前第几页
     */
    private Integer currentPage = 1;
    /**
     * 读取条数
     */
    private Integer pageSize = 10;

    public Paging() {
    }

    public Integer start() {
        return (this.currentPage - 1) * this.pageSize;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        if (currentPage == null) {
            currentPage = 1;
        }
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        if (pageSize == null) {
            pageSize = 10;
        }
        this.pageSize = pageSize;
    }

}
