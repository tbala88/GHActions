package com.bootlabs.springbootapi.controller;

import com.bootlabs.springbootapi.dto.ProductResponse;
import com.bootlabs.springbootapi.exception.ProductNotFoundException;
import com.bootlabs.springbootapi.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    void returnsAllProducts() throws Exception {
        given(productService.findAll()).willReturn(List.of(
                new ProductResponse(1L, "Kubernetes in Action", 39.99, "pod-a")));

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Kubernetes in Action"));
    }

    @Test
    void returns404WhenProductMissing() throws Exception {
        given(productService.findById(eq(99L))).willThrow(new ProductNotFoundException(99L));

        mockMvc.perform(get("/products/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("PRODUCT_NOT_FOUND"));
    }

    @Test
    void rejectsBlankProductName() throws Exception {
        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"\",\"price\":10.0}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("VALIDATION_ERROR"));
    }

    @Test
    void createsProductWithValidPayload() throws Exception {
        given(productService.create(any())).willReturn(new ProductResponse(4L, "Domain-Driven Design", 45.0, "pod-a"));

        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Domain-Driven Design\",\"price\":45.0}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(4));
    }
}
