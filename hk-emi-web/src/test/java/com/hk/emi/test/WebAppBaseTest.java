package com.hk.emi.test;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.Filter;
import javax.servlet.http.HttpSession;

/**
 * @author: huangkai
 * @date 2018-05-23 08:46
 */
@WebAppConfiguration
public class WebAppBaseTest extends BaseTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    private MockHttpSession httpSession;

    /**
     * Spring SecurityFilterChain
     */
    @Autowired
    private Filter springSecurityFilterChain;

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
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .addFilters(springSecurityFilterChain)
                .build();
        this.httpSession = (MockHttpSession) getLoginSession();
    }

    protected String getUsername() {
        return "18820136090";
    }

    protected String getPassword() {
        return "admin";
    }

    private HttpSession getLoginSession() throws Exception {
        MvcResult result = getMockMvc()
                .perform(MockMvcRequestBuilders.post("/login")
                        .param("username", getUsername())
                        .param("password", getPassword()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        return result.getRequest().getSession();
    }
}
