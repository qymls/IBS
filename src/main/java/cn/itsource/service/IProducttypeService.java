package cn.itsource.service;

import cn.itsource.domain.Producttype;
import cn.itsource.query.ProducttypeQuery;
import cn.itsource.util.PageUtil;

import java.util.List;

/**
 * (Producttype)表Service层接口
 *
 * @author 申林
 * @since 2020-04-30 11:50:13
 */
public interface IProducttypeService extends IBaseService<Producttype, Long> {
    PageUtil<Producttype> findAllHasChild(ProducttypeQuery producttypeQuery);

    List<Long> findAllParentByID(Long id);

    Long saveReturnParam(Producttype producttype);/*插入数据返回主键*/

    List<Producttype> findAllLastProducttype();/*获取所有最后一级的商品类型*/

}