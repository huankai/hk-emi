package com.hk.emi.test;

import com.hk.commons.fastjson.JsonUtils;
import com.hk.pms.core.domain.SysOrgDept;
import com.hk.pms.core.domain.SysUser;
import com.hk.pms.core.servcie.SysOrgDeptService;
import com.hk.pms.core.servcie.SysUserService;
import org.joda.time.DateTime;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;

/**
 * @author: huangkai
 * @date 2018-04-13 11:09
 */
public class SyUserServiceTest extends BaseTest {

    @Autowired
    private SysUserService userService;

    @Autowired
    private SysOrgDeptService orgDeptService;

    @Test
    public void saveTest() {
        SysUser user = new SysUser();
        SysOrgDept orgDept = orgDeptService.findOne("4028c08162bda84d0162bda85d6b0000");
        user.setOrgDept(orgDept);
        user.setOrg(orgDept.getOrg());
        user.setUserStatus(1);
        user.setRealName("系统管理员");
        user.setBrith(LocalDate.ofYearDay(2000, 1));
        user.setPassword(new BCryptPasswordEncoder().encode("admin"));
        user.setEmail("xx@xx.com");
        user.setPhone("18820136090");
        user.setIsProtect(true);
        user.setSex(1);
        user.setCreatedBy("1");
        user.setCreatedDate(DateTime.now());
        user.setLastModifiedBy("1");
        user.setLastModifiedDate(DateTime.now());
        System.out.println(JsonUtils.toJSONStringExcludes(userService.saveOrUpdate(user), "org", "orgDept"));
    }
}
