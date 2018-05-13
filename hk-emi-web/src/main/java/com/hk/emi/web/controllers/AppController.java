package com.hk.emi.web.controllers;

import com.hk.commons.fastjson.JsonUtils;
import com.hk.core.query.JpaQueryModel;
import com.hk.core.query.QueryPageable;
import com.hk.core.web.JsonResult;
import com.hk.core.web.controller.BaseController;
import com.hk.pms.core.domain.SysApp;
import com.hk.pms.core.servcie.SysAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        return JsonUtils.toJSONStringExcludes(JsonResult.success(app));
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
