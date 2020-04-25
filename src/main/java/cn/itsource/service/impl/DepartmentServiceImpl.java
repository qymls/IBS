package cn.itsource.service.impl;

import cn.itsource.domain.Department;
import cn.itsource.repository.IDepartmentRepository;
import cn.itsource.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * (Department)表Service层接口
 *
 * @author 申林
 * @since 2020-04-25 10:14:25
 */
 @Service
public class DepartmentServiceImpl extends BaseServiceImpl<Department,Long> implements IDepartmentService{

    private IDepartmentRepository departmentRepository;

    @Autowired
    public void setDepartmentRepository(IDepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }
}