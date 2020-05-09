package cn.itsource.service.impl;

import cn.itsource.domain.Stockincomebillitem;
import cn.itsource.repository.IStockincomebillitemRepository;
import cn.itsource.service.IStockincomebillitemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * (Stockincomebillitem)表Service层接口
 *
 * @author 申林
 * @since 2020-05-09 14:22:00
 */
 @Service
public class StockincomebillitemServiceImpl extends BaseServiceImpl<Stockincomebillitem,Long> implements IStockincomebillitemService{

    private IStockincomebillitemRepository stockincomebillitemRepository;

    @Autowired
    public void setStockincomebillitemRepository(IStockincomebillitemRepository stockincomebillitemRepository) {
        this.stockincomebillitemRepository = stockincomebillitemRepository;
    }
}