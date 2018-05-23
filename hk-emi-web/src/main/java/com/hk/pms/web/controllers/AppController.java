package com.hk.pms.web.controllers;

import com.hk.commons.fastjson.JsonUtils;
import com.hk.commons.util.ByteConstants;
import com.hk.core.query.JpaQueryModel;
import com.hk.core.query.Order;
import com.hk.core.query.QueryPageable;
import com.hk.core.web.JsonResult;
import com.hk.core.web.controller.BaseController;
import com.hk.pms.core.domain.SysApp;
import com.hk.pms.core.servcie.SysAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

    /**
     * 查询分页
     *
     * @param query
     * @return
     */
    @RequestMapping
    @PreAuthorize("hasAuthority('permission_list')")
    public String list(JpaQueryModel<SysApp> query) {
        QueryPageable<SysApp> pageable = appService.queryForPage(query);
        return JsonUtils.toJSONStringExcludes(JsonResult.success(pageable));
    }

    /**
     * 查询不分页，只查询有效状态的数据
     *
     * @param app app
     * @return
     */
    @RequestMapping("search")
    @PreAuthorize("hasAuthority('permission_list')")
    public String queryAll(SysApp app) {
        if (null == app.getAppStatus()) {
            app.setAppStatus(ByteConstants.ONE);
        }
        List<SysApp> appList = appService.findAll(app, Order.desc("lastModifiedDate"));
        return JsonUtils.toJSONStringExcludes(JsonResult.success(appList), "appIp", "appIcon", "appPort", "appStatus", "lastModifiedDate");
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAuthority('permission_edit')")
    public String detail(@PathVariable String id) {
        SysApp app = appService.getOne(id);
        return JsonUtils.toJSONStringExcludes(JsonResult.success(app));
    }


    /**
     * 保存或更新
     *
     * @param app
     * @param errors
     * @return
     */
    @PreAuthorize("hasAuthority('permission_create')")
    @PostMapping("save")
    public String saveOrUpdate(SysApp app, Errors errors) {
        if (errors.hasErrors()) {
            return JsonUtils.toJSONString(JsonResult.badRueqest(errors.getFieldError().getDefaultMessage()));
        }
        appService.saveOrUpdate(app);
        return JsonUtils.toJSONString(JsonResult.success());
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('permission_delete')")
    public String delete(@PathVariable String id) {
        appService.delete(id);
        return JsonUtils.toJSONString(JsonResult.success());
    }


    @PostMapping("disable/{id}")
    @PreAuthorize("hasAuthority('permission_edit')")
    public String disable(@PathVariable String id) {
        appService.disable(id);
        return JsonUtils.toJSONString(JsonResult.success());
    }

    @PostMapping("enable/{id}")
    @PreAuthorize("hasAuthority('permission_edit')")
    public String enable(@PathVariable String id) {
        appService.enable(id);
        return JsonUtils.toJSONString(JsonResult.success());
    }


}
