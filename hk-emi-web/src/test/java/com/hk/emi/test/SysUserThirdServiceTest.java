package com.hk.emi.test;

import com.hk.pms.core.domain.SysUser;
import com.hk.pms.core.domain.SysUserThird;
import com.hk.pms.core.servcie.SysUserService;
import com.hk.pms.core.servcie.SysUserThirdService;
import org.joda.time.DateTime;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: huangkai
 * @date 2018-04-13 14:21
 */
public class SysUserThirdServiceTest extends BaseTest {

    @Autowired
    private SysUserService userService;

    @Autowired
    private SysUserThirdService userThirdService;

    @Test
    public void saveTest(){
        SysUserThird userThird = new SysUserThird();
        SysUser user = userService.findOne("4028c08162bda8ce0162bda8df6a0000");
        userThird.setCreatedDate(DateTime.now());
        userThird.setLastModifiedDate(DateTime.now());
        userThird.setCreatedBy(user.getId());
        userThird.setLastModifiedBy(user.getId());
        userThird.setAccountType(1);
        userThird.setUserThirdName("haha");
        userThird.setOpenId("oNvZtv__To1bNI5slrj-oB05uO4U");
        userThird.setUser(user);
        userThirdService.saveOrUpdate(userThird);
    }
}
