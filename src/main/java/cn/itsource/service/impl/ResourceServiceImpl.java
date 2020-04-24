package cn.itsource.service.impl;

import cn.itsource.domain.Resource;
import cn.itsource.repository.IResourceRepository;
import cn.itsource.service.IResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * (Resource)表Service层接口
 *
 * @author 申林
 * @since 2020-04-24 18:41:41
 */
 @Service
public class ResourceServiceImpl extends BaseServiceImpl<Resource,Long> implements IResourceService{

    private IResourceRepository resourceRepository;

    @Autowired
    public void setResourceRepository(IResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }
}