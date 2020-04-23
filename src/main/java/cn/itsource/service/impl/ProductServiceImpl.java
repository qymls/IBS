package cn.itsource.service.impl;

import cn.itsource.domain.Product;
import cn.itsource.repository.IProductRepository;
import cn.itsource.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * (Product)表Service层接口
 *
 * @author 申林
 * @since 2020-04-23 21:39:49
 */
 @Service
public class ProductServiceImpl extends BaseServiceImpl<Product,Long> implements IProductService{

    private IProductRepository productRepository;

    @Autowired
    public void setProductRepository(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }
}