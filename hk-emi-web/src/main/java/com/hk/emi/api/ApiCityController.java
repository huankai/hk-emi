package com.hk.emi.api;

import com.hk.commons.fastjson.JsonUtils;
import com.hk.core.web.JsonResult;
import com.hk.core.web.controller.BaseController;
import com.hk.emi.core.domain.City;
import com.hk.emi.core.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: huangkai
 * @date 2018-04-19 10:28
 */
@RestController
@RequestMapping("api/citys")
public class ApiCityController extends BaseController {

    @Autowired
    private CityService cityService;

    @GetMapping("{id}")
    public String getById(@PathVariable String id) {
        City city = cityService.findOne(id);
        return JsonUtils.toJSONStringExcludes(JsonResult.success(city), "parent", "childs");
    }
}
