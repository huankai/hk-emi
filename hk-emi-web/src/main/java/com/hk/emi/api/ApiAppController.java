package com.hk.emi.api;

import com.hk.core.web.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: kevin
 * @date 2018-04-17 16:53
 */
@RestController
@RequestMapping("/api/apps")
public class ApiAppController extends BaseController {

//    @Autowired
//    private SysAppService appService;
//
//    @GetMapping("{appCode}")
//    public String getByCode(@PathVariable String appCode) {
//        SysApp app = appService.findByAppCode(appCode);
//        JsonResult result;
//        if (null == app) {
//            result = JsonResult.badRueqest("不存在的资源:" + appCode);
//        } else {
//            result = new JsonResult(app.getId());
//        }
//        return JsonUtils.toJSONString(result);
//    }
}
