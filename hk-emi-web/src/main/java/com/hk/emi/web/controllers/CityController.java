/**
 *
 */
package com.hk.emi.web.controllers;

import com.hk.commons.poi.excel.model.ErrorLog;
import com.hk.commons.util.CollectionUtils;
import com.hk.commons.util.StringUtils;
import com.hk.core.query.JpaQueryModel;
import com.hk.core.query.QueryPageable;
import com.hk.core.web.JsonResult;
import com.hk.core.web.Webs;
import com.hk.core.web.controller.BaseController;
import com.hk.emi.core.domain.City;
import com.hk.emi.core.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author kally
 * @date 2018年1月24日上午9:45:58
 */
@Controller
@RequestMapping("city")
public class CityController extends BaseController {

    @Autowired
    private CityService cityService;

    /**
     * 列表
     *
     * @return
     */
    @RequestMapping("list")
    public String list() {
        return "city/list";
    }

    /**
     * table数据
     *
     * @param query    查询参数
     * @param modelMap modelMap
     * @return
     */
    @RequestMapping("search")
    public String search(JpaQueryModel<City> query, ModelMap modelMap) {
        QueryPageable<City> page = cityService.queryForPage(query);
        modelMap.put("result", page);
        return "city/listTpl";
    }

    @GetMapping("add")
    public String add(ModelMap modelMap) {
        return edit(null, modelMap);
    }

    /**
     * 编辑
     *
     * @param id
     * @param modelMap
     * @return
     */
    @GetMapping("edit/{id}")
    public String edit(@PathVariable String id, ModelMap modelMap) {
        if (StringUtils.isNotEmpty(id)) {
            City city = cityService.getOne(id);
            modelMap.put("city", city);
        }
        return "city/edit";
    }

    @GetMapping("detail/{id}")
    public String detail(@PathVariable String id, ModelMap modelMap) {
        edit(id, modelMap);
        return "city/detail";
    }

    /**
     * 保存或更新
     *
     * @param city
     * @param errors
     * @return
     */
    @PostMapping("save")
    @ResponseBody
    public JsonResult saveOrUpdate(City city, Errors errors) {
        if (errors.hasErrors()) {
            return JsonResult.failure(errors.getFieldError().getDefaultMessage());
        }
        cityService.saveOrUpdate(city);
        return JsonResult.success();
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @PostMapping("delete/{id}")
    @ResponseBody
    public JsonResult delete(@PathVariable String id) {
        cityService.delete(id);
        return JsonResult.success();
    }

    /**
     * 导入
     *
     * @param attachment
     * @return
     */
    @PostMapping("import")
    @ResponseBody
    public JsonResult importExcel(@RequestParam MultipartFile attachment) throws IOException {
        List<ErrorLog<City>> errorLogList = cityService.importExcelData(attachment.getInputStream());
        return new JsonResult(CollectionUtils.isEmpty(errorLogList), errorLogList);
    }

    /**
     * 导出
     *
     * @param city
     * @return
     */
    @PostMapping("export")
    public ResponseEntity<byte[]> exportExcel(City city) {
        return Webs.toDownResponseEntity(getMessage("city_export_file_name"), cityService.getExcelData(city));
    }

}
