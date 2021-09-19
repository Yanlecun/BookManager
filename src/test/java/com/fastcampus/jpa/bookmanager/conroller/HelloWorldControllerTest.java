package com.fastcampus.jpa.bookmanager.conroller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.data.jpa.util.JpaMetamodel;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest // mock mvc 만들기
@MockBean(JpaMetamodelMappingContext.class)
class HelloWorldControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void helloWorld() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/hello-world"))
                .andDo(print())
                .andExpect(status().isOk()) //result matcher로 상속받자
                .andExpect(content().string("Hello world"));
    }
}