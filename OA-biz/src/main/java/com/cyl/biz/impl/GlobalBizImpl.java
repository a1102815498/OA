package com.cyl.biz.impl;

import com.cyl.biz.GlobalBiz;
import com.cyl.dao.EmployeeDao;
import com.cyl.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GlobalBizImpl implements GlobalBiz {
    @Autowired
    private EmployeeDao employeeDao;
    @Override
    public Employee login(String sn, String password) {
        Employee employee = employeeDao.select(sn);
        if (employee!=null&&employee.getPassword().equals(password)){
            return employee;
        }
        return null;
    }

    @Override
    public void changePassword(Employee employee) {
            employeeDao.update(employee);
    }
}
