/**
 *
 */
package com.hk.emi.web.controllers;

import com.hk.commons.util.JsonUtils;
import com.hk.core.query.JpaQueryModel;
import com.hk.core.query.QueryPageable;
import com.hk.core.web.JsonResult;
import com.hk.core.web.Webs;
import com.hk.core.web.controller.BaseController;
import com.hk.emi.core.domain.City;
import com.hk.emi.core.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author kally
 * @date 2018年1月24日上午9:45:58
 */
@RestController
@RequestMapping("/citys")
public class CityController extends BaseController {

    @Autowired
    private CityService cityService;

    /**
     * Search
     *
     * @param query query
     * @return json result
     */
    @RequestMapping
    public String search(JpaQueryModel<City> query) {
        QueryPageable<City> page = cityService.queryForPage(query);
        return JsonUtils.toJSONString(JsonResult.success(page));
    }

    /**
     * 查询下级
     *
     * @param parentId parentId
     * @return json result
     */
    @RequestMapping("child/{parentId}")
    public String childList(@PathVariable String parentId) {
        List<City> childList = cityService.findChildList(parentId);
        return JsonUtils.toJSONString(JsonResult.success(childList));
    }

    /**
     * 详情
     *
     * @param id id
     * @return json result
     */
    @RequestMapping("{id}")
    public String get(@PathVariable String id) {
        return JsonUtils.toJSONStringExcludes(cityService.findOne(id), "parentId");
    }

    /**
     * Save Or update
     *
     * @param city   city
     * @param errors errors
     * @return json result
     */
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
     * @return json result
     */
    @DeleteMapping("{id}")
    public String deleteById(@PathVariable String id) {
        cityService.delete(id);
        return JsonUtils.toJSONString(JsonResult.success());
    }

    /**
     * 导入
     *
     * @param multipartFile multipartFile
     * @return json result
     */
    @PostMapping("import")
    public String importExcel(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        cityService.importExcel(multipartFile.getInputStream());
        return JsonUtils.toJSONString(JsonResult.success());
    }

    /**
     * 导出
     *
     * @param city city
     * @return json result
     */
    @GetMapping("export")
    public ResponseEntity<byte[]> exportExcel(City city) {
        byte[] byteData = cityService.exportExcelData(city);
        return Webs.toDownResponseEntity(getMessage("city.export.filename"), byteData);
    }
}
