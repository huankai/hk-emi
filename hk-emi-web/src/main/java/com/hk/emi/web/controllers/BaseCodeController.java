package com.hk.emi.web.controllers;

import com.hk.commons.util.JsonUtils;
import com.hk.core.data.commons.query.QueryModel;
import com.hk.core.data.commons.query.QueryPage;
import com.hk.core.web.JsonResult;
import com.hk.core.web.controller.BaseController;
import com.hk.emi.core.domain.BaseCode;
import com.hk.emi.core.service.BaseCodeService;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("{id}")
    public String get(@PathVariable String id) {
        return JsonUtils.toJSONStringExcludes(JsonResult.success(baseCodeService.getOne(id)), "childCodes");
    }

    /**
     * 详情
     *
     * @param id
     * @return
     */
    @GetMapping("find/{id}")
    public String find(@PathVariable String id) {
        return JsonUtils.toJSONStringExcludes(JsonResult.success(baseCodeService.findOne(id)), "childCodes");
    }

    /**
     * 详情
     *
     * @param id
     * @return
     */
    @GetMapping("fin")
    public String find(BaseCode baseCode) {
        return JsonUtils.toJSONStringExcludes(JsonResult.success(baseCodeService.findOne(baseCode)), "childCodes");
    }

    /**
     * 详情
     *
     * @param id
     * @return
     */
    @GetMapping("findall")
    public String findAll(BaseCode baseCode) {
        return JsonUtils.toJSONStringExcludes(JsonResult.success(baseCodeService.findAll(baseCode)), "childCodes");
    }

    /**
     * 详情
     *
     * @param id
     * @return
     */
    @GetMapping("findids")
    public String findAll() {
        return JsonUtils.toJSONStringExcludes(JsonResult.success(baseCodeService.findByIds(
                Lists.newArrayList("4028c08163dde8800163dde8923c0003", "4028c08163dde8800163dde8923d0007"))), "childCodes");
    }

    /**
     * 详情
     *
     * @param id
     * @return
     */
    @GetMapping("exists/{id}")
    public String exists(@PathVariable String id) {
        return JsonUtils.toJSONStringExcludes(JsonResult.success(baseCodeService.exists(id)), "childCodes");
    }

    /**
     * 详情
     *
     * @param id
     * @return
     */
    @GetMapping("exists")
    public String exists(BaseCode baseCode) {
        return JsonUtils.toJSONStringExcludes(JsonResult.success(baseCodeService.exists(baseCode)), "childCodes");
    }

    /**
     * 详情
     *
     * @param id
     * @return
     */
    @GetMapping("countall")
    public String countAll() {
        return JsonUtils.toJSONStringExcludes(JsonResult.success(baseCodeService.count()));
    }

    /**
     * 详情
     *
     * @param id
     * @return
     */
    @GetMapping("count")
    public String count(BaseCode baseCode) {
        return JsonUtils.toJSONStringExcludes(JsonResult.success(baseCodeService.count(baseCode)));
    }


    /**
     * 删除
     *
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
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
    @DeleteMapping("delete")
    public String deleteEntity(BaseCode baseCode) {
        baseCode = baseCodeService.findOne(baseCode);
        baseCodeService.delete(baseCode);
        return JsonUtils.toJSONString(JsonResult.success());
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @DeleteMapping("deletebatch")
    public String deleteBatch() {
        Iterable<BaseCode> list = baseCodeService.findByIds("4028c08163dde8800163dde8923d0008", "4028c08163dde8800163dde8923d0007");
        baseCodeService.delete(list);
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

    @PostMapping("udpateselective")
    public String updateSelective() {
        BaseCode baseCode = new BaseCode();
        baseCode.setBaseCode("dfsa");
        baseCode.setId("4028c08163de2d830163de3ec2620007");
        BaseCode t = baseCodeService.updateByIdSelective(baseCode);
        return JsonUtils.toJSONString(JsonResult.success(t));
    }


    @PostMapping("saves")
    public String saveOrUpdates() {
        List<BaseCode> list = Lists.newArrayList();
        BaseCode code;
        for (int i = 0; i < 10; i++) {
            code = new BaseCode();
            code.setBaseCode("Items_" + i);
            code.setCodeName("Item_name" + i);
            list.add(code);
        }
        Iterable<BaseCode> result = baseCodeService.saveOrUpdate(list);
        return JsonUtils.toJSONString(JsonResult.success(result));
    }
}
