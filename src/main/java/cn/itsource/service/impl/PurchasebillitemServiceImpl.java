package cn.itsource.service.impl;

import cn.itsource.domain.Purchasebillitem;
import cn.itsource.repository.IPurchasebillitemRepository;
import cn.itsource.service.IPurchasebillitemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * (Purchasebillitem)表Service层接口
 *
 * @author 申林
 * @since 2020-05-06 10:28:42
 */
 @Service
public class PurchasebillitemServiceImpl extends BaseServiceImpl<Purchasebillitem,Long> implements IPurchasebillitemService{

    private IPurchasebillitemRepository purchasebillitemRepository;

    @Autowired
    public void setPurchasebillitemRepository(IPurchasebillitemRepository purchasebillitemRepository) {
        this.purchasebillitemRepository = purchasebillitemRepository;
    }
}