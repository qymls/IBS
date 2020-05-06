package cn.itsource.service.impl;

import cn.itsource.domain.Supplier;
import cn.itsource.repository.ISupplierRepository;
import cn.itsource.service.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * (Supplier)表Service层接口
 *
 * @author 申林
 * @since 2020-05-06 10:28:42
 */
 @Service
public class SupplierServiceImpl extends BaseServiceImpl<Supplier,Long> implements ISupplierService{

    private ISupplierRepository supplierRepository;

    @Autowired
    public void setSupplierRepository(ISupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }
}