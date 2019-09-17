package com.cyl.biz;

import com.cyl.entity.Department;
import com.cyl.entity.Employee;

import java.util.List;

public interface EmployeeBiz {
    void add(Employee employee);
    void edit(Employee employee);
    void remove(String sn);
    Employee get(String sn);
    List<Employee> getall();

}
