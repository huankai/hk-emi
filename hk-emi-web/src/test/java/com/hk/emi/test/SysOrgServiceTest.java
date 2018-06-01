package com.hk.emi.test;

import com.hk.commons.util.JsonUtils;
import com.hk.pms.core.domain.SysOrg;
import com.hk.pms.core.servcie.SysOrgService;
import org.joda.time.DateTime;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author huangkai
 * @
 * @date 2018/4/12 22:46
 */

public class SysOrgServiceTest extends BaseTest {

    @Autowired
    private SysOrgService orgService;

    @Test
    public void saveTest() {
        SysOrg org = new SysOrg();
        org.setCreatedBy("1");
        org.setCreatedDate(DateTime.now());
        org.setLastModifiedBy("1");
        org.setLastModifiedDate(DateTime.now());
        org.setOrgIcon("a.png");
        org.setOrgName("根节点");
        System.out.println(JsonUtils.toFormatJSONString(orgService.saveOrUpdate(org)));
    }

    /**
     * 同一事物中，会使用一级缓存查询
     */
    @Test
    @Transactional
    public void findTest() {
        SysOrg org = new SysOrg();
        org.setOrgName("根节点");
//        org.setOrgName("XXX机构");
        SysOrg findOrg = orgService.findOne(org);
        System.out.println(JsonUtils.toJSONStringExcludes(findOrg, "parent"));//使用 根节点 查询时只有一条sql,使用了一级缓存，一级缓存不需要配置
        System.out.println(JsonUtils.toJSONStringExcludes(findOrg.getParent(), "parent")); //使用 XXX机构 查询时会有两条sql
    }

    @Test
    public void saveChildTest() {
        SysOrg org = new SysOrg();
        org.setCreatedBy("1");
        org.setCreatedDate(DateTime.now());
        org.setLastModifiedBy("1");
        org.setLastModifiedDate(DateTime.now());
        org.setOrgIcon("b.png");
        org.setOrgName("YYY机构");
//        org.setParent(orgService.findOne("402881e662ba5fff0162ba602bff0000"));

        SysOrg parent = new SysOrg();
        parent.setId("402881e662ba5fff0162ba602bff0000");
        org.setParent(parent);
        System.out.println(JsonUtils.toFormatJSONString(orgService.saveOrUpdate(org)));
    }


}
