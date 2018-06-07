package com.hk.emi.web.controllers;

import com.hk.commons.util.JsonUtils;
import com.hk.core.data.commons.query.QueryPage;
import com.hk.core.data.jpa.query.JpaQueryModel;
import com.hk.core.web.JsonResult;
import com.hk.core.web.controller.BaseController;
import com.hk.emi.core.domain.ChildCode;
import com.hk.emi.core.service.ChildCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

/**
 * @author: huangkai
 * @date 2018-04-08 13:26
 */
@RestController
@RequestMapping("/childcodes")
public class ChildCodeController extends BaseController {

    @Autowired
    private ChildCodeService childCodeService;

    @RequestMapping
    public String search(JpaQueryModel<ChildCode> queryModel) {
        QueryPage<ChildCode> queryResult = childCodeService.queryForPage(queryModel);
        return JsonUtils.toJSONStringExcludes(JsonResult.success(queryResult));
    }

    @GetMapping("{id}")
    public String get(@PathVariable String id) {
        return JsonUtils.toJSONString(JsonResult.success(childCodeService.findOne(id)));
    }

    @DeleteMapping("{id}")
    public String deleteById(@PathVariable String id) {
        childCodeService.deleteById(id);
        return JsonUtils.toJSONString(JsonResult.success());
    }

    /**
     * 查询下级
     *
     * @param baseId
     * @return
     */
    @GetMapping("get_base_id/{baseId}")
    public String getByBaseId(@PathVariable String baseId) {
        return JsonUtils.toJSONString(JsonResult.success(childCodeService.findByBaseCodeId(baseId)));
    }



    @PostMapping("save")
    public String save(ChildCode childCode, Errors errors) {
        if (errors.hasErrors()) {
            return JsonUtils.toJSONString(JsonResult.badRueqest(errors.getFieldError().getDefaultMessage()));
        }
        childCodeService.saveOrUpdate(childCode);
        return JsonUtils.toJSONString(JsonResult.success());
    }
}
