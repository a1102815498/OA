package com.cyl.dao;

import com.cyl.entity.Claim_voucher_item;

import java.util.List;

public interface ClaimVoucherItemDao {
    void insert(Claim_voucher_item claim_voucher_item);
    void update(Claim_voucher_item claim_voucher_item);
    void delete(int id);
    List<Claim_voucher_item> selectByClaimVoucher(int cvid);
}
