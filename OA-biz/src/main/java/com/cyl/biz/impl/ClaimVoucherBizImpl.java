package com.cyl.biz.impl;

import com.cyl.biz.ClaimVoucherBiz;
import com.cyl.dao.ClaimVoucherDao;
import com.cyl.dao.ClaimVoucherItemDao;
import com.cyl.dao.DealRecordDao;
import com.cyl.dao.EmployeeDao;
import com.cyl.entity.Claim_voucher;
import com.cyl.entity.Claim_voucher_item;
import com.cyl.entity.Deal_record;
import com.cyl.entity.Employee;
import com.cyl.global.Contant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class ClaimVoucherBizImpl implements ClaimVoucherBiz {

     @Autowired
    ClaimVoucherDao claimVoucherDao;
    @Autowired
    ClaimVoucherItemDao claimVoucherItemDao;
    @Autowired
    DealRecordDao dealRecordDao;
    @Autowired
    EmployeeDao employeeDao;
    @Override
    public void save(Claim_voucher claim_voucher, List<Claim_voucher_item> list) {
            claim_voucher.setCreate_time(new Date());
            claim_voucher.setNext_deal_sn(claim_voucher.getCreate_sn());
            claim_voucher.setStatus(Contant.CLAIMVOUCHER_CREATED);
            claimVoucherDao.insert(claim_voucher);
        for (Claim_voucher_item item:list) {
            item.setClaim_voucher_id(claim_voucher.getId());
            claimVoucherItemDao.insert(item);
        }

    }

    @Override
    public Claim_voucher get(int id) {
        return claimVoucherDao.select(id);
    }

    @Override
    public List<Claim_voucher_item> getItems(int cvid) {
        return claimVoucherItemDao.selectByClaimVoucher(cvid);
    }

    @Override
    public List<Deal_record> getRecords(int cvid) {

        return dealRecordDao.selectByClaimVoucher(cvid);
    }

    @Override
    public List<Claim_voucher> getByCreate(String sn) {
        return claimVoucherDao.selectByCreateSn(sn);
    }

    @Override
    public List<Claim_voucher> getByDeal(String sn) {
        return claimVoucherDao.selectByNextDealSn(sn);
    }

    @Override
    public void update(Claim_voucher claim_voucher, List<Claim_voucher_item> items) {

        claim_voucher.setNext_deal_sn(claim_voucher.getCreate_sn());
        claim_voucher.setStatus(Contant.CLAIMVOUCHER_CREATED);
        claimVoucherDao.update(claim_voucher);
         List<Claim_voucher_item> olds = claimVoucherItemDao.selectByClaimVoucher(claim_voucher.getId());
        for (Claim_voucher_item old:olds) {
            boolean isHave = false;
            for (Claim_voucher_item item :items) {
                if (old.getId()==item.getId()){
                    isHave = true;
                    break;
                }
            }
            if (!isHave){
                claimVoucherItemDao.delete(old.getId());
            }

        }

        for (Claim_voucher_item item:items
             ) {
            if (item.getId()!=null&&item.getId()>0){
                claimVoucherItemDao.update(item);
            }else{
                claimVoucherItemDao.insert(item);
            }
        }
    }

    @Override
    public void submit(int id) {
       Claim_voucher claim_voucher = claimVoucherDao.select(id);
        Employee employee = employeeDao.select(claim_voucher.getCreate_sn());
        claim_voucher.setStatus(Contant.CLAIMVOUCHER_SUBMIT);
        claim_voucher.setNext_deal_sn(employeeDao.selectByDepartmentAndPost(employee.getDepartment_sn(),Contant.POST_FM).get(0).getSn());
        claimVoucherDao.update(claim_voucher);

        Deal_record deal_record = new Deal_record();
        deal_record.setClaim_voucher_id(id);
        deal_record.setDeal_result(Contant.DEAL_SUBMIT);
        deal_record.setDeal_way(Contant.DEAL_SUBMIT);
        deal_record.setDeal_sn(employee.getSn());
        deal_record.setComment("无");
        deal_record.setDeal_time(new Date());
        dealRecordDao.insert(deal_record);

    }

    @Override
    public void deal(Deal_record deal_record) {
        Claim_voucher claim_voucher = claimVoucherDao.select(deal_record.getClaim_voucher_id());
        Employee employee = employeeDao.select(deal_record.getDeal_sn());
        if (deal_record.getDeal_way().equals(Contant.DEAL_PASS)){
            if (claim_voucher.getTotal_amount()<=Contant.LIMIT_CHECK||employee.getPost().equals(Contant.POST_GM)){
                claim_voucher.setStatus(Contant.CLAIMVOUCHER_APPROVED);
                claim_voucher.setNext_deal_sn(employeeDao.selectByDepartmentAndPost(null,Contant.POST_CASHIER).get(0).getSn());
                deal_record.setDeal_time(new Date());
                deal_record.setDeal_result(Contant.CLAIMVOUCHER_APPROVED);

            }else{
                claim_voucher.setStatus(Contant.CLAIMVOUCHER_RECHECK);
                claim_voucher.setNext_deal_sn(employeeDao.selectByDepartmentAndPost(null,Contant.POST_GM).get(0).getSn());
                deal_record.setDeal_time(new Date());
                deal_record.setDeal_result(Contant.CLAIMVOUCHER_RECHECK);
            }
        }else if (deal_record.getDeal_way().equals(Contant.DEAL_BACK)){
            claim_voucher.setStatus(Contant.CLAIMVOUCHER_BACK);
            claim_voucher.setNext_deal_sn(claim_voucher.getCreate_sn());
            deal_record.setDeal_time(new Date());
            deal_record.setDeal_result(Contant.CLAIMVOUCHER_BACK);
        }else if (deal_record.getDeal_way().equals(Contant.DEAL_REJECT)){
            claim_voucher.setStatus(Contant.CLAIMVOUCHER_TERMINATED);
            claim_voucher.setNext_deal_sn(null);
            deal_record.setDeal_time(new Date());
            deal_record.setDeal_result(Contant.CLAIMVOUCHER_TERMINATED);
        }else if(deal_record.getDeal_way().equals(Contant.DEAL_PAID)){
            claim_voucher.setStatus(Contant.CLAIMVOUCHER_PAID);
            claim_voucher.setNext_deal_sn(null);
            deal_record.setDeal_time(new Date());
            deal_record.setDeal_result(Contant.CLAIMVOUCHER_PAID);
        }
        claimVoucherDao.update(claim_voucher);
        dealRecordDao.insert(deal_record);
    }
}
