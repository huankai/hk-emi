package com.hk.emi.test;

import com.hk.commons.util.ClassUtils;
import com.hk.pms.core.servcie.impl.SysUserServiceImpl;
import org.junit.Test;

/**
 * @author: huangkai
 * @date 2018-04-19 09:02
 */
public class ClassUtilsTest {

    @Test
    public void test() {
        System.out.println(ClassUtils.getGenericType(SysUserServiceImpl.class, 0));
        System.out.println(ClassUtils.getGenericType(SysUserServiceImpl.class, 1));
    }
}
