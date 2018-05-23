package com.hk.pms.web.controllers;

import com.hk.commons.fastjson.JsonUtils;
import com.hk.core.web.JsonResult;
import com.hk.core.web.controller.BaseController;
import com.hk.pms.core.domain.SysDeptRole;
import com.hk.pms.core.servcie.SysDeptRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

/**
 * @author: huangkai
 * @date 2018-05-23 11:26
 */
@RestController
@RequestMapping("deptroles")
public class SysDeptRoleController extends BaseController {

    @Autowired
    private SysDeptRoleService deptRoleService;

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('org_delete')")
    public String deleteById(@PathVariable String id) {
        deptRoleService.delete(id);
        return JsonUtils.toJSONString(JsonResult.success());
    }

    /**
     * 根据 deptId 和 roleId 删除
     *
     * @param deptId
     * @param roleId
     * @return
     */
    @DeleteMapping("{deptId}/{roleId}")
    @PreAuthorize("hasAuthority('org_delete')")
    public String delete(@PathVariable String deptId, @PathVariable String roleId) {
        deptRoleService.deleteByDeptIdAndRoleId(deptId, roleId);
        return JsonUtils.toJSONString(JsonResult.success());
    }

    @PostMapping("save")
    @PreAuthorize("hasAnyAuthority('org_create','org_edit')")
    public String saveOrUpdate(SysDeptRole deptRole, Errors errors) {
        if (errors.hasErrors()) {
            return JsonUtils.toJSONString(JsonResult.badRueqest(errors.getFieldError().getDefaultMessage()));
        }
        deptRoleService.saveOrUpdate(deptRole);
        return JsonUtils.toJSONString(JsonResult.success());
    }
}
