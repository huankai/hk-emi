package com.hk.pms.web.controllers;

import com.hk.commons.fastjson.JsonUtils;
import com.hk.commons.util.date.DatePattern;
import com.hk.core.query.JdbcQueryModel;
import com.hk.core.query.QueryPageable;
import com.hk.core.web.AppCodeUtils;
import com.hk.core.web.JsonResult;
import com.hk.core.web.controller.BaseController;
import com.hk.pms.core.domain.SysPermission;
import com.hk.pms.core.servcie.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: huangkai
 * @date 2018-05-08 15:33
 */
@RestController
@RequestMapping("permissions")
public class PermissionController extends BaseController {

    @Autowired
    private SysPermissionService permissionService;

    /**
     * 查询
     *
     * @param query 查询参数
     * @return
     */
    @RequestMapping()
    public String queryByPage(@RequestBody JdbcQueryModel query) {
        QueryPageable<SysPermission> pageResult = permissionService.queryForPage(query);
        return JsonUtils.toJSONStringExcludes(JsonResult.success(pageResult), "app", "parent", "child");
    }

    @DeleteMapping("{id}")
    public String deleteById(@PathVariable String id) {
        permissionService.delete(id);
        return JsonUtils.toJSONString(JsonResult.success());
    }

    @PostMapping("save")
    public String saveOrUpdate(SysPermission permission, Errors errors) {
        if (errors.hasErrors()) {
            return JsonUtils.toJSONString(JsonResult.error(errors.getFieldError().getDefaultMessage()));
        }
        permissionService.saveOrUpdate(permission);
        return JsonUtils.toJSONString(JsonResult.success());
    }

    @GetMapping("{id}")
    public String detail(@PathVariable String id) {
        SysPermission permission = permissionService.findOne(id);
        return JsonUtils.toJSONStringExcludes(JsonResult.success(permission), DatePattern.YYYY_MM_DD_HH_MM_SS, "app", "parent", "child");
    }

    /**
     * 获取当前登陆的所有权限列表
     *
     * @return
     */
    @GetMapping("mypermission")
    public String getMyPermissionList() {
        List<SysPermission> permisslonList = permissionService.getCurrentUserPermissionList(AppCodeUtils.getCurrentAppId());
        return JsonUtils.toJSONString(JsonResult.success(permisslonList), "app", "parent");
    }

}
