package com.hk.emi.test;

import com.hk.commons.fastjson.JsonUtils;
import com.hk.commons.util.ByteConstants;
import com.hk.core.web.AppCodeUtils;
import com.hk.pms.core.domain.SysOrgDept;
import com.hk.pms.core.domain.SysRole;
import com.hk.pms.core.domain.SysUser;
import com.hk.pms.core.servcie.*;
import org.joda.time.DateTime;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private SysRoleService roleService;

    @Autowired
    private SysPermissionService permissionService;

    @Autowired
    private SysAppService appService;

    @Test
    public void saveTest() {
        SysUser user = new SysUser();
        SysOrgDept orgDept = orgDeptService.findOne("4028c08162bda84d0162bda85d6b0000");
        user.setOrgDept(orgDept);
        user.setOrg(orgDept.getOrg());
        user.setUserStatus(ByteConstants.ONE);
        user.setRealName("系统管理员");
        user.setBrith(LocalDate.ofYearDay(2000, 1));
        user.setPassword(new BCryptPasswordEncoder().encode("admin"));
        user.setEmail("xx2@xx.com");
        user.setPhone("18820136091");
        user.setUserType(ByteConstants.ZERO);
        user.setIsProtect(true);
        user.setSex(ByteConstants.ZERO);
        user.setCreatedBy("1");
        user.setCreatedDate(DateTime.now());
        user.setLastModifiedBy("1");
        user.setLastModifiedDate(DateTime.now());
        System.out.println(JsonUtils.toJSONStringExcludes(userService.saveOrUpdate(user), "org", "orgDept"));
    }

    @Test
    public void roleTest() {
        SysRole role = new SysRole();
        role.setApp(appService.findOne("4028c08162b9340f0162b93427c40000"));
        role.setRoleCode("ADMIN");
        role.setRoleName("系统管理员");
        role.setRoleStatus(ByteConstants.ONE);
        role.setCreatedBy("1");
        role.setCreatedDate(DateTime.now());
        role.setLastModifiedBy("1");
        role.setLastModifiedDate(DateTime.now());
        System.out.println(JsonUtils.toJSONString(roleService.saveOrUpdate(role)));
    }

    @Test
    public void roleDeleteTest() {
        roleService.delete("4028c08162d2850b0162d2851db80000");
    }

    @Test
    public void findUsernameTest() {
        SysUser user = userService.findByLoginUsername("18820136090");
        System.out.println(JsonUtils.toJSONStringExcludes(user, "orgDept", "org", "roleSet"));
    }


    @Test
    @Transactional
    public void findRoleTest() {
        System.out.println("--------" + AppCodeUtils.getAppCode());
        System.out.println(JsonUtils.toJSONString(roleService.getOne("4028c08162d2866a0162d28687770000")));
    }
}
