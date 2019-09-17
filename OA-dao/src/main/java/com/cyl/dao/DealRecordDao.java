package com.cyl.dao;

import com.cyl.entity.Deal_record;

import java.util.List;

public interface DealRecordDao {
    void insert(Deal_record deal_record);
    List<Deal_record> selectByClaimVoucher(int cvid);
}
