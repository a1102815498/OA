package com.cyl.biz.impl;

import com.cyl.biz.DepartmentBiz;
import com.cyl.dao.DepartmentDao;
import com.cyl.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DepartmentBizImpl implements DepartmentBiz {
    @Qualifier("departmentDao")
    @Autowired
   private DepartmentDao departmentDao;
    @Override
    public void add(Department department) {
        departmentDao.insert(department);
    }

    @Override
    public void edit(Department department) {
departmentDao.update(department);
    }

    @Override
    public void remove(String sn) {
departmentDao.delete(sn);
    }

    @Override
    public Department get(String sn) {
        return departmentDao.select(sn);
    }

    @Override
    public List<Department> getall() {
        return departmentDao.selectall();
    }
}
