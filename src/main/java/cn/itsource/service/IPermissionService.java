package cn.itsource.service;

import cn.itsource.domain.Permission;

import java.util.Set;

/**
 * (Permission)表Service层接口
 *
 * @author 申林
 * @since 2020-04-26 13:55:48
 */
public interface IPermissionService extends IBaseService<Permission,Long>{
    Set<String> findPermissionsByID(Long id);

}