package com.waracle.cakes.cakemanager.controller;

import com.waracle.cakes.cakemanager.com.warcle.cakes.cakemanager.model.Cake;
import com.waracle.cakes.cakemanager.com.warcle.cakes.cakemanager.service.CakeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CakeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CakeService service;

    private Cake testObject;

    @BeforeEach
    public void before() {
        testObject = new Cake("cheesecake",
                "my favourite cake",
                "http://cheesecake-image.com");
        testObject.setCakeId(1L);
        when(service.getAllCakes()).thenReturn(Arrays.asList(testObject));
    }

    @Test
    public void canLoadAllCakesAsHtmlPage() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Accept", "text/html");

        mockMvc.perform(MockMvcRequestBuilders.get("/cakes")
                .headers(httpHeaders))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("cakes"))
                .andExpect(view().name("cakes"))
                .andExpect(model().attribute("cakes", Arrays.asList(testObject)));
    }

    @Test
    public void canLoadAllCakesAsJson() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Accept", "application/json");


        mockMvc.perform(MockMvcRequestBuilders.get("/cakes")
                .headers(httpHeaders))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cakeList", hasSize(1)))
                .andExpect(jsonPath("$.cakeList[*].id", contains(1)))
                .andExpect(jsonPath("$.cakeList[*].title", contains("cheesecake")))
                .andExpect(jsonPath("$.cakeList[*].desc", contains("my favourite cake")))
                .andExpect(jsonPath("$.cakeList[*].image", contains("http://cheesecake-image.com")))
                .andExpect(model().attributeDoesNotExist("cakes"))
                .andExpect(view().name(not("cakes")));
    }

    @Test
    public void canAddCake() throws Exception {
        Cake newCake = new Cake("honey", "honey cake", "http://honeycake-image.com");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Accept", "text/html");

        when(service.add( Mockito.any(Cake.class) )).thenReturn(newCake);

        mockMvc.perform(MockMvcRequestBuilders.post("/new-cake")
                .param("title", "honey")
                .param("description", "honey cake")
                .param("image", "http://honeycake-image.com"))
                .andExpect(redirectedUrl("/cakes"));
    }
}
