package com.itheima.service;

import com.itheima.pojo.Dept;

import java.util.List;

/**
 * 部门管理
 */
public interface DeptService {
    /**
     * 查询全部部门数据
     * @return
     */
    List<Dept> list();

    /**
     * 根据id删除部门
     */
    void delete(Integer id);


    /**
     * 新增部门
     * @param dept
     */
    void add(Dept dept);


    /**
     * 根据id查询部门
     * @param id
     * @return
     */
    Dept getById(Integer id);


    /**
     * 修改部门
     * @param dept
     */
    void update(Dept dept);
}
