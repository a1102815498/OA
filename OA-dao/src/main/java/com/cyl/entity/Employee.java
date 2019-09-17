package com.cyl.entity;

public class Employee {
    private String sn;
    private String password;
    private String name;
    private String department_sn;
    private String post;
    private Department department;
    public Employee() {
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Employee(String sn, String password, String name, String department_sn, String post) {
        this.sn = sn;
        this.password = password;
        this.name = name;
        this.department_sn = department_sn;
        this.post = post;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment_sn() {
        return department_sn;
    }

    public void setDepartment_sn(String department_sn) {
        this.department_sn = department_sn;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

}
