package com.cyl.biz;

import com.cyl.entity.Claim_voucher;
import com.cyl.entity.Claim_voucher_item;
import com.cyl.entity.Deal_record;

import java.util.List;

public interface ClaimVoucherBiz {
    void save(Claim_voucher claim_voucher, List<Claim_voucher_item> list);

    Claim_voucher get(int id);
    List<Claim_voucher_item> getItems(int cvid);
    List<Deal_record> getRecords(int cvid);
    List<Claim_voucher> getByCreate(String sn);
    List<Claim_voucher> getByDeal(String sn);
    void update(Claim_voucher claim_voucher,List<Claim_voucher_item> items);
    void submit(int id);
    void deal(Deal_record deal_record);
}
