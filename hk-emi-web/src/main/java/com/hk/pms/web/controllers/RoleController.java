package com.hk.pms.web.controllers;

import com.hk.commons.fastjson.JsonUtils;
import com.hk.commons.util.date.DatePattern;
import com.hk.core.query.JdbcQueryModel;
import com.hk.core.query.QueryPageable;
import com.hk.core.web.JsonResult;
import com.hk.core.web.controller.BaseController;
import com.hk.pms.core.domain.SysRole;
import com.hk.pms.core.servcie.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

/**
 * @author: huangkai
 * @date 2018-05-08 15:33
 */
@RestController
@RequestMapping("role")
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
    public String queryByPage(@RequestBody JdbcQueryModel query) {
        QueryPageable<SysRole> pageResult = roleService.queryForPage(query);
        return JsonUtils.toJSONStringExcludes(JsonResult.success(pageResult), "app");
    }

    @DeleteMapping("{id}")
    public String deleteById(@PathVariable String id) {
        roleService.delete(id);
        return JsonUtils.toJSONString(JsonResult.success());
    }

    @PostMapping("save")
    public String saveOrUpdate(SysRole role, Errors errors) {
        if (errors.hasErrors()) {
            return JsonUtils.toJSONString(JsonResult.error(errors.getFieldError().getDefaultMessage()));
        }
        roleService.saveOrUpdate(role);
        return JsonUtils.toJSONString(JsonResult.success());
    }

    @GetMapping("{id}")
    public String detail(@PathVariable String id) {
        SysRole role = roleService.findOne(id);
        return JsonUtils.toJSONStringExcludes(JsonResult.success(role), DatePattern.YYYY_MM_DD_HH_MM_SS, "app");
    }

}
