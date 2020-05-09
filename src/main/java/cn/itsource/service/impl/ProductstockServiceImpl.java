package cn.itsource.service.impl;

import cn.itsource.domain.Productstock;
import cn.itsource.repository.IProductstockRepository;
import cn.itsource.service.IProductstockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * (Productstock)表Service层接口
 *
 * @author 申林
 * @since 2020-05-09 14:21:58
 */
 @Service
public class ProductstockServiceImpl extends BaseServiceImpl<Productstock,Long> implements IProductstockService{

    private IProductstockRepository productstockRepository;

    @Autowired
    public void setProductstockRepository(IProductstockRepository productstockRepository) {
        this.productstockRepository = productstockRepository;
    }
}