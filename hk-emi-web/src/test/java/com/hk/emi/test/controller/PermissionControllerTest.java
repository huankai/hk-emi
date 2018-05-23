package com.hk.emi.test.controller;

import com.google.common.collect.Lists;
import com.hk.commons.fastjson.JsonUtils;
import com.hk.core.query.JdbcQueryModel;
import com.hk.core.query.Operator;
import com.hk.core.query.QueryCondition;
import com.hk.emi.test.WebAppBaseTest;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

/**
 * 权限测试
 *
 * @author: huangkai
 * @date 2018-05-23 08:48
 */
public class PermissionControllerTest extends WebAppBaseTest {

    /**
     * 创建权限
     *
     * @throws Exception
     */
    @Test
    public void permissionCreateTest() throws Exception {
        getMockMvc().perform(MockMvcRequestBuilders.post("/permissions/save")
                .param("app.id", "4028c08162b9340f0162b93427c40000")
                .param("parent.id", "4028c081638ac7d201638ac7efaf0000")
                .param("permissionCode", "user_edit")
                .param("permissionName", "编辑用户")
                .session(getHttpSession())
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    /**
     * 获取权限详情
     *
     * @throws Exception
     */
    @Test
    public void permissionGetTest() throws Exception {
        getMockMvc().perform(MockMvcRequestBuilders.get("/permissions/{id}", "4028c081634dff6501634e0128cf0008")
                .session(getHttpSession())).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    /**
     * 权限列表查询
     *
     * @throws Exception
     */
    @Test
    public void permissionListTest() throws Exception {
        JdbcQueryModel query = new JdbcQueryModel();
        List<QueryCondition> conditions = Lists.newArrayList();
        QueryCondition condition = new QueryCondition();
        condition.setEnabled(true);
        condition.setName("permission_code");
        condition.setValue("user");
        condition.setOperator(Operator.LIKEANYWHERE);
        conditions.add(condition);
        query.setParams(conditions);

        getMockMvc().perform(MockMvcRequestBuilders.get("/permissions")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(JsonUtils.toJSONString(query))
                .session(getHttpSession())).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    /**
     * 获取当前登陆用户的权限
     *
     * @throws Exception
     */
    @Test
    public void getMyPermissionListTest() throws Exception {
        getMockMvc().perform(MockMvcRequestBuilders.get("/permissions/my")
                .session(getHttpSession())).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    /**
     * 删除权限
     *
     * @throws Exception
     */
    @Test
    public void deletePermissionTest() throws Exception {
        getMockMvc().perform(MockMvcRequestBuilders.delete("/permissions/{id}", "4028c081638ac7d201638ac7efaf0000")
                .session(getHttpSession())).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
}
