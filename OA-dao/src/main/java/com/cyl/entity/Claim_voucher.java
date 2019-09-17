package com.cyl.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Claim_voucher {
    private Integer id;
    private String cause;
    private String create_sn;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date create_time;
    private String next_deal_sn;
    private Double total_amount;
    private String status;
    private Employee creater;
    private Employee dealer;

    public Employee getCreater() {
        return creater;
    }

    public void setCreater(Employee creater) {
        this.creater = creater;
    }

    public Employee getDealer() {
        return dealer;
    }

    public void setDealer(Employee dealer) {
        this.dealer = dealer;
    }

    public Claim_voucher() {
    }

    public Claim_voucher(Integer id, String cause, String create_sn, Date create_time, String next_deal_sn, Double total_amount, String status) {
        this.id = id;
        this.cause = cause;
        this.create_sn = create_sn;
        this.create_time = create_time;
        this.next_deal_sn = next_deal_sn;
        this.total_amount = total_amount;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getCreate_sn() {
        return create_sn;
    }

    public void setCreate_sn(String create_sn) {
        this.create_sn = create_sn;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getNext_deal_sn() {
        return next_deal_sn;
    }

    public void setNext_deal_sn(String next_deal_sn) {
        this.next_deal_sn = next_deal_sn;
    }

    public Double getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(Double total_amount) {
        this.total_amount = total_amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
