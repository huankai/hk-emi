package com.hk.emi.test;

import com.hk.commons.util.ByteConstants;
import com.hk.commons.util.JsonUtils;
import com.hk.emi.core.domain.BaseCode;
import com.hk.emi.core.domain.ChildCode;
import com.hk.emi.core.service.BaseCodeService;
import com.hk.emi.core.service.ChildCodeService;
import org.joda.time.DateTime;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: huangkai
 * @date 2018-04-13 14:32
 */
public class CodeServiceTest extends BaseTest {

    @Autowired
    private BaseCodeService baseCodeService;

    @Autowired
    private ChildCodeService childCodeService;

    @Test
    public void saveTest() {
        BaseCode baseCode = new BaseCode();
        baseCode.setCodeName("是否类型");
        baseCode.setBaseCode("SFLX");
        baseCodeService.saveOrUpdate(baseCode);
    }

    @Test
    public void saveChildCodeTest() {
        BaseCode baseCode = baseCodeService.findOne("4028c08162be20a70162be20b7b70000");
        ChildCode childCode = new ChildCode();
        childCode.setBaseCode(baseCode);
        childCode.setChildCode("YES");
        childCode.setCodeName("是");
        childCode.setState(ByteConstants.ONE);
        childCode.setCreatedDate(DateTime.now());
        childCode.setLastModifiedDate(DateTime.now());
        childCode.setCreatedBy("4028c08162bda8ce0162bda8df6a0000");
        childCode.setLastModifiedBy("4028c08162bda8ce0162bda8df6a0000");
        childCodeService.saveOrUpdate(childCode);
    }

    @Test
    @Transactional
    public void findTest() {
        BaseCode baseCode = baseCodeService.findOne("4028c08162bdb6810162bdb691e90000");
        System.out.println(JsonUtils.toJSONString(baseCode));
    }

    @Test
    @Transactional
    public void findChildTest(){
        ChildCode childCode = childCodeService.findOne("4028c08162bdb9be0162bdb9ce200000");
        System.out.println(JsonUtils.toJSONString(childCode));
    }

    @Test
    public void deleteTest() {
        baseCodeService.delete("4028c08162be197b0162be198bc50000");
    }

}
