package cn.itsource.service.impl;

import cn.itsource.domain.Systemdictionarytype;
import cn.itsource.repository.ISystemdictionarytypeRepository;
import cn.itsource.service.ISystemdictionarytypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * (Systemdictionarytype)表Service层接口
 *
 * @author 申林
 * @since 2020-04-30 11:50:11
 */
 @Service
public class SystemdictionarytypeServiceImpl extends BaseServiceImpl<Systemdictionarytype,Long> implements ISystemdictionarytypeService{

    private ISystemdictionarytypeRepository systemdictionarytypeRepository;

    @Autowired
    public void setSystemdictionarytypeRepository(ISystemdictionarytypeRepository systemdictionarytypeRepository) {
        this.systemdictionarytypeRepository = systemdictionarytypeRepository;
    }
}