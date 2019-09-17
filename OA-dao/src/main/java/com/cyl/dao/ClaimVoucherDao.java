package com.cyl.dao;

import com.cyl.entity.Claim_voucher;

import java.util.List;

public interface ClaimVoucherDao {
    void insert(Claim_voucher claim_voucher);
    void update(Claim_voucher claim_voucher);
    void delete(int id);
    Claim_voucher select(int id);
    List<Claim_voucher> selectByCreateSn(String sn);
    List<Claim_voucher> selectByNextDealSn(String ndsn);
}
