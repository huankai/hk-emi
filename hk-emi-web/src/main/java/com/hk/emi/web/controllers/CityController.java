/**
 *
 */
package com.hk.emi.web.controllers;

import com.hk.commons.fastjson.JsonUtils;
import com.hk.core.query.JpaQueryModel;
import com.hk.core.query.QueryPageable;
import com.hk.core.web.JsonResult;
import com.hk.core.web.controller.BaseController;
import com.hk.emi.core.domain.City;
import com.hk.emi.core.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author kally
 * @date 2018年1月24日上午9:45:58
 */
@RestController
@RequestMapping("/city")
public class CityController extends BaseController {

    @Autowired
    private CityService cityService;

    @RequestMapping
    public String search(JpaQueryModel<City> query) {
        QueryPageable<City> page = cityService.queryForPage(query);
        return JsonUtils.toJSONString(JsonResult.success(page));
    }

    /**
     * 查询下级
     *
     * @param parentId
     * @return
     */
    @RequestMapping("/child/{parentId}")
    public String childList(@PathVariable String parentId) {
        List<City> childList = cityService.findChildList(parentId);
        return JsonUtils.toJSONString(JsonResult.success(childList));
    }

    /**
     * 详情
     *
     * @param id
     * @return
     */
    @RequestMapping("{id}")
    public String get(@PathVariable String id) {
        return JsonUtils.toJSONString(cityService.getOne(id), "parentId");
    }

    @PostMapping("save")
    public String save(City city, Errors errors) {
        if (errors.hasErrors()) {
            return JsonUtils.toJSONString(JsonResult.badRueqest(errors.getFieldError().getDefaultMessage()));
        }
        cityService.saveOrUpdate(city);
        return JsonUtils.toJSONString(JsonResult.success());
    }

    /**
     * @param id 根据id删除
     * @return
     */
    @DeleteMapping("{id}")
    public String deleteById(@PathVariable String id) {
        cityService.delete(id);
        return JsonUtils.toJSONString(JsonResult.success());
    }
}
