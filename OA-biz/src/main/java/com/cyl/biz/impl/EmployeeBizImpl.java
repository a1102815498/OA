package com.cyl.biz.impl;

import com.cyl.biz.EmployeeBiz;
import com.cyl.dao.EmployeeDao;
import com.cyl.entity.Department;
import com.cyl.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeBizImpl implements EmployeeBiz {
    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public void add(Employee employee) {
        employee.setPassword("000000");
        employeeDao.insert(employee);
    }

    @Override
    public void edit(Employee employee) {
employeeDao.update(employee);
    }

    @Override
    public void remove(String sn) {
employeeDao.delete(sn);
    }

    @Override
    public Employee get(String sn) {
        return employeeDao.select(sn);
    }

    @Override
    public List<Employee> getall() {
        return employeeDao.selectall();
    }
}
