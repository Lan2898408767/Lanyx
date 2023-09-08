package com.xxxx.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxxx.server.pojo.Department;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *

 */
public interface DepartmentMapper extends BaseMapper<Department> {

    /**
     * 获取所有部门
     *
     * @param parentId
     * @return
     */
    List<Department> getAllDepartmentsByParentId(Integer parentId);

    /**
     * 添加部门
     *
     * @param dep
     */
    void addDep(Department dep);

    /**
     * 删除部门
     *
     * @param dep
     */
    void deleteDep(Department dep);
}