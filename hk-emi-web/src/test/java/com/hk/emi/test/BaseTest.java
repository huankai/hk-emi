package com.hk.emi.test;

import com.hk.emi.EmiApplication;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpSession;

/**
 * @author: huangkai
 * @date 2018-04-13 10:54
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {EmiApplication.class})
@WebAppConfiguration
public class BaseTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    private MockHttpSession httpSession;

    public WebApplicationContext getContext() {
        return context;
    }

    public MockMvc getMockMvc() {
        return mockMvc;
    }

    public MockHttpSession getHttpSession() {
        return httpSession;
    }

    @Before
    public void before() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        this.httpSession = (MockHttpSession) getLoginSession();
    }

    private HttpSession getLoginSession() throws Exception {
        String url = "http://127.0.0.1:8002/login";
        System.out.println("url:" + url);
        MvcResult result = getMockMvc()
                .perform(MockMvcRequestBuilders.post(url).param("username","18820136090").param("password","admin"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        return result.getRequest().getSession();
    }
}
