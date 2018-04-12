package com.hk.emi.test;

import com.hk.commons.fastjson.JsonUtils;
import com.hk.emi.EmiApplication;
import com.hk.pms.core.domain.SysOrg;
import com.hk.pms.core.servcie.SysOrgService;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author huangkai
 * @
 * @date 2018/4/12 22:46
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {EmiApplication.class})
public class SysOrgTest {

    @Autowired
    private SysOrgService orgService;

    @Test
    public void saveTest(){
        SysOrg org = new SysOrg();
        org.setCreatedBy("1");
        org.setCreatedDate(DateTime.now());
        org.setLastModifiedBy("1");
        org.setLastModifiedDate(DateTime.now());
        org.setOrgIcon("a.png");
        org.setOrgName("根节点");
        System.out.println(JsonUtils.toFormatJSONString(orgService.saveOrUpdate(org)));
    }

    @Test
    public void findTest(){
        SysOrg org = new SysOrg();
        org.setOrgName("根节点");
        System.out.println(JsonUtils.toJSONStringExcludes(orgService.findOne(org),"parent"));

    }
}
