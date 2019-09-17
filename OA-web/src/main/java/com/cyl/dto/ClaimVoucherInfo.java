package com.cyl.dto;

import com.cyl.entity.Claim_voucher;
import com.cyl.entity.Claim_voucher_item;

import java.util.List;

public class ClaimVoucherInfo {
    private Claim_voucher claim_voucher;
    private List<Claim_voucher_item> items;

    public Claim_voucher getClaim_voucher() {
        return claim_voucher;
    }

    public void setClaim_voucher(Claim_voucher claim_voucher) {
        this.claim_voucher = claim_voucher;
    }

    public List<Claim_voucher_item> getItems() {
        return items;
    }

    public void setItems(List<Claim_voucher_item> items) {
        this.items = items;
    }
}
