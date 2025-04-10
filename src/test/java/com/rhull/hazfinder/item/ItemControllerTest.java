package com.rhull.hazfinder.item;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ItemController.class)
class ItemControllerTest
{
    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockitoBean
    ItemRepository repository;

    private final List<Item> items = new ArrayList<>();

    @BeforeEach
    void setUp()
    {
        items.add(new Item(1,
                "B0KLA57",
                "Test Item",
                "Test Ingredients"
                ));
    }

    @Test
    void shouldFindAllItems() throws Exception
    {
        when(repository.findAll()).thenReturn(items);
        mvc.perform(get("/hazfinder/api/items"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(items.size())));
    }

    @Test
    void shouldFindItem() throws Exception
    {
        Item item = items.get(0);
        when(repository.findById(1)).thenReturn(Optional.of(item));
        mvc.perform(get("/hazfinder/api/items/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(item.id())))
                .andExpect(jsonPath("$.asin", is(item.asin())))
                .andExpect(jsonPath("$.name", is(item.name())))
                .andExpect(jsonPath("$.ingredients", is(item.ingredients())));

    }

    @Test
    void shouldFindItemByAsin() throws Exception
    {
        Item item = items.get(0);
        when(repository.findByAsin("B0KLA57")).thenReturn(Optional.of(item));
        mvc.perform(get("/hazfinder/api/items/asin/B0KLA57"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(item.id())))
                .andExpect(jsonPath("$.asin", is(item.asin())))
                .andExpect(jsonPath("$.name", is(item.name())))
                .andExpect(jsonPath("$.ingredients", is(item.ingredients())));

    }

    @Test
    void shouldReturnNotFoundWithInvalidId() throws Exception
    {
        mvc.perform(get("/hazfinder/api/items/456196854"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldCreateNewItem() throws Exception
    {
        Item item = items.get(0);
        mvc.perform(post("/hazfinder/api/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(item)))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldUpdateItem() throws Exception
    {
        Item item = items.get(0);
        mvc.perform(put("/hazfinder/api/items/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(item)))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldDeleteItem() throws Exception
    {
        Item item = items.get(0);
        when(repository.findById(1)).thenReturn(Optional.of(item));
        mvc.perform(delete("/hazfinder/api/items/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldReturnNotFoundDeletingWithInvalidId() throws Exception
    {
        mvc.perform(delete("/hazfinder/api/items/456196854"))
                .andExpect(status().isNotFound());
    }
}