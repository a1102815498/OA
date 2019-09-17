package com.cyl.entity;

public class Claim_voucher_item {
    private Integer id;
    private Integer claim_voucher_id;
    private String item;
    private Double amount;
    private String comment;

    public Claim_voucher_item() {
    }

    public Claim_voucher_item(Integer id, Integer claim_voucher_id, String item, Double amount, String comment) {
        this.id = id;
        this.claim_voucher_id = claim_voucher_id;
        this.item = item;
        this.amount = amount;
        this.comment = comment;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClaim_voucher_id() {
        return claim_voucher_id;
    }

    public void setClaim_voucher_id(Integer claim_voucher_id) {
        this.claim_voucher_id = claim_voucher_id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
