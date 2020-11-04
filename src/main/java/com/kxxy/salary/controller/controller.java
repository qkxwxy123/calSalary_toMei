package com.kxxy.salary.controller;

import com.kxxy.salary.add.add;
import com.kxxy.salary.find.find;
import com.kxxy.salary.model.findModel;
import com.kxxy.salary.model.msgModel;
import com.kxxy.salary.model.returnModel;
import com.kxxy.salary.model.workModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class controller {
    @GetMapping("/getWorkTime")
    public findModel getWorker(@RequestParam String workerName, @RequestParam String sex, @RequestParam String month)
    {
        findModel rm = new findModel();
        float hours = -1;
        find fd = new find();
        hours = find.getData(workerName, sex, month);
        if(hours >= 0)
        {
            rm.setStatus(200);
            rm.setMsg("success");
            rm.setRdata(hours);
        }
        else
        {
            rm.setStatus(500);
            rm.setMsg("search failed");
            rm.setRdata(-1);
        }
        return rm;
    }

    @GetMapping("/getWorker")
    public returnModel getWorker(@RequestParam String name, @RequestParam String month)
    {
        returnModel rm = new returnModel();
        msgModel wm = find.getWorker(name, month);
        if(wm != null)
        {
            rm.setStatus(200);
            rm.setMsg("success");
            rm.setRdata(wm);
        }
        else
        {
            rm.setStatus(500);
            rm.setMsg("search failed");
            rm.setRdata(wm);
        }
        return rm;
    }

    @PostMapping("/addWorker")
    public returnModel addWorker(@RequestParam String name, @RequestParam String sex, @RequestParam String phone)
    {
        returnModel rm = new returnModel();
        workModel wm = new workModel();
        wm.setSex(sex);
        wm.setPhone(phone);
        wm.setName(name);
        String status = add.addWorker(name, sex, phone);
        if(status.equals("success") || status.equals("已经添加")) {
            rm.setStatus(200);
            rm.setMsg("success");
        }
        else {
            rm.setStatus(500);
            rm.setMsg(status);
        }
        rm.setRdata(wm);
        return rm;
    }

    @PostMapping("/addWorkTime")
    public returnModel addWorkTime(@RequestParam String name, @RequestParam String sex, @RequestParam String month, @RequestParam float time)
    {
        returnModel rm = new returnModel();
        workModel wm = new workModel();
        String status = add.addWorkTime(name, sex, month, time);
        if(status.equals("success")) {
            rm.setStatus(200);
            rm.setMsg("success");
        }
        else {
            rm.setStatus(500);
            rm.setMsg(status);
        }
        rm.setRdata(wm);
        return rm;
    }
}
