package com.hk.emi.web.controllers;

import com.google.common.collect.Lists;
import com.hk.commons.fastjson.JsonUtils;
import com.hk.commons.util.ByteConstants;
import com.hk.core.query.JpaQueryModel;
import com.hk.core.query.QueryPageable;
import com.hk.core.web.JsonResult;
import com.hk.core.web.controller.BaseController;
import com.hk.pms.core.domain.SysApp;
import com.hk.pms.core.servcie.SysAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: huangkai
 * @date 2018-04-17 16:29
 */
@RestController
@RequestMapping("/apps")
public class AppController extends BaseController {

    @Autowired
    private SysAppService appService;

    @RequestMapping
    public String list(JpaQueryModel<SysApp> query) {
        QueryPageable<SysApp> pageable = appService.queryForPage(query);
        return JsonUtils.toJSONStringExcludes(JsonResult.success(pageable));
    }

    @GetMapping("{id}")
    public String detail(@PathVariable String id) {
        SysApp app = appService.findOne(id);
        System.out.println(appService.getOne(id));
        return JsonUtils.toJSONStringExcludes(JsonResult.success(app));
    }


    /**
     * 保存或更新
     *
     * @param app
     * @param errors
     * @return
     */
    @PostMapping("save")
    public String saveOrUpdate(SysApp app, Errors errors) {
        if (errors.hasErrors()) {
            return JsonUtils.toJSONString(JsonResult.badRueqest(errors.getFieldError().getDefaultMessage()));
        }
        appService.saveOrUpdate(app);
        return JsonUtils.toJSONString(JsonResult.success());
    }

    /**
     * 批量保存
     *
     * @return
     */
    @GetMapping("savelist")
    public String saveOrUpdateList() {
        List<SysApp> appList = Lists.newArrayList();
        SysApp app;
        for (int i = 0; i < 10; i++) {
            app = new SysApp();
            app.setAppStatus(ByteConstants.ONE);
            app.setAppPort(80);
            app.setAppIp("127.0.0.1");
            app.setAppIcon("a.png");
            app.setAppName("Name" + i);
            app.setAppCode("Code" + i);
            appList.add(app);
        }
        appService.saveOrUpdate(appList);
        return JsonUtils.toJSONString(JsonResult.success());
    }

    @GetMapping("saveandflush")
    public String saveAndFlush() {
        SysApp app = new SysApp();
        app.setAppStatus(ByteConstants.ONE);
        app.setAppPort(80);
        app.setAppIp("127.0.0.1");
        app.setAppName("Name");
        app.setAppCode("Code");
        appService.saveAndFlush(app);
        return JsonUtils.toJSONString(JsonResult.success());
    }

    @GetMapping("exists/{id}")
    public String exists(@PathVariable String id) {
        appService.exists(id);
        return JsonUtils.toJSONString(JsonResult.success());
    }

    @GetMapping("count")
    public String count() {
        return JsonUtils.toJSONString(JsonResult.success(appService.count()));
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable String id) {
        appService.delete(id);
        return JsonUtils.toJSONString(JsonResult.success());
    }

    @DeleteMapping("deleteentity")
    public String deleteEntity() {
        SysApp app = new SysApp();
        app.setAppStatus(ByteConstants.ONE);
        app.setAppPort(80);
        app.setAppIp("127.0.0.1");
        app.setAppName("Name");
        app.setAppCode("Code");
        appService.delete(app);
        return JsonUtils.toJSONString(JsonResult.success());
    }

    @DeleteMapping("deleteall")
    public String deleteAll() {
        List<SysApp> appList = Lists.newArrayList();
        SysApp app;
        for (int i = 0; i < 10; i++) {
            app = new SysApp();
            app.setAppStatus(ByteConstants.ONE);
            app.setAppPort(80);
            app.setAppIp("127.0.0.1");
            app.setAppName("Name" + i);
            app.setAppCode("Code" + i);
            appList.add(app);
        }
        appService.delete(appList);
        return JsonUtils.toJSONString(JsonResult.success());
    }

    @PostMapping("disable/{id}")
    public String disable(@PathVariable String id) {
        appService.disable(id);
        return JsonUtils.toJSONString(JsonResult.success());
    }

    @PostMapping("enable/{id}")
    public String enable(@PathVariable String id) {
        appService.enable(id);
        return JsonUtils.toJSONString(JsonResult.success());
    }


}
