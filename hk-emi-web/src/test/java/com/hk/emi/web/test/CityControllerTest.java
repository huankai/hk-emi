package com.hk.emi.web.test;

import com.hk.commons.util.ByteConstants;
import com.hk.commons.util.JsonUtils;
import com.hk.core.data.commons.query.Order;
import com.hk.core.data.commons.query.QueryModel;
import com.hk.emi.core.domain.City;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * @author: kevin
 * @date 2018-07-10 10:23
 */
public class CityControllerTest extends WebAppBaseTest {

    @Test
    public void searchTest() throws Exception {
        QueryModel<City> queryModel = new QueryModel<>();
        City city = new City();
        city.setCityType(City.CityType.PROVINCE.getCityType());
        queryModel.setParam(city);
        queryModel.getOrders().add(Order.asc("code"));
        getMockMvc().perform(MockMvcRequestBuilders.post("/citys")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(JsonUtils.toJSONString(queryModel))
                .session(getHttpSession())
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void getTest() throws Exception {
        getMockMvc().perform(MockMvcRequestBuilders.get("/citys/{id}", "4028c08162be57660162be5779cb0000")
                .session(getHttpSession())
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void saveOrUpdateTest() throws Exception {
        City city = new City();
        city.setId("4028c08162be57660162be5779cb0000");
        city.setCityType(ByteConstants.ONE);
        city.setDescription("中国setDescriptionaaa");
        getMockMvc().perform(MockMvcRequestBuilders.post("/citys/save")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(JsonUtils.toJSONStringExcludes(city, "parent", "child"))
                .session(getHttpSession())
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void childListTest() throws Exception {
        getMockMvc().perform(MockMvcRequestBuilders.get("/citys/child/{parentId}", "4028c08162be57660162be5779cb0000")
                .session(getHttpSession())
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
}
