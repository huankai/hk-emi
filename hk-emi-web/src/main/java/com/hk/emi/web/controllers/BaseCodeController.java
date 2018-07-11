package com.hk.emi.web.controllers;

import com.hk.commons.util.JsonUtils;
import com.hk.core.data.commons.query.QueryModel;
import com.hk.core.data.commons.query.QueryPage;
import com.hk.core.web.JsonResult;
import com.hk.core.web.controller.BaseController;
import com.hk.emi.core.domain.BaseCode;
import com.hk.emi.core.service.BaseCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

/**
 * @author: kevin
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
    public String search(QueryModel<BaseCode> query) {
        QueryPage<BaseCode> pageable = baseCodeService.queryForPage(query);
        return JsonUtils.toJSONStringExcludes(JsonResult.success(pageable), "childCodes");
    }


    /**
     * 详情
     *
     * @param id
     * @return
     */
    @GetMapping(path = "{id}")
    public String get(@PathVariable String id) {
        return JsonUtils.toJSONStringExcludes(JsonResult.success(baseCodeService.getOne(id)), "childCodes");
    }

    /**
     * 详情
     *
     * @param id
     * @return
     */
    @GetMapping(path = "findall")
    public String findAll(BaseCode baseCode) {
        return JsonUtils.toJSONStringExcludes(JsonResult.success(baseCodeService.findAll(baseCode)), "childCodes");
    }


    /**
     * 删除
     *
     * @param id
     * @return
     */
    @DeleteMapping(path = "{id}")
    public String delete(@PathVariable String id) {
        baseCodeService.deleteById(id);
        return JsonUtils.toJSONString(JsonResult.success());
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @DeleteMapping()
    public String deleteIds(String[] ids) {
        baseCodeService.deleteByIds(ids);
        return JsonUtils.toJSONString(JsonResult.success());
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @DeleteMapping(path = "delete")
    public String deleteEntity(BaseCode baseCode) {
        baseCodeService.delete(baseCode);
        return JsonUtils.toJSONString(JsonResult.success());
    }

    /**
     * 保存或更新
     *
     * @param baseCode
     * @param errors
     * @return
     */
    @PostMapping(path = "save")
    public String insertOrUpdate(BaseCode baseCode, Errors errors) {
        if (errors.hasErrors()) {
            return JsonUtils.toJSONString(JsonResult.badRueqest(errors.getFieldError().getDefaultMessage()));
        }
        baseCodeService.insertOrUpdate(baseCode);
        return JsonUtils.toJSONString(JsonResult.success());
    }
}
