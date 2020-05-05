package cn.itsource.service.impl;

import cn.itsource.domain.Permission;
import cn.itsource.repository.IPermissionRepository;
import cn.itsource.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * (Permission)表Service层接口
 *
 * @author 申林
 * @since 2020-04-26 13:55:48
 */
@Service
public class PermissionServiceImpl extends BaseServiceImpl<Permission, Long> implements IPermissionService {

    private IPermissionRepository permissionRepository;

    @Autowired
    public void setPermissionRepository(IPermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Override
    public Set<String> findPermissionsByID(Long id) {
        return permissionRepository.findPermissionsByID(id);
    }

    @Override
    public Set<Permission> findPermissionsByMenu(Long id) {
        return permissionRepository.findPermissionsByMenu(id);
    }
}