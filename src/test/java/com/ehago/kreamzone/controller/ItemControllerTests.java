package com.ehago.kreamzone.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class ItemControllerTests {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void items_dropped_200() throws Exception {
        mockMvc.perform(get("/items/dropped")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$").isArray())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].brand").exists())
                .andExpect(jsonPath("$[0].immediately_purchase_price").exists());
    }

}
