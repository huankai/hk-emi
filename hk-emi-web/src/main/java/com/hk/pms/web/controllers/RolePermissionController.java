package com.hk.pms.web.controllers;

import com.hk.commons.util.JsonUtils;
import com.hk.core.web.JsonResult;
import com.hk.core.web.controller.BaseController;
import com.hk.pms.core.domain.SysRolePermission;
import com.hk.pms.core.servcie.SysRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

/**
 * 角色与权限配置
 *
 * @author huangkai
 * @date 2018-5-13 11:29
 */
@RestController
@RequestMapping("rolepermission")
public class RolePermissionController extends BaseController {

    @Autowired
    private SysRolePermissionService rolePermissionService;

    /**
     * 根据 id 删除
     *
     * @param id rolePermissionId
     * @return json result
     */
    @DeleteMapping("{id}")
    public String delete(@PathVariable String id) {
        rolePermissionService.delete(id);
        return JsonUtils.toJSONString(JsonResult.success());
    }

    /**
     * 根据roleId 和 permissionId删除
     *
     * @param roleId
     * @param permissionId
     * @return
     */
    @DeleteMapping("{roleId}/{permissionId}")
    public String delete(@PathVariable String roleId, @PathVariable String permissionId) {
        rolePermissionService.deleteByRoleIdAndPermissionId(roleId, permissionId);
        return JsonUtils.toJSONString(JsonResult.success());
    }

    /**
     * 保存或更新
     *
     * @param rolePermission rolePermission
     * @param errors         errors
     * @return json result
     */
    @PostMapping("save")
    public String save(SysRolePermission rolePermission, Errors errors) {
        if (errors.hasErrors()) {
            return JsonUtils.toJSONString(JsonResult.badRueqest(errors.getFieldError().getDefaultMessage()));
        }
        rolePermissionService.saveOrUpdate(rolePermission);
        return JsonUtils.toJSONString(JsonResult.success());
    }
}
