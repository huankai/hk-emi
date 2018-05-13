package com.hk.pms.web.controllers;

import com.hk.commons.fastjson.JsonUtils;
import com.hk.core.query.JpaQueryModel;
import com.hk.core.query.QueryPageable;
import com.hk.core.web.JsonResult;
import com.hk.core.web.controller.BaseController;
import com.hk.pms.core.domain.SysUser;
import com.hk.pms.core.servcie.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

/**
 * @author huangkai
 * @date 2018-5-13 11:37
 */
@RestController
@RequestMapping("users")
public class UserController extends BaseController {

    @Autowired
    private SysUserService userService;

    @RequestMapping()
    public String list(JpaQueryModel<SysUser> query) {
        QueryPageable<SysUser> pageable = userService.queryForPage(query);
        return JsonUtils.toJSONStringExcludes(JsonResult.success(pageable));
    }

    @GetMapping("{id}")
    public String detail(@PathVariable String id) {
        return JsonUtils.toJSONStringExcludes(JsonResult.success(userService.getOne(id)));
    }

    @DeleteMapping("{id}")
    public String deleteById(@PathVariable String id) {
        userService.delete(id);
        return JsonUtils.toJSONString(JsonResult.success());
    }

    @PostMapping("save")
    public String saveOrUpdate(SysUser user, Errors errors) {
        if (errors.hasErrors()) {
            return JsonUtils.toJSONString(JsonResult.badRueqest(errors.getFieldError().getDefaultMessage()));
        }
        userService.saveOrUpdate(user);
        return JsonUtils.toJSONString(JsonResult.success());
    }

    @PostMapping("disable/{id}")
    public String disableUser(@PathVariable String id) {
        userService.disable(id);
        return JsonUtils.toJSONString(JsonResult.success());

    }

    @PostMapping("enable/{id}")
    public String enableUser(@PathVariable String id) {
        userService.enable(id);
        return JsonUtils.toJSONString(JsonResult.success());

    }


}
