package cn.itsource.query;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;


/**
 * 抽取高级查询和分页
 */
public abstract class BaseQuery<T> {/*泛型*/
    /**
     * 当前第几页
     */
    private Integer currentPage = 1;
    /**
     * 读取条数
     */
    private Integer pageSize = 10;
    /**
     * 排序字段
     */
    private String orderFiled;
    /**
     * 降序
     */
    private String orderType;

    public abstract Specification getSpecification();/*多条件查询*/

    public abstract Pageable getPageable();/*分页*/

    public abstract Sort getSort();/*排序*/

    public Integer start() {
        return (this.currentPage - 1) * this.pageSize;
    }

    public Integer getCurrentPage() {
        return currentPage - 1;/*Spring data jap 是从0开始时的*/
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

    public String getOrderFiled() {
        return orderFiled;
    }

    public void setOrderFiled(String orderFiled) {
        this.orderFiled = orderFiled;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
}
