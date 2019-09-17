package com.cyl.controller;

import com.cyl.biz.ClaimVoucherBiz;
import com.cyl.dao.ClaimVoucherDao;
import com.cyl.dto.ClaimVoucherInfo;
import com.cyl.entity.Deal_record;
import com.cyl.entity.Employee;
import com.cyl.global.Contant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/claim_voucher")
public class ClaimVoucherController {
    @Autowired
    ClaimVoucherBiz claimVoucherBiz;
    @RequestMapping("/to_add")
    public String to_add(Map<String,Object> map){
        map.put("info",new ClaimVoucherInfo());
        map.put("items", Contant.getItems());
        return "claim_voucher_add";
    }

    @RequestMapping("/add")
    public String add(HttpSession session,ClaimVoucherInfo info){
        Employee employee = (Employee) session.getAttribute("employee");
        info.getClaim_voucher().setCreate_sn(employee.getSn());
        claimVoucherBiz.save(info.getClaim_voucher(),info.getItems());
        return "redirect:deal";
    }

    @RequestMapping("/detail")
    public String detail(@RequestParam int id , Map<String,Object> map){
        map.put("claimVoucher",claimVoucherBiz.get(id));
        map.put("items",claimVoucherBiz.getItems(id));
        map.put("records",claimVoucherBiz.getRecords(id));
        return "claim_voucher_detail";
    }

    @RequestMapping("/self")
    public String self(HttpSession session,Map<String,Object> map){
        Employee employee = (Employee) session.getAttribute("employee");
        map.put("list",claimVoucherBiz.getByCreate(employee.getSn()));
        return "claim_voucher_self";
    }

    @RequestMapping("/deal")
    public String deal(HttpSession session,Map<String,Object> map){
        Employee employee = (Employee) session.getAttribute("employee");
        map.put("list",claimVoucherBiz.getByDeal(employee.getSn()));
        return "claim_voucher_deal";

    }

    @RequestMapping("/to_update")
    public String to_update(int id ,Map<String,Object> map){
        ClaimVoucherInfo info = new ClaimVoucherInfo();
        info.setClaim_voucher(claimVoucherBiz.get(id));
        info.setItems(claimVoucherBiz.getItems(id));
        map.put("info",info);
        map.put("items", Contant.getItems());
        return "claim_voucher_update";
    }

    @RequestMapping("/update")
    public String update(HttpSession session,ClaimVoucherInfo info){
        Employee employee = (Employee) session.getAttribute("employee");
        info.getClaim_voucher().setCreate_sn(employee.getSn());
        claimVoucherBiz.update(info.getClaim_voucher(),info.getItems());
        return "redirect:deal";
    }

    @RequestMapping("/submit")
    public String submit(int id){
      claimVoucherBiz.submit(id);
        return "redirect:deal";
    }

    @RequestMapping("/to_check")
    public String to_check(@RequestParam int id , Map<String,Object> map) {
        map.put("claimVoucher", claimVoucherBiz.get(id));
        map.put("items", claimVoucherBiz.getItems(id));
        map.put("records", claimVoucherBiz.getRecords(id));
        Deal_record deal_record = new Deal_record();
        deal_record.setClaim_voucher_id(id);
        map.put("record",deal_record);
        return "claim_voucher_check";
    }

    @RequestMapping("/check")
    public String check(HttpSession session,Deal_record deal_record) {
     Employee employee = (Employee) session.getAttribute("employee");
      deal_record.setDeal_sn(employee.getSn());
      claimVoucherBiz.deal(deal_record);
        return "redirect:deal";
    }
}
