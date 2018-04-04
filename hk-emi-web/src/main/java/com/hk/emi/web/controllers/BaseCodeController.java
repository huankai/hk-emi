package com.hk.emi.web.controllers;

import com.hk.commons.util.StringUtils;
import com.hk.core.query.JpaQueryModel;
import com.hk.core.query.QueryPageable;
import com.hk.core.web.JsonResult;
import com.hk.emi.core.domain.BaseCode;
import com.hk.emi.core.service.BaseCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

/**
 * @author: huangkai
 * @date 2018-04-03 17:39
 */
@Controller
@RequestMapping("/basecode")
public class BaseCodeController {

    @Autowired
    private BaseCodeService baseCodeService;

    @GetMapping("/list")
    public String list() {
        return "";
    }

    /**
     * 查询
     *
     * @param query
     * @param modelMap
     * @return
     */
    @GetMapping("/search")
    public String search(JpaQueryModel<BaseCode> query, ModelMap modelMap) {
        QueryPageable<BaseCode> pageable = baseCodeService.queryForPage(query);
        modelMap.put("result", pageable);
        return "";
    }

    /**
     * 添加
     *
     * @param modelMap
     * @return
     */
    @GetMapping("/add")
    public String add(ModelMap modelMap) {
        return detail(null, modelMap);
    }

    /**
     * 详情
     *
     * @param id
     * @param modelMap
     * @return
     */
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable String id, ModelMap modelMap) {
        if (StringUtils.isNotEmpty(id)) {
            modelMap.put("baseCode", baseCodeService.getOne(id));
        }
        return "";
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @PostMapping("/delete/{id}")
    @ResponseBody
    public JsonResult delete(@PathVariable String id) {
        baseCodeService.delete(id);
        return JsonResult.success();
    }

    /**
     * 保存或更新
     *
     * @param baseCode
     * @param errors
     * @return
     */
    @PostMapping("/save")
    @ResponseBody
    public JsonResult saveOrUpdate(BaseCode baseCode, Errors errors) {
        if (errors.hasErrors()) {
            return JsonResult.failure(errors.getFieldError().getDefaultMessage());
        }
        baseCodeService.saveOrUpdate(baseCode);
        return JsonResult.success();
    }
}
