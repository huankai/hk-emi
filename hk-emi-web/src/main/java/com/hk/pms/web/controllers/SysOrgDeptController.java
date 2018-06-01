package com.hk.pms.web.controllers;

import com.hk.commons.util.JsonUtils;
import com.hk.core.query.JpaQueryModel;
import com.hk.core.query.QueryPageable;
import com.hk.core.web.JsonResult;
import com.hk.core.web.controller.BaseController;
import com.hk.pms.core.domain.SysOrgDept;
import com.hk.pms.core.servcie.SysOrgDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

/**
 * @author: huangkai
 * @date 2018-05-23 11:26
 */
@RestController
@RequestMapping("orgdepts")
public class SysOrgDeptController extends BaseController {

    @Autowired
    private SysOrgDeptService orgDeptService;

    @RequestMapping()
    @PreAuthorize("hasAuthority('org_list')")
    public String list(@RequestBody JpaQueryModel<SysOrgDept> query) {
        QueryPageable<SysOrgDept> pageable = orgDeptService.queryForPage(query);
        return JsonUtils.toJSONStringExcludes(JsonResult.success(pageable));
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAuthority('org_list')")
    public String detail(@PathVariable String id) {
        return JsonUtils.toJSONStringExcludes(JsonResult.success(orgDeptService.getOne(id)));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('org_delete')")
    public String deleteById(@PathVariable String id) {
        orgDeptService.delete(id);
        return JsonUtils.toJSONString(JsonResult.success());
    }

    @PostMapping("save")
    @PreAuthorize("hasAnyAuthority('org_create','org_edit')")
    public String saveOrUpdate(SysOrgDept orgDept, Errors errors) {
        if (errors.hasErrors()) {
            return JsonUtils.toJSONString(JsonResult.badRueqest(errors.getFieldError().getDefaultMessage()));
        }
        orgDeptService.saveOrUpdate(orgDept);
        return JsonUtils.toJSONString(JsonResult.success());
    }
}
