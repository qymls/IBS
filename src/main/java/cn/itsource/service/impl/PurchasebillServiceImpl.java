package cn.itsource.service.impl;

import cn.itsource.domain.Purchasebill;
import cn.itsource.repository.IPurchasebillRepository;
import cn.itsource.service.IPurchasebillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * (Purchasebill)表Service层接口
 *
 * @author 申林
 * @since 2020-05-06 10:28:40
 */
 @Service
public class PurchasebillServiceImpl extends BaseServiceImpl<Purchasebill,Long> implements IPurchasebillService{

    private IPurchasebillRepository purchasebillRepository;

    @Autowired
    public void setPurchasebillRepository(IPurchasebillRepository purchasebillRepository) {
        this.purchasebillRepository = purchasebillRepository;
    }
}