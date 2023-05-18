package com.itheima.controller;

import com.itheima.anno.Log;
import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门管理Controller
 */
@Slf4j
@RestController
//公共路径
@RequestMapping("/depts")
public class DeptController {
    //注入一个service对象
    @Autowired
    private DeptService deptService;

    //获取日志对象
//    private static Logger log = LoggerFactory.getLogger(DeptController.class);

    /**
     * 查询部门信息
     * @return
     */
//    @RequestMapping(value = "/depts", method = RequestMethod.GET)   //指定请求方式为GET
    @GetMapping
    public Result list() {
        log.info("查询全部部门数据");

        //调用service查询部门数据
        List<Dept> deptList = deptService.list();

        return Result.success(deptList);
    }

    /**
     * 删除部门信息
     */
    @Log
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        log.info("根据id删除部门:{}", id);

        //调用service删除部门
        deptService.delete(id);

        return Result.success();
    }

    /**
     * 新增部门
     * @return
     */
    @Log
    @PostMapping
    public Result add(@RequestBody Dept dept) {
        log.info("新增部门：{}", dept);

        //调用service新增部门
        deptService.add(dept);

        return Result.success();
    }

    /**
     * 根据id查询部门
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("查询部门：{}", id);

        //调用service根据id查询部门
        Dept dept = deptService.getById(id);

        return Result.success(dept);
    }

    /**
     * 更新部门
     * @param dept
     * @return
     */
    @Log
    @PutMapping
    public Result update(@RequestBody Dept dept) {
        log.info("修改部门：{}", dept);

        //调用service
        deptService.update(dept);

        return Result.success();
    }
}
