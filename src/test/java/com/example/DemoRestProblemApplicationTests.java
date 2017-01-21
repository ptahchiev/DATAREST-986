package com.example;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class DemoRestProblemApplicationTests {

    protected final MediaType MEDIA_TYPE = MediaType.parseMediaType("application/hal+json;charset=UTF-8");

    private MockMvc mockMvc;

    @Resource
    private WebApplicationContext wac;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testSavePut() throws Exception {
        MockHttpServletRequestBuilder builder = post("/productEntities");

        builder.accept(MEDIA_TYPE);
        builder.content(getCreateBody()).contentType(MEDIA_TYPE);

        mockMvc.perform(builder).andExpect(status().isCreated());

        builder = put("/productEntities/1");

        builder.accept(MEDIA_TYPE);
        builder.content(getUpdateBody()).contentType(MEDIA_TYPE);

        mockMvc.perform(builder).andExpect(status().isOk());

        //
        //        new TransactionTemplate(transactionManager).execute(new TransactionCallbackWithoutResult() {
        //            protected void doInTransactionWithoutResult(TransactionStatus status) {
        //                try {
        //                    testSavePut(categoryRepository.findByCode("Mens").getId());
        //                } catch (Exception e) {
        //                    e.printStackTrace();
        //                }
        //            }
        //        });
        //
        //        new TransactionTemplate(transactionManager).execute(new TransactionCallbackWithoutResult() {
        //            protected void doInTransactionWithoutResult(TransactionStatus status) {
        //                assertEquals("some-bg-value", categoryRepository.findByCode("mynewcode").getName(new Locale("bg")));
        //            }
        //        });

    }

    // @formatter:off

    protected String getCreateBody() {
        return "{"
                        + "\"id\" : \"1\""
              + "}";
    }

    protected String getUpdateBody() {
                return "{"
                        + "\"id\" : \"1\", "
                        + "\"name\" : {"
                        + "              \"en\" : {\"value\" : \"some-en-value\"},"
                        + "              \"bg\" : {\"value\" : \"some-bg-value\"}"
                        + "}"
              + "}";
    }

    // @formatter:on
}
