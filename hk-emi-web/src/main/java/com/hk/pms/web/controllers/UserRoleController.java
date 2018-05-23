package com.hk.pms.web.controllers;

import com.hk.commons.fastjson.JsonUtils;
import com.hk.core.web.JsonResult;
import com.hk.core.web.controller.BaseController;
import com.hk.pms.core.domain.SysUserRole;
import com.hk.pms.core.servcie.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

/**
 * @author: huangkai
 * @date 2018-05-23 11:06
 */
@RestControllerAdvice
@RequestMapping("userrole")
public class UserRoleController extends BaseController {


    @Autowired
    private SysUserRoleService userRoleService;

    @DeleteMapping("{id}")
    public String delete(@PathVariable String id) {
        userRoleService.delete(id);
        return JsonUtils.toJSONString(JsonResult.success());
    }

    /**
     * 根据 userId 和 roleId 删除
     *
     * @param userId
     * @param roleId
     * @return
     */
    @DeleteMapping("{userId}/{roleId}")
    public String delete(@PathVariable String userId, @PathVariable String roleId) {
        userRoleService.deleteByUserIdAndRoleId(userId, roleId);
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
    public String save(SysUserRole userRole, Errors errors) {
        if (errors.hasErrors()) {
            return JsonUtils.toJSONString(JsonResult.badRueqest(errors.getFieldError().getDefaultMessage()));
        }
        userRoleService.saveOrUpdate(userRole);
        return JsonUtils.toJSONString(JsonResult.success());
    }
}
