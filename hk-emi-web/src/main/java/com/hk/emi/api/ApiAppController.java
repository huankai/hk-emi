package com.hk.emi.api;

import com.hk.commons.util.StringUtils;
import com.hk.core.web.controller.BaseController;
import com.hk.pms.core.domain.SysApp;
import com.hk.pms.core.servcie.SysAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: huangkai
 * @date 2018-04-17 16:53
 */
@RestController
@RequestMapping("/api/apps")
public class ApiAppController extends BaseController {

    @Autowired
    private SysAppService appService;

    @RequestMapping("appCode")
    public String getByCode(@RequestParam String appCode) {
        SysApp app = appService.findByAppCode(appCode);
        return null == app ? StringUtils.EMPTY : app.getId();
    }
}
