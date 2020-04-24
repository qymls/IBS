package cn.itsource.service.impl;

import cn.itsource.domain.Permission;
import cn.itsource.repository.IPermissionRepository;
import cn.itsource.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * (Permission)表Service层接口
 *
 * @author 申林
 * @since 2020-04-24 18:41:37
 */
 @Service
public class PermissionServiceImpl extends BaseServiceImpl<Permission,Long> implements IPermissionService{

    private IPermissionRepository permissionRepository;

    @Autowired
    public void setPermissionRepository(IPermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }
}