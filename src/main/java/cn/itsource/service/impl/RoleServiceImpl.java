package cn.itsource.service.impl;

import cn.itsource.domain.Role;
import cn.itsource.repository.IRoleRepository;
import cn.itsource.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * (Role)表Service层接口
 *
 * @author 申林
 * @since 2020-04-26 14:28:46
 */
 @Service
public class RoleServiceImpl extends BaseServiceImpl<Role,Long> implements IRoleService{

    private IRoleRepository roleRepository;

    @Autowired
    public void setRoleRepository(IRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
}