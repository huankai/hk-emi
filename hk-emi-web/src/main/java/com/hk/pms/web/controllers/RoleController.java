package com.hk.pms.web.controllers;

import com.hk.core.web.controller.BaseController;
import com.hk.pms.core.servcie.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: huangkai
 * @date 2018-05-08 15:33
 */
@RestController
@RequestMapping("role")
public class RoleController extends BaseController {

    @Autowired
    private SysRoleService roleService;

}
