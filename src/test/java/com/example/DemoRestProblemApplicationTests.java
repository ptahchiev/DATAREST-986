package com.example;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static org.junit.Assert.assertEquals;
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

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();

        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(1);

        LocalizedValue lv = new LocalizedValue();
        lv.setValue("old-value");

        Map<Locale, LocalizedValue> name = new HashMap<>();
        name.put(Locale.ENGLISH, lv);

        productEntity.setName(name);

        productRepository.save(productEntity);
    }

    @Test
    public void testSavePut() throws Exception {
        //        MockHttpServletRequestBuilder builder = post("/productEntities");
        //
        //        builder.accept(MEDIA_TYPE);
        //        builder.content(getCreateBody()).contentType(MEDIA_TYPE);
        //
        //        mockMvc.perform(builder).andExpect(status().isCreated());

        new TransactionTemplate(transactionManager).execute(new TransactionCallbackWithoutResult() {
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                try {
                    MockHttpServletRequestBuilder builder = put("/productEntities/1");

                    builder.accept(MEDIA_TYPE);
                    builder.content(getUpdateBody()).contentType(MEDIA_TYPE);

                    mockMvc.perform(builder).andExpect(status().isOk());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        new TransactionTemplate(transactionManager).execute(new TransactionCallbackWithoutResult() {
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                assertEquals("some-en-value", productRepository.findOne(1L).getName().get(Locale.ENGLISH).getValue());
            }
        });

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
