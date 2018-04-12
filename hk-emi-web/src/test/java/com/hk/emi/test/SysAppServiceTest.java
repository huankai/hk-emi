package com.hk.emi.test;

import com.hk.commons.fastjson.JsonUtils;
import com.hk.core.query.JpaQueryModel;
import com.hk.core.query.QueryModel;
import com.hk.core.query.QueryPageable;
import com.hk.emi.EmiApplication;
import com.hk.pms.core.domain.SysApp;
import com.hk.pms.core.servcie.SysAppService;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author: huangkai
 * @date 2018-04-12 17:10
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {EmiApplication.class})
public class SysAppServiceTest {

    @Autowired
    private SysAppService appService;


    @Test
    public void saveTest() {
        SysApp sysApp = new SysApp();
        sysApp.setAppCode("HK_PMS");
        sysApp.setAppName("权限管理系统");
        sysApp.setAppIp("127.0.0.1");
        sysApp.setAppPort(80);
        sysApp.setAppIcon("a.png");
        sysApp.setCreatedDate(DateTime.now());
        sysApp.setAppStatus(1);
        sysApp.setCreatedBy("1");
        sysApp.setLastModifiedBy("1");
        sysApp.setLastModifiedDate(DateTime.now());
        SysApp result = appService.saveOrUpdate(sysApp);
        System.out.println(JsonUtils.toJSONString(result));
    }

    @Test
    public void findOneTest(){
        System.out.println(JsonUtils.toJSONString(appService.findOne("4028c08162b930640162b930792d0000")));
    }

    @Test
    public void findOneTest2(){
        SysApp sysApp = new SysApp();
        sysApp.setAppStatus(1);
        sysApp.setAppCode("HK_PMSss");
        System.out.println(JsonUtils.toJSONString(appService.findOne(sysApp)));
    }

    @Test
    public void findByAppCodeTest(){
        System.out.println(JsonUtils.toJSONString(appService.findByAppCode("HK_PMS")));
    }

    @Test
    public void deleteById(){
        appService.delete("4028c08162b930640162b930792d0000");
    }

    @Test
    public void findPageTest(){
        QueryModel query = new QueryModel();
        QueryPageable<SysApp> pageResult = appService.queryForPage(query);
        System.out.println(JsonUtils.toFormatJSONString(pageResult));
    }

    @Test
    public void findPageTest2(){
        JpaQueryModel<SysApp> queryModel = new JpaQueryModel<>();
        SysApp sysApp = new SysApp();
        sysApp.setAppStatus(1);
        sysApp.setAppIcon("aa");
        sysApp.setAppCode("HK_PMSss");
        queryModel.setParams(sysApp);
        QueryPageable<SysApp> pageResult = appService.queryForPage(queryModel);
        System.out.println(JsonUtils.toFormatJSONString(pageResult));
    }


}
