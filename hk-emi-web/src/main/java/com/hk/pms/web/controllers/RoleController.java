package com.hk.pms.web.controllers;

import com.hk.commons.fastjson.JsonUtils;
import com.hk.commons.util.date.DatePattern;
import com.hk.core.authentication.api.UserPrincipal;
import com.hk.core.query.JdbcQueryModel;
import com.hk.core.query.QueryPageable;
import com.hk.core.web.JsonResult;
import com.hk.core.web.controller.BaseController;
import com.hk.pms.core.domain.SysRole;
import com.hk.pms.core.servcie.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: huangkai
 * @date 2018-05-08 15:33
 */
@RestController
@RequestMapping("roles")
public class RoleController extends BaseController {

    @Autowired
    private SysRoleService roleService;

    /**
     * 查询
     *
     * @param query 查询参数
     * @return
     */
    @RequestMapping()
    @PreAuthorize("hasAuthority('role_list')")
    public String queryByPage(@RequestBody JdbcQueryModel query) {
        QueryPageable<SysRole> pageResult = roleService.queryForPage(query);
        return JsonUtils.toJSONStringExcludes(JsonResult.success(pageResult), "app");
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('role_delete')")
    public String deleteById(@PathVariable String id) {
        roleService.delete(id);
        return JsonUtils.toJSONString(JsonResult.success());
    }

    @PostMapping("save")
    @PreAuthorize("hasAnyAuthority('role_create','role_edit')")
    public String saveOrUpdate(SysRole role, Errors errors) {
        if (errors.hasErrors()) {
            return JsonUtils.toJSONString(JsonResult.error(errors.getFieldError().getDefaultMessage()));
        }
        roleService.saveOrUpdate(role);
        return JsonUtils.toJSONString(JsonResult.success());
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAuthority('role_list')")
    public String detail(@PathVariable String id) {
        SysRole role = roleService.findOne(id);
        return JsonUtils.toJSONStringExcludes(JsonResult.success(role), DatePattern.YYYY_MM_DD_HH_MM_SS, "app");
    }

    @PostMapping("disable/{id}")
    @PreAuthorize("hasAuthority('role_edit')")
    public String disableRole(@PathVariable String id) {
        roleService.disable(id);
        return JsonUtils.toJSONString(JsonResult.success());

    }

    @PostMapping("enable/{id}")
    @PreAuthorize("hasAuthority('role_edit')")
    public String enableRole(@PathVariable String id) {
        roleService.enable(id);
        return JsonUtils.toJSONString(JsonResult.success());

    }

    /**
     * 获取当前登陆的所有权限列表
     *
     * @return
     */
    @GetMapping("my")
    public String getMyPermissionList() {
        UserPrincipal principal = getPrincipal();
        return JsonUtils.toJSONString(JsonResult.success(principal.getPermissionByAppId(principal.getAppId())));
    }

    /**
     * 获取用户权限详情
     *
     * @return
     */
    @GetMapping("my/detail")
    public String getMyPermissionDetailList() {
        List<SysRole> roleList = roleService.getCurrentUserRoleList(getPrincipal().getAppId());
        return JsonUtils.toJSONStringExcludes(JsonResult.success(roleList), "app");
    }

}
