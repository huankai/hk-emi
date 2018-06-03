package com.hk.emi.test;

import com.hk.commons.util.ByteConstants;
import com.hk.commons.util.IDGenerator;
import com.hk.commons.util.JsonUtils;
import com.hk.core.query.JpaQueryModel;
import com.hk.core.query.QueryModel;
import com.hk.core.query.QueryPageable;
import com.hk.pms.core.domain.SysApp;
import com.hk.pms.core.servcie.SysAppService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.assertj.core.util.Lists;
import org.joda.time.DateTime;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author: huangkai
 * @date 2018-04-12 17:10
 */
public class SysAppServiceTest extends BaseTest {

    @Autowired
    private SysAppService appService;

    @Test
    public void saveTest() {
        List<SysApp> list = Lists.newArrayList();
        SysApp sysApp;
        long start = System.currentTimeMillis();
        for (int i = 0; i < 500000; i++) {
            sysApp = new SysApp();
            sysApp.setAppCode(RandomStringUtils.random(10,"ABCDEFGHIGKLMNOPQRSTUVWXYZ"));
            sysApp.setAppName(IDGenerator.STRING_UUID.generate());
            sysApp.setAppIp("127.0.0.1");
            sysApp.setAppPort(RandomUtils.nextInt(1000, 9999));
            sysApp.setAppIcon(IDGenerator.STRING_UUID.generate() + ".png");
            sysApp.setCreatedDate(DateTime.now());
            sysApp.setAppStatus(ByteConstants.ONE);
            sysApp.setCreatedBy("4028c08162bda8ce0162bda8df6a0000");
            sysApp.setLastModifiedBy("4028c08162bda8ce0162bda8df6a0000");
            sysApp.setLastModifiedDate(DateTime.now());
            list.add(sysApp);
//            appService.saveOrUpdate(sysApp);
//            System.out.println(JsonUtils.toJSONString(result));
        }
        appService.saveOrUpdate(list);
        System.out.println(System.currentTimeMillis() - start);
    }

    @Test
    public void findOneTest() {
        System.out.println(JsonUtils.toJSONString(appService.findOne("4028c08162b930640162b930792d0000")));
    }

    @Test
    public void findOneTest2() {
        SysApp sysApp = new SysApp();
        sysApp.setAppStatus(ByteConstants.ONE);
        sysApp.setAppCode("HK_PMSss");
        System.out.println(JsonUtils.toJSONString(appService.findOne(sysApp)));
    }

    @Test
    public void findByAppCodeTest() {
        System.out.println(JsonUtils.toJSONString(appService.findByAppCode("HK_PMS")));
    }

    @Test
    public void deleteById() {
        appService.delete("4028c08162b930640162b930792d0000");
    }

    @Test
    public void findPageTest() {
        QueryModel query = new QueryModel();
        QueryPageable<SysApp> pageResult = appService.queryForPage(query);
        System.out.println(JsonUtils.toFormatJSONString(pageResult));
    }

    @Test
    public void findPageTest2() {
        JpaQueryModel<SysApp> queryModel = new JpaQueryModel<>();
//        SysApp sysApp = new SysApp();
//        sysApp.setAppStatus(1);
//        sysApp.setAppIcon("aa");//不会加入条件查询
//        sysApp.setAppCode("HK_PMSss");
//        queryModel.setParams(sysApp);
        QueryPageable<SysApp> pageResult = appService.queryForPage(queryModel);
        System.out.println(JsonUtils.toFormatJSONString(pageResult));
    }


}
