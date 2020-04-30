package cn.itsource.service.impl;

import cn.itsource.domain.Systemdictionarydetail;
import cn.itsource.repository.ISystemdictionarydetailRepository;
import cn.itsource.service.ISystemdictionarydetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * (Systemdictionarydetail)表Service层接口
 *
 * @author 申林
 * @since 2020-04-30 11:50:12
 */
 @Service
public class SystemdictionarydetailServiceImpl extends BaseServiceImpl<Systemdictionarydetail,Long> implements ISystemdictionarydetailService{

    private ISystemdictionarydetailRepository systemdictionarydetailRepository;

    @Autowired
    public void setSystemdictionarydetailRepository(ISystemdictionarydetailRepository systemdictionarydetailRepository) {
        this.systemdictionarydetailRepository = systemdictionarydetailRepository;
    }
}