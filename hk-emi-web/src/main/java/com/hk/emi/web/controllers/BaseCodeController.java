package com.hk.emi.web.controllers;

import com.hk.commons.fastjson.JsonUtils;
import com.hk.core.query.JpaQueryModel;
import com.hk.core.query.QueryPageable;
import com.hk.core.web.JsonResult;
import com.hk.core.web.controller.BaseController;
import com.hk.emi.core.domain.BaseCode;
import com.hk.emi.core.service.BaseCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

/**
 * @author: huangkai
 * @date 2018-04-03 17:39
 */
@RestController
@RequestMapping("/basecodes")
public class BaseCodeController extends BaseController {

    @Autowired
    private BaseCodeService baseCodeService;

    /**
     * 查询
     *
     * @param query
     * @return
     */
    @GetMapping
    public String search(JpaQueryModel<BaseCode> query) {
        QueryPageable<BaseCode> pageable = baseCodeService.queryForPage(query);
        return JsonUtils.toJSONStringExcludes(JsonResult.success(pageable), "childCodes");
    }


    /**
     * 详情
     *
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public String get(@PathVariable String id) {
        return JsonUtils.toJSONStringExcludes(JsonResult.success(baseCodeService.findOne(id)), "childCodes");
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    public String delete(@PathVariable String id) {
        baseCodeService.delete(id);
        return JsonUtils.toJSONString(JsonResult.success());
    }

    /**
     * 保存或更新
     *
     * @param baseCode
     * @param errors
     * @return
     */
    @PostMapping("save")
    public String saveOrUpdate(BaseCode baseCode, Errors errors) {
        if (errors.hasErrors()) {
            return JsonUtils.toJSONString(JsonResult.badRueqest(errors.getFieldError().getDefaultMessage()));
        }
        baseCodeService.saveOrUpdate(baseCode);
        return JsonUtils.toJSONString(JsonResult.success());
    }
}
