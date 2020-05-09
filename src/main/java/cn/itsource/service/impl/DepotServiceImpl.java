package cn.itsource.service.impl;

import cn.itsource.domain.Depot;
import cn.itsource.repository.IDepotRepository;
import cn.itsource.service.IDepotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * (Depot)表Service层接口
 *
 * @author 申林
 * @since 2020-05-09 14:21:55
 */
 @Service
public class DepotServiceImpl extends BaseServiceImpl<Depot,Long> implements IDepotService{

    private IDepotRepository depotRepository;

    @Autowired
    public void setDepotRepository(IDepotRepository depotRepository) {
        this.depotRepository = depotRepository;
    }
}